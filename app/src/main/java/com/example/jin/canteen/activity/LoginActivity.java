package com.example.jin.canteen.activity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.jin.canteen.R;
import com.example.jin.canteen.bean.UpdateStatus;
import com.example.jin.canteen.bean.User;
import com.example.jin.canteen.util.GlobalData;
import com.example.jin.canteen.util.HttpUtils;
import com.example.jin.canteen.util.SharedHelper;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    private Context mContext;
    private Button btnLogin;
    private EditText etName, etPwd;
    private SharedHelper sh;
    private String loginRes;
    private CheckBox checkBox;
    Gson g;

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        findIds();
        initView();
        listener();
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    private void findIds() {
        etName = (EditText) findViewById(R.id.et_name);
        etPwd = (EditText) findViewById(R.id.et_pwd);
        btnLogin = (Button) findViewById(R.id.btn_login);
        sh = new SharedHelper(LoginActivity.this);
        checkBox=(CheckBox)findViewById(R.id.checkBox);
    }

    private void listener() {
        btnLogin.setOnClickListener(this);

    }

    private void initView() {
        mContext = this;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_login:
                if (etName.getText().toString().equals("") || etPwd.getText().toString().equals("")) {
                    Toast.makeText(getApplicationContext(), "输入登录信息", Toast.LENGTH_SHORT).show();
                } else {
                    new Thread() {
                        public void run() {
                            User user=new User(etName.getText().toString(),etPwd.getText().toString());
                            Log.e("用户登陆的发送信息",new Gson().toJson(user));
//                              ;

                            HashMap<String,String> hashMap=new HashMap<String, String>();
                            hashMap.put("username",etName.getText().toString());
                            hashMap.put("password",etPwd.getText().toString());
                            loginRes = HttpUtils.sendPost(GlobalData.URL + "users", hashMap, "utf8");
                            mHandler.sendEmptyMessage(1);
                        }
                    }.start();
                }
                break;

        }
    }


    Handler mHandler = new Handler() {

        @Override
        public void handleMessage(Message msg) {//
            super.handleMessage(msg);
            if (msg.what == 1) {
                 g=new Gson();
                Log.e("哈哈",loginRes);
            UpdateStatus status=g.fromJson(loginRes,UpdateStatus.class);

                if (status.getStatus()==1){//登录成功
                    Toast.makeText(getApplicationContext(),"登录成功~",Toast.LENGTH_SHORT).show();
                    if(checkBox.isChecked()==true)
                    sh.save(etName.getText().toString(),etPwd.getText().toString());
                    if (checkBox.isChecked()==false)
                        sh.clear();
                    GlobalData.uid=status.getUser_id();
                    Intent intent=new Intent(mContext,MainActivity.class);
                    startActivity(intent);
                    finish();
                }else if (status.getStatus()==0){//密码错误
                    Toast.makeText(getApplicationContext(), status.getMessage(),Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(getApplicationContext(),status.getMessage(),Toast.LENGTH_SHORT).show();
                }
            }
        }
    };

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    public Action getIndexApiAction() {
        Thing object = new Thing.Builder()
                .setName("Login Page") // TODO: Define a title for the content shown.
                // TODO: Make sure this auto-generated URL is correct.
                .setUrl(Uri.parse("http://[ENTER-YOUR-URL-HERE]"))
                .build();
        return new Action.Builder(Action.TYPE_VIEW)
                .setObject(object)
                .setActionStatus(Action.STATUS_TYPE_COMPLETED)
                .build();
    }

    @Override
    public void onStart() {
        super.onStart();

            Map<String,String> data = sh.read();
            etName.setText(data.get("username"));
            etPwd.setText(data.get("passwd"));

    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        AppIndex.AppIndexApi.end(client, getIndexApiAction());
        client.disconnect();
    }
}
