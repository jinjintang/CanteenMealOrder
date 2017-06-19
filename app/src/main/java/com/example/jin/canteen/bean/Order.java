package com.example.jin.canteen.bean;


import java.io.Serializable;
import java.util.List;

public class Order implements Serializable {
    private int order_id;
    private double order_price;
    private int canteennum;
    private String canteenname;
    private String order_time;
    private String canteenurl;






    public int getOrder_id() {
        return order_id;
    }

    public void setOrder_id(int order_id) {
        this.order_id = order_id;
    }


    public int getCanteennum() {
        return canteennum;
    }

    public void setCanteennum(int canteennum) {
        this.canteennum = canteennum;
    }

    public String getCanteenname() {
        return canteenname;
    }

    public void setCanteenname(String canteenname) {
        this.canteenname = canteenname;
    }

    public String getCanteenurl() {
        return canteenurl;
    }

    public void setCanteenurl(String canteenurl) {
        this.canteenurl = canteenurl;
    }

    public Order(int order_id, double order_price, int canteennum, String canteenname, String order_time, String canteenurl) {
        this.order_id = order_id;
        this.order_price = order_price;
        this.canteennum = canteennum;
        this.canteenname = canteenname;
        this.order_time = order_time;
        this.canteenurl = canteenurl;
    }

    public double getOrder_price() {
        return order_price;
    }

    public void setOrder_price(double order_price) {
        this.order_price = order_price;
    }



    public String getOrder_time() {
        return order_time;
    }

    public void setOrder_time(String order_time) {
        this.order_time = order_time;
    }


}
