package com.shen.ssmschoolshop.controller.test;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Shen Peihong on 2020/5/15 10:34
 * Description:
 *
 * @since 1.0.0
 */
@RestController
public abstract class Parent {

    protected String parent;

    public abstract void setParent();

    @RequestMapping("/parent.do")
    public void test(){
        System.out.println("11221");
        System.out.println(this.parent);
    }

    @RequestMapping("/test15.do")
    public void test15(){
        System.out.println("121231");
    }
}
