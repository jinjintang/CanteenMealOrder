package com.example.jin.canteen.bean;

import java.util.List;

/**
 * Created by jin on 2017/6/28.
 */

public class Categroy {


        private int id;
        private String name;
        private String avatar;
        private List<Dish> dish;

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

        public List<Dish> getDish() {
            return dish;
        }

        public void setDish(List<Dish> dish) {
            this.dish = dish;
        }


}

