package com.example.jin.canteen.bean;

public class GoodsItem{
    public int id;
    public int typeId;
    public String avator;
    public String name;
    public String typeName;
    public double price;
    public int count;

    public GoodsItem(int id, double price, String name, int typeId, String typeName,String avator) {
        this.id = id;
        this.price = price;
        this.name = name;
        this.typeId = typeId;
        this.typeName = typeName;
        this.avator=avator;
    }


}
