package com.shen.ssmschoolshop.controller.admin;


import com.shen.ssmschoolshop.entity.Admin;
import com.shen.ssmschoolshop.service.AdminService;
import com.shen.ssmschoolshop.util.FieldDef;
import com.shen.ssmschoolshop.util.Md5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

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


    /**
     * 用户点击系统管理时，跳转到系统管理制定的jsp
     * @param request
     * @param model
     * @return
     */
    @GetMapping("/system")
    public String adminSystem(HttpServletRequest request,Model model) {
        HttpSession session = request.getSession();
        Admin admin = (Admin)session.getAttribute("admin");
        if(session.getAttribute("admin")==null){
            return "redirect:/admin/login";
        }
        //这里直接判断是否为超管 往model塞值 并且把查询到的管理员List数据存放到model里面
        if (admin.getAuthority() == FieldDef.superManager){
            model.addAttribute("isSuper", FieldDef.superManager);
            //当前若是超管的话，获取管理员数据
            model.addAttribute("allManager", adminService.selectAllManager());
        }else if(admin.getAuthority() == FieldDef.ordManager){
            model.addAttribute("isSuper", FieldDef.ordManager);
        }
        return "adminSystem";
    }

    @RequestMapping("/logout")
    public String adminLogout(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.removeAttribute("admin");
        return "redirect:/admin/login";
    }

    @RequestMapping("/addManager")
    public String addManagerJsp(){
        return "addManager";
    }

    /**
     * 添加管理员jsp 输入form表单 点击确定响应接口
     * @param request
     * @param model
     * @return
     */
    @RequestMapping("addManager.do")
    public String addManager(HttpServletRequest request,Model model){
        //

        return " ";
    }


    /*@RequestMapping("/index")
    public String showAdminIndex() {
        return "user";
    }*/
}
