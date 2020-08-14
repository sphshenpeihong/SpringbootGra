package com.shen.ssmschoolshop.controller.admin;

import com.shen.ssmschoolshop.entity.ImagePath;
import com.shen.ssmschoolshop.entity.User;
import com.shen.ssmschoolshop.service.GoodsService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Shen Peihong on 2020/6/18 11:59
 * Description:
 *
 * @since 1.0.0
 */
@Controller
public class GetObjectCtl {

    @Resource(name = "goodsService")
    private GoodsService goodsService;

    @RequestMapping("/hello.do")
    public void test(HttpServletRequest request, @RequestParam(name = "size",defaultValue = "12")Integer size){
        System.out.println(size);
        System.out.println(goodsService);
        System.out.println("调用");
        Utils u = new Utils();
        u.xuan(goodsService);
        System.out.println(request.getContextPath());
    }

    @Test
    public void test1(){
        User u1= new User();
        u1.setPassword("123");
        List<User> list = new ArrayList<>();
        list.add(u1);
        System.out.println(u1.getPassword());
        for (User user : list) {
            if (user!=null){
                user.setPassword("456");
            }
        }
        System.out.println(list.get(0).getPassword());
        System.out.println(u1.getPassword());
    }

}
