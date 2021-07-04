package com.example.book.service.impl;

import com.example.book.bean.User;
import com.example.book.dao.UserDao;
import com.example.book.dao.impl.UserDaoImpl;
import com.example.book.service.UserService;

import java.sql.Connection;

/**
 * @author chenzhilei
 * @date 2021/6/10) 17:04)
 */
public class UserServiceImpl implements UserService {

    private UserDao userDao=new UserDaoImpl();

    @Override
    public void registUser(Connection conn,User user) {
        userDao.saveUser(conn,user);
    }

    @Override
    public User login(Connection conn,User user) {
        return userDao.queryByUsernameAndPassword(conn, user.getUsername(), user.getPassword());
    }

    @Override
    public boolean existUsername(Connection conn,String username) {
        if(userDao.queryByUsername(conn,username)==null){
            return false;
        }
        return true;
    }
}
