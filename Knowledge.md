# 编写第一个SpringMVC程序
**步骤**  
1.新建项目，添加web的支持  
2.导入SpringMVC的相关依赖  
3.配置web.xml，注册DispatcherServlet
```xml
<!-- 注册DispatcherServlet -->
    <servlet>
        <servlet-name>springmvc</servlet-name>
        <servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
        <!-- 关联一个springmvc的配置文件：servlet.xml -->
        <init-param>
            <param-name>contextConfigLocation</param-name>
            <param-value>classpath:springmvc-servlet.xml</param-value>
        </init-param>
        <load-on-startup>1</load-on-startup>
    </servlet>

    <servlet-mapping>
        <servlet-name>springmvc</servlet-name>
        <url-pattern>/</url-pattern>
    </servlet-mapping>
```
4.编写SpringMVC的配置文件 (springmvc-servlet.xml) 通过Spring注册bean对象
** 注解开发的配置文件 **
___
```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:context="http://www.springframework.org/schema/context"
       xmlns:mvc="http://www.springframework.org/schema/mvc"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
        http://www.springframework.org/schema/beans/spring-beans.xsd
        http://www.springframework.org/schema/mvc
        http://www.springframework.org/schema/mvc/spring-mvc.xsd
        http://www.springframework.org/schema/context
        http://www.springframework.org/schema/context/spring-context.xsd">

    <!-- 配置spring自动扫描的包 -->
    <context:component-scan base-package="com.engulf.controller"></context:component-scan>
    <!-- 让SpringMVC不处理静态资源 -->
    <mvc:default-servlet-handler></mvc:default-servlet-handler>
    <!--
        支持mvc注解驱动
            在spring中一般采用@RequestMapping注解来完成映射关系
            要想使@RequestMapping注解生效
            必须向上下文中注册DefaultAnnotationHandlerMapping
            和一个AnnotationMethodHandlerAdapter实例
            这两个实例分别在类级别和方法级别处理映射
            而annotation-driver配置可以帮助我们自动完成上述两个实例的注入
    -->
   <mvc:annotation-driven></mvc:annotation-driven>

    <!-- 视图解析器 -->
    <bean id="internalResourceViewResolver" class="org.springframework.web.servlet.view.InternalResourceViewResolver">
        <property name="prefix" value="/WEB-INF/jsp/"></property>
        <property name="suffix" value=".jsp"></property>
    </bean>
</beans>
```
___
**实现Controller接口的配置文件**
___
```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd">

    <!-- 配置处理器映射器 -->
    <bean class="org.springframework.web.servlet.handler.BeanNameUrlHandlerMapping"></bean>

    <!-- 配置处理器适配器 -->
    <bean class="org.springframework.web.servlet.mvc.SimpleControllerHandlerAdapter"></bean>

    <!-- 配置视图解析器 ModelAndView -->
    <bean class="org.springframework.web.servlet.view.InternalResourceViewResolver">
        <!-- 前缀 -->
        <property name="prefix" value="/WEB-INF/jsp/"></property>
        <!-- 后缀 -->
        <property name="suffix" value=".jsp"></property>
    </bean>

    <!-- 注册处理器 -->
    <bean id="/hello" class="com.engulf.controller.HelloController"></bean>
</beans>
```
___
5.编写处理器(Controller)  

注解方式：
```java
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
```
实现接口方式：
```java
package com.engulf.controller;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HelloController implements Controller {
    public ModelAndView handleRequest(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception {
        //ModelAndView 模型和视图对象
        ModelAndView mv = new ModelAndView();

        //封装对象，放在ModelAndView中。Model
        mv.addObject("msg","HelloSpringMVC");
        mv.setViewName("hello");   //会查询到视图解析器的配置  /WEB-INF/jsp/hello.jsp
        return mv;
    }
}
```
6.在springMVC配置文件中配置处理器bean(注解不需要，因为@Controller已经注册了)

