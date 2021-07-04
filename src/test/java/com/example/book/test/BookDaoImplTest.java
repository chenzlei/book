package com.example.book.test;

import com.example.book.bean.Book;
import com.example.book.bean.Page;
import com.example.book.dao.BookDao;
import com.example.book.dao.impl.BookDaoImpl;
import com.example.book.utils.JDBCUtils;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.sql.Connection;
import java.util.List;

/**
 * @author chenzhilei
 * @date 2021/6/28) 15:20)
 */
class BookDaoImplTest {
    BookDao bookDao=new BookDaoImpl();
    Connection conn = JDBCUtils.getConnection();

    @Test
    void addBook() {

        int count = bookDao.addBook(conn,new Book(null, "金庸", "三侠五义", new BigDecimal(99), 100, 999, null));
        System.out.println("添加了"+count+"本书");
    }

    @Test
    void deleteBook() {
        int count = bookDao.deleteBook(conn,21);
        System.out.println("删除了"+count+"本书");
    }

    @Test
    void updateBook() {
        int count=bookDao.updateBook(conn,new Book(22, "金庸", "神雕侠侣", new BigDecimal(99), 999, 0, null));
        System.out.println("修改了"+count+"本书");
    }

    @Test
    void queryOneBook() {
        Book book = bookDao.queryOneBook(conn,22);
        System.out.println(book);
    }

    @Test
    void queryBooks() {
        List<Book> books = bookDao.queryBooks(conn);
        books.forEach(System.out::println);
        //System.out.println(books);
    }

    @Test
    void queryForTotalCount(){
        System.out.println(bookDao.queryForTotalCount(conn));
        System.out.println(bookDao.queryForTotalCount(conn,90,100));
    }

    @Test
    void queryForPageItems(){
        bookDao.queryForPageItemsByPrice(conn,0, Page.PAGE_SIZE,0,100).forEach(System.out::println);
        // bookDao.queryForPageItems(0,4,0,100).forEach(System.out::println);
        //bookDao.queryForPageItems(4, Page.PAGE_SIZE).forEach(System.out::println);
    }
}