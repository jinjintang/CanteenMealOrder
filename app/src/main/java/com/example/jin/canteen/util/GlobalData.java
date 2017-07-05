package com.example.jin.canteen.util;

import com.example.jin.canteen.R;
import com.example.jin.canteen.bean.User;

import java.util.HashMap;

/**
 * Created by jin on 2017/6/15.
 */

public class GlobalData {
    public static int uid ;
    public static String MAIN_TAG="1";
    public static String URL="http://106.14.167.106/web/index.php/";
    public static int MY_THEME= R.style.MyTheme;
    public static String PIC_URL_1="http://img0.imgtn.bdimg.com/it/u=314322505,247147223&fm=23&gp=0.jpg";
    public static HashMap<Integer,String>categoryMap= new HashMap<Integer, String>();
    public static HashMap<Integer,String> canteenMap= new HashMap<Integer,String>();

   public static User user;

}
