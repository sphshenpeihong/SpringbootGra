package com.shen.ssmschoolshop.api;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Shen Peihong on 2020/6/20 10:20
 * Description: 测试单点登录
 *
 * @since 1.0.0
 */
@RestController
public class TestLogin {

    @RequestMapping(name = "/simplePointLogin.do")
    public void login(HttpServletResponse response,
                      HttpServletRequest request,
                      String id) throws IOException {
        System.out.println(request.getHeader("Referer"));
        if (StringUtils.isAnyEmpty(id)){
            System.out.println(request.getHeader("Referer"));
            System.out.println(URLEncoder.encode("是我的了","UTF-8"));
            System.out.println(request.getContextPath());
            Cookie cookie = new Cookie("username","123");
            response.addCookie(cookie);
            System.out.println(cookie);
            return;
        }
    }

    public static void main(String[] args) {
        System.out.println(System.currentTimeMillis()); //获取当前时间戳
        System.out.println(new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date())); //将Date格式化 当前日期格式化存储
    }


}
