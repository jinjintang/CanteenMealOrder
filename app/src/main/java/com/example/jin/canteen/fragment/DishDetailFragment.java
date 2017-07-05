package com.example.jin.canteen.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.jin.canteen.R;
import com.example.jin.canteen.activity.ShoppingCartActivity;
import com.example.jin.canteen.adapter.CommentAdapter;
import com.example.jin.canteen.bean.Comment;
import com.example.jin.canteen.bean.Dish;
import com.example.jin.canteen.bean.GoodsItem;
import com.example.jin.canteen.util.GlobalData;
import com.example.jin.canteen.util.HttpUtils;
import com.flipboard.bottomsheet.BottomSheetLayout;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import java.util.List;

@SuppressLint("ValidFragment")
public class DishDetailFragment extends BaseFragment {
    //定义组件
    private TextView dishname;
    private TextView introduction;
    private TextView price;
    private Button buy;
    private int dishnum;
    private ListView comments;
    private List<Comment> commentlist;
    private GoodsItem item;
private String dishrequest;
    private    ImageView dishimg;
    public DishDetailFragment() {
    }

    public DishDetailFragment(GoodsItem item) {
        this.item = item;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_dish_detail, container, false);
        bottomSheetLayout = (BottomSheetLayout)v.findViewById(R.id.bottomSheetLayout);
        dishname=(TextView)v.findViewById(R.id.tv_menu_name);
        introduction=(TextView)v.findViewById(R.id.tv_menu_introduction);
        price=(TextView)v.findViewById(R.id.tv_menu_price);
        buy=(Button)v.findViewById(R.id.addtoshoppingcart);
      dishimg = (ImageView) v.findViewById(R.id.iv_menu_pic);
        comments=(ListView)v.findViewById(R.id.comments);
        dishname.setText(item.name);
        new Thread() {
            public void run() {


                dishrequest = HttpUtils.sendGet(GlobalData.URL + "dishes/" + item.id, "utf8");
                mHandler.sendEmptyMessage(1);
            }
        }.start();


    return v;
    }
    Handler mHandler = new Handler() {

        @Override
        public void handleMessage(Message msg) {//
            super.handleMessage(msg);
            if (msg.what == 1) {
                Gson gson = new Gson();
                Dish dish = gson.fromJson(dishrequest, Dish.class);


                Log.e("不知道内存有没有泄露","java全局变量能保存多久");
                introduction.setText(dish.getIntroduciton());
                price.setText(dish.getPrice());
                Picasso.with(getActivity()).load(dish.getAvatar()).into(dishimg);
                commentlist= dish.getComments();

                CommentAdapter madapter=new CommentAdapter( (ShoppingCartActivity) getActivity(),commentlist);
               Log.e("size是多少",madapter.getCount()+"dd");
                comments.setAdapter(madapter);
                buy.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        int[] loc = new int[2];
                        v.getLocationInWindow(loc);

                        ShoppingCartActivity activity = (ShoppingCartActivity) getActivity();
                        activity.add(item,false);
                        activity.playAnimation(loc);
                    }
                });
                }
            }




    }

            ;




    }

