package com.example.jin.canteen.bean;

import java.util.List;

/**
 * Created by jin on 2017/6/16.
 */

public  class Dish implements Comparable<Dish> {
    /**
     * id : 4
     * name : 阿虎
     * avatar : http://106.14.167.106/uploads/5.jpg
     * introduciton : 撒大声地
     * price : 10
     * categroy_id : 4
     * cid : 49
     * cname : 三食堂
     * categroy_name : 荤菜
     * comments : [{"id":8,"mid":4,"uid":1,"comment":"这个菜很好吃","mname":"阿虎","avatar":"http://106.14.167.106/uploads/5.jpg","user":"1114010606","reply":null}]
     */

    private int id;
    private String name;
    private String avatar;
    private String introduciton;
    private String price;
    private int categroy_id;
    private int cid;
    private String cname;
    private String categroy_name;
    private List<Comment> comments;



    @Override
    public int compareTo(Dish o) {
        if (this.getCid()!=o.getCid())return this.cid-o.cid;
    else return this.categroy_id-o.categroy_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getIntroduciton() {
        return introduciton;
    }

    public void setIntroduciton(String introduciton) {
        this.introduciton = introduciton;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public int getCategroy_id() {
        return categroy_id;
    }

    public void setCategroy_id(int categroy_id) {
        this.categroy_id = categroy_id;
    }

    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public String getCategroy_name() {
        return categroy_name;
    }

    public void setCategroy_name(String categroy_name) {
        this.categroy_name = categroy_name;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }


}

