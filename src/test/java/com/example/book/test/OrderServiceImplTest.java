package com.example.book.test;

import com.example.book.bean.Cart;
import com.example.book.bean.CartItem;
import com.example.book.service.OrderService;
import com.example.book.service.impl.OrderServiceImpl;
import com.example.book.utils.JDBCUtils;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.sql.Connection;

/**
 * @author chenzhilei
 * @date 2021/7/1) 18:41)
 */
class OrderServiceImplTest {
    OrderService orderService=new OrderServiceImpl();
    Connection conn = JDBCUtils.getConnection();

    @Test
    void createOrder() {
        Cart cart=new Cart();
        cart.addItem(new CartItem(1,"java",2,new BigDecimal(200)));
        cart.addItem(new CartItem(2,"C++",1,new BigDecimal(50)));
        cart.addItem(new CartItem(3,"PHP",2,new BigDecimal(100)));
        String orderId = orderService.CreateOrder(conn,cart, 1);
        System.out.println(orderId);
    }

    @Test
    void showAllOrders() {
    }

    @Test
    void sendOrder() {
    }

    @Test
    void showOrderDetails() {
    }

    @Test
    void showMyOrders() {
    }

    @Test
    void receiveOrder() {
    }
}