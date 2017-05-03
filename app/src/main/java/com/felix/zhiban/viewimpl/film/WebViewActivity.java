package com.felix.zhiban.viewimpl.film;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;


import com.felix.zhiban.R;
import com.felix.zhiban.base.BaseActivity;
import com.felix.zhiban.tool.Constants;
import com.felix.zhiban.tool.NetUtils;
import com.felix.zhiban.tool.SharePreferencesHelper;

/**
 * Created by XiaGF on 2017/5/3.
 */

public class WebViewActivity extends BaseActivity {

    private Toolbar toolbar;

    private WebView webView;

    private String url;

    public static String EXTRA_URL="extra_url";

    @Override
    public String setActName() {
        return null;
    }

    @Override
    public void showToast(String msg) {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_film_webview);
        initData();
        initView();
        initToolbar();
        initWebViewClient();
        webView.loadUrl(url);
    }

    private void initView(){
        toolbar=(Toolbar)findViewById(R.id.toolbar_film_webview);
        webView=(WebView)findViewById(R.id.wv_film);
    }

    private void initToolbar(){
        toolbar.setNavigationIcon(R.drawable.icon_back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                backThActivity();
            }
        });

    }

    private void initData(){
        Intent intent=getIntent();
        if(intent!=null){
            url=intent.getStringExtra(EXTRA_URL);
        }

    }

    private void initWebViewClient(){
        WebSettings settings=webView.getSettings();
        if(SharePreferencesHelper.getInstance(this).getBoolean(Constants.WebViewSetting.SP_NO_IMAGE,false)){
            settings.setBlockNetworkImage(true);
        }
        if(SharePreferencesHelper.getInstance(this).getBoolean(Constants.WebViewSetting.SP_AUTO_CACHE,true)){
            //启动应用缓存
            settings.setAppCacheEnabled(true);
            //使用localStorage则必须打开
            settings.setDomStorageEnabled(true);

            settings.setDatabaseEnabled(true);
            if(NetUtils.isConnected(this)){
                //设置缓存模式
                settings.setCacheMode(WebSettings.LOAD_DEFAULT);
            }else{
                settings.setCacheMode(WebSettings.LOAD_CACHE_ONLY);
            }
        }
        //提醒WebView启用JavaScript执行.默认执行false
        settings.setJavaScriptEnabled(true);
        //网页内容的宽度是否可以大于WebView控件的宽度
        settings.setLoadWithOverviewMode(true);
        settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        settings.setSupportZoom(true);
        webView.setWebViewClient(new WebViewClient(){

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });
    }

}
