package com.example.book.test;

import com.example.book.bean.Book;
import com.example.book.bean.Page;
import com.example.book.service.BookService;
import com.example.book.service.impl.BookServiceImpl;
import com.example.book.utils.JDBCUtils;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.sql.Connection;
import java.util.List;

/**
 * @author chenzhilei
 * @date 2021/6/28) 16:29)
 */
class BookServiceImplTest {
    BookService bookService=new BookServiceImpl();
    Connection conn = JDBCUtils.getConnection();

    @Test
    void addBook() {

        int count = bookService.addBook(conn,new Book(null, "金庸", "天龙八部", new BigDecimal(99), 100, 999, null));
        System.out.println("添加了"+count+"本书");
    }

    @Test
    void deleteBook() {
        int count = bookService.deleteBook(conn,23);
        System.out.println("删除了"+count+"本书");
    }

    @Test
    void updateBook() {
        int count=bookService.updateBook(conn,new Book(22, "金庸", "射雕英雄传", new BigDecimal(99), 999, 0, null));
        System.out.println("修改了"+count+"本书");
    }

    @Test
    void queryOneBook() {
        Book book = bookService.queryOneBook(conn,22);
        System.out.println(book);
    }

    @Test
    void queryBooks() {
        List<Book> books = bookService.queryBooks(conn);
        books.forEach(System.out::println);
    }

    @Test
    void page(){
        System.out.println(bookService.pageByPrice(conn,0,4,0,100));
        System.out.println(bookService.page(conn,1, Page.PAGE_SIZE));
    }

    @Test
    void queryForPageItems(){

    }
}