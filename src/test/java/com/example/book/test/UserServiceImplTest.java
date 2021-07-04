package com.example.book.test;

import com.example.book.bean.User;
import com.example.book.service.UserService;
import com.example.book.service.impl.UserServiceImpl;
import com.example.book.utils.JDBCUtils;
import org.junit.jupiter.api.Test;

import java.sql.Connection;

/**
 * @author chenzhilei
 * @date 2021/6/10) 18:13)
 */
class UserServiceImplTest {

    UserService userService = new UserServiceImpl();
    Connection conn = JDBCUtils.getConnection();

    @Test
    void registUser() {
        //userService.registUser(new User(null,"root","root","123@163.com"));
        userService.registUser(conn,new User(null,"czlczl","root","123@163.com"));
    }

    @Test
    void login() {
        System.out.println(userService.login(conn,new User(null, "czlczl", "root", null)));
    }

    @Test
    void existUsername() {
        System.out.println(userService.existUsername(conn,"root1"));
    }
}