package com.example.book.dao;

import com.example.book.bean.Book;

import java.sql.Connection;
import java.util.List;

/**
 * @author chenzhilei
 * @date 2021/6/28) 14:18)
 */
public interface BookDao {
    public int addBook(Connection conn, Book book);

    public int deleteBook(Connection conn,Integer id);

    public int updateBook(Connection conn,Book book);

    public Book queryOneBook(Connection conn,Integer id);

    public List<Book> queryBooks(Connection conn);

    Integer queryForTotalCount(Connection conn);

    List<Book> queryForPageItems(Connection conn,int begin, int pageSize);

    Integer queryForTotalCount(Connection conn,int min, int max);

    List<Book> queryForPageItemsByPrice(Connection conn,int begin, int pageSize, int min, int max);
}
