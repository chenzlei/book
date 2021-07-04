package com.example.book.service;

import com.example.book.bean.User;

import java.sql.Connection;

/**
 *
 * @author chenzhilei
 * @date 2021/6/10) 16:59)
 */
public interface UserService {

    /**
     * 注册用户
     * @param user
     */
    public void registUser(Connection conn,User user);

    /**
     * 用户登录
     * @param user
     * @return 登录成功返回用户，失败返回null
     */
    public User login(Connection conn,User user);

    /**
     * 判断用户名是否存在
     * @param username
     * @return 用户名存在返回true
     */
    public boolean existUsername(Connection conn,String username);
}
