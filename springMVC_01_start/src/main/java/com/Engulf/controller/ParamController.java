package com.Engulf.controller;

import com.Engulf.domain.Champion;
import com.Engulf.domain.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 请求参数绑定
 */

@Controller
@RequestMapping("/param")
public class ParamController {
    @RequestMapping("/testParam")
    public String testParam(String username,String password){
        System.out.println("执行了param中的方法...");
        System.out.println("username:" + username);
        System.out.println("password:" + password);
        return "success";
    }

    /**
     * 请求参数绑定，封装JavaBean
     * @return
     */
    @RequestMapping("/saveChampion")
    public String saveChampion(Champion champion){
        System.out.println("执行了saveChampion...");
        System.out.println(champion);
        return "success";
    }


    /**
     * 自定义类型转换器
     * @param user
     * @return
     */
    @RequestMapping("/saveUser")
    public String saveUser(User user){
        System.out.println("执行了saveUser方法...");
        System.out.println(user);
        return "success";
    }


    /**
     * 原生ServletAPI获取
     * @return
     */
    @RequestMapping("/testServlet")
    public String MyTestServlet(HttpServletRequest req, HttpServletResponse resp){
        System.out.println(req);
        System.out.println(req.getSession());
        System.out.println(req.getSession().getServletContext());
        System.out.println("--------------------------------");
        System.out.println(resp);
        System.out.println("执行了MyTestServlet方法...");
        return "success";
    }
}
