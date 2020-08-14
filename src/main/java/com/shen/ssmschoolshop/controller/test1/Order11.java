package com.shen.ssmschoolshop.controller.test1;

import com.shen.ssmschoolshop.entity.Order;
import com.shen.ssmschoolshop.entity.User;
import org.junit.Test;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Shen Peihong on 2020/7/3 10:51
 * Description:
 *
 * @since 1.0.0
 */
public class Order11 {

    @Test
    public void test(){
        String str = "111";
        User u = new User();
        u.setPassword("mima");
        Map<String,Object> map = new HashMap<String,Object>(){{
            //第一层{} 是内部类，既HashMap就是相对的外部类
            //第二层{} 是内部类的构造块
            isEmpty(); //这个内部类是属于HashMap的内部类，再加一个括号是构造块，所以HashMap
            put("username","123");
            put("password","456");
            put("sex",str);
            u.setPassword("wogai");
            put("temp",u);
        }};
        System.out.println(map.get("username"));
        System.out.println(map.get("sex"));
        User temp = (User) map.get("temp");
        System.out.println(temp.getPassword());
        u.setPassword("555");
        System.out.println(temp.getPassword());
    }
}
