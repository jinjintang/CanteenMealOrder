package com.example.jin.canteen.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.jin.canteen.R;
import com.example.jin.canteen.Request.OrderRequest;
import com.example.jin.canteen.adapter.OrderAdapter;
import com.example.jin.canteen.bean.Order;
import com.example.jin.canteen.util.GlobalData;

import java.util.ArrayList;

public class OrderActivity extends AppCompatActivity {

    private Context mContext;
    private ListView lvOrder;
    private ArrayList<Order> orders;

    @Override
    protected void onCreate( Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_order);
        mContext=this;

        findIds();

        initView();
    }

    private void initView() {
        new Thread(){
            public void run(){
//                HashMap<String,String>map=new HashMap<String, String>();
//                map.put("orderformUsername", GlobalData.USER_NAME);
//                orderResult= HttpUtils.sendPost(GlobalData.URL+"orderform/selectOrderform",map,"utf8");

                orders= OrderRequest.getorderbyusername(GlobalData.USER_NAME);

                mHandler.sendEmptyMessage(1);
            }
        }.start();

    }

    private void findIds() {
//        TopMenuHeader header=new TopMenuHeader(getWindow().getDecorView());
//        header.topMenuTitle.setText("订单信息");
//        header.topMenuLeft.setOnClickListener(this);
        lvOrder=(ListView)findViewById(R.id.orderlist);
    }

    Handler mHandler=new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 1) {
//                Gson gson=new Gson();
//                OrderListBean orderListBean=gson.fromJson(orderResult,OrderListBean.class);
//                ArrayList<Order>orders=orderListBean.getList();

         OrderAdapter adapter = new OrderAdapter(orders, mContext);

              lvOrder.setAdapter(adapter);
//                adapter.notifyDataSetChanged();

            }
        }
    };


}