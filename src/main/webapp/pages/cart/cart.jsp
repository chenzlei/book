<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>购物车</title>
    <%--静态包含写base标签，永远固定相对路径，加载js,css--%>
    <style type="text/css">
        input {
            text-align: center;
            width: 80px;
        }
    </style>
    <%@include file="/pages/commons/head.jsp" %>
</head>
<body>

<div id="header">
    <img class="logo_img" alt="" src="static/img/logo.gif">
    <span class="wel_word">购物车</span>
    <%--    静态包含--%>
    <%@include file="/pages/commons/login_success_menu.jsp" %>
    <script type="text/javascript">
        $(function (){
            $(".updateCount").change(function (){
                var id = $(this).attr("bookId");
                var count = this.value;
                var name = $(this).parent().parent().find("td:first").text();
                if(confirm("你确定要将【"+name +"】的数量更新为"+count+"吗")){
                    location.href="${pageScope.path}cart?action=updateCount&count="+count+"&id="+id;
                }else{
                    this.value=this.defaultValue;
                }
            });

            $("a.deleteCartClass").click(function (){
                var name = $(this).parent().parent().find("td:first").text();
                return confirm("你确认要删除购物车中的【"+name +"】吗");
            });
        })
    </script>
</div>

<div id="main">

    <table>
        <tr>
            <td>商品名称</td>
            <td>数量</td>
            <td>单价</td>
            <td>金额</td>
            <td>操作</td>
        </tr>
        <c:if test="${empty sessionScope.cart.items}">
            <%--如果购物车空的情况--%>
            <tr>
                <td colspan="5"><a href="index.jsp">亲，当前购物车为空！快跟小伙伴们去浏览商品吧！！！</a> </td>
            </tr>
        </c:if>
        <c:forEach items="${sessionScope.cart.items}" var="item">
        <tr>
            <td>${item.value.name}</td>
            <td >
                <input bookId="${item.value.id}" type="text" class="updateCount" value="${item.value.count}" />
            </td>
            <td>${item.value.price}</td>
            <td>${item.value.totalPrice}</td>
            <td><a  class="deleteCartClass" href="cart?action=deleteItem&id=${item.value.id}">删除</a></td>
        </tr>
        </c:forEach>
    </table>
    <c:if test="${ not empty sessionScope.cart.items}">
        <div class="cart_info">
            <span class="cart_span">购物车中共有<span class="b_count">${sessionScope.cart.countTotal}</span>件商品</span>
            <span class="cart_span">总金额<span class="b_price">${sessionScope.cart.priceTotal}</span>元</span>
            <span class="cart_span"><a href="cart?action=clear">清空购物车</a></span>
            <span class="cart_span"><a href="order?action=createOrder">去结账</a></span>
        </div>
    </c:if>
</div>
<%--页脚，静态包含--%>
<%@include file="/pages/commons/footer.jsp"%>
</body>
</html>