package com.example.jin.canteen.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.Toast;

import com.example.jin.canteen.R;
import com.example.jin.canteen.activity.ShoppingCartActivity;
import com.example.jin.canteen.adapter.GoodsAdapter;
import com.example.jin.canteen.adapter.TypeAdapter;
import com.example.jin.canteen.bean.Canteen;
import com.example.jin.canteen.bean.Dish;
import com.example.jin.canteen.bean.GoodsItem;
import com.example.jin.canteen.util.DividerDecoration;
import com.example.jin.canteen.util.GlobalData;
import com.example.jin.canteen.util.HttpUtils;
import com.flipboard.bottomsheet.BottomSheetLayout;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import se.emilsjolander.stickylistheaders.StickyListHeadersListView;

import static com.example.jin.canteen.util.GlobalData.categoryMap;


public class AllDishFragment extends BaseFragment {


    //定义组件
    private RecyclerView rvType;
    private ArrayList<GoodsItem> dataList,typeList;
    private ShoppingCartActivity sp;

    private StickyListHeadersListView listView;
    private int mmid;
    private int cid;
    String dishrequest;
    public TypeAdapter typeAdapter;
    public GoodsAdapter myAdapter;




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        Bundle bundle = getArguments();//从activity传过来的Bundle  
        View v = inflater.inflate(R.layout.fragment_all_dish, container, false);


        bottomSheetLayout = (BottomSheetLayout) v.findViewById(R.id.bottomSheetLayout);

        rvType = (RecyclerView) v.findViewById(R.id.typeRecyclerView);
        listView = (StickyListHeadersListView) v.findViewById(R.id.itemListView);

        rvType.setLayoutManager(new LinearLayoutManager(getActivity()));
        if (bundle != null) {
            mmid = bundle.getInt("mmid");
            cid = bundle.getInt("cid");
        }

        new Thread() {
            public void run() {


                dishrequest = HttpUtils.sendGet(GlobalData.URL + "canteens/" + cid, "utf8");
                mHandler.sendEmptyMessage(1);
            }
        }.start();
        return v;
    }


    public int getSelectedGroupPosition(int typeId) {
        for (int i = 0; i < typeList.size(); i++) {
            if (typeId == typeList.get(i).typeId) {
                return i;
            }
        }
        return 0;
    }
    public int getSelectedDishPosition(int Id) {
        for (int i = 0; i < typeList.size(); i++) {
            if (Id == typeList.get(i).id) {
                return i;
            }
        }
        return 0;
    }

    public void onTypeClicked(int typeId) {
        listView.setSelection(getSelectedPosition(typeId));

    }

    private int getSelectedPosition(int typeId) {
        int position = 0;
        for (int i = 0; i < dataList.size(); i++) {
            if (dataList.get(i).typeId == typeId) {
                position = i;
                break;
            }
        }

        return position;
    }

    Handler mHandler = new Handler() {

        @Override
        public void handleMessage(Message msg) {//
            super.handleMessage(msg);
            if (msg.what == 1) {
                try {


                Gson gson = new Gson();
                Canteen canteen = gson.fromJson(dishrequest, Canteen.class);
                List<Dish> dishes = canteen.getDishes();
                Collections.sort(dishes);
                typeList = new ArrayList<GoodsItem>();
                dataList = new ArrayList<GoodsItem>();
                if (dishes.size()!= 0) {
                    for (int i = 0; i < dishes.size(); i++) {
                        Dish dish = dishes.get(i);
                        Log.e("种类","dd"+dish.getCategroy_id());
                        GoodsItem goodsItem = new GoodsItem(dish.getId(), Double.parseDouble(dish.getPrice()), dish.getName(), dish.getCategroy_id(), categoryMap.get(dish.getCategroy_id()),dish.getAvatar());
                        dataList.add(goodsItem);
                        if (i == 0 || dishes.get(i - 1).getCategroy_id() != dish.getCategroy_id()) {
                            typeList.add(goodsItem);


                        }
                    }


                    typeAdapter = new TypeAdapter((ShoppingCartActivity) getActivity(), typeList, AllDishFragment.this);
                    rvType.setAdapter(typeAdapter);
                    rvType.addItemDecoration(new DividerDecoration(getActivity()));





                    myAdapter = new GoodsAdapter(dataList, (ShoppingCartActivity) getActivity(), typeAdapter);
                    listView.setAdapter(myAdapter);
                    listView.setSelection(getSelectedDishPosition(mmid));
                    listView.setOnScrollListener(new AbsListView.OnScrollListener() {
                        @Override
                        public void onScrollStateChanged(AbsListView view, int scrollState) {

                        }

                        @Override
                        public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                            GoodsItem item = dataList.get(firstVisibleItem);
                            if (typeAdapter.selectTypeId != item.typeId) {
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

                            ((ShoppingCartActivity) getActivity()).fr = new DishDetailFragment(myAdapter.getItem(position));
                            getActivity().getSupportFragmentManager()
                                    .beginTransaction()
                                    .addToBackStack(null)
                                    .replace(R.id.fragment, ((ShoppingCartActivity) getActivity()).fr).commit();


                        }
                    });//


                }}
            catch (Exception e){

                Toast.makeText(getActivity(), "没有这个食堂" , Toast.LENGTH_SHORT).show();

            }
            }

            }


        }

        ;


}
