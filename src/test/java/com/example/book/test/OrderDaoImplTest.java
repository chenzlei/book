package com.example.book.test;

import com.example.book.bean.Order;
import com.example.book.dao.OrderDao;
import com.example.book.dao.impl.OrderDaoImpl;
import com.example.book.utils.JDBCUtils;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

/**
 * @author chenzhilei
 * @date 2021/7/1) 15:48)
 */
class OrderDaoImplTest {
    OrderDao orderDao=new OrderDaoImpl();
    Connection conn = JDBCUtils.getConnection();

    @Test
    void saveOrder() {

        //int i = orderDao.saveOrder(new Order("20210701155100001", new Timestamp(new Date().getTime()), new BigDecimal(100), 0, 1));
        int j = orderDao.saveOrder(conn,new Order("20210701174000000", new Timestamp(new Date().getTime()), new BigDecimal(100), 0, 1));
        System.out.println(j);
    }

    @Test
    void queryForOrders() {
        List<Order> orders = orderDao.queryForOrders(conn);
        orders.forEach(System.out::println);
    }

    @Test
    void updateStatus() {
        int i = orderDao.updateStatus(conn,"20210701155100001", 1);
        System.out.println(i);
    }

    @Test
    void queryForOrderByUserId() {
        List<Order> order = orderDao.queryForOrderByUserId(conn,1);
        System.out.println(order);
    }
}