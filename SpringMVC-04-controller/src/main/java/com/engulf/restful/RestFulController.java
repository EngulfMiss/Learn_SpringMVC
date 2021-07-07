package com.engulf.restful;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class RestFulController {

    //原来：http://localhost:8080/add?a=1&b=6
    //RestFul：http://localhost:8080/add/a/b

//    @RequestMapping(value = "/add/{a}/{b}", method = RequestMethod.GET)
    @GetMapping("/add/{a}/{b}")
    public String test(@PathVariable int a,@PathVariable int b, Model model){
        int res = a + b;
        model.addAttribute("msg","Get结果为"+res);
        return "gnar";
    }

    @PostMapping("/add")
    public String test2(int a,int b, Model model){
        int res = a + b;
        model.addAttribute("msg","Post结果为"+res);
        return "gnar";
    }
}
