package com.example.jin.canteen.Request;

import com.example.jin.canteen.bean.Dish;
import com.example.jin.canteen.bean.Order;
import com.example.jin.canteen.bean.OrderItem;
import com.example.jin.canteen.util.GlobalData;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jin on 2017/6/16.
 */

public class OrderItemRequest {
    public static List<OrderItem> getOrderItemsbyOrderId(int orderid){
        List<OrderItem> items=new ArrayList<OrderItem>();
       for(int i=0;i<5;i++){
        OrderItem orderItem=new OrderItem("番茄炒蛋", GlobalData.PIC_URL_1,1,3,1,1);

        items.add(orderItem);}
                return items;

    }

}
