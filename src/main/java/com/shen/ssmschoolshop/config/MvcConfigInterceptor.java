package com.shen.ssmschoolshop.config;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Shen Peihong on 2020/3/19 11:31
 */
//通过注解使用拦截器
public class MvcConfigInterceptor extends WebMvcConfigurationSupport {
    //重写父类的添加拦截器方法 参数是拦截器登记对象
    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
        //拦截代码写在匿名内部类中，返回的对象调用方法配置拦截路径和不拦截路径
        InterceptorRegistration registration = registry.addInterceptor(new HandlerInterceptor() {
            @Override
            public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
                return false;
            }

            @Override
            public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

            }

            @Override
            public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

            }
        });
        //现在是试用，暂时不配置拦截器
        /*registration.addPathPatterns("/**");*/
    }
}
