package com.Engulf.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

//控制器
@Controller
@RequestMapping(path = "/kindred")
/*
RequestMapping属性：
    path等同于value ：指定请求URL
    method ：指定请求方式
    params ：用于指定请求参数的条件.它支持简单的表达式.要求请求参数的key和value必须和配置的
    一模一样
    headers ：用于指定限制请求消息头的条件
*/
public class HelloController {

    //用于建立请求URL和处理请求方法之间的对应关系
    @RequestMapping(path="/hello")
    public String sayHello(){
        System.out.println("Hello SpringMVC");
        return "success";
    }

    /**
     * RequestMapping注解
     * @return
     */
    @RequestMapping(path = "/Gnar",method = {RequestMethod.GET,RequestMethod.POST},
    params = {"username=Gnar"},headers = {"Accept"})
    public String testRequestMapping(){
        System.out.println("测试RequestMapping注解...");
        return "success";
    }
}
