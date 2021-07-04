package com.example.book.web;

import com.example.book.bean.User;
import com.example.book.service.BookService;
import com.example.book.service.UserService;
import com.example.book.service.impl.UserServiceImpl;
import com.example.book.utils.JDBCUtils;
import com.example.book.utils.WebUtils;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;

import static com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY;

/**
 * @author chenzhilei
 * @date 2021/6/26) 17:07)
 */
@WebServlet(name = "UserServlet",value = "/user")
public class UserServlet extends BaseServlet {
    private UserService userService=new UserServiceImpl();

    protected void login(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection conn = JDBCUtils.getConnection();
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        System.out.println(username);
        System.out.println(password);
        User userPara = WebUtils.copyParamToBean(req.getParameterMap(), new User());
        User user=userService.login(conn,userPara);
        if(user==null){
            req.setAttribute("msg","用户名或者密码错误");
            req.setAttribute("username",username);
            req.getRequestDispatcher("pages/user/login.jsp").forward(req,resp);
            System.out.println("登录失败");
        }else{
            System.out.println("登录成功");
            Cookie cookie = WebUtils.findCookie("JSESSIONID", req.getCookies());
            System.out.println(cookie.getName());
            System.out.println(cookie.getMaxAge());
            cookie.setMaxAge(1200);
            resp.addCookie(cookie);
            System.out.println(cookie.getMaxAge());
            req.getSession().setAttribute("user",user);
            req.getRequestDispatcher("pages/user/login_success.jsp").forward(req,resp);
        }
        //JDBCUtils.close(conn);
    }

    protected void ajaxExistsUsername(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection conn = JDBCUtils.getConnection();
        String username = req.getParameter("username");
        boolean existsUserName = userService.existUsername(conn, username);
        Map<String,Object> map=new HashMap<>();
        map.put("existsUsername",existsUserName);
        Gson gson=new Gson();
        String json = gson.toJson(map);
        resp.getWriter().write(json);
        //JDBCUtils.close(conn);
    }

    protected void logout(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getSession().invalidate();
        resp.sendRedirect(req.getContextPath());
    }

    protected void regist(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection conn = JDBCUtils.getConnection();
        System.out.println("post 方法");
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String repwd = req.getParameter("repwd");
        String token = (String) req.getSession().getAttribute(KAPTCHA_SESSION_KEY);
        req.getSession().removeAttribute(KAPTCHA_SESSION_KEY);
        String code = req.getParameter("code");
        String email = req.getParameter("email");
        System.out.println(username);
        System.out.println(password);
        System.out.println(email);
        System.out.println(code);
        User user = WebUtils.copyParamToBean(req.getParameterMap(), new User());
        if(token!=null&&token.equalsIgnoreCase(code)){
            if(!userService.existUsername(conn,username)){
                userService.registUser(conn,user);
                req.getSession().setAttribute("user",user);
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
        //JDBCUtils.close(conn);
    }
}
