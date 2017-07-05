package com.example.jin.canteen.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.jin.canteen.R;
import com.example.jin.canteen.bean.User;
import com.example.jin.canteen.util.GlobalData;
import com.example.jin.canteen.util.HttpUtils;
import com.google.gson.Gson;

import static com.example.jin.canteen.util.GlobalData.uid;
import static com.example.jin.canteen.util.GlobalData.user;

public class UserInfo extends AppCompatActivity {
private TextView account;
    private String orderRes;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info);
        new Thread() {

            public void run() {

                orderRes = HttpUtils.sendGet(GlobalData.URL + "users/"+uid, "utf8");





                mHandler.sendEmptyMessage(2);
            }
        }.start();

    }
    Handler mHandler = new Handler() {

        @Override
        public void handleMessage(Message msg) {//
            super.handleMessage(msg);

            Gson g=new Gson();
           try {
               user = g.fromJson(orderRes, User.class);

            account=(TextView)findViewById(R.id.money);
            account.setText( GlobalData.user.getAccount());
           }catch (Exception e){}

        }




};






}
