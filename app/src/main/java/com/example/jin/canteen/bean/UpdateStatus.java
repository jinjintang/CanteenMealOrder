package com.example.jin.canteen.bean;

/**
 * Created by jin on 2017/6/25.
 */

public class UpdateStatus {
    private String message;
    private int status;
    private int user_id;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }
}
