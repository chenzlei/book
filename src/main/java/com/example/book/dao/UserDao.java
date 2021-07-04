package com.example.book.dao;

import com.example.book.bean.User;

import java.sql.Connection;

/**
 * user类的操作
 * @author chenzhilei
 * @date 2021/6/10) 16:13)
 */
public interface UserDao {

    /**
     * 根据用户名查找用户
     * @param username
     * @return  用户存在返回用户信息，不存在返回null
     */
    public User queryByUsername(Connection conn,String username);

    /**
     * 根据用户名和密码查找用户
     * @param username
     * @param password
     * @return 用户存在返回用户信息，不存在返回null
     */
    public User queryByUsernameAndPassword(Connection conn,String username,String password);

    /**
     * 保存用户注册信息
     * @param user
     * @return 返回-1表示操作失败，返回正数表示影响的行数
     */
    public int saveUser(Connection conn,User user);

    public <T> T getCount(Connection conn,String sql);
}
