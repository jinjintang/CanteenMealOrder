package com.example.jin.canteen.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import com.example.jin.canteen.R;
import com.example.jin.canteen.Request.OrderItemRequest;
import com.example.jin.canteen.adapter.OrderDetailAdapter;
import com.example.jin.canteen.bean.OrderItem;

import java.util.List;

public class OrderDetailActivity extends AppCompatActivity {


    private Context mContext;
    private ListView lvOrderItem;
    private List<OrderItem> orderItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_detail);
        mContext=this;

        findIds();

        initView();
    }

    private void initView() {
        new Thread(){
            public void run(){
                Intent intent=getIntent();
                int orderId = intent.getIntExtra("OrderId",0);

                orderItems= OrderItemRequest.getOrderItemsbyOrderId(orderId);

                mHandler.sendEmptyMessage(1);
            }
        }.start();

    }

    private void findIds() {
//        TopMenuHeader header=new TopMenuHeader(getWindow().getDecorView());
//        header.topMenuTitle.setText("订单信息");
//        header.topMenuLeft.setOnClickListener(this);
        lvOrderItem=(ListView)findViewById(R.id.itemlist);
    }

    Handler mHandler=new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 1) {
//                Gson gson=new Gson();
//                OrderListBean orderListBean=gson.fromJson(orderResult,OrderListBean.class);
//                ArrayList<Order>orders=orderListBean.getList();
                OrderDetailAdapter adapter = new OrderDetailAdapter(orderItems, mContext);

                lvOrderItem.setAdapter(adapter);
                adapter.notifyDataSetChanged();

            }
        }
    };


}

