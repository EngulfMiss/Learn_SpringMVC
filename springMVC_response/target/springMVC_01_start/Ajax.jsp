<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>传送Ajax请求</title>
    <script src="js/jquery.min.js"></script>
    <script>
        $(function () {
            $("#btn").click(function () {
                // alert("Hello World");
                //发送ajax请求
                $.ajax({
                    url:"user/testAjax",
                    contentType:"application/json;charset=UTF-8",
                    data:'{"username":"Gnar","password":"52snowgnar","age":8}',
                    dataType:"json",
                    type:"post",
                    success:function (data) {
                        //data是服务器端响应的json数据，进行解析
                        alert(data);
                        alert(data.username);
                        alert(data.password);
                        alert(data.age);
                    }
                });
            });
        });
    </script>
</head>
<body>
    <button id="btn">发送ajax的请求</button>
</body>
</html>
