package com.example.book.test;

import com.example.book.bean.OrderItem;
import com.example.book.dao.OrderItemDao;
import com.example.book.dao.impl.OrderItemIpml;
import com.example.book.utils.JDBCUtils;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.sql.Connection;
import java.util.List;

/**
 * @author chenzhilei
 * @date 2021/7/1) 17:17)
 */
class OrderItemIpmlTest {
    OrderItemDao orderItemDao=new OrderItemIpml();
    Connection conn = JDBCUtils.getConnection();

    @Test
    void saveOrderItem() {
        int java = orderItemDao.saveOrderItem(conn,new OrderItem("php", new BigDecimal(20), 2, "20210701155100001"));
        System.out.println(java);
    }

    @Test
    void queryForOrderItemByID() {
        List<OrderItem> orderItem = orderItemDao.queryForOrderItemByID(conn,"20210701155100001");
        orderItem.forEach(System.out::println);
    }
}