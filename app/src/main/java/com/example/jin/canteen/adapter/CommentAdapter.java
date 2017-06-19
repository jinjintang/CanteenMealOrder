package com.example.jin.canteen.adapter;


import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


import com.example.jin.canteen.R;
import com.example.jin.canteen.bean.Comment;


import java.util.List;

public class CommentAdapter extends BaseAdapter {

    private List<Comment> dataList;
    private Context mContext;


    public CommentAdapter(FragmentActivity mContext, List<Comment> dataList) {
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
    public Comment getItem(int position) {
        return dataList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
       CommentAdapter.ItemViewHolder holder = null;
        if(convertView==null){
            convertView =LayoutInflater.from(mContext).inflate(R.layout.item_comment,parent,false);
            holder = new ItemViewHolder(convertView);
            convertView.setTag(holder);
        }else{
            holder = (CommentAdapter.ItemViewHolder) convertView.getTag();
        }
        Comment item = dataList.get(position);
        holder.bindData(item);
        return convertView;
    }



    class ItemViewHolder {
        private TextView content;



        private Comment item;


        public ItemViewHolder(View itemView) {

          content = (TextView) itemView.findViewById(R.id.content);




        }

        public void bindData(Comment item){
            this.item = item;
            content.setText(item.getContent());




        }


    }}
