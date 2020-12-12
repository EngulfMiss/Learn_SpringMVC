package com.Engulf.controller;

import com.Engulf.domain.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

import java.util.Date;
import java.util.Map;

/**
 * 常用注解:
 * @RequestParam:用于解决参数名称不同无法正确封装的问题
 * @RequestBody:用于获取请求体内容.直接使用得到的是 key=value&key=value...的结构数据
 * @PathVaribale:用于绑定url中的占位符
 * @RequestHeader:用于获取请求消息头 value="消息头的key"
 * @CookieValue：用于获取指定的cookie的value值
 * @ModelAttribute：出现在方法上，表示当前方法会在控制器的方法执行之前执行
 *                  出现在参数上，获取指定的数据给参数赋值
 * @SessionAttributes
 */
@Controller
@RequestMapping("/anno")
@SessionAttributes("msg")
public class AnnoController {
    @RequestMapping("/testRequestParam")
    public String testRequestParam(@RequestParam(name="uname") String username){
        System.out.println("执行了testRequestParam方法");
        System.out.println(username);
        return "success";
    }

    /**
     * @RequestBody
     * 获取请求体内容
     * @return
     */
    @RequestMapping("/testRequestBody")
    public String testRequestBody(@RequestBody String body){
        System.out.println("执行了testRequestBody方法");
        System.out.println(body);
        return "success";
    }


    /**
     * @PathVariable
     *      将url中的参数赋给方法的形参
     * @return
     */
    @RequestMapping("/testPathVariable/{Myid}")
    public String testPathVariable(@PathVariable(name = "Myid") String id){
        System.out.println("执行了testPathVariable方法");
        System.out.println(id);
        return "success";
    }


    @RequestMapping("/testRequestHeader")
    public String testRequestHeader(@RequestHeader(value = "Accept") String header){
        System.out.println("执行了testRequestHeader方法");
        System.out.println(header);
        return "success";
    }


    @RequestMapping("/testCookieValue")
    public String testCookieValue(@CookieValue(value = "JSESSIONID") String cookieValue){
        System.out.println("testCookieValue方法执行了");
        return "success";
    }


    /**
     * ModelAttribute注解
     * @return
     * 方式一
     */
    /*@RequestMapping("/testModelAttribute")
    public String testModelAttribute(User user){
        System.out.println("testModelAttribute方法执行了");
        System.out.println(user);
        return "success";
    }*/

    /**
     * 该方法先执行
     * 方式一
     */
    @ModelAttribute
    public User showChampion(String username){
        System.out.println("showChampion执行了...");
        //通过用户名查询用户(模拟)
        User user = new User();
        user.setUsername(username);
        user.setAge(22);
        user.setDate(new Date());
        return user;
    }

    /**
     * 方式二
     * @param username
     * @param map
     */
    @ModelAttribute
    public void showChampion(String username, Map<String,User> map){
        System.out.println("showChampion执行了...");
        //通过用户名查询用户(模拟)
        User user = new User();
        user.setUsername(username);
        user.setAge(22);
        user.setDate(new Date());
        map.put("test",user);
    }

    /**
     * 方式二
     * @param user
     * @return
     */
    @RequestMapping("/testModelAttribute")
    public String testModelAttribute(@ModelAttribute("test") User user){
        System.out.println("testModelAttribute方法执行了");
        System.out.println(user);
        return "success";
    }


    /**
     * @SessionAttributes
     * @return
     */
    @RequestMapping("/testSessionAttributes")
    public String testSessionAttributes(Model model){
        System.out.println("testSessionAttributes方法执行了");
        //底层会存储到request域对象中
        model.addAttribute("msg","Hello World!");
        return "success";
    }

    /**
     * 获取值
     * @param modelMap
     * @return
     */
    @RequestMapping("/getSessionAttributes")
    public String getSessionAttributes(ModelMap modelMap){
        System.out.println("getSessionAttributes方法执行了");
        String msg = (String)modelMap.get("msg");
        System.out.println(msg);
        return "success";
    }


    /**
     * 清除
     * @param status
     * @return
     */
    @RequestMapping("/delSessionAttributes")
    public String delSessionAttributes(SessionStatus status){
        System.out.println("delSessionAttributes方法执行了");
        status.setComplete();
        return "success";
    }
}
