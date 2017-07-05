package com.example.jin.canteen.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.jin.canteen.R;
import com.example.jin.canteen.bean.Canteen;
import com.example.jin.canteen.bean.Dish;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Jay on 2015/9/25 0025.
 */
public class MyBaseExpandableListAdapter extends BaseExpandableListAdapter {

    private List<Canteen> gData;
    private List<List<Dish>> iData;
    private Context mContext;

//    public MyBaseExpandableListAdapter(List<Canteen> gData, List<List<Dish>> iData, Context mContext) {
//
//    }

    public MyBaseExpandableListAdapter(List<Canteen> gData, List<List<Dish>> iData, Context mContext) {
        this.gData = gData;
        this.iData = iData;
        this.mContext = mContext;
    }

    @Override
    public int getGroupCount() {
        return gData.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return iData.get(groupPosition).size();
    }

    @Override
    public Canteen getGroup(int groupPosition) {
        return gData.get(groupPosition);
    }

    @Override
    public Dish getChild(int groupPosition, int childPosition) {
        return iData.get(groupPosition).get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    //取得用于显示给定分组的视图. 这个方法仅返回分组的视图对象
    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {

        ViewHolderGroup groupHolder;
        if(convertView == null){
            convertView = LayoutInflater.from(mContext).inflate(
                    R.layout.item_canteen, parent, false);
            groupHolder = new ViewHolderGroup();
            groupHolder.img=(ImageView)convertView.findViewById(R.id.img);
            groupHolder.tv_group_name= (TextView) convertView.findViewById(R.id.name);
            convertView.setTag(groupHolder);
        }else{
            groupHolder = (ViewHolderGroup) convertView.getTag();
        }
        groupHolder.tv_group_name.setText(gData.get(groupPosition).getName());
        Picasso.with(mContext).load(gData.get(groupPosition).getAvatar()).into(groupHolder.img
        );

        return convertView;
    }

    //取得显示给定分组给定子位置的数据用的视图
    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        ViewHolderItem itemHolder;
        if(convertView == null){
            convertView = LayoutInflater.from(mContext).inflate(
                    R.layout.item_searchdish, parent, false);
            itemHolder = new ViewHolderItem();
            itemHolder.img_icon = (ImageView) convertView.findViewById(R.id.img);
            itemHolder.tv_name = (TextView) convertView.findViewById(R.id.tvName);
            itemHolder.tv_price=(TextView) convertView.findViewById(R.id.tvPrice);

            convertView.setTag(itemHolder);
        }else{
            itemHolder = (ViewHolderItem) convertView.getTag();
        }

        Picasso.with(mContext).load(iData.get(groupPosition).get(childPosition).getAvatar()).into(itemHolder.img_icon
        );
        Log.e("难道没有名字吗","简直不敢相信"+iData.get(groupPosition).get(childPosition).getName());
       itemHolder.tv_name.setText(iData.get(groupPosition).get(childPosition).getName());
        itemHolder.tv_price.setText("￥"+iData.get(groupPosition).get(childPosition).getPrice());
        return convertView;
    }

    //设置子列表是否可选中
    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }


    private static class ViewHolderGroup{
        private TextView tv_group_name;
        private ImageView img;

    }

    private static class ViewHolderItem{
        private ImageView img_icon;
        private TextView tv_name;
        private TextView tv_price;
    }

}
