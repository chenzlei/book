<%--
  Created by IntelliJ IDEA.
  User: Chenzlei
  Date: 2021/6/26
  Time: 15:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path=request.getScheme()
            +"://"+request.getServerName()
            +":"+request.getServerPort()
            +request.getContextPath()
            +"/";
    //request.setAttribute("path",path);
    pageContext.setAttribute("path",path);
%>
<%--写base标签，永远固定相对路径，加载js,css--%>
<base href="${path}">
<link type="text/css" rel="stylesheet" href="static/css/style.css">
<script type="text/javascript" src="static/script/jquery-1.7.2.js"></script>