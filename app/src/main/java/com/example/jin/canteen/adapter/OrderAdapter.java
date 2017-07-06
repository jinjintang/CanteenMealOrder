package com.example.jin.canteen.adapter;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.jin.canteen.R;
import com.example.jin.canteen.activity.OrderDetailActivity;
import com.example.jin.canteen.activity.ShoppingCartActivity;
import com.example.jin.canteen.bean.Deleteorder;
import com.example.jin.canteen.bean.Order;
import com.example.jin.canteen.bean.OrderItem;
import com.example.jin.canteen.util.GlobalData;
import com.example.jin.canteen.util.HttpUtils;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class OrderAdapter extends BaseAdapter {

    private List<Order> dataList;
    private Context mContext;

    private ImageView image;


    public OrderAdapter(List<Order> dataList,Context mContext) {
        super();
        this.dataList = dataList;
        this.mContext = mContext;

    }




    @Override
    public int getCount() {
        if(dataList==null){
            return 0;
        }
        return dataList.size();
    }

    @Override
    public Order getItem(int position) {
        return dataList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        OrderAdapter.ItemViewHolder holder = null;
        if(convertView==null){
            convertView =LayoutInflater.from(mContext).inflate(R.layout.item_order,parent,false);
            holder = new OrderAdapter.ItemViewHolder(convertView);
            convertView.setTag(holder);
        }else{
            holder = (OrderAdapter.ItemViewHolder) convertView.getTag();
        }
        Order item = dataList.get(position);
        holder.bindData(item);
        return convertView;
    }



    class ItemViewHolder {
        private TextView name,price,time;
        private Button button;
        private View order_view;


        private Order item;


        public ItemViewHolder(View itemView) {

            name = (TextView) itemView.findViewById(R.id.name);
            price = (TextView) itemView.findViewById(R.id.price);
            time=(TextView)itemView.findViewById(R.id.time);
            image=(ImageView) itemView.findViewById(R.id.img);
            button=(Button)itemView.findViewById(R.id.delete);
            order_view=(View)itemView.findViewById(R.id.ordersection);

            image.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.e("按了食堂头像","dd");
                    Intent intent = new Intent(mContext, ShoppingCartActivity.class);

                    intent.putExtra("cid", item.getCid());
                    mContext.startActivity(intent);


                }
            });
     order_view.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {

             Log.e("我爱你红梅品格","我带就打了估计");
             List<OrderItem> orderItems=item.getOrderitems();
             Log.e("数据发送前","d"+orderItems.toString());
             Intent intent=new Intent(mContext, OrderDetailActivity.class);
             Bundle bundle=new Bundle();
             bundle.putSerializable("orderitems",(Serializable)orderItems);
             intent.putExtras(bundle);
             intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
             mContext.startActivity(intent);




         }
     });
button.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Log.e("按了删除键","ddd");

        final Deleteorder deleteorder=new Deleteorder(2,item.getId());
        Log.e("发送删除订单的数据",new Gson().toJson(deleteorder));
        new Thread() {
            public void run() {

                 HttpUtils.sendPostUrl(GlobalData.URL + "orderes",new Gson().toJson(deleteorder), "utf8");
//              mHandler.sendEmptyMessage(1);
            }
        }.start();
        dataList.remove(item);
        notifyDataSetChanged();
    }
});


        }

        public void bindData(Order item){
            this.item = item;
           name.setText(item.getCname());
           Log.e("食堂头像","dd"+item.getCavatar());
            Picasso.with(mContext).load(item.getCavatar()).into(image);
            price.setText(""+item.getPrice());
            time.setText(""+item.getTime());
            SimpleDateFormat sdf =new SimpleDateFormat( " yyyy-MM-dd HH:mm:ss " );
            Calendar cal=Calendar.getInstance();

            cal.add(Calendar.DATE,-1);
            Date time=cal.getTime();
            String str = sdf.format(time);
            String str2=" "+item.getTime();
            if(str.compareTo(str2)<0){
                Log.e("昨天的时间",str);
                Log.e("下单的时间",str2);

                button.setVisibility(View.GONE);}
            else button.setVisibility(View.VISIBLE);
        }


    }}
