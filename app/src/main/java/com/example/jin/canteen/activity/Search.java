package com.example.jin.canteen.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jin.canteen.R;
import com.example.jin.canteen.adapter.MyBaseExpandableListAdapter;
import com.example.jin.canteen.bean.Canteen;
import com.example.jin.canteen.bean.Dish;
import com.example.jin.canteen.bean.SearchBean;
import com.example.jin.canteen.util.GlobalData;
import com.example.jin.canteen.util.HttpUtils;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.example.jin.canteen.util.GlobalData.canteenMap;


public class Search extends Activity{
    private TextView tvCancel;
    private EditText etSearch;
    private ImageView imgSearch;
    private LinearLayout searchMain;
    private ProgressBar progressBar;
    private String result;
    private Activity mActivity;
    private List<Canteen> gData = null;
    private List<List<Dish>> iData = null;
    private List<Dish> lData = null;
    private Context mContext;
    private ExpandableListView searchList;
    private MyBaseExpandableListAdapter myAdapter = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        setTheme(GlobalData.MY_THEME);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search);
        mContext=this;
        mActivity=this;

        imgSearch=(ImageView)findViewById(R.id.img_search);
        tvCancel=(TextView)findViewById(R.id.tv_cancel);
        etSearch=(EditText)findViewById(R.id.et_search);
        etSearch.addTextChangedListener(watcher);

        progressBar=(ProgressBar)findViewById(R.id.progressBar);
        searchList =(ExpandableListView)findViewById(R.id.searchlist);
        tvCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(mContext,MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
        imgSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (etSearch.getText().toString()!=""){
                new Thread(){
                    public void run(){
//                        String keyword= etSearch.getText().toString();
////                        try {
////                            keyword = new String(etSearch.getText().toString().getBytes("GBK"),"UTF-8");
////                        } catch (UnsupportedEncodingException e) {
////                            e.printStackTrace();
////                        }
                        SearchBean searchBean=new SearchBean(1, etSearch.getText().toString());
                        Log.e("搜索菜品发送的字符串",new Gson().toJson(searchBean));
                        result= HttpUtils.sendPostUrl(GlobalData.URL+"dishes",new Gson().toJson(searchBean),"utf8");
                        Log.e("搜索菜品返回的字符串",result+"不会什么都没有吧");
                        mHandler.sendEmptyMessage(1);
                    }
                }.start();}
            }
        });



    }


    private TextWatcher watcher= new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            if (etSearch.getText().toString().equals("")){
                searchList.setVisibility(View.GONE);

            }
        }

        @Override
        public void afterTextChanged(Editable editable) {

        }
    };

    private List<Dish> list;
    Handler mHandler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what==1){
                Gson gson=new Gson();
              try {
                  list = gson.fromJson(result, new TypeToken<List<Dish>>() {
                  }.getType());
                  //数据准备
                  Collections.sort(list);
                  // Log.e("搜索的结果",list.toString());
                  gData = new ArrayList<Canteen>();
                  iData = new ArrayList<List<Dish>>();
                  List<Dish> dishes = null;
                  Canteen canteen;
                  for (int i = 0; i < list.size(); i++) {
                      Dish dish = list.get(i);
                      if (i == 0 || dish.getCid() != list.get(i - 1).getCid()) {
                          canteen = new Canteen(dish.getCid(), dish.getCname(), canteenMap.get(dish.getAvatar()));
                          gData.add(canteen);
                          dishes = new ArrayList<Dish>();
                          iData.add(dishes);
                          dishes.add(list.get(i));

                      } else dishes.add(list.get(i));


                  }


                  myAdapter = new MyBaseExpandableListAdapter(gData, iData, mContext);
                  searchList.setAdapter(myAdapter);


                  //为列表设置点击事件
                  searchList.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
                      @Override
                      public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                          Intent intent = new Intent(Search.this, ShoppingCartActivity.class);
                          intent.putExtra("mid", iData.get(groupPosition).get(childPosition).getId());
                          intent.putExtra("cid", iData.get(groupPosition).get(childPosition).getCid());
                          startActivity(intent);
                          // Toast.makeText(mContext, "你点击了：" + iData.get(groupPosition).get(childPosition).getDishname(), Toast.LENGTH_SHORT).show();
                          return true;
                      }
                  });
              }catch (Exception e){
                  Toast.makeText(mContext, "没有这个菜" , Toast.LENGTH_SHORT).show();


              }

    }else {
                    Toast.makeText(getApplicationContext(),"没有搜到任何信息~",Toast.LENGTH_SHORT).show();
                }
            }

    };
}
