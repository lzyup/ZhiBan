package com.felix.zhiban;


import android.app.Application;
import android.content.Context;

public class ZhiBanApp extends Application {
   public static Context mContext;


    @Override
    public void onCreate() {
        super.onCreate();
        mContext=getApplicationContext();
    }
}
