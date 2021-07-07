package com.engulf.controller;

import com.engulf.pojo.Champion;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/info")
public class ChampionController {

    //前端接收参数
    @GetMapping("/t1")
    public String test(@RequestParam("username") String name, Model model){
        //1.接收前端参数
        System.out.println("接收到前端的参数为：" + name);
        //2.将返回的结果传递给前端
        model.addAttribute("msg",name);
        //3.跳转视图
        return "gnar";
    }

    //前端接收的是一个对象：id,name,age
    @GetMapping("/champion")
    public String test2(Champion champion,Model model){
        System.out.println(champion);
        return "gnar";
    }

}
