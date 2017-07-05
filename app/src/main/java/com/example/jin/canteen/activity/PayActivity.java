package com.example.jin.canteen.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jin.canteen.R;
import com.example.jin.canteen.bean.User;
import com.example.jin.canteen.bean.submitOrder;
import com.example.jin.canteen.util.GlobalData;
import com.example.jin.canteen.util.HttpUtils;
import com.google.gson.Gson;

import java.util.List;

import static com.example.jin.canteen.util.GlobalData.canteenMap;
import static com.example.jin.canteen.util.GlobalData.uid;

public class PayActivity extends AppCompatActivity {
private TextView price;
    private RadioButton lunch;
    private RadioButton dinner;
    private Button button;
    private List<submitOrder.OrderItemBean> orderitems;
    private int cid;
    private double yuan;
    private String cname;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay);
       price=(TextView)findViewById(R.id.textView);
        lunch=(RadioButton)findViewById(R.id.lunch);
        dinner=(RadioButton)findViewById(R.id.dinner);
        button=(Button)findViewById(R.id.button);
      cid=getIntent().getIntExtra("cid",0);
        cname=getIntent().getStringExtra("cname");
        yuan=getIntent().getDoubleExtra("money",0.00);
        price.setText(yuan+"元");
      orderitems=(List<submitOrder.OrderItemBean>)getIntent().getSerializableExtra("orderitems");

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               int eattime;
                if(lunch.isChecked())eattime=2;
                else eattime=3;
                Log.e("为什么这里的食堂编号变了","dd"+cid);
                final submitOrder order=new submitOrder(eattime,cid,uid,(float)yuan,0,cname,canteenMap.get(cid),orderitems);
                new Thread() {
                    public void run() {

                        try {
                        Gson g= new Gson();

                        String userinfo=  HttpUtils.sendGet(GlobalData.URL + "users/"+ GlobalData.uid, "utf8");
                       GlobalData.user=g.fromJson(userinfo,User.class);
                        double money=Double.parseDouble(GlobalData.user.getAccount());
                       money-=yuan;
                       if(money<0){
                           Toast.makeText(getApplicationContext(),"余额不足",Toast.LENGTH_SHORT).show();


                       }
                            else{
                            GlobalData.user.setAccount(money+"");




                        Log.e("提交订单发送数据",g.toJson(order));
                      String submit=  HttpUtils.sendPostUrl(GlobalData.URL + "orderes", g.toJson(order), "utf8");
                      Log.e("提交订单返回数据",submit+"不会什么都没有吧");

                          mHandler.sendEmptyMessage(1);}}
                        catch (Exception e){Log.e("错了",e.toString());}
                    }
                }.start();

            }
        });


    }


    Handler mHandler = new Handler() {

        @Override
        public void handleMessage(Message msg) {//
            super.handleMessage(msg);

                    Toast.makeText(getApplicationContext(),"下单成功~",Toast.LENGTH_SHORT).show();

                    Intent intent=new Intent(PayActivity.this,MainActivity.class);
                  GlobalData.MAIN_TAG="2";
                    startActivity(intent);


        }
    };
}
