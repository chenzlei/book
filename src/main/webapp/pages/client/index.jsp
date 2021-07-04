<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>书城首页</title>
    <%--静态包含写base标签，永远固定相对路径，加载js,css--%>
    <%@include file="/pages/commons/head.jsp"%>
    <script type="text/javascript">
        $(function (){
            $("button.addToCart").click(function (){
                var id = $(this).attr("bookId");
                $.getJSON("${pageScope.path}cart","action=ajaxAddItem&id="+id,function (data) {
                        $("#cartCountTotal").text("您的购物车中有"+data.totalCount +"件商品");
                        $("#cartLastName").text(data.lastName);
                    });
                //location.href="${path}cart?action=addItem&id="+id;
            });
        })
    </script>
</head>
<body>

<div id="header">
    <img class="logo_img" alt="" src="static/img/logo.gif">
    <span class="wel_word">网上书城</span>
    <div>
        <c:if test="${empty sessionScope.user.username}">
            <a href="pages/user/login.jsp">登录</a> |
            <a href="pages/user/regist.jsp">注册</a>
        </c:if>
        <c:if test="${not empty sessionScope.user.username}">
            <span>欢迎<span class="um_span">${sessionScope.user.username}</span>光临尚硅谷书城</span>
            <a href="pages/order/order.jsp">我的订单</a>
            <a href="user?action=logout">注销</a>
        </c:if>
        <a href="pages/cart/cart.jsp">购物车</a>
        <a href="pages/manager/manager.jsp">后台管理</a>
    </div>
</div>
<div id="main">
    <div id="book">
        <div class="book_cond">
            <form action="client/book" method="get">
                <input type="hidden" name="action" value="pageByPrice"/>
                价格：<input id="min" type="text" name="min" value="${param.min}"> 元 -
                <input id="max" type="text" name="max" value="${param.max}"> 元
                <input type="submit" value="查询"/>
            </form>
        </div>
        <c:if test="${not empty sessionScope.cart.items}">
            <div style="text-align: center">
                <span id="cartCountTotal">您的购物车中有${sessionScope.cart.countTotal}件商品</span>
                <div>
                    您刚刚将<span id="cartLastName" style="color: red">${sessionScope.lastName}</span>加入到了购物车中
                </div>
            </div>
        </c:if>
        <c:if test="${empty sessionScope.cart.items}">
        <div style="text-align: center">
            <span id="cartCountTotal"></span>
            <div>
                <span id="cartLastName" style="color: red">购物车中当前无商品</span>
            </div>
        </div>
        </c:if>
        <c:forEach items="${requestScope.page.items}" var="book">
            <div class="b_list">
            <div class="img_div">
                <img class="book_img" alt="" src="static/img/default.jpg"/>
            </div>
            <div class="book_info">
                <div class="book_name">
                    <span class="sp1">书名:</span>
                    <span class="sp2">${book.name}</span>
                </div>
                <div class="book_author">
                    <span class="sp1">作者:</span>
                    <span class="sp2">${book.author}</span>
                </div>
                <div class="book_price">
                    <span class="sp1">价格:</span>
                    <span class="sp2">${book.price}</span>
                </div>
                <div class="book_sales">
                    <span class="sp1">销量:</span>
                    <span class="sp2">${book.sales}</span>
                </div>
                <div class="book_amount">
                    <span class="sp1">库存:</span>
                    <span class="sp2">${book.stock}</span>
                </div>
                <div class="book_add">
                    <button bookId="${book.id}" class="addToCart">加入购物车</button>
                </div>
            </div>
        </div>
        </c:forEach>

    </div>

    <%--    页码开始--%>
    <%@include file="/pages/commons/page_menu.jsp"%>
    <%--    页码结束--%>

</div>

<%--页脚，静态包含--%>
<%@include file="/pages/commons/footer.jsp"%>
</body>
</html>