<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>后台管理</title>
    <%--静态包含写base标签，永远固定相对路径，加载js,css--%>
    <%@include file="/pages/commons/head.jsp"%>
    <style type="text/css">
        h1 {
            text-align: center;
            margin-top: 200px;
        }
    </style>
</head>
<body>

<div id="header">
    <img class="logo_img" alt="" src="static/img/logo.gif">
    <span class="wel_word">后台管理系统</span>
    <%--后台菜单管理--%>
    <%@include file="/pages/commons/manages_menu.jsp"%>
</div>

<div id="main">
    <h1>欢迎管理员进入后台管理系统</h1>
</div>

<%--页脚，静态包含--%>
<%@include file="/pages/commons/footer.jsp"%>
</body>
</html>