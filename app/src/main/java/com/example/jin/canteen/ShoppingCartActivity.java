package com.example.jin.canteen;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.SparseArray;
import android.util.SparseIntArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.LinearInterpolator;
import android.view.animation.TranslateAnimation;

import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jin.canteen.activity.OrderActivity;

import com.example.jin.canteen.adapter.SelectAdapter;
import com.example.jin.canteen.bean.GoodsItem;
import com.example.jin.canteen.fragment.AllDishFragment;
import com.example.jin.canteen.fragment.BaseFragment;

import java.text.NumberFormat;


public class ShoppingCartActivity extends AppCompatActivity implements View.OnClickListener{

    private ImageView imgCart;
    private ViewGroup anim_mask_layout;
    private RecyclerView rvSelected;
    private TextView tvCount,tvCost,tvSubmit;
  //  private BottomSheetLayout bottomSheetLayout;
    private View bottomSheet;



//    private ArrayList<GoodsItem> dataList,typeList;
    private SparseArray<GoodsItem> selectedList;
    private SparseIntArray groupSelect;
public BaseFragment fr;
    //    private GoodsAdapter myAdapter;
    private SelectAdapter selectAdapter;
//    private TypeAdapter typeAdapter;

    private NumberFormat nf;
    private Handler mHanlder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_cart);

        nf = NumberFormat.getCurrencyInstance();
        nf.setMaximumFractionDigits(2);
        mHanlder = new Handler(getMainLooper());
