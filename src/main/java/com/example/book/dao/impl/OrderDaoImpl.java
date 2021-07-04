package com.example.book.dao.impl;

import com.example.book.bean.Order;
import com.example.book.dao.OrderDao;

import java.sql.Connection;
import java.util.List;

/**
 * @author chenzhilei
 * @date 2021/7/1) 15:16)
 */
public class OrderDaoImpl extends BaseDao implements OrderDao {
    @Override
    public int saveOrder(Connection conn,Order order) {
        String sql="insert into t_order(order_id,create_time,price,status,user_id) values(?,?,?,?,?)";
        return update(conn,sql,order.getOrderId(),order.getCreateTime(),order.getPrice(),order.getStatus(),order.getUserId());
    }

    @Override
    public List<Order> queryForOrders(Connection conn) {
        String sql="select order_id as orderId,create_time as createTime,price,status as status,user_id as userId from t_order";
        return queryForList(conn,Order.class,sql);
    }

    @Override
    public int updateStatus(Connection conn,String orderId, int status) {
        String sql="update t_order set status=? where order_id=?";
        return update(conn,sql,status,orderId);
    }

    @Override
    public List<Order> queryForOrderByUserId(Connection conn,int userId) {
        String sql="select order_id as orderId,create_time as createTime,price,status as status,user_id as userId from t_order where user_id=?";
        return queryForList(conn,Order.class,sql,userId);
    }
}
