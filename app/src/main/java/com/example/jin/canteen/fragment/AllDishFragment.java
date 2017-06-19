package com.example.jin.canteen.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;

import com.example.jin.canteen.util.DividerDecoration;
import com.example.jin.canteen.adapter.GoodsAdapter;
import com.example.jin.canteen.bean.GoodsItem;
import com.example.jin.canteen.R;
import com.example.jin.canteen.ShoppingCartActivity;
import com.example.jin.canteen.adapter.TypeAdapter;
import com.flipboard.bottomsheet.BottomSheetLayout;

import java.util.ArrayList;

import se.emilsjolander.stickylistheaders.StickyListHeadersListView;




public class AllDishFragment extends BaseFragment {


    //定义组件
    private RecyclerView rvType;
    private ArrayList<GoodsItem> dataList, typeList;
    private TypeAdapter typeAdapter;
    private GoodsAdapter myAdapter;
    private StickyListHeadersListView listView;






    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

     View v=inflater.inflate(R.layout.fragment_all_dish, container, false);
        dataList = GoodsItem.getGoodsList();
        typeList = GoodsItem.getTypeList();

        bottomSheetLayout = (BottomSheetLayout)v.findViewById(R.id.bottomSheetLayout);

        rvType = (RecyclerView) v.findViewById(R.id.typeRecyclerView);
        listView = (StickyListHeadersListView) v.findViewById(R.id.itemListView);

        rvType.setLayoutManager(new LinearLayoutManager(getActivity()));
        typeAdapter = new TypeAdapter((ShoppingCartActivity) getActivity(),typeList,this);
        rvType.setAdapter(typeAdapter);
        rvType.addItemDecoration(new DividerDecoration(getActivity()));

        myAdapter = new GoodsAdapter(dataList, (ShoppingCartActivity) getActivity(),typeAdapter);
        listView.setAdapter(myAdapter);

        listView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {

            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                GoodsItem item = dataList.get(firstVisibleItem);
                if(typeAdapter.selectTypeId != item.typeId) {
                    typeAdapter.selectTypeId = item.typeId;
                    typeAdapter.notifyDataSetChanged();
                    rvType.smoothScrollToPosition(getSelectedGroupPosition(item.typeId));
                }
            }
        });
        //修改的代码片段
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                ((ShoppingCartActivity) getActivity()).fr=new DishDetailFragment(myAdapter.getItem(position));
                getActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .addToBackStack(null)
                        .replace(R.id.fragment, ((ShoppingCartActivity) getActivity()).fr ).commit();

            }
        });//
     return v;

    }
    public int getSelectedGroupPosition(int typeId){
        for(int i=0;i<typeList.size();i++){
            if(typeId==typeList.get(i).typeId){
                return i;
            }
        }
        return 0;
    }

    public void onTypeClicked(int typeId){
        listView.setSelection(getSelectedPosition(typeId));
    }

    private int getSelectedPosition(int typeId){
        int position = 0;
        for(int i=0;i<dataList.size();i++){
            if(dataList.get(i).typeId == typeId){
                position = i;
                break;
            }
        }
        return position;
    }


}
