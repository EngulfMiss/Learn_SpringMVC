package com.engulf.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
public class ModelTest {
    @GetMapping("/test")
    public String test(HttpServletRequest request, HttpServletResponse response){
        HttpSession session = request.getSession();
        System.out.println(session.getId());
        return "gnar";
    }

    //SpringMVC的转发(无视图解析器)
    /*@GetMapping("/test2")
    public String test2(Model model){
        model.addAttribute("msg","SpringMVC转发");
        return "/WEB-INF/jsp/gnar.jsp";
    }

    @GetMapping("/test3")
    public String test3(Model model){
        model.addAttribute("msg","SpringMVC转发");
        return "forward:/WEB-INF/jsp/gnar.jsp";
    }*/


    //重定向
    @GetMapping("/test4")
    public String test4(Model model){
        model.addAttribute("msg","SpringMVC转发");
        return "redirect:/test.jsp";
    }
}
