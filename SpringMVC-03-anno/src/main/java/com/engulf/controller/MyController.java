package com.engulf.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/champion")
public class MyController {
    @RequestMapping({"/kind"})
    public String kindred(Model model){
        //封装数据
        model.addAttribute("msg","Hello,Kindred");
        return "kindred";  //会被视图解析器处理
    }
}
