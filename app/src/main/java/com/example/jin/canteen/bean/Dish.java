package com.example.jin.canteen.bean;

import org.w3c.dom.Comment;

import java.io.Serializable;
import java.util.List;

/**
 * Created by jin on 2017/6/16.
 */

public class Dish implements Serializable{
    private int dishnum;
    private String dishname;
    private String introduction;
    private int canteennum;
    private double price;
    private String picurl;


    public String getPicurl() {
        return picurl;
    }

    public void setPicurl(String picurl) {
        this.picurl = picurl;
    }





    public int getDishnum() {
        return dishnum;
    }

    public void setDishnum(int dishnum) {
        this.dishnum = dishnum;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public String getDishname() {
        return dishname;
    }

    public void setDishname(String dishname) {
        this.dishname = dishname;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getCanteennum() {
        return canteennum;
    }

    public void setCanteennum(int canteennum) {
        this.canteennum = canteennum;
    }


}
