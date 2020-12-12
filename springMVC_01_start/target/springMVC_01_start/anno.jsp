<%--
  Created by IntelliJ IDEA.
  User: Lenovo
  Date: 2020/12/11
  Time: 9:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>SpringMVC常用注解</title>
</head>
<body>
    <%--常用的注解--%>
    <a href="anno/testRequestParam?uname=gnar">RequestParam</a>

    <br>
    <hr>

    <form action="anno/testRequestBody" method="post">
        用户姓名：<input type="text" name="username" /><br>
        用户年龄：<input type="text" name="age" /><br>
        <input type="submit" value="提交">
    </form>

    <br>
    <hr>
    <a href="anno/testPathVariable/1024">testPathVariable</a>

    <br>
    <hr>
    <a href="anno/testRequestHeader">testRequestHeader</a>

    <br>
    <hr>
    <a href="anno/testCookieValue">testCookieValue</a>


    <br>
    <hr>
    <form action="anno/testModelAttribute" method="post">
        用户姓名：<input type="text" name="username" /><br>
        用户年龄：<input type="text" name="age" /><br>
        <input type="submit" value="提交">
    </form>


    <br>
    <hr>
    <a href="anno/testSessionAttributes">testSessionAttributes</a>

    <br>
    <hr>
    <a href="anno/getSessionAttributes">getSessionAttributes</a>

    <br>
    <hr>
    <a href="anno/delSessionAttributes">delSessionAttributes</a>
</body>
</html>
