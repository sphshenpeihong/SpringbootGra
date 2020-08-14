package com.shen.ssmschoolshop.controller.test1;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Shen Peihong on 2020/6/8 1:53
 * Description:
 *
 * @since 1.0.0
 */
@RequestMapping("/test1")
@Controller
public class TestMgrCtl1 {

    @RequestMapping("/test1.do")
    public void test1(Integer id){
        System.out.println("进来否?");
        System.out.println(id);
    }

}
