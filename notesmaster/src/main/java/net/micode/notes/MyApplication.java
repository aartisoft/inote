package net.micode.notes;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by 2016/11/20.
 */

public class MyApplication extends Application {

    //一种存储方式
    public static SharedPreferences preferences;




    @Override
    public void onCreate() {
        super.onCreate();
        preferences = getSharedPreferences("user", Context.MODE_PRIVATE);
    }




    //是否已登录标示相关设置，存入Sharedpreferences
    public static Boolean getIsLogin() {
        return preferences.getBoolean("login", false);
    }

    public static void setIsLogin(Boolean b) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean("login", b);
        editor.apply();
    }

    //密码信息，存入Sharedpreferences
    public static String getPassword() {
        return preferences.getString("password", "");
    }

    public static void setPassword(String p) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("password", p);
        editor.apply();
    }


}