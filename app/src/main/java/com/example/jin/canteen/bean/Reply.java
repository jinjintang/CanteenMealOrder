package com.example.jin.canteen.bean;

/**
 * Created by jin on 2017/6/28.
 */

public class Reply {
    /**
     * id : 1
     * comment_id : 0
     * text : 0
     */

    private int id;
    private int comment_id;
    private String text;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getComment_id() {
        return comment_id;
    }

    public void setComment_id(int comment_id) {
        this.comment_id = comment_id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
