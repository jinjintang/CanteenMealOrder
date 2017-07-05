package com.example.jin.canteen.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ListView;

import com.example.jin.canteen.R;
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
        orderItems=(List<OrderItem>)getIntent().getSerializableExtra("orderitems");
        Log.e("数据接受",orderItems.toString());
        findIds();

        initView();
    }

    private void initView() {
        OrderDetailAdapter adapter = new OrderDetailAdapter(orderItems, mContext);

        lvOrderItem.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    private void findIds() {
//        TopMenuHeader header=new TopMenuHeader(getWindow().getDecorView());
//        header.topMenuTitle.setText("订单信息");
//        header.topMenuLeft.setOnClickListener(this);
        lvOrderItem=(ListView)findViewById(R.id.itemlist);
    }




}

