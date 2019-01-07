<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/1/7
  Time: 20:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="doupdate.do" method="post">
    <input type="text" readonly="readonly" name="id" value="${u.id}">
    <input type="text" name="username" value="${u.username}">
    <input type="text" name="password" value="${u.password}">
    <input type="text" name="email" value="${u.email}">
    <input type="text" name="lv" value="${u.lv}">
    <input type="submit" value="修改">

</form>
</body>
</html>

