package com.example.book.test;

import com.example.book.dao.UserDao;
import com.example.book.dao.impl.UserDaoImpl;
import com.example.book.utils.JDBCUtils;
import org.junit.jupiter.api.Test;

import java.sql.Connection;

/**
 * @author chenzhilei
 * @date 2021/6/10) 16:50)
 */
class BaseDaoTest {
    private UserDao userDao=new UserDaoImpl();
    private Connection conn= JDBCUtils.getConnection();

    @Test
    void getValue() {
        String sql = "select count(1) from t_user";
        Object value = userDao.getCount(conn,sql);
        System.out.println(value);
    }
}