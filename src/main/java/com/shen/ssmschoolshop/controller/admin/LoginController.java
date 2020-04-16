package com.shen.ssmschoolshop.controller.admin;


import com.shen.ssmschoolshop.entity.Admin;
import com.shen.ssmschoolshop.service.AdminService;
import com.shen.ssmschoolshop.util.Md5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/admin")
public class LoginController {

    @Autowired
    private AdminService adminService;

    //访问后台登陆页面 由于我们的项目是springboot项目 然后采用的是jsp 不是前后端分离项目，无法直接在js跳转到指定的jsp，所以需要Controller来当中转器
    @RequestMapping("/login")
    public String adminLogin() {
        return "adminLogin";
    }

    @RequestMapping(value = "/confirmLogin")
    //客户端传了账号和密码到服务器端，形参若是类类型接收，则Springmvc规定要是其成员变量才行
    public String confirmLogin(Admin admin, Model model, HttpServletRequest request) {
        admin.setPassword(Md5Util.MD5Encode(admin.getPassword(),"utf-8")); //使用MD5加密算法将密码进行加密
        Admin selectAdmin = adminService.selectByName(admin);
        if (selectAdmin == null) {
            model.addAttribute("errorMsg", "用户名或密码错误");
            return "adminLogin"; //查库若无此号，则返回后台登陆页面
        } else {
            HttpSession session = request.getSession();
            session.setAttribute("admin", selectAdmin);
            return "redirect:/admin/user/show";
        }
    }

    @RequestMapping("/logout")
    public String adminLogout(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.removeAttribute("admin");
        return "redirect:/admin/login";
    }

    /*@RequestMapping("/index")
    public String showAdminIndex() {
        return "user";
    }*/
}
