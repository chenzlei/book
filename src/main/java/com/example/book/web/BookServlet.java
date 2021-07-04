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
import java.util.List;

/**
 * @author chenzhilei
 * @date 2021/6/28) 16:40)
 */
@WebServlet(name = "BookServlet",value = "/manager/book")
public class BookServlet extends BaseServlet {
    private BookService bookService=new BookServiceImpl();

    protected void addBook(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Connection conn = JDBCUtils.getConnection();
        int pageNo = WebUtils.parseInt(request.getParameter("pageNo"), 0);
        pageNo++;
        System.out.println(response.getCharacterEncoding());
        Book book = WebUtils.copyParamToBean(request.getParameterMap(), new Book());
        bookService.addBook(conn,book);
        String bool = request.getParameter("bool");
        if("true".equalsIgnoreCase(bool)){
            response.sendRedirect(request.getContextPath()+"/pages/manager/book_edit.jsp?pageNo="+pageNo);
        }else{
            response.sendRedirect(request.getContextPath()+"/manager/book?action=page&pageNo="+pageNo);
        }
        //JDBCUtils.close(conn);
    }

    protected void deleteBook(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Connection conn = JDBCUtils.getConnection();
        int pageNo = WebUtils.parseInt(request.getParameter("pageNo"), 1);
        String id = request.getParameter("id");
        int i = WebUtils.parseInt(id,0);
        bookService.deleteBook(conn,i);
        response.sendRedirect(request.getContextPath()+"/manager/book?action=page&pageNo="+pageNo);
        //JDBCUtils.close(conn);
    }

    protected void updateBook(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Connection conn = JDBCUtils.getConnection();
        Book book = WebUtils.copyParamToBean(request.getParameterMap(), new Book());
        bookService.updateBook(conn,book);
        response.sendRedirect(request.getContextPath()+"/manager/book?action=page&pageNo="+request.getParameter("pageNo"));
        //JDBCUtils.close(conn);
    }

    protected void listBook(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Connection conn = JDBCUtils.getConnection();
        List<Book> books = bookService.queryBooks(conn);
        request.setAttribute("books",books);
        request.getRequestDispatcher("/pages/manager/book_manager.jsp").forward(request,response);
        //JDBCUtils.close(conn);
    }

    protected void listOneBook(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Connection conn = JDBCUtils.getConnection();
        String id = request.getParameter("id");
        int i = WebUtils.parseInt(id,0);
        Book book = bookService.queryOneBook(conn,i);
        request.setAttribute("book",book);
        request.getRequestDispatcher("/pages/manager/book_edit.jsp").forward(request,response);
        //JDBCUtils.close(conn);
    }

    /**
     * 处理分页功能
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void page(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Connection conn = JDBCUtils.getConnection();
        int pageNo = WebUtils.parseInt(request.getParameter("pageNo"), 1);
        int pageSize = WebUtils.parseInt(request.getParameter("pageSize"), Page.PAGE_SIZE);
        Page<Book> page=bookService.page(conn,pageNo,pageSize);
        page.setUrl("manager/book?action=page");
        request.setAttribute("page",page);
        request.getRequestDispatcher("/pages/manager/book_manager.jsp").forward(request,response);
        //JDBCUtils.close(conn);
    }
}
