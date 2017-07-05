package com.example.jin.canteen.bean;

import java.util.List;

/**
 * Created by jin on 2017/6/15.
 */

public class Canteen {

    /**
     * id : 47
     * name : 一食堂
     * avatar : 0
     * dishes : [{"id":1,"name":"火锅","avatar":"","introduciton":"还是","price":"10","cid":47,"cname":"一食堂"},{"id":2,"name":"辣子鸡丁","avatar":"","introduciton":"还是","price":"10","cid":47,"cname":"一食堂"}]
     */

    private int id;
    private String name;
    private String avatar;
    private List<Dish> dishes;

    public Canteen(int id, String name, String avatar) {
        this.id = id;
        this.name = name;
        this.avatar = avatar;
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

    public List<Dish> getDishes() {
        return dishes;
    }

    public void setDishes(List<Dish> dishes) {
        this.dishes = dishes;
    }
}
