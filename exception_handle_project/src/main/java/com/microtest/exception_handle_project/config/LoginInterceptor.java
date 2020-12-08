package com.microtest.exception_handle_project.config;

import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        Object username = request.getSession().getAttribute("loginUser");
        if(!StringUtils.isEmpty(username)){
            request.removeAttribute("msg");
            return true;
        }
        else{
            request.setAttribute("msg","没有权限请登录");
            request.getRequestDispatcher("/").forward(request,response);
            return false;
        }

    }
}
