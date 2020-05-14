package com.shen.ssmschoolshop.controller.admin;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.shen.ssmschoolshop.entity.Admin;
import com.shen.ssmschoolshop.entity.ResultVO;
import com.shen.ssmschoolshop.entity.User;
import com.shen.ssmschoolshop.service.AdminService;
import com.shen.ssmschoolshop.service.UserService;
import com.shen.ssmschoolshop.util.FieldDef;
import com.shen.ssmschoolshop.util.Md5Util;
import com.sun.org.apache.regexp.internal.RE;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class LoginController {

    @Autowired
    private AdminService adminService;

    @Autowired
    private UserService userService;

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

    /**
     * 跳转到添加管理员页面
     * @param request
     * @return
     */
    @GetMapping("/addOrdManagerPage.do")
    public String addOrdManagerPage(HttpServletRequest request){
        HttpSession session = request.getSession();
        Admin admin = (Admin) session.getAttribute("admin");
        if (admin==null){
            return "redirect:/admin/login"; //重定向到上面的接口
        }
        return "addManager";
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
    @PostMapping("/addManager.do")
    public String addManager(HttpServletRequest request,Model model,Admin adminFront){
        //获取表单传过来的账号和密码，将其添加到admin表中
        HttpSession session = request.getSession();
        Admin admin = (Admin) session.getAttribute("admin");
        if (session.getAttribute("admin")==null){
            return "redirect:/admin/login";
        }

        if (adminFront!=null){
            adminFront.setAuthority(0);
            adminService.addManager(adminFront);
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

    /**
     * 跳转到充值页面
     * @param request
     * @return
     */
    @GetMapping("/topUp.do")
    public String topUp(HttpServletRequest request){
        return "addTopUp";
    }

    @GetMapping("/getAllUser")
    @ResponseBody
    public List<User> getAllUser(HttpServletRequest request){
        HttpSession session = request.getSession();
        Admin admin = (Admin) session.getAttribute("admin");
        return userService.getAllUser();
    }

    /**
     * 提供充值接口
     * @param request
     * @param id  当前用户ID
     * @param money  充值金额
     */
    @ResponseBody
    @RequestMapping(value = "/userTopUp.do",method = RequestMethod.POST )
    public ResultVO userTopUp(HttpServletRequest request,
                            @RequestParam("user") Integer id,
                            @RequestParam("topupMoney")Integer money){
        //判断用户不为null后，直接插入 插入后返回充值已成功！
        HttpSession session = request.getSession();
        Admin admin = (Admin) session.getAttribute("admin");
        if (admin!=null){
            adminService.userTopup(money,id);
        }
        ResultVO resultVO = new ResultVO();
        resultVO.setCode(200);
        resultVO.setDesc("充值已成功");
        return resultVO;
    }

}
