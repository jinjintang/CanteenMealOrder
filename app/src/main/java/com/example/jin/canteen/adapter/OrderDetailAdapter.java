package com.example.jin.canteen.adapter;


import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


import com.example.jin.canteen.R;
import com.example.jin.canteen.activity.CommentActivity;
import com.example.jin.canteen.bean.OrderItem;
import com.squareup.picasso.Picasso;

import java.util.List;

public class OrderDetailAdapter extends BaseAdapter {

    private List<OrderItem> dataList;
    private Context mContext;

    private LayoutInflater mInflater;

    public OrderDetailAdapter(List<OrderItem> dataList, Context mContext) {
        super();
        this.dataList = dataList;
        this.mContext = mContext;

        mInflater = LayoutInflater.from(mContext);
    }




    @Override
    public int getCount() {
        if(dataList==null){
            return 0;
        }
        return dataList.size();
    }

    @Override
    public OrderItem getItem(int position) {
        return dataList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        OrderDetailAdapter.ItemViewHolder holder = null;
        if(convertView==null){
            convertView = mInflater.inflate(R.layout.item_orderitem,parent,false);
            holder = new OrderDetailAdapter.ItemViewHolder(convertView);
            convertView.setTag(holder);
        }else{
            holder = (OrderDetailAdapter.ItemViewHolder) convertView.getTag();
        }
        OrderItem item = dataList.get(position);
        holder.bindData(item);
        return convertView;
    }



    class ItemViewHolder {
        private TextView name,price,amount;
        private Button button;

        private ImageView image;

        private OrderItem item;


        public ItemViewHolder(View itemView) {
            name = (TextView) itemView.findViewById(R.id.name);
            price = (TextView) itemView.findViewById(R.id.price);
            amount=(TextView)itemView.findViewById(R.id.amount);
            image=(ImageView) itemView.findViewById(R.id.img);
            button=(Button)itemView.findViewById(R.id.remark);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent intent = new Intent(mContext, CommentActivity.class);
                    intent.putExtra("MenuId",item.getDishnum());
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    mContext.startActivity(intent);

                }
            });



        }

        public void bindData(OrderItem item){
            this.item = item;
            name.setText(item.getDishname());
            Picasso.with(mContext).load(item.getDishurl()).into(image);
            price.setText(item.getAmount()*item.getPrice()+"");
            amount.setText("× "+item.getAmount());

        }



    }}
