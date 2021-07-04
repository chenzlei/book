package com.example.book.service;

import com.example.book.bean.Book;
import com.example.book.bean.Page;

import java.sql.Connection;
import java.util.List;

/**
 * @author chenzhilei
 * @date 2021/6/28) 16:22)
 */
public interface BookService {
    public int addBook(Connection conn,Book book);

    public int deleteBook(Connection conn,Integer id);

    public int updateBook(Connection conn,Book book);

    public Book queryOneBook(Connection conn,Integer id);

    public List<Book> queryBooks(Connection conn);

    Page<Book> page(Connection conn,int pageNo, int pageSize);

    Page<Book> pageByPrice(Connection conn,int pageNo, int pageSize, int min, int max);
}
