<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>$Title$</title>
  </head>
  <body>
    <form action="${pageContext.request.contextPath}/upload2" method="post" enctype="multipart/form-data">
      <input type="file" name="file">
      <input type="submit" value="uploadFile">
    </form>
  <a href="${pageContext.request.contextPath}/download">下载图片</a>
  </body>
</html>
