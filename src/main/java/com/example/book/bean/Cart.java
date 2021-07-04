package com.example.book.bean;

import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 购物车
 * @author chenzhilei
 * @date 2021/6/30) 14:27)
 */
public class Cart {
    //private Integer priceTotal;
    //private Integer countTotal;
    private Map<Integer,CartItem> items=new LinkedHashMap<Integer,CartItem>();

    public void addItem(CartItem item){
        CartItem cartItem = items.get(item.getId());
        if(cartItem!=null){
            cartItem.setCount(cartItem.getCount()+item.getCount());
        }else{
            items.put(item.getId(),item);
        }
    }

    public void deleteItem(Integer id){
        items.remove(id);
    }

    public void updateCount(Integer id,int count){
        CartItem cartItem = items.get(id);
        if(cartItem!=null){
            cartItem.setCount(count);
        }
    }

    public void clear(){
        items.clear();
    }

    public Map<Integer, CartItem> getItems() {
        return items;
    }

    public BigDecimal getPriceTotal() {
        BigDecimal priceTotal=new BigDecimal(0);
        for(Map.Entry<Integer,CartItem> entry:items.entrySet()){
            priceTotal=priceTotal.add(entry.getValue().getTotalPrice());
        }
        return priceTotal;
    }

    public Integer getCountTotal() {
        Integer countTotal=0;
        for(Map.Entry<Integer,CartItem> entry:items.entrySet()){
            countTotal+=entry.getValue().getCount();
        }
        return countTotal;
    }

    public void setItems(Map<Integer, CartItem> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return "Cart{" +
                "priceTotal=" + getPriceTotal() +
                ", countTotal=" + getCountTotal() +
                ", items=" + items +
                '}';
    }
}
