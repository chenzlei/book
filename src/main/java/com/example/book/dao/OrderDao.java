package com.example.book.dao;

import com.example.book.bean.Order;

import java.sql.Connection;
import java.util.List;

/**
 * @author chenzhilei
 * @date 2021/7/1) 15:03)
 */
public interface OrderDao {
    public int saveOrder(Connection conn,Order order);

    public List<Order> queryForOrders(Connection conn);

    public int updateStatus(Connection conn,String orderId,int status);

    public List<Order> queryForOrderByUserId(Connection conn,int userId);
}
