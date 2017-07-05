package com.example.jin.canteen.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by jin on 2017/6/23.
 */

public class User implements Serializable {


    /**
     * id : 1
     * username : 1114010606
     * avatar : 0
     * sign : 0
     * account : 300
     * orders : []
     */

    private int id;
    private String username;
    private String avatar;
    private int sign;
    private String account;
    private List<Order> orders;
    private String password;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public int getSign() {
        return sign;
    }

    public void setSign(int sign) {
        this.sign = sign;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }
}
