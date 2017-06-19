package com.example.jin.canteen.bean;

import java.io.Serializable;

/**
 * Created by jin on 2017/6/16.
 */

public class Comment implements Serializable {
    private int commentnum;
    private int dish_num;

    private String content;

    public int getDish_num() {
        return dish_num;
    }

    public void setDish_num(int dish_num) {
        this.dish_num = dish_num;
    }




    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getCommentnum() {
        return commentnum;
    }

    public void setCommentnum(int commentnum) {
        this.commentnum = commentnum;
    }
}
