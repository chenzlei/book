package com.example.book.web;

import com.example.book.bean.Cart;
import com.example.book.bean.User;
import com.example.book.service.OrderService;
import com.example.book.service.impl.OrderServiceImpl;
import com.example.book.utils.JDBCUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;

/**
 * @author chenzhilei
 * @date 2021/7/1) 18:46)
 */
@WebServlet(name = "OrderServlet",value = "/order")
public class OrderServlet extends BaseServlet {
    OrderService orderService=new OrderServiceImpl();

    protected void createOrder(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Connection conn = JDBCUtils.getConnection();
        Cart cart = (Cart) request.getSession().getAttribute("cart");
        User user= (User) request.getSession().getAttribute("user");
        if(user==null){
            response.sendRedirect(request.getContextPath()+"/pages/user/login.jsp");
            return;
        }
        if(cart==null){
            response.sendRedirect(request.getHeader("Referer"));
            return;
        }
        String orderId = orderService.CreateOrder(conn,cart, user.getId());
        request.getSession().setAttribute("orderId",orderId);
        //request.getRequestDispatcher("/pages/cart/checkout.jsp").forward(request,response);
        request.getSession().setAttribute("orderId",orderId);
        response.sendRedirect(request.getContextPath()+"/pages/cart/checkout.jsp");
        //JDBCUtils.close(conn);
    }

    protected void showAllOrders(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void sendOrder(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void showOrderDetails(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void showMyOrders(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void receiveOrder(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
