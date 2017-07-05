package com.example.jin.canteen.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jin.canteen.R;
import com.example.jin.canteen.adapter.CanteenAdapter;
import com.example.jin.canteen.adapter.OrderAdapter;
import com.example.jin.canteen.bean.AllCanteens;
import com.example.jin.canteen.bean.AllCategorys;
import com.example.jin.canteen.bean.Canteen;
import com.example.jin.canteen.bean.Categroy;
import com.example.jin.canteen.bean.Order;
import com.example.jin.canteen.bean.User;
import com.example.jin.canteen.util.GlobalData;
import com.example.jin.canteen.util.HttpUtils;
import com.example.jin.canteen.util.PerfectClickListener;
import com.example.jin.canteen.util.TopMenuHeader;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.example.jin.canteen.util.GlobalData.categoryMap;
import static com.example.jin.canteen.util.GlobalData.uid;
import static com.example.jin.canteen.util.GlobalData.user;

/**
 * 主界面的Activity
 */
public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {
    private ListView lvOrder;
    private List<Order> orders;
    private List<Canteen> canteens;
    private ListView canteenlist;

    private FrameLayout contnet;
    private List<Fragment> fragments;
    private Context mContext;
    private ProgressBar mProgress;
    private ListView mListView;
    private SwipeRefreshLayout mRefresh;
    private String result;

    private int[] itemId = {R.id.navigation_home, R.id.navigation_order, R.id.navigation_mine};
    private ArrayList<String> tags = new ArrayList<>(Arrays.asList("1", "2", "3"));
    private Activity mActivity;
    private ArrayList<String> mImages;

private String orderRes;
    /**
     * 加载菜谱页面
     */
    private void loadHomePage() {
        Log.e("load", "load");
        new Thread() {

            public void run() {
                String categoryRes = HttpUtils.sendGet(GlobalData.URL + "categroys", "utf8");
                Log.e("获取所有食堂返回的信息",categoryRes);
                Gson g=new Gson();
                AllCategorys allCategorys=g.fromJson(categoryRes,AllCategorys.class);
                try{


                    List<Categroy> categroys=allCategorys.getCategroy();
                    for(int i=0;i<categroys.size();i++){
                        categoryMap.put(categroys.get(i).getId(),categroys.get(i).getName());

                    }
                }catch (Exception e){
                    System.out.print(e.toString());

                }
                String canteensRes = HttpUtils.sendGet(GlobalData.URL + "canteens", "utf8");
                Log.e("获取所有食堂返回的信息",canteensRes);


                canteens=g.fromJson(canteensRes,AllCanteens.class).getCanteen();

                mHandler.sendEmptyMessage(1);
            }
        }.start();








    }



    /**
     * 加载订单
     */
    private void loadOrderPage() {
        new Thread() {

            public void run() {

                orderRes = HttpUtils.sendGet(GlobalData.URL + "users/"+uid, "utf8");





                mHandler.sendEmptyMessage(2);
            }
        }.start();

    }
    /**
     * 加载个人中心
     */
    private void loadMyPage() {
        View mInfalte=View.inflate(this,R.layout.my_page,null);
        TopMenuHeader top=new TopMenuHeader(mInfalte);
        top.topMenuRight.setVisibility(View.GONE);
        top.topMenuLeft.setVisibility(View.GONE);
        top.topMenuTitle.setText("个人中心");
        contnet.addView(mInfalte);
        TextView tvLogout = (TextView) mInfalte.findViewById(R.id.tv_logout);
        LinearLayout btnLogout=(LinearLayout)mInfalte.findViewById(R.id.select_logout);

        LinearLayout layoutOut=(LinearLayout)mInfalte.findViewById(R.id.select_logout);
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        LinearLayout mInfo = (LinearLayout) mInfalte.findViewById(R.id.person_info);
        mInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(mContext,UserInfo.class);
                startActivity(intent);
            }
        });
       
        layoutOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(mContext,LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }


    /**
     * 进入该界面第一个执行的方法onCreate（），里面初始化一些基本资源
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // ThemeUtil.onActivityCreateSetTheme(this);
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);//去掉标题栏
        setContentView(R.layout.activity_main);
        mContext = this;
        mActivity = this;
        contnet = (FrameLayout) findViewById(R.id.content);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(this);//导航栏监听事件
        if (GlobalData.MAIN_TAG.equals("1")) {
            loadHomePage();
        } else if (GlobalData.MAIN_TAG.equals("2")) {
            loadOrderPage();
        } else {
            loadHomePage();
        }
        navigation.setSelectedItemId(itemId[tags.indexOf(GlobalData.MAIN_TAG)]);//改变导航选择的全局变量
    }

    private int LOAD_NEWSES = 1;


    /**
     * 底部导航的监听
     *
     * @param item
     * @return
     */
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.navigation_home:
                contnet.removeAllViews();
                GlobalData.MAIN_TAG = "1";
                Log.e("tag", "home");
                loadHomePage();
                return true;
            case R.id.navigation_order:
                contnet.removeAllViews();
                GlobalData.MAIN_TAG = "2";
                Log.e("tag", "like");
                loadOrderPage();
                return true;
            case R.id.navigation_mine:
                contnet.removeAllViews();
                GlobalData.MAIN_TAG = "3";
                loadMyPage();
                return true;
        }
        return false;
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {//按回退键
            moveTaskToBack(true);//移动任务包含此活动活动栈的后面。在任务活动的顺序不变

            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
    Handler mHandler = new Handler() {

        @Override
        public void handleMessage(Message msg) {//
            super.handleMessage(msg);
            if (msg.what == 1) {
                View topInflate = View.inflate(MainActivity.this, R.layout.search_bar, null);


                LinearLayout searchBar = (LinearLayout) topInflate.findViewById(R.id.top_search_bar);

                View mInflate = View.inflate(MainActivity.this, R.layout.canteen_page, null);
                contnet.addView(topInflate);
                contnet.addView(mInflate);
                canteenlist=(ListView)mInflate.findViewById(R.id.canteenlist);
                final CanteenAdapter canteenAdapter=new CanteenAdapter(MainActivity.this,canteens);
                canteenlist.setAdapter(canteenAdapter);
                canteenlist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {




                        Intent intent=new Intent(MainActivity.this, ShoppingCartActivity.class);
                        intent.putExtra("cid",canteenAdapter.getItem(position).getId());
                        intent.putExtra("cname",canteenAdapter.getItem(position).getName());
                        Log.e("这里的食堂编号是", "哈哈"+canteenAdapter.getItem(position).getId());
                        startActivity(intent);
                    }
                });



                searchBar.setOnClickListener(new PerfectClickListener() {
                    @Override
                    protected void onNoDoubleClick(View v) {
                        Intent intent=new Intent(MainActivity.this,Search.class);
                        startActivity(intent);
                        finish();
                    }
                });

//
            }
            else if(msg.what==2){
                View mInfalte=View.inflate(MainActivity.this,R.layout.order_page,null);
                TopMenuHeader top=new TopMenuHeader(mInfalte);
                top.topMenuRight.setVisibility(View.GONE);
                top.topMenuLeft.setVisibility(View.GONE);
                top.topMenuTitle.setText("历史订单");
                contnet.addView(mInfalte);
                lvOrder=(ListView)mInfalte.findViewById(R.id.orderlist);
                try {
                    Gson g = new Gson();
                    user = g.fromJson(orderRes, User.class);
                    orders = user.getOrders();
                    final OrderAdapter orderAdapter = new OrderAdapter(orders, MainActivity.this);
                    lvOrder.setAdapter(orderAdapter);
                }catch (Exception e){

                    Toast.makeText(MainActivity.this, "您没有订单" , Toast.LENGTH_SHORT).show();
                }
            }



        }
    };


}