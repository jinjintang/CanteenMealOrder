package com.example.jin.canteen.bean;

import java.util.List;

public class ResultBean {
    private List<Dish> dishes;
    private int cid;

    public ResultBean(List<Dish> dishes, int cid) {
        this.dishes = dishes;
        this.cid = cid;
    }

    public List<Dish> getDishes() {
        return dishes;
    }

    public void setDishes(List<Dish> dishes) {
        this.dishes = dishes;
    }

    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }
}
