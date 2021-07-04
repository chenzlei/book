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
 * @date 2021/6/10) 13:06)
 */
@WebServlet(name = "RegistServlet", value = "/registServlet")
public class RegistServlet extends HttpServlet {
    private UserService userService=new UserServiceImpl();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection conn = JDBCUtils.getConnection();
        System.out.println("post 方法");
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String repwd = req.getParameter("repwd");
        String code = req.getParameter("code");
        String email = req.getParameter("email");
        System.out.println(username);
        System.out.println(password);
        System.out.println(email);
        System.out.println(code);
        if("abcde".equalsIgnoreCase(code)){
            if(!userService.existUsername(conn,username)){
                userService.registUser(conn,new User(null,username,password,email));
                req.getRequestDispatcher("pages/user/regist_success.jsp").forward(req,resp);

            }else{
                req.setAttribute("msg","用户名已存在");
                req.setAttribute("username",username);
                req.setAttribute("password",password);
                req.setAttribute("repwd",repwd);
                req.setAttribute("email",email);
                req.setAttribute("code",code);
                System.out.println("用户名["+ username +"]已存在");
                req.getRequestDispatcher("pages/user/regist.jsp").forward(req,resp);

            }
        }else{
            req.setAttribute("msg","验证码错误");
            req.setAttribute("username",username);
            req.setAttribute("password",password);
            req.setAttribute("repwd",repwd);
            req.setAttribute("email",email);
            req.setAttribute("code",code);
            System.out.println("验证码["+ code +"]错误");
            req.getRequestDispatcher("pages/user/regist.jsp").forward(req,resp);
        }
        JDBCUtils.close(conn);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       doPost(req,resp);
    }
}
