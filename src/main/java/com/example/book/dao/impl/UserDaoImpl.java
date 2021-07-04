package com.example.book.dao.impl;

import com.example.book.bean.User;
import com.example.book.dao.UserDao;

import java.sql.Connection;

/**
 * @author chenzhilei
 * @date 2021/6/10) 16:19)
 */
public class UserDaoImpl extends BaseDao implements UserDao {

    @Override
    public User queryByUsername(Connection conn,String username) {
        String sql="select id,username,password,email from t_user where username = ?";
        return queryForOne(conn,User.class,sql,username);
    }

    @Override
    public User queryByUsernameAndPassword(Connection conn,String username, String password) {
        String sql="select id,username,password,email from t_user where username = ? and password = ?";
        return queryForOne(conn,User.class,sql,username,password);
    }

    @Override
    public int saveUser(Connection conn,User user) {
        String sql="insert into t_user (username,`password`,email) values(?,?,?)";
        return update(conn,sql,user.getUsername(),user.getPassword(),user.getEmail());
    }

    @Override
    public <T> T getCount(Connection conn,String sql) {
        return getValue(conn,sql);
    }
}
