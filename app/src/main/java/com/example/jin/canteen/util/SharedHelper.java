package com.example.jin.canteen.util;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Jay on 2015/9/2 0002.
 */
public class SharedHelper {

    private Context mContext;
   private SharedPreferences sp;
    private SharedPreferences.Editor editor;
    public SharedHelper() {
    }

    public SharedHelper(Context mContext) {
        this.mContext = mContext;
        sp= mContext.getSharedPreferences("mysp",0);
        editor = sp.edit();
    }


    //定义一个保存数据的方法
    public void save(String username, String passwd) {

        editor.putString("username", username);
        editor.putString("passwd", passwd);
        editor.commit();

    }
    public void clear() {
        editor.clear().commit();

    }
    //定义一个读取SP文件的方法
    public Map<String, String> read() {
        Map<String, String> data = new HashMap<String, String>();

        data.put("username", sp.getString("username", ""));
        data.put("passwd", sp.getString("passwd", ""));
        return data;
    }
}
