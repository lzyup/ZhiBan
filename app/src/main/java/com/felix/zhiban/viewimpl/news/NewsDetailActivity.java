package com.felix.zhiban.viewimpl.news;


import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.felix.zhiban.R;
import com.felix.zhiban.base.BaseActivity;
import com.felix.zhiban.bean.zhihunews.StroyDetailEntity;
import com.felix.zhiban.presenter.news.ZhihuNewsDetailPresenter;
import com.felix.zhiban.presenterinterface.news.IZhihuNewsDetailPresenter;
import com.felix.zhiban.tool.Constants;
import com.felix.zhiban.tool.HtmlUtil;
import com.felix.zhiban.tool.ImageUtils.ImageLoaderFactory;
import com.felix.zhiban.tool.NetUtils;
import com.felix.zhiban.tool.SharePreferencesHelper;
import com.felix.zhiban.viewinterface.news.IGetNewsDetailView;

public class NewsDetailActivity extends BaseActivity implements IGetNewsDetailView {

    private Toolbar mToolbar;

    private ImageView mDetailBarImg;

    private TextView mDetailbartitle;

    private TextView mDetailBarCopyRight;

    private WebView mDetailNewsContentWv;

    private IZhihuNewsDetailPresenter iZhihuNewsDetailPresenter;

    private StroyDetailEntity stroyDetailEntity;

    private String mId;//跳转的新闻的id

    //动画布局
    private RelativeLayout relativeLayoutAniContainer;
    //等待加载的动画布局
    private LinearLayout mLoadingAni;
    //加载中
    private LinearLayout mLlloading;
    //加载失败
    private LinearLayout mRefresh;

    private ImageView imageView;

    //动画
    private AnimationDrawable mAnimationDrawable;



    @Override
    public String setActName() {
        return null;
    }

    @Override
    public void showToast(String msg) {

    }

    @Override
    public void getNewsDetail(StroyDetailEntity stroyDetailEntity) {
            this.stroyDetailEntity=stroyDetailEntity;
            fillData();


    }

    @Override
    public void showContentView() {
        if(mLoadingAni!=null&&mLoadingAni.getVisibility()==View.VISIBLE){
            mLoadingAni.setVisibility(View.GONE);
        }
        if(mLlloading!=null&&mLlloading.getVisibility()==View.VISIBLE){
            mLlloading.setVisibility(View.GONE);
        }
        if(mAnimationDrawable!=null&&mAnimationDrawable.isRunning()){
            mAnimationDrawable.stop();
        }
        if(mRefresh!=null&&mRefresh.getVisibility()==View.VISIBLE){
            mRefresh.setVisibility(View.GONE);
        }

        if(relativeLayoutAniContainer!=null&&relativeLayoutAniContainer.getVisibility()==View.VISIBLE){
            relativeLayoutAniContainer.setVisibility(View.GONE);
        }
    }

    @Override
    public void showError() {
        if(relativeLayoutAniContainer!=null&&relativeLayoutAniContainer.getVisibility()==View.GONE){
            relativeLayoutAniContainer.setVisibility(View.VISIBLE);
        }
        if (mLlloading.getVisibility() != View.GONE) {
            mLlloading.setVisibility(View.GONE);
        }
        // 停止动画
        if (mAnimationDrawable.isRunning()) {
            mAnimationDrawable.stop();
        }
        if (mRefresh.getVisibility() != View.VISIBLE) {
            mRefresh.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void showLoading() {
        if(mLoadingAni!=null&&mLoadingAni.getVisibility()==View.GONE){
            mLoadingAni.setVisibility(View.VISIBLE);
        }
        if(mLlloading!=null&&mLlloading.getVisibility()==View.GONE){
            mLlloading.setVisibility(View.VISIBLE);
        }
        if(mRefresh!=null&&mRefresh!=null&&mRefresh.getVisibility()==View.VISIBLE){
            mRefresh.setVisibility(View.GONE);
        }
        //开始动画
        if(mAnimationDrawable!=null&&!mAnimationDrawable.isRunning()){
            mAnimationDrawable.start();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_newsdetail);
        initView();
        initEvent();
        initData();
        initToolBar();
        initWebViewClient();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        backThActivity();
        return super.onKeyDown(keyCode, event);
    }

    private void initView(){
        mToolbar=(Toolbar)findViewById(R.id.toolbar);
        mDetailBarImg=(ImageView)findViewById(R.id.img_detail);
        mDetailbartitle=(TextView)findViewById(R.id.detail_bar_title);
        mDetailBarCopyRight=(TextView)findViewById(R.id.detail_bar_copyright);
        mDetailNewsContentWv=(WebView)findViewById(R.id.wv_detail_content);

        relativeLayoutAniContainer=(RelativeLayout)findViewById(R.id.ani_container);
        mLoadingAni=(LinearLayout)findViewById(R.id.loadingAni);
        mLlloading=(LinearLayout)findViewById(R.id.ll_loading);
        mRefresh=(LinearLayout)findViewById(R.id.ll_error_refresh);
        imageView=(ImageView)findViewById(R.id.img_progress);
    }


    private void initEvent(){
        if(imageView!=null){
            //加载动画
            mAnimationDrawable=(AnimationDrawable)imageView.getDrawable();
            //默认进入界面开始加载动画
            if(!mAnimationDrawable.isRunning()){
                mAnimationDrawable.start();
            }
        }
        if(mRefresh!=null){
            mRefresh.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    showLoading();
                    iZhihuNewsDetailPresenter.getNewsDetailById(mId);
                }
            });
        }
    }
    private void fillData(){
        String imgUrl=stroyDetailEntity.getImage();
        ImageLoaderFactory.getImageLoader().displaycrossFade(this,imgUrl,mDetailBarImg);
        mDetailbartitle.setText(stroyDetailEntity.getTitle());
        mDetailBarCopyRight.setText(stroyDetailEntity.getImage_source());
        String htmlData= HtmlUtil.createHtmlData(stroyDetailEntity.getBody(),stroyDetailEntity.getCss(),stroyDetailEntity.getJs());
        mDetailNewsContentWv.loadData(htmlData,HtmlUtil.MIME_TYPE,HtmlUtil.ENCODING);

    }
    private void initData(){
        iZhihuNewsDetailPresenter=new ZhihuNewsDetailPresenter(getContext(),this);
        Intent intent=getIntent();
        if(intent!=null){
            mId=intent.getStringExtra("EXTRA_ID");
        }
        if(!TextUtils.isEmpty(mId)){
            iZhihuNewsDetailPresenter.getNewsDetailById(mId);
        }
    }


    private void initToolBar() {
        setSupportActionBar(mToolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            //去除默认Title显示
            actionBar.setDisplayShowTitleEnabled(false);
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.icon_back);
        }
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                backThActivity();
            }
        });
       // mToolbar.setTitle("新闻详情");

    }

    private void initWebViewClient(){
        WebSettings settings=mDetailNewsContentWv.getSettings();
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
        mDetailNewsContentWv.setWebViewClient(new WebViewClient(){

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });
    }

}
