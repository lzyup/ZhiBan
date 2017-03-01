package com.felix.zhiban.base;


import android.content.Context;

import com.felix.zhiban.api.INetWorkManager;
import com.felix.zhiban.api.NetWorkManagerFactory;

public abstract class BasePresenter {
    public final static INetWorkManager iNetWorkManager= NetWorkManagerFactory.getNetWorkManager();
    private Context mContext;
    public BasePresenter(Context mContext) {
        this.mContext = mContext;
    }
}
