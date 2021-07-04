package com.example.book.dao.impl;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * 封装了对数据库的通用操作
 * @author chenzhilei
 * @date 2021/6/10) 15:40)
 */
public abstract class BaseDao {
    private QueryRunner queryRunner=new QueryRunner();

    /**
     * 更新插入删除表
     * @param sql
     * @param orgs
     * @return
     */
    public int update(Connection conn,String sql,Object... orgs){
        //Connection conn=JDBCUtils.getConnection();
        try {
            return queryRunner.update(conn,sql,orgs);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            throw new RuntimeException();
        }
    }

    /**
     * 查询单个记录,查找失败返回null
     * @param sql
     * @param args
     * @param <T>
     * @return
     */
    public <T> T queryForOne(Connection conn,Class<T> type,String sql,Object... args){
        try {
            return queryRunner.query(conn,sql,new BeanHandler<T>(type),args);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            throw new RuntimeException();
        }
    }

    public <T> List<T> queryForList(Connection conn,Class<T> type, String sql, Object... args){
        try {
            return queryRunner.query(conn,sql,new BeanListHandler<T>(type),args);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            throw new RuntimeException();
        }
    }

    public <T> T getValue(Connection conn,String sql,Object... args){
        try {
            return queryRunner.query(conn,sql,new ScalarHandler<T>(),args);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            throw new RuntimeException();
        }
    }

}
