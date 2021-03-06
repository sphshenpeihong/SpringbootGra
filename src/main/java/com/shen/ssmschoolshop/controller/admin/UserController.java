package com.shen.ssmschoolshop.controller.admin;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import com.shen.ssmschoolshop.entity.User;
import com.shen.ssmschoolshop.entity.UserExample;
import com.shen.ssmschoolshop.service.UserService;
import com.shen.ssmschoolshop.util.Msg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;


@Controller
@RequestMapping("/admin/user")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 后台用户管理展示
     * @param pn
     * @param response
     * @param model
     * @return
     */
    @RequestMapping("/showjson")
    @ResponseBody
    public Msg getAllGoods(@RequestParam(value = "page",defaultValue = "1") Integer pn, HttpServletResponse response, Model model) {
        //一页显示几个数据
        PageHelper.startPage(pn, 10);
        List<User> userList = userService.selectByExample(new UserExample());
        //显示几个页号
        PageInfo page = new PageInfo(userList,5);

       /* model.addAttribute("pageInfo", page);*/
        //返回一个对象，方法里面封装了对象的成员变量
        return Msg.success("查询成功!").add("pageInfo", page);
    }

    @RequestMapping("/show")
    public String userManage() {
        return "userManage";
    }

    @RequestMapping(value = "/delete/{userid}", method = RequestMethod.DELETE)
    @ResponseBody
    public Msg deleteUser(@PathVariable("userid")Integer userid) {
//        goodsService.deleteGoodsById(goodsid);
        userService.deleteUserById(userid);
        return Msg.success("删除成功!");
    }
}
