package com.engulf.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MyController {
    @RequestMapping("/gnar")
    public String gnar(Model model){
        model.addAttribute("msg","Gnar");
        return "gnar";
    }
}
