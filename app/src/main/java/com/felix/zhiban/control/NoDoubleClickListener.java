package com.felix.zhiban.control;


import android.icu.util.Calendar;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.view.View;

public abstract class NoDoubleClickListener implements View.OnClickListener {
    private static final int MIN_CLICK_DELAY_TIME=1000;//1秒
    private long lastClickTime=0;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onClick(View view) {
        long currentClickTime= Calendar.getInstance().getTimeInMillis();
        if(currentClickTime-lastClickTime>MIN_CLICK_DELAY_TIME){
            lastClickTime=currentClickTime;
            onNoDoubleClick(view);
        }
    }

    public abstract void onNoDoubleClick(View v);
}
