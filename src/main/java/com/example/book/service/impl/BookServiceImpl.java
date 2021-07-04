package com.example.book.service.impl;

import com.example.book.bean.Book;
import com.example.book.bean.Page;
import com.example.book.dao.BookDao;
import com.example.book.dao.impl.BookDaoImpl;
import com.example.book.service.BookService;

import java.sql.Connection;
import java.util.List;

/**
 * @author chenzhilei
 * @date 2021/6/28) 16:23)
 */
public class BookServiceImpl  implements BookService {
    private BookDao bookDao=new BookDaoImpl();

    @Override
    public int addBook(Connection conn,Book book) {
        return bookDao.addBook(conn, book);
    }

    @Override
    public int deleteBook(Connection conn,Integer id) {
        return bookDao.deleteBook(conn, id);
    }

    @Override
    public int updateBook(Connection conn,Book book) {
        return bookDao.updateBook(conn, book);
    }

    @Override
    public Book queryOneBook(Connection conn,Integer id) {
        return bookDao.queryOneBook(conn, id);
    }

    @Override
    public List<Book> queryBooks(Connection conn) {
        return bookDao.queryBooks(conn);
    }

    @Override
    public Page<Book> page(Connection conn,int pageNo, int pageSize) {
        Page<Book> page=new Page<>();
        page.setPageSize(pageSize);
        Integer pageTotalCount =bookDao.queryForTotalCount(conn);
        page.setPageTotalCount(pageTotalCount);
        Integer pageTotal=pageTotalCount/pageSize;
        if(pageTotalCount%pageSize>0){
            pageTotal++;
        }
        page.setPageTotal(pageTotal);
        page.setPageNo(pageNo);
        int begin= (page.getPageNo()-1)*pageSize;
        List<Book> items=bookDao.queryForPageItems(conn,begin,pageSize);
        page.setItems(items);
        return page;
    }

    @Override
    public Page<Book> pageByPrice(Connection conn,int pageNo, int pageSize, int min, int max) {
        Page<Book> page=new Page<>();
        page.setPageSize(pageSize);
        Integer pageTotalCount =bookDao.queryForTotalCount(conn,min,max);
        page.setPageTotalCount(pageTotalCount);
        Integer pageTotal=pageTotalCount/pageSize;
        if(pageTotalCount%pageSize>0){
            pageTotal++;
        }
        page.setPageTotal(pageTotal);
        page.setPageNo(pageNo);
        int begin= (page.getPageNo()-1)*pageSize;
        List<Book> items=bookDao.queryForPageItemsByPrice(conn,begin,pageSize,min,max);
        page.setItems(items);
        return page;
    }
}
