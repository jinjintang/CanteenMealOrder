package com.example.jin.canteen.bean;

import java.util.List;

public class Order  {


    /**
     * id : 1
     * time : 2017-06-27 00:58:02
     * eattime : 3
     * cid : 48
     * uid : 1
     * price : 10
     * user : 1114010606
     * cname : 二食堂
     *
     * orderitems : [{"id":1,"mid":1,"num":2,"oid":1},{"id":3,"mid":1,"num":2,"oid":1},{"id":4,"mid":1,"num":2,"oid":1}]
     */

    private int id;
    private String time;
    private int eattime;
    private int cid;
    private int uid;
    private float price;
    private String user;
    private String cname;
    private String cavatar;
    private List<OrderItem> orderitems;
    private int type;
    public Order(int cid, int eattime, int uid, String cname,String cavatar,float price, List<OrderItem> orderitems, int type) {
        this.cid = cid;
        this.eattime = eattime;
        this.orderitems = orderitems;
        this.uid = uid;
        this.price = price;
        this.type=type;
        this.cavatar=cavatar;
        this.cname=cname;
    }

    public String getCavatar() {
        return cavatar;
    }

    public void setCavatar(String cavatar) {
        this.cavatar = cavatar;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getEattime() {
        return eattime;
    }

    public void setEattime(int eattime) {
        this.eattime = eattime;
    }

    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }
//        public String getPrice() {
//        return price;
//    }
//
//    public void setPrice(String price) {
//        this.price = price;
//    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public List<OrderItem> getOrderitems() {
        return orderitems;
    }

    public void setOrderitems(List<OrderItem> orderitems) {
        this.orderitems = orderitems;
    }


}
