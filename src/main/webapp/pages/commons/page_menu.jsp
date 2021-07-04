<%--
  Created by IntelliJ IDEA.
  User: Chenzlei
  Date: 2021/6/29
  Time: 12:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

</body>
</html><%--    页码开始--%>
<div id="page_nav">
    <c:if test="${requestScope.page.pageNo>1}">
        <a href="${requestScope.page.url}&pageNo=1">首页</a>
        <a href="${requestScope.page.url}&pageNo=${requestScope.page.pageNo-1}">上一页</a>
    </c:if>
    <c:choose>
        <c:when test="${requestScope.page.pageTotal<=7}">
            <c:set var="begin" value="1"></c:set>
            <c:set var="end" value="${requestScope.page.pageTotal}"></c:set>
        </c:when>
        <c:when test="${requestScope.page.pageNo<4}">
            <c:set var="begin" value="1"></c:set>
            <c:set var="end" value="7"></c:set>
        </c:when>
        <c:when test="${requestScope.page.pageNo>requestScope.page.pageTotal-4}">
            <c:set var="begin" value="${requestScope.page.pageTotal-6}"></c:set>
            <c:set var="end" value="${requestScope.page.pageTotal}"></c:set>
        </c:when>
        <c:otherwise>
            <c:set var="begin" value="${requestScope.page.pageNo-3}"></c:set>
            <c:set var="end" value="${requestScope.page.pageNo+3}"></c:set>
        </c:otherwise>
    </c:choose>
    <c:forEach begin="${begin}" end="${end}" var="i">
        <c:if test="${i==requestScope.page.pageNo}">
            【${i}】
        </c:if>
        <c:if test="${i!=requestScope.page.pageNo}">
            <a href="${requestScope.page.url}&pageNo=${i}">${i}</a>
        </c:if>
    </c:forEach>
    <c:if test="${requestScope.page.pageNo<requestScope.page.pageTotal}">
        <a href="${requestScope.page.url}&pageNo=${requestScope.page.pageNo+1}">下一页</a>
        <a href="${requestScope.page.url}&pageNo=${requestScope.page.pageTotal}">末页</a>
    </c:if>
    共${requestScope.page.pageTotal}页，
    ${requestScope.page.pageTotalCount}条记录
    到第<input value="${param.pageNo}" name="pn" id="pn_input"/>页
    <input type="button" id="searchPageBtn" value="确定">
    <script type="text/javascript">
        $(function (){
            $("#searchPageBtn").click(function (){
                var pageNo = $("#pn_input").val();
                if(pageNo<1||pageNo>${requestScope.page.pageTotal}){
                    alert("页码【"+pageNo+"】不合法")
                    return false;
                }
                // alert(location.href);
                location.href="${pageScope.path}"+"${requestScope.page.url}&pageNo="+pageNo;
            });
        })
    </script>
</div>
<%--    页码结束--%>
