<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>图书管理</title>
    <%--静态包含写base标签，永远固定相对路径，加载js,css--%>
    <%@include file="/pages/commons/head.jsp"%>
    <script type="text/javascript">
        $(function (){
            $("a.deleteClass").click(function(){
                return confirm("确定要删除【"+ $(this).parent().parent().find("td:first").text() +"】吗");
            });

        })
    </script>
</head>
<body>

<div id="header">
    <img class="logo_img" alt="" src="static/img/logo.gif">
    <span class="wel_word">图书管理系统</span>
    <%--后台菜单管理--%>
    <%@include file="/pages/commons/manages_menu.jsp"%>
</div>

<div id="main">
    <table>
        <tr>
            <td>名称</td>
            <td>价格</td>
            <td>作者</td>
            <td>销量</td>
            <td>库存</td>
            <td colspan="2">操作</td>
        </tr>
        <c:forEach items="${requestScope.page.items}" var="book">
        <tr>
            <td>${book.name}</td>
            <td>${book.price}</td>
            <td>${book.author}</td>
            <td>${book.sales}</td>
            <td>${book.stock}</td>
            <td><a href="manager/book?action=listOneBook&id=${book.id}&pageNo=${requestScope.page.pageNo}">修改</a></td>
            <td><a class="deleteClass" href="manager/book?action=deleteBook&id=${book.id}&pageNo=${requestScope.page.pageNo}">删除</a></td>
        </tr>
        </c:forEach>
        <tr>
            <td></td>
            <td></td>
            <td></td>
            <td></td>
            <td></td>
            <td></td>
            <td><a href="pages/manager/book_edit.jsp?pageNo=${requestScope.page.pageTotal}">添加图书</a></td>
        </tr>
    </table>
<%--    页码开始--%>
    <%@include file="/pages/commons/page_menu.jsp"%>
<%--    页码结束--%>
</div>

<%--页脚，静态包含--%>
<%@include file="/pages/commons/footer.jsp"%>
</body>
</html>