package com.shen.ssmschoolshop.controller.admin;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import com.shen.ssmschoolshop.entity.*;
import com.shen.ssmschoolshop.service.CateService;
import com.shen.ssmschoolshop.service.GoodsService;
import com.shen.ssmschoolshop.util.ImageUtil;
import com.shen.ssmschoolshop.util.Msg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;


@Controller
@RequestMapping("/admin/goods")
public class GoodsController {

    @Autowired
    private GoodsService goodsService;

    @RequestMapping("/showjson")
    @ResponseBody
    public Msg getAllGoods(@RequestParam(value = "page", defaultValue = "1") Integer pn, HttpServletResponse response, Model model, HttpSession session) {
        Admin admin = (Admin) session.getAttribute("admin");
        if (admin == null) {
            return Msg.fail("请先登录");
        }
        //一页显示几个数据
        PageHelper.startPage(pn, 10);

        List<Goods> employees = goodsService.selectByExample(new GoodsExample());
        for(Goods good:employees){
            System.out.println(good);
        }
        //显示几个页号
        PageInfo page = new PageInfo(employees, 5);

        model.addAttribute("pageInfo", page);

        return Msg.success("查询成功!").add("pageInfo", page);
    }

    @RequestMapping("/show")
    public String goodsManage(@RequestParam(value = "page", defaultValue = "1") Integer pn, HttpServletResponse response, Model model, HttpSession session) throws IOException {
        Admin admin = (Admin) session.getAttribute("admin");
        if (admin == null) {
            return "redirect:/admin/login";
        }

        List<Category> categoryList = cateService.selectByExample(new CategoryExample());
        model.addAttribute("categoryList", categoryList);

        return "adminAllGoods";
    }

    @RequestMapping("/add")
    public String showAdd(@ModelAttribute("succeseMsg") String msg, Model model, HttpSession session) {
        Admin admin = (Admin) session.getAttribute("admin");
        if (admin == null) {
            return "redirect:/admin/login";
        }

        if (!msg.equals("")) {
            model.addAttribute("msg", msg);
        }

        List<Category> categoryList = cateService.selectByExample(new CategoryExample());
        model.addAttribute("categoryList", categoryList);


        //还需要查询分类传给addGoods页面
        return "addGoods";
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public Msg updateGoods(Goods goods, HttpSession session) {
        Admin admin = (Admin) session.getAttribute("admin");
        if (admin == null) {
            return Msg.fail("请先登录");
        }
        /* goods.setGoodsid(goodsid);*/
        goodsService.updateGoodsById(goods);
        return Msg.success("更新成功!");
    }

    @RequestMapping(value = "/delete/{goodsid}", method = RequestMethod.DELETE)
    @ResponseBody
    public Msg deleteGoods(@PathVariable("goodsid") Integer goodsid) {
        goodsService.deleteGoodsById(goodsid);
        return Msg.success("删除成功!");
    }

    /**
     * 后台管理 -> 商品管理 -> 添加商品
     * @param goods
     * @param fileToUpload
     * @param request
     * @param response
     * @param redirectAttributes
     * @return
     * @throws IOException
     */
    @RequestMapping("/addGoodsSuccess")
    public String addGoods(Goods goods,//直接用GoodsPO类去接收，直接子属性对接映射
                           @RequestParam MultipartFile[] fileToUpload,//上传图片可能不止一张，所以用数组接收
                           HttpServletRequest request,
                           HttpServletResponse response,
                           RedirectAttributes redirectAttributes) throws IOException {
        /*goods.setCategory(1);*/
        goods.setUptime(new Date());
        //添加商品默认情况下,设置成活动ID为1的活动
        goods.setActivityid(1);
        goodsService.addGoods(goods);
        for (MultipartFile multipartFile : fileToUpload) {
            String fileName = goods.getGoodsname()+ multipartFile.getOriginalFilename();
            if (multipartFile != null) {
                //文件名加UUID处理 以及 将图片存放到指定的硬盘当中
               String ImagePath= ImageUtil.imagePath(multipartFile,fileName);
               System.out.println("最后存入数据的图片名字为:"+ImagePath);
                //把图片路径存入数据库中  不但是要将图片存到指定硬盘，而且路径也要保存到库中(全路径还是相对路径自己考虑)
              goodsService.addImagePath(new ImagePath(null, goods.getGoodsid(), ImagePath));

            }
        }
        //由于跳转方式是重定向，不共享request域，但是想传参，所以需要在这里添加key/value
        redirectAttributes.addFlashAttribute("succeseMsg", "商品添加成功!");

        return "redirect:/admin/goods/add";
    }

    @RequestMapping("/addCategory")
    public String addcategory(@ModelAttribute("succeseMsg") String msg, Model model, HttpSession session) {
        Admin admin = (Admin) session.getAttribute("admin");
        if (admin == null) {
            return "redirect:/admin/login";
        }
        CategoryExample categoryExample = new CategoryExample();
        categoryExample.or();
        List<Category> categoryList;
        categoryList = cateService.selectByExample(categoryExample);
        model.addAttribute("categoryList", categoryList);
        if (!msg.equals("")) {
            model.addAttribute("msg", msg);
        }
        return "addCategory";
    }

    @Autowired
    private CateService cateService;

    @RequestMapping("/addCategoryResult")
    public String addCategoryResult(Category category, Model addCategoryResult, RedirectAttributes redirectAttributes) {
        List<Category> categoryList = new ArrayList<>();
        CategoryExample categoryExample = new CategoryExample();
        categoryExample.or().andCatenameEqualTo(category.getCatename());
        categoryList = cateService.selectByExample(categoryExample);
        if (!categoryList.isEmpty()) {
            redirectAttributes.addAttribute("succeseMsg", "分类已存在");
            return "redirect:/admin/goods/addCategory";
        } else {
            cateService.insertSelective(category);
            redirectAttributes.addFlashAttribute("succeseMsg", "分类添加成功!");
            return "redirect:/admin/goods/addCategory";
        }
    }

    @RequestMapping("/saveCate")
    @ResponseBody
    public Msg saveCate(Category category) {
        CategoryExample categoryExample = new CategoryExample();
        categoryExample.or().andCatenameEqualTo(category.getCatename());
        List<Category> categoryList = cateService.selectByExample(categoryExample);
        if (categoryList.isEmpty()) {
            cateService.updateByPrimaryKeySelective(category);
            return Msg.success("更新成功");
        } else return Msg.success("名字已经存在");
    }

    @RequestMapping("/deleteCate")
    @ResponseBody
    public Msg deleteCate(Category category) {
        cateService.deleteByPrimaryKey(category.getCateid());
        return Msg.success("删除成功");
    }
}
