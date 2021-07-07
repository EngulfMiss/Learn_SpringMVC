package com.engulf.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginInterceptor implements HandlerInterceptor {
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        //什么情况下放行
        //是登录用户放行
        HttpSession session = request.getSession();
        if(session.getAttribute("userLoginInfo") != null){
            return true;
        }

        //访问登录页面要放行
        if (request.getRequestURI().contains("toLogin")){
            return true;
        }

        //登录请求要放行
        if (request.getRequestURI().contains("login")){
            return true;
        }

        //什么情况下拦截
        request.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(request,response);
        return false;
    }
}
