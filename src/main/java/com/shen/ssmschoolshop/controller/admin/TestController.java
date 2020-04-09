package com.shen.ssmschoolshop.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Shen Peihong on 2020/4/9 14:21
 */
@Controller
public class TestController {

    @RequestMapping("/entry")
    public String Entry(){
        System.out.println("进入entry");
        return "forward:Test1";
    }

    @RequestMapping(path = {"/Test"})
    public void Test(){
        System.out.println("Test");
    }
}
