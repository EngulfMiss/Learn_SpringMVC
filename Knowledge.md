# 编写第一个SpringMVC程序
** 步骤 **
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
5.编写处理器(Controller)
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
6.在springMVC配置文件中配置处理器bean

### 在SpringMVC中，/ 和 /* 的区别
- /：只匹配所有的请求，不会去匹配jsp页面
- /*：匹配所有的请求，包括jsp页面      可能会出现 xxx.jsp.jsp.jsp
