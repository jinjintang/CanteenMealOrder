package com.example.jin.canteen.Request;

import com.example.jin.canteen.bean.Order;
import com.example.jin.canteen.util.GlobalData;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jin on 2017/6/15.
 */

public  class OrderRequest {
    public static ArrayList<Order> getorderbyusername(String username){
        ArrayList<Order> orderList=new ArrayList<Order>();
        for(int i=0;i<3;i++){
        Order order=new Order(1,15,5,"五食堂","2017-6-17", GlobalData.PIC_URL_1);
        orderList.add(order);}
        return orderList;

    }
public static void deleteorder(int ordernum){



}


}
