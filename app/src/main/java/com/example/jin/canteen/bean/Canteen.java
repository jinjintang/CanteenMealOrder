package com.example.jin.canteen.bean;

import java.io.Serializable;

/**
 * Created by jin on 2017/6/15.
 */

public class Canteen implements Serializable{
    private String picurl;
    private int canteennum;
    private String namae;


    public String getPicurl() {
        return picurl;
    }

    public void setPicurl(String picurl) {
        this.picurl = picurl;
    }

    public int getCanteennum() {
        return canteennum;
    }

    public void setCanteennum(int canteennum) {
        this.canteennum = canteennum;
    }

    public String getNamae() {
        return namae;
    }

    public void setNamae(String namae) {
        this.namae = namae;
    }
}
