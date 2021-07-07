package com.engulf.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class EncodingController {
    @PostMapping("/encoding/t1")
    public String test(String name, Model model){
        model.addAttribute("msg",name);
        return "gnar";
    }
}
