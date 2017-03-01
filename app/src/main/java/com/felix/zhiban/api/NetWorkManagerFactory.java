package com.felix.zhiban.api;


public class NetWorkManagerFactory {

    public static INetWorkManager getNetWorkManager(){
        return NetWorkManager.getInstance();
    }
}
