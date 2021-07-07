<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    首页,哈哈 ${sessionScope.userLoginInfo}
    <a href="${pageContext.request.contextPath}/logout">注销</a>
</body>
</html>
