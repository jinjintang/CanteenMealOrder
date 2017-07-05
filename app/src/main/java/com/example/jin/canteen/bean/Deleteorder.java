package com.example.jin.canteen.bean;

/**
 * Created by jin on 2017/6/29.
 */

public class Deleteorder {
private int type;
    private int oid;

    public Deleteorder(int type, int oid) {
        this.type = type;
        this.oid = oid;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getOid() {
        return oid;
    }

    public void setOid(int oid) {
        this.oid = oid;
    }
}
