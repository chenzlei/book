package com.example.book.service;

import com.example.book.bean.Cart;
import com.example.book.bean.Order;
import com.example.book.bean.OrderItem;

import java.sql.Connection;
import java.util.List;

/**
 * @author chenzhilei
 * @date 2021/7/1) 17:45)
 */
public interface OrderService {
    public String CreateOrder(Connection conn, Cart cart, int userId);

    public List<Order> showAllOrders();

    public void sendOrder(String orderId);

    public List<OrderItem> showOrderDetails(String orderId);

    public List<Order> showMyOrders(int userId);

    public void receiveOrder(String orderId);
}
