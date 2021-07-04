package com.example.book.test;

import com.example.book.bean.Cart;
import com.example.book.bean.CartItem;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

/**
 * @author chenzhilei
 * @date 2021/6/30) 14:51)
 */
class CartTest {

    @Test
    void addItem() {
        Cart cart=new Cart();
        cart.addItem(new CartItem(1,"java",2,new BigDecimal(200)));
        cart.addItem(new CartItem(2,"C++",1,new BigDecimal(50)));
        cart.addItem(new CartItem(3,"PHP",2,new BigDecimal(100)));
        cart.addItem(new CartItem(3,"PHP",2,new BigDecimal(100)));
        cart.deleteItem(3);
        cart.updateCount(1,4);
        cart.clear();
        System.out.println(cart);
    }
}