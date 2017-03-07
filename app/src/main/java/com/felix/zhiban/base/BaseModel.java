package com.felix.zhiban.base;


import android.content.Context;

import com.felix.zhiban.api.INetWorkManager;
import com.felix.zhiban.api.NetWorkManagerFactory;

public class BaseModel {
    public final static INetWorkManager iNetWorkManager= NetWorkManagerFactory.getNetWorkManager();
    private Context mContext;
    public BaseModel(Context mContext) {
        this.mContext = mContext;
    }
}
