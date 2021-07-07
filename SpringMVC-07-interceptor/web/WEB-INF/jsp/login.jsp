<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

    <%-- 在WEB-INF下的所有页面或者资源，只能通过controller或者servlet进行访问 --%>

    <h1>登录页面</h1>
    <form action="${pageContext.request.contextPath}/login" method="post">
        用户名:<input type="text" name="username" required>
        密 码：<input type="text" name="password" required>
        <input type="submit" value="提交">
    </form>
</body>
</html>
