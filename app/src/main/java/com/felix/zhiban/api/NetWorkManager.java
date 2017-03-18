package com.felix.zhiban.api;


import android.util.Log;

import com.felix.zhiban.bean.book.Books;
import com.felix.zhiban.bean.book.BookRoot;
import com.felix.zhiban.bean.zhihunews.RootEntity;
import com.felix.zhiban.bean.zhihunews.StroyDetailEntity;
import com.google.gson.Gson;
import com.squareup.okhttp.Call;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;

public class NetWorkManager implements INetWorkManager {

    private static final String TAG="NetWorkManager";

    private static NetWorkManager mNetWorkManager;

    public static NetWorkManager getInstance(){
        if(mNetWorkManager==null){
            mNetWorkManager=new NetWorkManager();
        }
        return mNetWorkManager;
    }
    @Override
    public BookRoot searchBookByTag(String url, String tag) {
        BookRoot bookRoot=null;
        Gson gson=new Gson();
        OkHttpClient mOkHttpClient=new OkHttpClient();
        String urltemp=url+tag;
        //创建一个Request
        Request request=new Request.Builder()
                        .url(urltemp)
                        .build();
        //new call
        Call call=mOkHttpClient.newCall(request);
        try {
            Response response=call.execute();
            if(response.isSuccessful()){
                String temp=response.body().string();
                bookRoot=gson.fromJson(temp,BookRoot.class);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return bookRoot;

    }

    @Override
    public Books getBookDetail(String url, String id) {
            Books books =null;
        Gson gson=new Gson();
        OkHttpClient mOKHttpClient=new OkHttpClient();
        String urltemp=url+id;
        Log.v("xgf123","url="+urltemp);
        //创建一个Request
        Request request=new Request.Builder()
                        .url(urltemp)
                        .build();
        //new call
        Call call=mOKHttpClient.newCall(request);
        try {
            Response response=call.execute();
//            String temp=response.body().string();
//            books =gson.fromJson(temp,Books.class);
            if(response.isSuccessful()){
                String temp=response.body().string();
                books =gson.fromJson(temp,Books.class);
                Log.v("xgf456","获得books="+books);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return books;
    }

    @Override
    public RootEntity getLatesNews(String url) {
        RootEntity today_RootEntity=null;
        Gson gson=new Gson();
        OkHttpClient mOkHttpClient=new OkHttpClient();

        //创建一个Request
        Request request=new Request.Builder()
                            .url(url)
                            .build();
        //new call
        Call call=mOkHttpClient.newCall(request);
        try {
            Response response=call.execute();
            if(response.isSuccessful()){
                String temp=response.body().string();
                today_RootEntity=gson.fromJson(temp,RootEntity.class);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return today_RootEntity;
    }

    @Override
    public RootEntity getSafety(String url) {
        RootEntity safe_RootEntity=null;
        Gson gson=new Gson();
        OkHttpClient mOkHttpClient=new OkHttpClient();

        //创建一个Request
        Request request=new Request.Builder()
                            .url(url)
                            .build();
        //new call
        Call call= mOkHttpClient.newCall(request);
        try {
            Response response=call.execute();
            if(response.isSuccessful()){
                String temp=response.body().string();
                safe_RootEntity=gson.fromJson(temp,RootEntity.class);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return safe_RootEntity;
    }

    @Override
    public RootEntity getInterest(String url) {
        RootEntity interest_RootEntity=null;
        Gson gson=new Gson();
        OkHttpClient mOkHttpClient=new OkHttpClient();

        //创建一个Request
        Request request=new Request.Builder()
                                    .url(url)
                                    .build();

        //new call
        Call call=mOkHttpClient.newCall(request);
        try {
            Response response=call.execute();
            if(response.isSuccessful()){
                String temp=response.body().string();
                interest_RootEntity=gson.fromJson(temp,RootEntity.class);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


        return interest_RootEntity;
    }

    @Override
    public RootEntity getSport(String url) {
        RootEntity sport_RootEntity=null;
        Gson gson=new Gson();
        OkHttpClient mOkHttpClient=new OkHttpClient();

        //创建一个Request
        Request request=new Request.Builder()
                                    .url(url)
                                    .build();

        //new call
        Call call=mOkHttpClient.newCall(request);
        try {
            Response response=call.execute();
            if(response.isSuccessful()){
                String temp=response.body().string();
                sport_RootEntity=gson.fromJson(temp,RootEntity.class);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return sport_RootEntity;
    }

    @Override
    public StroyDetailEntity getStroyDetail(String url, String id) {
        StroyDetailEntity stroyDetailEntity=null;
        Gson gson=new Gson();
        OkHttpClient mOkHttpClient=new OkHttpClient();
        String tempurl=url+id;

        //创建一个Request
        Request request=new Request.Builder()
                                    .url(tempurl)
                                        .build();
        //new call
        Call call=mOkHttpClient.newCall(request);

        try {
            Response response=call.execute();
            if(response.isSuccessful()){
                String temp=response.body().string();
                stroyDetailEntity=gson.fromJson(temp,StroyDetailEntity.class);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stroyDetailEntity;
    }
}
