<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/1/10
  Time: 19:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <form action="doregister.do">
        <input type="text" name="username" placeholder="username" id="username" class="a"><span id="message"></span><br>
        <input type="password" name="password" placeholder="password" id="password" class="a"><br>
        <input type="password" name="cpwd" placeholder="Confirm Password" id="cpwd" class="a"><span id="pms"></span><br>
        <input type="text" name="email" placeholder="email" id="email"><br>
        <input type="text" name="lv" placeholder="lv" id="lv"><br>
        <input type="button" id="button" disabled="disabled" value="sgin up"><br>
    </form>
    <script src="js/jquery-3.3.1.min.js"></script>
    <script>
        $(function () {
            var username;
            var password;
            var cpwd;
            var email;
            var lv;
            $(".a").blur(function () {
                username = $("#username").val();
                password = $("#password").val();
                cpwd = $("#cpwd").val();
                email = $("#email").val();
                lv = $("#lv").val();
                $.ajax({
                    url:"doregister.do",
                    type:"get",
                    data:{"username":username,"password":password,"cpwd":cpwd,"email":email ,"lv":lv},
                    success:function (result) {
                        if (result == "1"){
                            $("#message").text("用户名可用");
                            $("#pms").text("密码可用");
                            $("#button").attr("disabled",false);
                        }
                        if (result == "2"){
                            $("#message").text("用户名可用");
                            $("#pms").text("两次密码不一致");
                        }
                        if (result == "3") {
                            $("#message").text("用户名已存在");
                        }
                        if (result == "4" ){
                            $("#message").text("用户名不能为空");
                        }
                        if (result == "5"){
                            $("#pms").text("密码不能为空");
                            $("#message").text("用户名可用");
                        }
                    }
                });
            });

            $("#button").click(function () {
                $.ajax({
                    url:"addregister.do",
                    type:"post",
                    data:{"username":username,"password":password,"cpwd":cpwd,"email":email ,"lv":lv},
                    success:function (data) {
                        if (data == 1){
                            alert("注册成功");
                            window.location.href="http://localhost:8080/ssm01/login.do";
                        }
                    }
                });
            });
        })
    </script>
</body>
</html>
