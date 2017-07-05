package com.example.jin.canteen.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by jin on 2017/6/30.
 */

public class submitOrder {
    private int cid;
    private int eattime;
    private int uid;
    private float price;
    private int type;
    private String cname;
    private String cavatar;
    private List<OrderItemBean> orderitems;



    public submitOrder(int eattime, int cid, int uid,float price, int type, String cname, String cavatar, List<OrderItemBean> orderitems) {
        this.eattime = eattime;
        this.cid = cid;
        this.uid = uid;
        this.price = price;
        this.type = type;
        this.cname = cname;
        this.cavatar = cavatar;
        this.orderitems = orderitems;
    }

    public  static class OrderItemBean implements Serializable{
        private int mid;
        private int num;
        private String mname;
        private String mavatar;
        private float mprice;

        public OrderItemBean(int mid, int num, String mname, String mavatar, float mprice) {
            this.mid = mid;
            this.num = num;
            this.mname = mname;
            this.mavatar = mavatar;
            this.mprice = mprice;
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
    }

    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    public int getEattime() {
        return eattime;
    }

    public void setEattime(int eattime) {
        this.eattime = eattime;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public String getCavatar() {
        return cavatar;
    }

    public void setCavatar(String cavatar) {
        this.cavatar = cavatar;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public List<OrderItemBean> getOrderitems() {
        return orderitems;
    }

    public void setOrderitems(List<OrderItemBean> orderitems) {
        this.orderitems = orderitems;
    }
}
