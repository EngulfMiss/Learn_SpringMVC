<%--
  Created by IntelliJ IDEA.
  User: Lenovo
  Date: 2020/12/10
  Time: 16:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>请求参数的绑定</title>
</head>
<body>
    <%--<a href="param/testParam?username=gnar&password=123">请求参数绑定</a>--%>
    <%--
    把数据封装到Champion类当中
    <form action="param/saveChampion" method="post">
        名字：<input type="text" name="name" /><br>
        密码：<input type="text" name="psw" /><br>
        金额：<input type="text" name="gold" /><br>
        用户姓名：<input type="text" name="user.username" /><br>
        用户年龄：<input type="text" name="user.age" /><br>
        <input type="submit" value="提交">
    </form>
     --%>

    <%--  把数据封装Champion类中，类存在List和Map集合  --%>
    <%--
    <form action="param/saveChampion" method="post">
        名字：<input type="text" name="name" /><br>
        密码：<input type="text" name="psw" /><br>
        金额：<input type="text" name="gold" /><br>

        用户姓名：<input type="text" name="list[0].username" /><br>
        用户年龄：<input type="text" name="list[0].age" /><br>

        用户姓名：<input type="text" name="map['one'].username" /><br>
        用户年龄：<input type="text" name="map['one'].age" /><br>
        <input type="submit" value="提交">
    </form>
    --%>

    <%--自定义类型转换器--%>
    <%--
    <form action="param/saveUser" method="post">
        用户姓名：<input type="text" name="username" /><br>
        用户年龄：<input type="text" name="age" /><br>
        用户生日：<input type="text" name="date" /><br>
        <input type="submit" value="提交">
    </form>
    --%>


    <a href="param/testServlet">获取原生ServletAPI</a>
</body>
</html>
