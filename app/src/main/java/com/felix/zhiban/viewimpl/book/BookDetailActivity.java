package com.felix.zhiban.viewimpl.book;


import android.annotation.TargetApi;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.AnimationDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.felix.zhiban.R;
import com.felix.zhiban.base.BaseActivity;
import com.felix.zhiban.bean.book.Books;
import com.felix.zhiban.presenter.book.DoubanBookDetailPresenter;
import com.felix.zhiban.presenterinterface.book.IDoubanBookDetailPresenter;
import com.felix.zhiban.tool.ImageUtils.ImageLoaderFactory;
import com.felix.zhiban.tool.Utils;
import com.felix.zhiban.viewinterface.book.IGetBookDetailView;

import jp.wasabeef.glide.transformations.BlurTransformation;

public class BookDetailActivity extends BaseActivity implements IGetBookDetailView {

    private FrameLayout frameLayoutroot;

    private ImageView imageViewToolBg;

    private Toolbar toolbar;

    private ImageView imageViewHeaderBg;

    private ImageView imageViewBookPhoto;

    private TextView textViewWriter;//作者

    private TextView textViewRatingRate;//评分

    private TextView textViewRatingNumber;//评分人数;

    private TextView textViewPulishTime;//出版时间

    private TextView getTextViewPulishPlace;//出版社


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


    private TextView textViewBookSummary; //书籍摘要

    private TextView textViewWriterSummary;//作者简介

    private TextView textViewBookCatalog;//书籍目录

    private Books books;

    String id;//跳转的书籍的id



    private IDoubanBookDetailPresenter iDoubanBookDetailPresenter;



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
        setContentView(R.layout.layout__bookdetail_base);
        initView();
        initEvent();
        initData();

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
//        if(frameLayoutroot!=null&&frameLayoutroot.getVisibility()==View.GONE){
//            frameLayoutroot.setVisibility(View.VISIBLE);
//        }

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

    private void initView(){

        frameLayoutroot=(FrameLayout)findViewById(R.id.fl_root);
        imageViewToolBg=(ImageView)findViewById(R.id.iv_titlebar_bg);
        toolbar=(Toolbar)findViewById(R.id.tb_toolbar);

        imageViewHeaderBg=(ImageView)findViewById(R.id.header_bg);
        imageViewBookPhoto=(ImageView)findViewById(R.id.iv_photo);
        textViewWriter=(TextView)findViewById(R.id.tv_writer);
        textViewRatingRate=(TextView)findViewById(R.id.tv_rating_rate);
        textViewRatingNumber=(TextView)findViewById(R.id.tv_rating_number);
        textViewPulishTime=(TextView)findViewById(R.id.tv_publish_time);
        getTextViewPulishPlace=(TextView)findViewById(R.id.tv_publish_place);

        relativeLayoutAniContainer=(RelativeLayout)findViewById(R.id.ani_container);
        mLoadingAni=(LinearLayout)findViewById(R.id.loadingAni);
        mLlloading=(LinearLayout)findViewById(R.id.ll_loading);
        mRefresh=(LinearLayout)findViewById(R.id.ll_error_refresh);
        imageView=(ImageView)findViewById(R.id.img_progress);

        textViewBookSummary=(TextView)findViewById(R.id.tv_book_summary);
        textViewWriterSummary=(TextView)findViewById(R.id.tv_writer_summary);
        textViewBookCatalog=(TextView)findViewById(R.id.tv_book_catalog);

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
                    iDoubanBookDetailPresenter.getBookById(BookDetailActivity.this,id);
                }
            });
        }
    }

    private void initData(){
        books=new Books();
       // douBanBookPresenter=new DouBanBookPresenter(this);
        iDoubanBookDetailPresenter=new DoubanBookDetailPresenter(this);
        Intent intent=getIntent();
        if(intent!=null){
             id=intent.getStringExtra("id");
            if(!TextUtils.isEmpty(id)){
                //douBanBookPresenter.getBookById(this,id);
                iDoubanBookDetailPresenter.getBookById(this,id);
            }
        }
//        initToolBar();
//        initHead();
//        initContent();
    }


    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private void initToolBar(){
        Glide.with(this)
              .load(books.getImages().getMedium())
                .error(R.drawable.stackblur_default)
                .bitmapTransform(new BlurTransformation(this, 23, 4)).listener(new RequestListener<String, GlideDrawable>() {
            @Override
            public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {
                return false;
            }

            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
                    toolbar.setBackgroundColor(Color.TRANSPARENT);
                    imageViewToolBg.setImageAlpha(0);
                    imageViewToolBg.setVisibility(View.VISIBLE);
                return false;
            }
        }).into(imageViewToolBg);
       // ImageLoaderFactory.getImageLoader().displayGaussian(this,books.getImages().getMedium(),imageViewToolBg);
        setSupportActionBar(toolbar);
        ActionBar actionBar=getSupportActionBar();
        if(actionBar!=null){
            //去除默认Title显示
            actionBar.setDisplayShowTitleEnabled(false);
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.icon_back);
        }
        toolbar.setTitleTextAppearance(this,R.style.ToolBar_Title);
        toolbar.setOverflowIcon(ContextCompat.getDrawable(this, R.drawable.actionbar_more));
        toolbar.setSubtitleTextAppearance(this,R.style.Toolbar_SubTitle);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                backThActivity();
            }
        });
        toolbar.setTitle(books.getTitle());
        toolbar.setSubtitle("作者: "+books.getSubtitle());

    }

    private void initHead(){
        ImageLoaderFactory.getImageLoader().displayGaussian(this,books.getImages().getMedium(),imageViewHeaderBg);
       // ImageLoaderFactory.getImageLoader().displayForImageView(this, books.getImages().getMedium(),imageViewHeaderBg);
        ImageLoaderFactory.getImageLoader().displayForImageView(this,books.getImages().getLarge(),imageViewBookPhoto);
        textViewWriter.setText(Utils.formatName(books.getAuthor()));
        textViewRatingRate.setText("评分: "+books.getRating().getAverage());
        textViewRatingNumber.setText(books.getRating().getNumRaters()+"人评分");
        textViewPulishTime.setText(books.getPubdate());
        getTextViewPulishPlace.setText(books.getPublisher());

    }

    private void initContent(){
        textViewBookSummary.setText(books.getSummary());
        textViewWriterSummary.setText(books.getAuthorIntro());
        textViewBookCatalog.setText(books.getCatalog());
    }

    @Override
    public void getBookSuccess(Books books) {
        if(books!=null){
            this.books=books;
            initToolBar();
            initHead();
            initContent();

        }else{
            Toast.makeText(this, "由於接口已經過期，所以新鮮事頁面无法正常顯示", Toast.LENGTH_SHORT).show();
        }

    }

}
