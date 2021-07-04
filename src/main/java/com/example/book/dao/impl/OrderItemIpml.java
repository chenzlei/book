package com.example.book.dao.impl;

import com.example.book.bean.OrderItem;
import com.example.book.dao.OrderItemDao;

import java.sql.Connection;
import java.util.List;

/**
 * @author chenzhilei
 * @date 2021/7/1) 16:49)
 */
public class OrderItemIpml extends BaseDao implements OrderItemDao {
    @Override
    public int saveOrderItem(Connection conn,OrderItem orderItem) {
        String sql="insert into t_order_item(name,price,count,total_price,order_id) values(?,?,?,?,?)";
        return update(conn,sql,orderItem.getName(), orderItem.getPrice(),orderItem.getCount(),orderItem.getTotalPrice(),orderItem.getOrderId());
    }

    @Override
    public List<OrderItem> queryForOrderItemByID(Connection conn,String orderId) {
        String sql="select id,name,price,total_price as totalPrice,count,order_id as orderId from t_order_item where order_id=?";
        return queryForList(conn,OrderItem.class,sql,orderId);
    }
}
