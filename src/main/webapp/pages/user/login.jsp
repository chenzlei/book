<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>尚硅谷会员登录页面</title>
    <%--静态包含写base标签，永远固定相对路径，加载js,css--%>
    <%@include file="/pages/commons/head.jsp"%>
    <script type="text/javascript">
        $(function(){
            $("#sub_btn").click(function (){
                var username = $("#username").val();
                var password = $("#password").val();
                // alert(username);
                // alert(password);
                if($("#username").val()==""){
                    $("span.errorMsg").text("用户名不能为空");
                    return false;
                }
                if($("#password").val()==""){
                    $("span.errorMsg").text("密码不能为空");
                    return false;
                }
            })
        })

    </script>
</head>
<body>
<div id="login_header">
    <img class="logo_img" alt="" src="static/img/logo.gif">
</div>

<div class="login_banner">

    <div id="l_content">
        <span class="login_word">欢迎登录</span>
    </div>

    <div id="content">
        <div class="login_form">
            <div class="login_box">
                <div class="tit">
                    <h1>尚硅谷会员</h1>
                    <a href="pages/user/regist.jsp
">立即注册</a>
                </div>
                <div class="msg_cont">
                    <b></b>
                    <span class="errorMsg">${msg==null?"请输入用户名":msg}</span>
                </div>
                <div class="form">
                    <form action="user" method="post">
                        <input type="hidden" name="action" value="login"/>
                        <label>用户名称：</label>
                        <input class="itxt" type="text" placeholder="请输入用户名" autocomplete="off" tabindex="1"
                               name="username" id="username" value="${requestScope.username}"/>
                        <br/>
                        <br/>
                        <label>用户密码：</label>
                        <input class="itxt" type="password" placeholder="请输入密码" autocomplete="off" tabindex="1"
                               name="password" id="password"/>
                        <br/>
                        <br/>
                        <input type="submit" value="登录" id="sub_btn"/>
                    </form>
                </div>

            </div>
        </div>
    </div>
</div>
<%--页脚，静态包含--%>
<%@include file="/pages/commons/footer.jsp"%>
</body>
</html>