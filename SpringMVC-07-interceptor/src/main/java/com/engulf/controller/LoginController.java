package com.engulf.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
public class LoginController {

    @RequestMapping("/toLogin")
    public String toLogin(String username, String password, HttpSession session){
        return "login";
    }

    @RequestMapping("/toMain")
    public String toLogin(){
        return "main";
    }

    @RequestMapping("/login")
    public String login(String username, String password, HttpSession session){
        //把用户信息存在session中
        session.setAttribute("userLoginInfo",username);
        return "main";
    }

    @RequestMapping("/logout")
    public String logout(HttpSession session){
        //把用户信息存在session中
        session.removeAttribute("userLoginInfo");
        return "redirect:/toMain";
    }
}