### 在SpringMVC中，/ 和 /* 的区别
- /：只匹配所有的请求，不会去匹配jsp页面
- /*：匹配所有的请求，包括jsp页面      可能会出现 xxx.jsp.jsp.jsp

## 跳转方式
- servlet API
转发：req.getRequestDispatcher("/WEB-INF/jsp/test.jsp").forward(req,resp);  
重定向：resp.sendRedirect("/test.jsp");

- SpringMVC
通过SpringMVC来实现转发和重定向，**无视图解析器**  
```java
//SpringMVC的转发
    @GetMapping("/test2")
    public String test2(Model model){
        model.addAttribute("msg","SpringMVC转发");
        return "/WEB-INF/jsp/gnar.jsp";
    }

    @GetMapping("/test3")
    public String test3(Model model){
        model.addAttribute("msg","SpringMVC转发");
        return "forward:/WEB-INF/jsp/gnar.jsp";
    }

//重定向
    @GetMapping("/test4")
    public String test4(Model model){
        model.addAttribute("msg","SpringMVC转发");
        return "redirect:/test.jsp";
    }
```

通过SpringMVC来实现转发和重定向，**有视图解析器**  
```java
//转发
@GetMapping("/test")
public String test(){
    return "gnar";
}

//重定向
    @GetMapping("/test4")
    public String test4(Model model){
        model.addAttribute("msg","SpringMVC转发");
        return "redirect:/test.jsp";
    }
```

## 接收请求参数与数据回显
- @RequestParam:请求地址中的参数绑定  
请求地址：http://localhost:8080/info/t1?username=kindred
```java
    public String test(@RequestParam("username") String name, Model model){
        return "gnar";
    }
```
**传递的如果是对象，如果请求参数与实体类的属性名相同，则方法的参数可以直接使用对象**  
____
数据回显
- ModelAndView：可以在存储数据的同时，进行设置返回的逻辑视图，进行控制显示层的跳转
```java
public ModelAndView handleRequest(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception      {
    //ModelAndView 模型和视图对象
    ModelAndView mv = new ModelAndView();

    //封装对象，放在ModelAndView中
    mv.addObject("msg","HelloSpringMVC");
    mv.setViewName("hello");   //会查询到视图解析器的配置  /WEB-INF/jsp/hello.jsp
    System.out.println(mv.getViewName());
    return mv;
}
```
- Model：只有寥寥几个方法，只适用于存储数据，简化了新手对于Model对象的操作和理解
```java
@GetMapping("/t1")
public String test(@RequestParam("username") String name, Model model){
    //1.接收前端参数
    System.out.println("接收到前端的参数为：" + name);
    //2.将返回的结果传递给前端
    model.addAttribute("msg",name);
    //3.跳转视图
    return "gnar";
}
```
- ModelMap：继承了LinkedHashMap，除了实现了自身的一些方法，同样的继承LindedHashMap的方法和特性

## 乱码问题(一定要注意过滤的路径是 /*)
使用过滤器解决乱码问题（手动书写filter）
步骤：  
1.写一个类实现Filter接口(javax.servlet.*)  
2.重写doFilter方法  
```java
public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
    servletRequest.setCharacterEncoding("utf-8");
    servletResponse.setCharacterEncoding("utf-8");
    filterChain.doFilter(servletRequest,servletResponse);
}
```
3.在web.xml中注册filter
```xml
<filter>
    <filter-name>encoding</filter-name>
    <filter-class>com.engulf.filter.EncodingFilter</filter-class>
</filter>
<filter-mapping>
    <filter-name>encoding</filter-name>
    <url-pattern>/*</url-pattern>
</filter-mapping>
```

**使用SpringMVC提供的过滤器**
在web.xml中配置 springmvc提供的过滤器
```xml
<!-- 使用SpringMVC提供的乱码过滤器 -->
<filter>
    <filter-name>encoding</filter-name>
    <filter-class>org.springframework.web.filter.CharacterEncodingFilter</filter-class>
</filter>
<filter-mapping>
    <filter-name>encoding</filter-name>
    <url-pattern>/*</url-pattern>
</filter-mapping>
```

# JSON
在JavaScript语言中，一切都是对象。因此，任何JavaScript支持的类型都可以通过JSON来表示，例如字符串，数字，对象，数组等。  
语法要求：  
- 对象表示为键值对，数组由逗号分隔
- 花括号保存对象
- 方括号保存数组

JSON的一些方法：
- 将js对象转为JSON字符串：  
  JSON.stringify(user);
- 将JSON字符串转为js对象：  
  JSON.parse(json);
```html
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <script type="text/javascript">
        //编写一个JavaScript对象
        var user = {
            name:"kindred",
            age:3,
            sex:"女"
        }

        //将js对象转为JSON字符串
        var json = JSON.stringify(user);
        console.log(json);

        console.log("================");
        //将JSON字符串转为js对象
        var user = JSON.parse(json);
        console.log(user);
    </script>
</head>
<body>

</body>
</html>
```

## Controller返回JSON数据
**json解析工具-Jackson**  
引入Jackson的jar包
```xml
<dependency>
    <groupId>com.fasterxml.jackson.core</groupId>
    <artifactId>jackson-databind</artifactId>
    <version>2.12.3</version>
</dependency>
```

**JSON乱码问题代码优化**
在spingmvc配置文件中的 <mvc:annotation-driven>标签中配置
```xml
<!-- JSON乱码问题配置 -->
    <mvc:annotation-driven>
        <mvc:message-converters register-defaults="true">
            <bean class="org.springframework.http.converter.StringHttpMessageConverter">
                <constructor-arg value="UTF-8"></constructor-arg>
            </bean>
            <bean class="org.springframework.http.converter.json.MappingJackson2HttpMessageConverter">
                <property name="objectMapper">
                    <bean class="org.springframework.http.converter.json.Jackson2ObjectMapperFactoryBean">
                        <property name="failOnEmptyBeans" value="false"></property>
                    </bean>
                </property>
            </bean>
        </mvc:message-converters>
    </mvc:annotation-driven>
```

使用
```java
@Controller
//@RestController   后面的方法都只返回字符串不走视图解析器
public class MyController {
    @ResponseBody  //有ResponseBody注解就不会走视图解析器，会直接返回一个字符串
    @RequestMapping(value = "/j3")
    public String json3() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        Date date = new Date();

        //时间格式化方式二:(ObjectMapper)
        mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        mapper.setDateFormat(sdf);
        return mapper.writeValueAsString(sdf.format(date));
        
        // ObjectMapper,时间解析后的默认格式是Timestamp时间戳
        // 时间格式化方式一:(SimpleDateFormat)
        /*SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return mapper.writeValueAsString(sdf.format(date));*/
    }

    @ResponseBody  //有ResponseBody注解就不会走视图解析器，会直接返回一个字符串
    @RequestMapping(value = "/j2")
    public String json2() throws JsonProcessingException {

        //jackson   ObjectMapper
        ObjectMapper mapper = new ObjectMapper();

        List<Champion> champions = new ArrayList<Champion>();

        Champion champion = new Champion(1,"千珏",1500);
        Champion champion2 = new Champion(2,"纳尔",10);
        Champion champion3 = new Champion(3,"妮蔻",15);
        Champion champion4 = new Champion(1,"莉莉娅",150);

        champions.add(champion);
        champions.add(champion2);
        champions.add(champion3);
        champions.add(champion4);

        String s = mapper.writeValueAsString(champions);

        return s;
    }

    @ResponseBody
    @RequestMapping("/j1"/*,produces = "application/json;charset=utf-8"*/)
    public String json1() throws JsonProcessingException {

        //jackson   ObjectMapper
        ObjectMapper mapper = new ObjectMapper();

        List<Champion> champions = new ArrayList<Champion>();

        Champion champion = new Champion(1,"千珏",1500);


        String s = mapper.writeValueAsString(champion);
        //toString返回结果 ：
        // Champion(id=1, name=kindred, age=1500)
        //jackson的ObjectMapper的writeValueAsString方法的结果 ：
        // {"id":1,"name":"kindred","age":1500}
        return s;
    }
}
```

**json解析工具-Fastjson**
导入依赖
```xml
<dependency>
    <groupId>com.alibaba</groupId>
    <artifactId>fastjson</artifactId>
    <version>1.2.76</version>
