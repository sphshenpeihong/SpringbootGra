package com.shen.ssmschoolshop.controller.test;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.tags.Param;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Shen Peihong on 2020/5/15 10:34
 * Description:
 *
 * @since 1.0.0
 */
@Controller
@RequestMapping("/test15")
public class Son extends Parent{

    private String ooo;

    @RequestMapping("/test1515.do")
    public String test1515(HttpServletRequest request,HttpServletResponse response) throws IOException, ServletException {
        System.out.println("111");
        /*response.sendRedirect("/zi.do");*/
        return "redirect:/zi.do";
    }

    @RequestMapping("/zi.do")
    public void zi(){
        System.out.println("子");
    }

    @Override
    public void setParent() {
        System.out.println("我是子");
    }
}