//        dataList = GoodsItem.getGoodsList();
//        typeList = GoodsItem.getTypeList();
        selectedList = new SparseArray<>();
        groupSelect = new SparseIntArray();

        initView();

    }



    private void initView(){

        tvCount = (TextView) findViewById(R.id.tvCount);
        tvCost = (TextView) findViewById(R.id.tvCost);

        tvSubmit  = (TextView) findViewById(R.id.tvSubmit);


        imgCart = (ImageView) findViewById(R.id.imgCart);
        anim_mask_layout = (RelativeLayout) findViewById(R.id.containerLayout);

     fr=new AllDishFragment();
        getSupportFragmentManager()
                .beginTransaction() .add(R.id.fragment, fr).commit();






    }


    public void playAnimation(int[] start_location){
        ImageView img = new ImageView(this);
        img.setImageResource(R.drawable.button_add);
        setAnim(img,start_location);
    }

    private Animation createAnim(int startX,int startY){
        int[] des = new int[2];
        imgCart.getLocationInWindow(des);

        AnimationSet set = new AnimationSet(false);

        Animation translationX = new TranslateAnimation(0, des[0]-startX, 0, 0);
        translationX.setInterpolator(new LinearInterpolator());
        Animation translationY = new TranslateAnimation(0, 0, 0, des[1]-startY);
        translationY.setInterpolator(new AccelerateInterpolator());
        Animation alpha = new AlphaAnimation(1,0.5f);
        set.addAnimation(translationX);
        set.addAnimation(translationY);
        set.addAnimation(alpha);
        set.setDuration(500);

        return set;
    }

    private void addViewToAnimLayout(final ViewGroup vg, final View view,
                                     int[] location) {

        int x = location[0];
        int y = location[1];
        int[] loc = new int[2];
        vg.getLocationInWindow(loc);
        view.setX(x);
        view.setY(y-loc[1]);
        vg.addView(view);
    }
    private void setAnim(final View v, int[] start_location) {

        addViewToAnimLayout(anim_mask_layout, v, start_location);
        Animation set = createAnim(start_location[0],start_location[1]);
        set.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(final Animation animation) {
                mHanlder.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        anim_mask_layout.removeView(v);
                    }
                },100);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        v.startAnimation(set);
    }

    @Override
    public void onClick(View v){
        switch (v.getId()){
            case R.id.bottom:
                showBottomSheet();
                break;
            case R.id.clear:
                clearCart();
                break;
            case R.id.tvSubmit:
                Toast.makeText(ShoppingCartActivity.this, "结算", Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(ShoppingCartActivity.this, OrderActivity.class);


                startActivity(intent);
                break;
            default:
                break;
        }
    }
    //添加商品
    public void add(GoodsItem item,boolean refreshGoodList){

        int groupCount = groupSelect.get(item.typeId);
        if(groupCount==0){
            groupSelect.append(item.typeId,1);
        }else{
            groupSelect.append(item.typeId,++groupCount);
        }

        GoodsItem temp = selectedList.get(item.id);
        if(temp==null){
            item.count=1;
            selectedList.append(item.id,item);
        }else{
            temp.count++;
        }

        update(refreshGoodList);

    }
    //移除商品
    public void remove(GoodsItem item,boolean refreshGoodList){

        int groupCount = groupSelect.get(item.typeId);
        if(groupCount==1){
            groupSelect.delete(item.typeId);
        }else if(groupCount>1){
            groupSelect.append(item.typeId,--groupCount);
        }

        GoodsItem temp = selectedList.get(item.id);
        if(temp!=null){
            if(temp.count<2){
                selectedList.remove(item.id);
            }else{
                item.count--;
            }
        }
        update(refreshGoodList);
    }
    //刷新布局 总价、购买数量等
    private void update(boolean refreshGoodList){
        int size = selectedList.size();
        int count =0;
        double cost = 0;
        for(int i=0;i<size;i++){
            GoodsItem item = selectedList.valueAt(i);
            count += item.count;
            cost += item.count*item.price;
        }

        if(count<1){
            tvCount.setVisibility(View.GONE);
        }else{
            tvCount.setVisibility(View.VISIBLE);
        }
        if(cost > 0){

            tvSubmit.setVisibility(View.VISIBLE);
        }else{
            tvSubmit.setVisibility(View.GONE);

        }


        tvCount.setText(String.valueOf(count));


           // tvTips.setVisibility(View.GONE);
            tvSubmit.setVisibility(View.VISIBLE);


        tvCost.setText(nf.format(cost));
        Log.e("飞扬","dddd");
//        if(myAdapter!=null && refreshGoodList){
//            myAdapter.notifyDataSetChanged();
//        }
        if(selectAdapter!=null){
            selectAdapter.notifyDataSetChanged();
        }
//        if(typeAdapter!=null){
//            typeAdapter.notifyDataSetChanged();
//        }
Log.e("恨安卓","drtjh");
        if(fr.bottomSheetLayout.isSheetShowing() && selectedList.size()<1){
            fr.bottomSheetLayout.dismissSheet();
        }
    }
    //清空购物车
    public void clearCart(){
        selectedList.clear();
        groupSelect.clear();
        update(true);

    }
    //根据商品id获取当前商品的采购数量
    public int getSelectedItemCountById(int id){
        GoodsItem temp = selectedList.get(id);
        if(temp==null){
            return 0;
        }
        return temp.count;
    }
    //根据类别Id获取属于当前类别的数量
    public int getSelectedGroupCountByTypeId(int typeId){
        return groupSelect.get(typeId);
    }
    //根据类别id获取分类的Position 用于滚动左侧的类别列表
//    public int getSelectedGroupPosition(int typeId){
//        for(int i=0;i<typeList.size();i++){
//            if(typeId==typeList.get(i).typeId){
//                return i;
//            }
//        }
//        return 0;
//    }
//
//    public void onTypeClicked(int typeId){
//        listView.setSelection(getSelectedPosition(typeId));
//    }
//
//    private int getSelectedPosition(int typeId){
//        int position = 0;
//        for(int i=0;i<dataList.size();i++){
//            if(dataList.get(i).typeId == typeId){
//                position = i;
//                break;
//            }
//        }
//        return position;
//    }

    private View createBottomSheetView(){
        View view = LayoutInflater.from(this).inflate(R.layout.layout_bottom_sheet,(ViewGroup) getWindow().getDecorView(),false);
        rvSelected = (RecyclerView) view.findViewById(R.id.selectRecyclerView);
        rvSelected.setLayoutManager(new LinearLayoutManager(this));
        TextView clear = (TextView) view.findViewById(R.id.clear);
        clear.setOnClickListener(this);
        selectAdapter = new SelectAdapter(this,selectedList);
        rvSelected.setAdapter(selectAdapter);
        return view;
    }

    private void showBottomSheet(){
        if(bottomSheet==null){
            bottomSheet = createBottomSheetView();
        }
        if(fr.bottomSheetLayout.isSheetShowing()){
           fr.bottomSheetLayout.dismissSheet();
        }else {
            if(selectedList.size()!=0){
                fr.bottomSheetLayout.showWithSheetView(bottomSheet);
            }
        }
    }
}
