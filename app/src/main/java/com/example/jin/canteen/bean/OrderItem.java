package com.example.jin.canteen.bean;

import java.io.Serializable;

/**
 * Created by jin on 2017/6/14.
 */

public class OrderItem implements Serializable{
    private String dishname;
    private String dishurl;
    private int dishnum;
    private double price;

    public String getDishname() {
        return dishname;
    }

    public void setDishname(String dishname) {
        this.dishname = dishname;
    }

    public String getDishurl() {
        return dishurl;
    }

    public void setDishurl(String dishurl) {
        this.dishurl = dishurl;
    }

    public int getDishnum() {
        return dishnum;
    }

    public void setDishnum(int dishnum) {
        this.dishnum = dishnum;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public OrderItem(String dishname, String dishurl, int dishnum, double price, int amount, int order_id) {
        this.dishname = dishname;
        this.dishurl = dishurl;
        this.dishnum = dishnum;
        this.price = price;
        this.amount = amount;
        this.order_id = order_id;
    }

    private int amount;
    private int order_id;


    public int getOrder_id() {
        return order_id;
    }

    public void setOrder_id(int order_id) {
        this.order_id = order_id;
    }


    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}

