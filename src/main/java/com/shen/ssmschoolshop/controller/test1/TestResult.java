package com.shen.ssmschoolshop.controller.test1;

import com.shen.ssmschoolshop.model.TestResult111;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Shen Peihong on 2020/7/2 23:12
 * Description:
 *
 * @since 1.0.0
 */
@RestController
@RequestMapping("/ptl")
public class TestResult {

    /**
     * data返回String类型给前端
     * @return
     */
    @RequestMapping("/test0.do")
    public TestResult111 test0(){
        String str = "test";
        TestResult111 testResult111 = new TestResult111(str);
        return testResult111;
    }

    /**
     * 返回Float类型给前端
     */
    @RequestMapping("/test1.do")
    public TestResult111 test1(){
        Float str = 1.1f;
        TestResult111 testResult111 = new TestResult111(str);
        return testResult111;
    }

    /**
     * 返回Double类型给前端
     * @return
     */
    @RequestMapping("/test2.do")
    public TestResult111 test2(){
        Double str = 1.1d;
        TestResult111 testResult111 = new TestResult111(str);
        return testResult111;
    }

    /**
     * 返回一个String[]数组给前端  返回一个数组 里面的值都是String类型的
     * @return
     */
    @RequestMapping("/test3.do")
    public TestResult111 test3(){
        String[] str = new String[]{"123","456","789"};
        TestResult111 testResult111 = new TestResult111(str);
        return testResult111;
    }

    /**
     * 返回一个List<String>类型给前端 效果是跟String[]数组一样的，因为List的类型 向下转型是ArrayList 底层也是数组
     * @return
     */
    @RequestMapping("/test4.do")
    public TestResult111 test4(){
        List<String> list = new ArrayList<>();
        list.add("123");
        list.add("456");
        list.add("789");
        TestResult111 testResult111 = new TestResult111(list);
        return testResult111;
    }

    @RequestMapping("/test5.do")
    public TestResult111 test5(){

        return null;
    }

    @RequestMapping("/test6.do")
    public TestResult111 test6(){

        return null;
    }

    @RequestMapping("/test7.do")
    public TestResult111 test7(){

        return null;
    }

    @RequestMapping("/test8.do")
    public TestResult111 test8(){

        return null;
    }

    @RequestMapping("/test9.do")
    public TestResult111 test9(){

        return null;
    }

    @RequestMapping("/test10.do")
    public TestResult111 test10(){

        return null;
    }

    @RequestMapping("/test11.do")
    public TestResult111 test11(){

        return null;
    }

}
