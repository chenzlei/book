<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>结算页面</title>
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
    <span class="wel_word">结算</span>
    <%--    静态包含--%>
    <%@include file="/pages/commons/login_success_menu.jsp"%>
</div>

<div id="main">

    <h1>你的订单已结算，订单号为${sessionScope.orderId}</h1>


</div>

<%--页脚，静态包含--%>
<%@include file="/pages/commons/footer.jsp"%>
</body>
</html>