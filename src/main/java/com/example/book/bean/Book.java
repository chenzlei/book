package com.example.book.bean;

import java.math.BigDecimal;

/**
 * @author chenzhilei
 * @date 2021/6/28) 14:04)
 */
public class Book {
    private  Integer id;
    private String author;
    private String name;
    private BigDecimal price;
    private Integer sales;
    private Integer stock;
    private  String imgPath="static/img/default.jpg";

    public Book() {
    }

    public Book(Integer id, String author, String name, BigDecimal price, Integer sales, Integer stock, String imgPath) {
        this.id = id;
        this.author = author;
        this.name = name;
        this.price = price;
        this.sales = sales;
        this.stock = stock;
        if(imgPath!=null&&!"".equals(imgPath)){
            this.imgPath = imgPath;
        }
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
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

    public Integer getSales() {
        return sales;
    }

    public void setSales(Integer sales) {
        this.sales = sales;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        if(imgPath!=null&&!"".equals(imgPath)){
            this.imgPath = imgPath;
        }
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", author='" + author + '\'' +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", sales=" + sales +
                ", stock=" + stock +
                ", imgPath='" + imgPath + '\'' +
                '}';
    }
}
