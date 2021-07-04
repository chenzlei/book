package com.example.book.test;

import com.example.book.bean.User;
import com.example.book.dao.UserDao;
import com.example.book.dao.impl.UserDaoImpl;
import com.example.book.utils.JDBCUtils;
import org.junit.jupiter.api.Test;

import java.sql.Connection;

/**
 * @author chenzhilei
 * @date 2021/6/10) 16:34)
 */
class UserDaoImplTest {
    private UserDao user=new UserDaoImpl();
    Connection conn = JDBCUtils.getConnection();

    @Test
    void queryByUsername() {
        if(user.queryByUsername(conn,"root1")!=null){
            System.out.println("用户名已存在");
        }else{
            System.out.println("用户名可用");
        }
    }

    @Test
    void queryByUsernameAndPassword() {
        if(user.queryByUsernameAndPassword(conn,"root","root")==null){
            System.out.println("用户名或者密码错误");
        }else{
            System.out.println("登录成功");
        }
        User user = this.user.queryByUsernameAndPassword(conn,"admin", "admin");
        System.out.println(user);
    }

    @Test
    void saveUser() {
        int count = user.saveUser(conn,new User(null, "czl", "czl123", "czl123@163.com"));
        System.out.println(count);
    }
}