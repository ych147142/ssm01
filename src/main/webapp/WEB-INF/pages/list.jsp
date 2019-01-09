<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/1/7
  Time: 18:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <form >
        <input type="text" name="username"><br>
        <input type="submit">
    </form>
    <table>
        <thead>
            <tr>
                <th>员工编号</th>
                <th>员工名称</th>
                <th>密码</th>
                <th>邮箱</th>
                <th>等级</th>
                <th>操作</th>
            </tr>
        </thead>
        <tbody>
        <c:forEach items="${lists}" var="u">
            <tr>
                <td>${u.id}</td>
                <td>${u.username}</td>
                <td>${u.password}</td>
                <td>${u.email}</td>
                <td>${u.lv}</td>
                <td><a href="delete.do?id=${u.id}">删除</a>|<a href="update.do?id=${u.id}">修改</a></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <form action="add.do" method="post">
        <input type="submit" value="添加">
    </form>
    <form action="exit.do" method="post">
        <input type="submit" value="退出">
    </form>


    <form action="doUpload.do" method="post" enctype="multipart/form-data">
        <input type="file" name="files">
        <input type="file" name="files">
        <input type="file" name="files">
        <input type="file" name="files">
        <input type="submit">
    </form>
    <ul>
    <c:choose>
        <c:when test="${page.pages > 0}">
            <c:choose>
                <c:when test="${page.isFirstPage}">
                    <li>首页</li>
                    <li>上一页</li>
                </c:when>
                <c:otherwise>
                    <li><a href="list.do?pageNum=${page.navigateFirstPage}${username}">首页</a></li>
                    <li><a href="list.do?pageNum=${page.prePage}${username}">上一页</a></li>
                </c:otherwise>

            </c:choose>
            <c:forEach items="${page.navigatepageNums}" var="i">
                <c:choose>
                    <c:when test="${page.pageNum==i}">
                        <li>${i}</li>
                    </c:when>
                    <c:otherwise>
                        <li><a href="list.do?pageNum=${i}${username}">${i}</a></li>
                    </c:otherwise>
                </c:choose>
            </c:forEach>
            <c:choose>
                <c:when test="${page.isLastPage}">
                    <li>下一页</li>
                    <li>尾页</li>
                </c:when>
                <c:otherwise>
                    <li><a href="list.do?pageNum=${page.nextPage}${username}">下一页</a></li>
                    <li><a href="list.do?pageNum=${page.navigateLastPage}${username}">尾页</a></li>
                </c:otherwise>

            </c:choose>
        </c:when>
    </c:choose>
    </ul>
</body>
</html>

