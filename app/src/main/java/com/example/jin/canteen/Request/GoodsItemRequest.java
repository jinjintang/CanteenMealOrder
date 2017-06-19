package com.example.jin.canteen.Request;

import com.example.jin.canteen.bean.GoodsItem;

import java.util.ArrayList;

/**
 * Created by jin on 2017/6/19.
 */

public class GoodsItemRequest {
    private static ArrayList<GoodsItem> goodsList;
    private static ArrayList<GoodsItem> typeList;

    private static void initDatabyCanteen(int canteennum){
        goodsList = new ArrayList<>();
        typeList = new ArrayList<>();
        GoodsItem item = null;
        for(int i=1;i<15;i++){
            for(int j=1;j<10;j++){
                item = new GoodsItem(100*i+j,Math.random()*100,"商品"+(100*i+j),i,"种类"+i);
                goodsList.add(item);
            }
            typeList.add(item);
        }
    }


}
