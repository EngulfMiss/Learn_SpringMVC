package com.Engulf.controller;

import com.Engulf.exception.SysException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/ErrorManage")
public class ExceptionController {
    @RequestMapping("/testException")
    public String testException() throws Exception{
        System.out.println("testException方法执行了...");
        try {
            //模拟异常
            int a = 10/0;
        } catch (Exception e) {
            //控制台打印异常信息
            e.printStackTrace();
            //抛出自定义异常信息
            throw new Exception("你的操作出现了错误...");
        }
        return "success";
    }
}
