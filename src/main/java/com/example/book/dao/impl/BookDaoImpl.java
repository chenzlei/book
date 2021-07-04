package com.example.book.dao.impl;

import com.example.book.bean.Book;
import com.example.book.dao.BookDao;

import java.sql.Connection;
import java.util.List;

/**
 * @author chenzhilei
 * @date 2021/6/28) 14:23)
 */
public class BookDaoImpl extends BaseDao implements BookDao {

    /**
     *添加图书
     * @param book
     * @return
     */
    @Override
    public int addBook(Connection conn,Book book) {
        String sql="insert into t_book(`name`,`author`,`price`,`sales`,`stock`,`img_path`) values(?,?,?,?,?,?)";
        return update(conn,sql,book.getName(),book.getAuthor(),book.getPrice(),book.getSales(),book.getStock(),book.getImgPath());
    }

    @Override
    public int deleteBook(Connection conn,Integer id) {
        String sql="delete from t_book where id=?";
        return update(conn,sql,id);
    }

    @Override
    public int updateBook(Connection conn,Book book) {
        String sql="update t_book set name=?,author=?,price=?,sales=?,stock=?,img_path=? where id=?";
        return update(conn,sql,book.getName(),book.getAuthor(),book.getPrice(),book.getSales(),book.getStock(),book.getImgPath(),book.getId());
    }

    @Override
    public Book queryOneBook(Connection conn,Integer id) {
        String sql="select id,name,author,price,sales,stock,img_path as imgPath from t_book where id=?";
        return queryForOne(conn,Book.class,sql,id);
    }

    @Override
    public List<Book> queryBooks(Connection conn) {
        String sql="select id,name,author,price,sales,stock,img_path as imgPath from t_book";
        return queryForList(conn,Book.class,sql);
    }

    @Override
    public Integer queryForTotalCount(Connection conn) {
        String sql="select count(*) from t_book";
        Number value = getValue(conn,sql);
        return value.intValue();
    }

    @Override
    public List<Book> queryForPageItems(Connection conn,int begin, int pageSize) {
        String sql="select id,name,author,price,sales,stock,img_path as imgPath from t_book limit ?,?";
        List<Book> books = queryForList(conn,Book.class, sql, begin, pageSize);
        return books;
    }

    @Override
    public Integer queryForTotalCount(Connection conn,int min, int max) {
        String sql="select count(*) from t_book where price>=? and price<=?";
        Number value = getValue(conn,sql,min,max);
        return value.intValue();
    }

    @Override
    public List<Book> queryForPageItemsByPrice(Connection conn,int begin, int pageSize, int min, int max) {
        String sql="select id,name,author,price,sales,stock,img_path as imgPath from t_book where price between ? and ? order by price limit ?,?";
        List<Book> books = queryForList(conn,Book.class, sql,min,max,begin, pageSize);
        return books;
    }
}
