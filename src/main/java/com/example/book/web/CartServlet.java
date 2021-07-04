package com.example.book.web;

import com.example.book.bean.Book;
import com.example.book.bean.Cart;
import com.example.book.bean.CartItem;
import com.example.book.service.BookService;
import com.example.book.service.impl.BookServiceImpl;
import com.example.book.utils.JDBCUtils;
import com.example.book.utils.WebUtils;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;

/**
 * @author chenzhilei
 * @date 2021/6/30) 15:06)
 */
@WebServlet(name = "CartServlet",value = "/cart")
public class CartServlet extends BaseServlet{
    private BookService bookService=new BookServiceImpl();

    protected void addItem(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //System.out.println(request.getParameter("id"));
        Connection conn = JDBCUtils.getConnection();
        int id = WebUtils.parseInt(request.getParameter("id"), 0);
        Book book = bookService.queryOneBook(conn,id);
        Cart cart= (Cart) request.getSession().getAttribute("cart");
        if(cart==null){
            cart=new Cart();
            request.getSession().setAttribute("cart",cart);
        }
        cart.addItem(new CartItem(book.getId(), book.getName(), 1,book.getPrice()));
//        System.out.println(cart);
//        System.out.println(request.getHeader("Referer"));
        request.getSession().setAttribute("lastName",book.getName());
        response.sendRedirect(request.getHeader("Referer"));
        //JDBCUtils.close(conn);
    }

    protected void deleteItem(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = WebUtils.parseInt(request.getParameter("id"), 0);
        //System.out.println(id);
        Cart cart= (Cart) request.getSession().getAttribute("cart");
        if(cart!=null){
            cart.deleteItem(id);
        }
        response.sendRedirect(request.getHeader("Referer"));
    }

    protected void updateCount(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = WebUtils.parseInt(request.getParameter("id"), 0);
        int count = WebUtils.parseInt(request.getParameter("count"), 1);
        Cart cart= (Cart) request.getSession().getAttribute("cart");
        if(cart!=null){
            cart.updateCount(id,count);
        }
        response.sendRedirect(request.getHeader("Referer"));
    }

    protected void clear(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Cart cart= (Cart) request.getSession().getAttribute("cart");
        if(cart!=null){
            cart.clear();
        }
        response.sendRedirect(request.getHeader("Referer"));
    }

    protected void ajaxAddItem(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //System.out.println(request.getParameter("id"));
        Connection conn = JDBCUtils.getConnection();
        int id = WebUtils.parseInt(request.getParameter("id"), 0);
        Book book = bookService.queryOneBook(conn,id);
        Cart cart= (Cart) request.getSession().getAttribute("cart");
        if(cart==null){
            cart=new Cart();
            request.getSession().setAttribute("cart",cart);
        }
        cart.addItem(new CartItem(book.getId(), book.getName(), 1,book.getPrice()));
//        System.out.println(cart);
//        System.out.println(request.getHeader("Referer"));
        request.getSession().setAttribute("lastName",book.getName());
        Map<String,Object> map=new HashMap<>();
        map.put("totalCount",cart.getCountTotal());
        map.put("lastName",book.getName());
        Gson gson=new Gson();
        String json = gson.toJson(map);
        response.getWriter().write(json);
        //response.sendRedirect(request.getHeader("Referer"));
        //JDBCUtils.close(conn);
    }
}
