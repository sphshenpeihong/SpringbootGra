package com.shen.ssmschoolshop.controller.front;


import com.shen.ssmschoolshop.entity.*;
import com.shen.ssmschoolshop.service.GoodsService;
import com.shen.ssmschoolshop.service.ShopCartService;
import com.shen.ssmschoolshop.util.Msg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;

@Controller
public class CartController {

    @Autowired
    private ShopCartService shopCartService;

    @Autowired
    private GoodsService goodsService;

    /**
     * 添加商品到购物车
     * @param shopCart
     * @param request
     * @return
     */
    @RequestMapping("/addCart")
    public String addCart(ShopCart shopCart, HttpServletRequest request) {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if(user == null) {
            return "redirect:/login";
        }
        //判断是否已经加入购物车  查找购物车表 若当前用户ID和添加的该商品ID已在数据库的话，直接重定向到购物车
        ShopCart shopCart1 = shopCartService.selectCartByKey(new ShopCartKey(user.getUserid(), shopCart.getGoodsid()));
        if (shopCart1 != null) {
            return "redirect:/showcart";
        }

        //用户
        shopCart.setUserid(user.getUserid());

        //加入时间
        shopCart.setCatedate(new Date());

        //新增购物车表
        shopCartService.addShopCart(shopCart);

        //返回到购物车页面
        return "redirect:/showcart";
    }

    /**
     * 跳转到购物车页面 由购物车jsp的js去加载购物车的信息进行渲染
     * @param session
     * @return
     */
    @RequestMapping("/showcart")
    public String showCart(HttpSession session) {
        User user = (User) session.getAttribute("user");
        if(user == null) {
            return "redirect:/login";
        }
        return "shopcart";
    }

    /**
     * 购物车jsp的js请求加载购物车页面消息进行渲染
     * @param session
     * @return
     */
    @RequestMapping("/cartjson")
    @ResponseBody
    public Msg getCart(HttpSession session) {
        User user = (User) session.getAttribute("user");
        if(user == null) {
            return Msg.fail("请先登录");
        }

        //获取当前用户的购物车信息
        ShopCartExample shopCartExample = new ShopCartExample();
        shopCartExample.or().andUseridEqualTo(user.getUserid());
        List<ShopCart> shopCart = shopCartService.selectByExample(shopCartExample);

        //获取购物车中的商品信息
        List<Goods> goodsAndImage = new ArrayList<>();
        for (ShopCart cart:shopCart) {
            Goods goods = goodsService.selectById(cart.getGoodsid());
            List<ImagePath> imagePathList = goodsService.findImagePath(goods.getGoodsid());
            goods.setImagePaths(imagePathList);
            goods.setNum(cart.getGoodsnum());
            goodsAndImage.add(goods);
        }

        return Msg.success("查询成功").add("shopcart",goodsAndImage);
    }

    @RequestMapping(value = "/deleteCart/{goodsid}", method = RequestMethod.DELETE)
    @ResponseBody
    public Msg deleteCart(@PathVariable("goodsid")Integer goodsid, HttpSession session) {
        User user = (User) session.getAttribute("user");
        if(user == null) {
            return Msg.fail("请先登录");
        }

        shopCartService.deleteByKey(new ShopCartKey(user.getUserid(), goodsid));
        return Msg.success("删除成功");
    }

    @RequestMapping("/update")
    @ResponseBody
    public Msg updateCart(Integer goodsid,Integer num,HttpSession session) {
        User user = (User) session.getAttribute("user");
        if(user == null) {
            return Msg.fail("请先登录");
        }
        ShopCart shopCart = new ShopCart();
        shopCart.setUserid(user.getUserid());
        shopCart.setGoodsid(goodsid);
        shopCart.setGoodsnum(num);
        shopCartService.updateCartByKey(shopCart);
        return Msg.success("更新购物车成功");
    }
}
