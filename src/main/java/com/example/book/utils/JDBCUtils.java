package com.example.book.utils;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

/**
 * 工具类获取sql连接和关闭sql连接
 * @author chenzhilei
 * @date 2021/6/10) 14:48)
 */
public class JDBCUtils {
    private static DruidDataSource dataSource=new DruidDataSource();
    private static ThreadLocal<Connection> conns=new ThreadLocal<>();

    static{
        try {
            Properties pros=new Properties();
            InputStream is = JDBCUtils.class.getClassLoader().getResourceAsStream("jdbc.properties");
            //InputStream is = ClassLoader.getSystemClassLoader().getResourceAsStream("jdbc.properties");
            pros.load(is);
            dataSource= (DruidDataSource) DruidDataSourceFactory.createDataSource(pros);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection(){
        Connection conn=conns.get();
        if(conn==null){
            try {
                conn=dataSource.getConnection();
                conns.set(conn);
                conn.setAutoCommit(false);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return conn;
    }

    public static void commitAndClose(){
        Connection connection=conns.get();
        if(connection!=null){
            try {
                connection.commit();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            finally {
                try {
                    connection.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        }
        conns.remove();
    }

    public static void rollbackAndClose(){
        Connection connection=conns.get();
        if(connection!=null){
            try {
                connection.rollback();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            finally {
                try {
                    connection.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        }
        conns.remove();
    }


    public static void close(Connection conn){
        System.out.println("close" + conn);
        if(conn!=null){
            try {
                conn.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }
}
