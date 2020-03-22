package com.zhang.ssmschoolshop.config;

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.annotation.WebListener;

/**
 * Created by Shen Peihong on 2020/3/19 13:47
 */
//注解监听器
@WebListener
public class MvcConfigListener implements ServletRequestListener {
    @Override
    public void requestDestroyed(ServletRequestEvent sre) {
        System.out.println("request请求结束");
    }

    @Override
    public void requestInitialized(ServletRequestEvent sre) {
        System.out.println("request请求开始");
    }
}
