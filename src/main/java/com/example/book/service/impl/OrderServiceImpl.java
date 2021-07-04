package com.example.book.service.impl;

import com.example.book.bean.*;
import com.example.book.dao.OrderDao;
import com.example.book.dao.OrderItemDao;
import com.example.book.dao.impl.OrderDaoImpl;
import com.example.book.dao.impl.OrderItemIpml;
import com.example.book.service.BookService;
import com.example.book.service.OrderService;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author chenzhilei
 * @date 2021/7/1) 18:25)
 */
public class OrderServiceImpl implements OrderService {
    private OrderDao orderDao = new OrderDaoImpl();
    private OrderItemDao orderItemDao = new OrderItemIpml();
    private BookService bookService = new BookServiceImpl();

    @Override
    public String CreateOrder(Connection conn, Cart cart, int userId) {
        if (cart.getItems().size() == 0) {
            return null;
        }
        String orderId = System.currentTimeMillis() + "" + userId;
        Order order = new Order(orderId, new Timestamp(new Date().getTime()), cart.getPriceTotal(), 0, userId);
        orderDao.saveOrder(conn, order);
        for (Map.Entry<Integer, CartItem> entry : cart.getItems().entrySet()) {
            CartItem cartItem = entry.getValue();
            Book book = bookService.queryOneBook(conn, cartItem.getId());
            book.setStock(book.getStock() - cartItem.getCount());
            book.setSales(book.getSales() + cartItem.getCount());
            bookService.updateBook(conn, book);
            OrderItem orderItem = new OrderItem(cartItem.getName(), cartItem.getPrice(), cartItem.getCount(), orderId);
            orderItemDao.saveOrderItem(conn, orderItem);
            if (cartItem.getCount() > book.getStock()) {
                throw new RuntimeException();
            }
        }
        cart.clear();
        return orderId;
    }

    @Override
    public List<Order> showAllOrders() {
        return null;
    }

    @Override
    public void sendOrder(String orderId) {

    }

    @Override
    public List<OrderItem> showOrderDetails(String orderId) {
        return null;
    }

    @Override
    public List<Order> showMyOrders(int userId) {
        return null;
    }

    @Override
    public void receiveOrder(String orderId) {

    }
}
