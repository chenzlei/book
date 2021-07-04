<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>编辑图书</title>
    <%--静态包含写base标签，永远固定相对路径，加载js,css--%>
    <%@include file="/pages/commons/head.jsp"%>
    <style type="text/css">
        h1 {
            text-align: center;
            margin-top: 200px;
        }

        h1 a {
            color: red;
        }

        input {
            text-align: center;
        }
    </style>
</head>
<body>
<div id="header">
    <img class="logo_img" alt="" src="static/img/logo.gif">
    <span class="wel_word">编辑图书</span>
    <%--后台菜单管理--%>
    <%@include file="/pages/commons/manages_menu.jsp"%>
</div>
<div id="main">
    <form action="manager/book" method="post">
        <input id="bool" type="hidden" name="bool"/>
        <input id="action" type="hidden" name="action" value="${empty param.id?"addBook":"updateBook"}"/>
        <input type="hidden" name="id" value="${requestScope.book.id}"/>
        <input type="hidden" name="pageNo" value="${param.pageNo}"/>
        <table>
            <tr>
                <td>名称</td>
                <td>价格</td>
                <td>作者</td>
                <td>销量</td>
                <td>库存</td>
                <td colspan="2">操作</td>
            </tr>
            <tr>
                <td><input id="name" name="name" type="text" value="${requestScope.book.name}"/></td>
                <td><input name="price" type="text" value="${requestScope.book.price}"/></td>
                <td><input name="author" type="text" value="${requestScope.book.author}"/></td>
                <td><input name="sales" type="text" value="${requestScope.book.sales}"/></td>
                <td><input name="stock" type="text" value="${requestScope.book.stock}"/></td>
                <td><input id="sub_bt" type="submit" value="提交"/></td>
            </tr>
        </table>
    </form>
    <script type="text/javascript">
        $(function (){
            $("#sub_bt").click(function (){
                var action = $("#action").val();
                //alert(action);
                if(action=="addBook"){
                    var name = $("#name").val();
                    var bool = confirm("【"+name+"】添加成功，是否继续添加图书信息？");
                    $("#bool").val(bool);
                    //alert(bool)
                    <%--if(bool){--%>
                    <%--    // alert(location.href);--%>
                    <%--    location.href="${pageScope.path}"+"manager/book?action=addBook&bool="+bool;--%>
                    <%--}--%>
                }
            });
        })
    </script>

</div>

<%--页脚，静态包含--%>
<%@include file="/pages/commons/footer.jsp"%>
</body>
</html>