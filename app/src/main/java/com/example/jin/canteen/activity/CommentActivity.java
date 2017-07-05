package com.example.jin.canteen.activity;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.jin.canteen.R;
import com.example.jin.canteen.bean.Comment;
import com.example.jin.canteen.util.GlobalData;
import com.example.jin.canteen.util.HttpUtils;
import com.google.gson.Gson;

import static com.example.jin.canteen.util.GlobalData.uid;

public class CommentActivity extends AppCompatActivity {
    private Context mContext;
    private Button submit;
    private EditText comment;
    private String commentres;
    private int mid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment);
        mid=getIntent().getIntExtra("mid",0);
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
                    Toast.makeText(getApplicationContext(),"请输入评价",Toast.LENGTH_SHORT).show();
                }else {
                    new Thread(){
                        public void run(){
                            Comment remark=new Comment(mid,uid,comment.getText().toString());
                            //map.put("userName",.getText().toString());
                            // map.put("userPwd",etPwd.getText().toString());//先以map形式保存然后在httputils里面拆分
                            Log.e("发送留言前",new Gson().toJson(remark));
                           commentres= HttpUtils.sendPostUrl(GlobalData.URL+"comments",new Gson().toJson(remark),"utf8");
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
//                Intent intent=new Intent(mContext,OrderActivity.class);
//                startActivity(intent);
                finish();
            }else {Toast.makeText(getApplicationContext(),"评价失败！",Toast.LENGTH_SHORT).show();
            }
        }

    };
}
