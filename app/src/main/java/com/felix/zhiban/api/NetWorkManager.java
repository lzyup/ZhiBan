package com.felix.zhiban.api;


import android.util.Log;

import com.felix.zhiban.bean.book.Books;
import com.felix.zhiban.bean.book.BookRoot;
import com.felix.zhiban.bean.filmdetail.FilmDetail;
import com.felix.zhiban.bean.filmlive.FilmLive;
import com.felix.zhiban.bean.top250.Root;
import com.felix.zhiban.bean.zhihunews.RootEntity;
import com.felix.zhiban.bean.zhihunews.StroyDetailEntity;
import com.google.gson.Gson;
import com.squareup.okhttp.Call;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;

import static com.felix.zhiban.api.Url.COUNT_TOP250;
import static com.felix.zhiban.api.Url.START_TOP250;

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
    public FilmLive searchFilmLive(String url) {
        FilmLive filmLive=null;
        Gson gson=new Gson();
        OkHttpClient mOkHttpClient=new OkHttpClient();
        String urltemp=url;

        Request request=new Request.Builder()
                            .url(urltemp)
                            .build();
        //new call
        Call call=mOkHttpClient.newCall(request);

        try {
            Response response=call.execute();
            if(response.isSuccessful()){
                String temp=response.body().string();
                filmLive=gson.fromJson(temp,FilmLive.class);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return filmLive;
    }

    @Override
    public Root searchFilmTop250(String url) {
        Root root=null;
        Gson gson=new Gson();
        OkHttpClient mOkHttpClient=new OkHttpClient();
        String urltemp=url;

        Request request=new Request.Builder()
                                    .url(urltemp)
                                    .build();

        //new call
        Call call=mOkHttpClient.newCall(request);

        try {
            Response response=call.execute();
            if(response.isSuccessful()){
                String temp=response.body().string();
                root=gson.fromJson(temp,Root.class);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return root;
    }

    @Override
    public FilmDetail getFilmDetail(String url, String id) {
        FilmDetail filmDetail=null;
        Gson gson=new Gson();
        OkHttpClient mOkHttpClient=new OkHttpClient();
        String urltemp=url+id;

        Request request=new Request.Builder()
                                    .url(urltemp)
                                    .build();

        //new call
        Call call=mOkHttpClient.newCall(request);

        try {
            Response response=call.execute();
            if(response.isSuccessful()){
                String temp=response.body().string();
                filmDetail=gson.fromJson(temp,FilmDetail.class);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return filmDetail;
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
