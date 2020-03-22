package com.zhang.ssmschoolshop.config;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

/**
 * Created by Shen Peihong on 2020/3/19 11:44
 */
//使用注解配置过滤器
/*@WebFilter(urlPatterns = {"/*"},asyncSupported = true,filterName = "MvcConfigFilter")*/
public class MvcConfigFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

    }

    @Override
    public void destroy() {

    }
}