</dependency>
```
使用
```java
/*
    * JSON字符串 转 Java对象 --> JSON.parseObject(str,Object.class)
    * java对象 转 JSON对象 --> (JSONObject)JSON.toJSON(object)
    * java对象 转 JSON字符串 --> JSON.toJSONString(object)
    * JSON对象 转 java对象 --> JSON.toJavaObject(jsonObject,Object.class)
    *
    * */
@ResponseBody
@RequestMapping("/j5")
public String json5(){
    List<Champion> champions = new ArrayList<Champion>();

    Champion champion = new Champion(1,"千珏",1500);
    Champion champion2 = new Champion(2,"纳尔",10);
    Champion champion3 = new Champion(3,"妮蔻",15);
    Champion champion4 = new Champion(1,"莉莉娅",150);

    champions.add(champion);
    champions.add(champion2);
    champions.add(champion3);
    champions.add(champion4);

    String s = JSON.toJSONString(champions);
    return s;
}
```

## 拦截器
**概述**
SpringMVC的处理器拦截器类似于Servlet开发中的过滤器Filter，用于对处理器进行预处理和后处理。  
过滤器和拦截器的区别：
- 拦截器是AOP思想的具体应用。
- 拦截器只会拦截访问的控制器方法，如果访问的是jsp/html/css/image/js是不会进行拦截的
___
**自定义拦截器**  
步骤：  
1. 编写自定义拦截器类 实现 HandlerInterceptor接口实现preHandle，postHandle，afterCompletion方法
```java
public class MyInterceptor implements HandlerInterceptor {

    //return true：执行下一个拦截器，放行
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("============处理前============");
        return true;
    }

    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("============处理后============");
    }

    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        System.out.println("============清理============");
    }
}
```
2. 配置拦截器
```xml
<!-- 配置拦截器 -->
    <mvc:interceptors>
        <mvc:interceptor>
            <!-- /**包括这个请求下面的所有的请求 -->
            <mvc:mapping path="/**"/>
            <bean class="com.engulf.interceptor.MyInterceptor"></bean>
        </mvc:interceptor>
    </mvc:interceptors>
```
