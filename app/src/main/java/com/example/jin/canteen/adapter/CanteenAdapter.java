package com.example.jin.canteen.adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import com.example.jin.canteen.R;
import com.example.jin.canteen.activity.CanteenActivity;
import com.example.jin.canteen.bean.Canteen;
import com.squareup.picasso.Picasso;


import java.util.List;

public class CanteenAdapter extends BaseAdapter {

    private List<Canteen> dataList;
    private Context mContext;


    public CanteenAdapter(CanteenActivity mContext, List<Canteen> dataList) {
        super();
        this.dataList=dataList;
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
    public Canteen getItem(int position) {
        return dataList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        CanteenAdapter.ItemViewHolder holder = null;
        if(convertView==null){
            convertView =LayoutInflater.from(mContext).inflate(R.layout.item_canteen,parent,false);
            holder = new ItemViewHolder(convertView);
            convertView.setTag(holder);
        }else{
            holder = (CanteenAdapter.ItemViewHolder) convertView.getTag();
        }
        Canteen item = dataList.get(position);
        holder.bindData(item);
        return convertView;
    }



    class ItemViewHolder {
        private TextView name;
        private ImageView canteenpic;



        private Canteen item;

        public ItemViewHolder(View itemView) {

            name = (TextView) itemView.findViewById(R.id.name);
            canteenpic=(ImageView)itemView.findViewById(R.id.img);



        }

        public void bindData(Canteen item){
            this.item = item;
            name.setText(item.getNamae());

            Picasso.with(mContext).load(item.getPicurl()).into(canteenpic);



        }


    }}
