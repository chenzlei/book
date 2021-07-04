package com.example.book.bean;

import java.math.BigDecimal;

/**
 * 订单详情
 * @author chenzhilei
 * @date 2021/7/1) 15:00)
 */
public class OrderItem {
    private Integer id;

    private String name;

    private BigDecimal price;

    private BigDecimal totalPrice;

    private Integer count;

    private String orderId;

    public OrderItem() {
    }

    public OrderItem( String name, BigDecimal price, Integer count, String orderId) {
        //this.id = id;
        this.name = name;
        this.price = price;
        //this.totalPrice = totalPrice;
        this.count = count;
        this.orderId = orderId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public BigDecimal getTotalPrice() {
        return this.price.multiply(new BigDecimal(this.count));
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    @Override
    public String toString() {
        return "OrderItem{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", totalPrice=" + totalPrice +
                ", count=" + count +
                ", orderId='" + orderId + '\'' +
                '}';
    }
}
