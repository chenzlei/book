<%--
  Created by IntelliJ IDEA.
  User: Chenzlei
  Date: 2021/7/4
  Time: 14:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@include file="/pages/commons/head.jsp"%>
    <title>资源找不到</title>
</head>
<body>
<div id="header">
    <img class="logo_img" alt="" src="static/img/logo.gif">
    <%--  静态包含登录成功后的菜单  --%>
    <%@include file="/pages/commons/login_success_menu.jsp"  %>
</div>
<div id="main">
    <h1 href="index.jsp">很抱歉，您访问的后台程序出现了问题，程序员小哥正在加紧抢修</h1>
    <a href="index.jsp">返回首页</a>
</div>
</body>
</html>
