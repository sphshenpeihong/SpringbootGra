package com.shen.ssmschoolshop.config;


import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.io.OutputStreamWriter;
import java.io.Reader;
import java.util.Properties;


/**
 * @author created by Coding shenpeihong
 * @version v.0.1
 * @Description TODO
 * @date 2020/3/10
 * @备注  springboot内置tomcat配置虚拟路径
 *      linux： /usr/upload  /pictures
 *      window:  d:/upload  /pictures
 **/

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    public static void main(String[] args) {
        System.out.println("时间戳"+System.currentTimeMillis());
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        String os = System.getProperty("os.name");
        String pathPatterns="/pictures/**";
        String pathAbsolute="file:D:/upload/";
        if (os.toLowerCase().startsWith("linux")){
            pathAbsolute="file:/usr/upload/";
        }
        registry.addResourceHandler(pathPatterns).addResourceLocations(pathAbsolute);
    }

}