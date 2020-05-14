package com.shen.ssmschoolshop.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Shen Peihong on 2020/5/9 14:50
 * Description:
 *
 * @since 1.0.0
 */
@Controller
public class TestCtl {

    /**
     * 请求转发
     * @return
     */
    @RequestMapping("/into.do")
    public String into(){
        return "Test2";
    }

    @RequestMapping("/testInto.do")
    public void testInto(String username,String password){
        System.out.println(username);
        System.out.println(password);
    }

}
