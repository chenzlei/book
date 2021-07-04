package com.example.book.web;

import com.example.book.bean.User;
import com.example.book.service.UserService;
import com.example.book.service.impl.UserServiceImpl;
import com.example.book.utils.JDBCUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;

/**
 * @author chenzhilei
 * @date 2021/6/10) 18:36)
 */
@WebServlet(name = "LoginServlet", value = "/loginServlet")
public class LoginServlet extends HttpServlet {
    private UserService userService=new UserServiceImpl();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection conn = JDBCUtils.getConnection();
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        System.out.println(username);
        System.out.println(password);
        if(userService.login(conn,new User(null,username,password,null))==null){
            req.setAttribute("msg","用户名或者密码错误");
            req.setAttribute("username",username);
            req.getRequestDispatcher("pages/user/login.jsp").forward(req,resp);
            System.out.println("登录失败");
        }else{
            System.out.println("登录成功");
            req.getRequestDispatcher("pages/user/login_success.jsp").forward(req,resp);
        }
        JDBCUtils.close(conn);
    }

}
