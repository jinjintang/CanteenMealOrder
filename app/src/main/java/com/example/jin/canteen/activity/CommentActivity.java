package com.example.jin.canteen.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import com.example.jin.canteen.R;

import java.util.HashMap;

public class CommentActivity extends AppCompatActivity {
    private Context mContext;
    private Button submit;
    private EditText comment;
    private String commentres;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment);
        findIds();
        initView();
        listener();
    } private void findIds() {
        comment=(EditText)findViewById(R.id.edit_content);

        submit=(Button)findViewById(R.id.edit_finish);

    }

    private void initView() {
        mContext=this;
    }
    private void listener() {
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (comment.getText().toString().equals("")){
                    Toast.makeText(getApplicationContext(),"输入登录信息",Toast.LENGTH_SHORT).show();
                }else {
                    new Thread(){
                        public void run(){
                            HashMap<String,String> map=new HashMap<String, String>();
                            //map.put("userName",.getText().toString());
                            // map.put("userPwd",etPwd.getText().toString());//先以map形式保存然后在httputils里面拆分
//                            commentres= HttpUtils.sendPost(GlobalData.URL+"comment/addcomment",map,"utf8");utf8
                            mHandler.sendEmptyMessage(1);
                        }
                    }.start();
                }
            }
        });

    }
    Handler mHandler=new Handler(){

        @Override
        public void handleMessage(Message msg) {//
            super.handleMessage(msg);
            if (msg.what==1){
//                Gson gson=new Gson();
//                UpdateStatus status=gson.fromJson(commentres,UpdateStatus.class);
//                if (status.getI()==1){
                Toast.makeText(getApplicationContext(),"评价成功~",Toast.LENGTH_SHORT).show();
                //GlobalData.USER_NAME=etName.getText().toString();
                Intent intent=new Intent(mContext,OrderActivity.class);
                startActivity(intent);
                finish();
            }else {Toast.makeText(getApplicationContext(),"评价失败！",Toast.LENGTH_SHORT).show();
            }
        }

    };
}
