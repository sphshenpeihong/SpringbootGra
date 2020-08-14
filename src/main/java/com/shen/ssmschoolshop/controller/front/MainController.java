package com.shen.ssmschoolshop.controller.front;


import com.shen.ssmschoolshop.entity.*;
import com.shen.ssmschoolshop.service.CateService;
import com.shen.ssmschoolshop.service.GoodsService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Controller
public class MainController {
    
    private final static Logger logger = LoggerFactory.getLogger(MainController.class);

    @Autowired
    private CateService cateService;

    @Autowired
    private GoodsService goodsService;


    /**
     * tomcat启动时，调用该接口，从库取出前台页面将渲染的数据，然后请求转发到main.jsp
     * @param model
     * @param session
     * @return
     */
    @RequestMapping("/")
    public String showAdmin(Model model, HttpSession session, HttpServletResponse response, HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        int i = 0;
        for (Cookie cookie : cookies) {
            System.out.println(cookie);
        }
        Cookie cookie = new Cookie("username111","123444");
        response.addCookie(cookie);
        Integer userid;
        User user = (User) session.getAttribute("user");
        if (user == null) {
            userid = null;
        } else {
            userid = user.getUserid();
        }

        //数码分类
        List<Goods> digGoods = getCateGoods("数码", userid);
        model.addAttribute("digGoods", digGoods);

        //家电
        List<Goods> houseGoods = getCateGoods("家电", userid);
        model.addAttribute("houseGoods", houseGoods);

        //服饰
        List<Goods> colGoods = getCateGoods("服饰", userid);
        model.addAttribute("colGoods", colGoods);

        //书籍
        List<Goods> bookGoods = getCateGoods("书籍", userid);
        model.addAttribute("bookGoods", bookGoods);

        return "main";
    }

    @RequestMapping("/zidingyi.do")
    public String zidingyi(){
        return "zidingyi";
    }

    /**
     * 试试map的一些API
     * @param request
     * @param username
     */
    @RequestMapping("/zidingyi111.do")
    public void zidingyi111(HttpServletRequest request,String[] username){
        Map<String, String[]> map = request.getParameterMap();//key是前端name值，value是值，因为可能有复选框、或者多个name命名一样，所以用String[]数组接收
        Set<Map.Entry<String, String[]>> entrySet = map.entrySet();
        for (Map.Entry<String, String[]> entry : entrySet) {
            for (String s : entry.getValue()) { //数组，遍历数组
                System.out.println(s);
            }
        }
        for (String s : username) {
            System.out.println(s);
        }
        System.out.println(request.getParameter("category"));
    }

    /**
     * 跳转入口
     * @return
     */
    @RequestMapping("/tiaozhuan.do")
    public String tiaozhuanRukou(){
        return "zidingyi";
    }

    /**
     * 该接口作用与默认/一样
     * @param model
     * @param session
     * @return
     */
    @RequestMapping("/main")
    public String showAllGoods(Model model, HttpSession session) {
        Integer userid;
        User user = (User) session.getAttribute("user");
        if (user == null) {
            userid = null;
        } else {
            userid = user.getUserid();
        }
        //数码分类
        List<Goods> digGoods = getCateGoods("数码", userid);
        model.addAttribute("digGoods", digGoods);
        //家电
        List<Goods> houseGoods = getCateGoods("家电", userid);
        model.addAttribute("houseGoods", houseGoods);
        //服饰
        List<Goods> colGoods = getCateGoods("服饰", userid);
        model.addAttribute("colGoods", colGoods);
        //书籍
        List<Goods> bookGoods = getCateGoods("书籍", userid);
        model.addAttribute("bookGoods", bookGoods);

        return "main";
    }

    /**
     * 通过商品类别和当前客户userId查出页面渲染的数据商品 通过userId是获取客户是否对某件商品感兴趣
     * @param cate
     * @param userid
     * @return
     */
    public List<Goods> getCateGoods(String cate, Integer userid) {
        //查询分类
        CategoryExample digCategoryExample = new CategoryExample();
        digCategoryExample.or().andCatenameLike(cate);
        List<Category> digCategoryList = cateService.selectByExample(digCategoryExample);

        if (digCategoryList.size() == 0) {
            return null;
        }

        //查询属于刚查到的分类的商品
        GoodsExample digGoodsExample = new GoodsExample();
        List<Integer> digCateId = new ArrayList<Integer>();
        for (Category tmp:digCategoryList) {
            digCateId.add(tmp.getCateid());
        }
        digGoodsExample.or().andCategoryIn(digCateId);

        List<Goods> goodsList = goodsService.selectByExampleLimit(digGoodsExample);

        List<Goods> goodsAndImage = new ArrayList<>();
        //获取每个商品的图片
        for (Goods goods:goodsList) {
            //判断是否为登录状态
            if (userid == null) {
                goods.setFav(false);
            } else {
                Favorite favorite = goodsService.selectFavByKey(new FavoriteKey(userid, goods.getGoodsid()));
                if (favorite == null) {
                    goods.setFav(false);
                } else {
                    goods.setFav(true);
                }
            }
            //由于商品表和商品的图片地址表分开，所以需要遍历查询
            List<ImagePath> imagePathList = goodsService.findImagePath(goods.getGoodsid());
            goods.setImagePaths(imagePathList);
            goodsAndImage.add(goods);
        }
        return goodsAndImage;
    }

    @Test
    public void test(){
        System.out.println("14:42");
    }
}
