package com.shen.ssmschoolshop.controller.admin;

import com.shen.ssmschoolshop.entity.Admin;
import org.springframework.http.HttpCookie;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Created by Shen Peihong on 2020/4/9 14:21
 */
@Controller
public class TestController {

    public static void main(String[] args) {
        Map<String,Admin> map = new HashMap<String,Admin>();
        Admin admin = new Admin();
        map.put("test",admin);
        admin.setPassword("123");
        System.out.println(map.get("test").getPassword());

    }

    @RequestMapping(path = {"/Test"})
    public String Test(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //request的方法
        /*
        System.out.println("getRequestURL:"+request.getRequestURL()); //获取该次请求的URL全路径
        System.out.println("getRequestURI:"+request.getRequestURI());  //获取该请求的相对路径
        System.out.println("getHeader:"+request.getHeader("User-Agent")); //获取该请求的请求头中的User-Agent
        System.out.println("getQueryString:"+request.getQueryString()); //获得该请求的请求参数部分key=value
        System.out.println("getContextPath:"+request.getContextPath()); //获得相对路径
        System.out.println("getMethod:"+request.getMethod());  //获得请求的类型
        System.out.println("getRequestedSessionId:"+request.getRequestedSessionId());  //获得存储在前端Cookie中的SessionID 若没有的话，系统会自动生成一个 然后响应到前端的Cookie中
        System.out.println("getRemoteUser:"+request.getRemoteUser());
        System.out.println("getAuthType:"+request.getAuthType());
        System.out.println("getPathInfo:"+request.getPathInfo());
        System.out.println("getRemoteHost:"+request.getRemoteHost());
        */
        //获取请求的session session的方法
        /*
        HttpSession session = request.getSession();
        System.out.println("session为:"+session); //session在JVM内存中的地址值
        System.out.println("getId:"+session.getId()); //该session的成员变量ID的值
        System.out.println("getLastAccessedTime:"+session.getLastAccessedTime()); //
        System.out.println("getCreationTime:"+session.getCreationTime()); //返回session的创建时间
        System.out.println("getMaxInactiveInterval:"+session.getMaxInactiveInterval()); //有效时间 单位是s 默认是30分钟
        System.out.println("getServletContext:"+session.getServletContext());
        System.out.println("isNew:"+session.isNew()); //判断是否是刚生成的(新用户)
        session.invalidate(); //清空session容器中
         */

        //response
        /*response.setHeader("head","123");
        System.out.println(response.getHeader("Content-Length"));*/
        //response.setDateHeader("Expires");
        //response.sendRedirect("test.html"); //会自动加上前面的路径
        /*
        String userCookie = UUID.randomUUID().toString().trim(); //随机生成Cookie的值
        Cookie cookie = new Cookie("userCookie",userCookie); //创建Cookie对象
        //给Cookie设置有效时间，单位是秒
        // cookie的maxAge属性的默认值是-1：只在浏览器内存存活，关闭浏览器Cookie就消失
        // 大于0：浏览器会把Cookie保存到本地硬盘中，就算关闭浏览器，就算重启客户端电脑，也可以访问服务器
        cookie.setMaxAge(60);
        response.addCookie(cookie);//将Cookie添加到响应头
        */
        return "Test1";
    }
}
