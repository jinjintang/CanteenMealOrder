package com.example.jin.canteen.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.jin.canteen.R;
import com.example.jin.canteen.Request.CommentRequest;
import com.example.jin.canteen.ShoppingCartActivity;
import com.example.jin.canteen.adapter.CommentAdapter;
import com.example.jin.canteen.bean.Comment;
import com.example.jin.canteen.bean.GoodsItem;
import com.flipboard.bottomsheet.BottomSheetLayout;

import java.util.List;


public class DishDetailFragment extends BaseFragment {
    //定义组件
    private TextView dishname;
    private TextView introduction;
    private ImageView dishimg;
    private TextView price;
    private Button buy;
    private int dishnum;
    private ListView comments;
    private List<Comment> commentlist;
    private GoodsItem item;

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
        dishimg=(ImageView)v.findViewById(R.id.iv_menu_pic);
        comments=(ListView)v.findViewById(R.id.comments);
        commentlist= CommentRequest.getbydishnum(dishnum);
        CommentAdapter myadapter=new CommentAdapter(getActivity(),commentlist);
        comments.setAdapter(myadapter);
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
    return v;
    }
    }

