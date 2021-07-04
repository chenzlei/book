package com.example.book.test;

import com.example.book.utils.JDBCUtils;
import org.junit.jupiter.api.Test;

import java.sql.Connection;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author chenzhilei
 * @date 2021/6/10) 15:01)
 */
class JDBCUtilsTest {

    @Test
    void getConnection() {
        Connection conn = JDBCUtils.getConnection();
        System.out.println(conn);
        JDBCUtils.close(conn);
    }
}