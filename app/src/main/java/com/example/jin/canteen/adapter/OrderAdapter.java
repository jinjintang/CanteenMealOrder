package com.example.jin.canteen.adapter;


import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


import com.example.jin.canteen.R;
import com.example.jin.canteen.Request.OrderRequest;
import com.example.jin.canteen.activity.OrderDetailActivity;
import com.example.jin.canteen.bean.Order;
import com.squareup.picasso.Picasso;


import java.util.ArrayList;

public class OrderAdapter extends BaseAdapter {

    private ArrayList<Order> dataList;
    private Context mContext;

    private ImageView image;


    public OrderAdapter(ArrayList<Order> dataList,Context mContext) {
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
                }
            });
     order_view.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {


             Intent intent=new Intent(mContext,OrderDetailActivity.class);
             intent.putExtra("OrderId",item.getOrder_id());
             intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
             mContext.startActivity(intent);


         }
     });
button.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Log.e("按了删除键","ddd");
        dataList.remove(item);
        OrderRequest.deleteorder(item.getOrder_id());
        notifyDataSetChanged();
    }
});


        }

        public void bindData(Order item){
            this.item = item;
            name.setText(item.getCanteenname());
            Picasso.with(mContext).load(item.getCanteenurl()).into(image);
            price.setText(""+item.getOrder_price());
            time.setText(""+item.getOrder_time());

        }


    }}
