package com.example.book.dao;

import com.example.book.bean.OrderItem;

import java.sql.Connection;
import java.util.List;

/**
 * @author chenzhilei
 * @date 2021/7/1) 15:13)
 */
public interface OrderItemDao {
    public  int saveOrderItem(Connection conn,OrderItem orderItem);

    public List<OrderItem> queryForOrderItemByID(Connection conn,String orderId);
}
