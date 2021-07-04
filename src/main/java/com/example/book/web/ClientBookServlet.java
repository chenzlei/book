package com.example.book.web;

import com.example.book.bean.Book;
import com.example.book.bean.Page;
import com.example.book.service.BookService;
import com.example.book.service.impl.BookServiceImpl;
import com.example.book.utils.JDBCUtils;
import com.example.book.utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;

/**
 * @author chenzhilei
 * @date 2021/6/29) 12:30)
 */
@WebServlet(name = "ClientBookServlet",value = "/client/book")
public class ClientBookServlet extends BaseServlet {
    private BookService bookService=new BookServiceImpl();

    protected void page(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Connection conn = JDBCUtils.getConnection();
        int pageNo = WebUtils.parseInt(request.getParameter("pageNo"), 1);
        int pageSize = WebUtils.parseInt(request.getParameter("pageSize"), Page.PAGE_SIZE);
        Page<Book> page=bookService.page(conn,pageNo,pageSize);
        page.setUrl("client/book?action=page");
        request.setAttribute("page",page);
        request.getRequestDispatcher("/pages/client/index.jsp").forward(request,response);
        //JDBCUtils.close(conn);
    }

    protected void pageByPrice(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Connection conn = JDBCUtils.getConnection();
        int pageNo = WebUtils.parseInt(request.getParameter("pageNo"), 1);
        int pageSize = WebUtils.parseInt(request.getParameter("pageSize"), Page.PAGE_SIZE);
        int min=WebUtils.parseInt(request.getParameter("min"),0);
        int max=WebUtils.parseInt(request.getParameter("max"),Integer.MAX_VALUE);
        Page<Book> page=bookService.pageByPrice(conn,pageNo,pageSize,min,max);
        StringBuilder sb=new StringBuilder("client/book?action=pageByPrice");
        if(request.getParameter("min")!=null){
            sb.append("&min=").append(request.getParameter("min"));
        }
        if(request.getParameter("max")!=null){
            sb.append("&max=").append(request.getParameter("max"));
        }
        page.setUrl(sb.toString());
        request.setAttribute("page",page);
        request.getRequestDispatcher("/pages/client/index.jsp").forward(request,response);
        //JDBCUtils.close(conn);
    }
}
