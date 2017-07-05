package com.example.jin.canteen.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by jin on 2017/6/14.
 */

public class OrderItem implements Serializable {

    /**
     * id : 1
     * mid : 1
     * num : 2
     * dish : ["10","西红柿"]
     * oid : 1
     */

    private int id;
    private int mid;
    private int num;
    private int oid;
    private List<String> dish;
    private String mname;
    private String mavatar;
    private float mprice;



    public OrderItem(int mid, int num,String mavatar,String mname,float mprice) {
        this.mid = mid;
        this.num = num;
        this.mname=mname;
        this.mavatar=mavatar;
        this.mprice=mprice;
    }

    public float getMprice() {
        return mprice;
    }

    public void setMprice(float mprice) {
        this.mprice = mprice;
    }

    public String getMname() {
        return mname;
    }

    public void setMname(String mname) {
        this.mname = mname;
    }

    public String getMavatar() {
        return mavatar;
    }

    public void setMavatar(String mavatar) {
        this.mavatar = mavatar;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMid() {
        return mid;
    }

    public void setMid(int mid) {
        this.mid = mid;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public int getOid() {
        return oid;
    }

    public void setOid(int oid) {
        this.oid = oid;
    }

    public List<String> getDish() {
        return dish;
    }

    public void setDish(List<String> dish) {
        this.dish = dish;
    }
}


