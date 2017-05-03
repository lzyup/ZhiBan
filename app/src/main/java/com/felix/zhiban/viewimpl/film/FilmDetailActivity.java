package com.felix.zhiban.viewimpl.film;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.AnimationDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.KeyEvent;
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
import com.bumptech.glide.util.Util;
import com.felix.zhiban.R;
import com.felix.zhiban.adapter.CastAdapter;
import com.felix.zhiban.base.BaseActivity;
import com.felix.zhiban.bean.filmdetail.FilmDetail;
import com.felix.zhiban.bean.filmdetail.FilmPeople;
import com.felix.zhiban.bean.top250.Casts;
import com.felix.zhiban.bean.top250.Directors;
import com.felix.zhiban.presenter.film.DoubanFilmDetailPresenter;
import com.felix.zhiban.presenterinterface.film.IDoubanFilmDetailPresenter;
import com.felix.zhiban.tool.ImageUtils.ImageLoaderFactory;
import com.felix.zhiban.tool.Utils;
import com.felix.zhiban.viewinterface.film.IGetFilmDetailView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import jp.wasabeef.glide.transformations.BlurTransformation;

/**
 * Created by XiaGF on 2017/5/1.
 */

public class FilmDetailActivity extends BaseActivity implements IGetFilmDetailView {

    private FrameLayout frameLayout;

    private ImageView imageViewToolBg;

    private Toolbar toolbar;

    private ImageView imageViewHeaderBg;

    private ImageView imageViewFilmPhoto;

    private TextView textViewRatingRate;//评分

    private TextView textViewRatingNumber;//评分人数

    private TextView textViewDirector;//导演

    private TextView textViewCasts;//主演

    private TextView textViewGenres;//类型

    private TextView textViewdDate;//日期

    private TextView textViewCity;//制片国家/地区

    //动画布局
    private RelativeLayout relativeLayoutAniContainer;
    //加载中
    private LinearLayout mLlloading;
    //加载失败
    private LinearLayout mRefresh;

    private ImageView imageView;

    //动画
    private AnimationDrawable mAnimationDrawable;


    private TextView textViewFilmTitle;//电影另称

    private TextView textViewFilmSummary;//电影简介

    private RecyclerView recyclerViewFilmCasts;//导演演员

    private FilmDetail filmDetail;

    private String id ;//跳转电影详情的id

    private String alt;//导演，演员点击跳转参数

    private IDoubanFilmDetailPresenter iDoubanFilmDetailPresenter;
    @Override
    public String setActName() {
        return null;
    }

    @Override
    public void showToast(String msg) {

    }

    @Override
    public void getFilmSuccess(FilmDetail filmDetail) {
        if(filmDetail!=null){
            this.filmDetail=filmDetail;
            initToolBar();
            initHead();
            initContent();
        }else{
            Toast.makeText(this, "由於接口已經過期，所以新鮮事頁面无法正常顯示", Toast.LENGTH_SHORT).show();
        }


    }

    @Override
    public void showContentView() {

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
        setContentView(R.layout.layout_filmdetail_base);
        initView();
        initEvent();
        initData();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        backThActivity();
        return super.onKeyDown(keyCode, event);
    }

    private void initView(){
        frameLayout=(FrameLayout)findViewById(R.id.fl_root);
        imageViewToolBg=(ImageView)findViewById(R.id.img_titlebar_bg);
        toolbar=(Toolbar)findViewById(R.id.tb_toolbar);

        imageViewHeaderBg=(ImageView)findViewById(R.id.img_head);
        imageViewFilmPhoto=(ImageView)findViewById(R.id.img_photo);
        textViewRatingRate=(TextView)findViewById(R.id.tv_rating_rate);
        textViewRatingNumber=(TextView)findViewById(R.id.tv_rating_number);
        textViewDirector=(TextView)findViewById(R.id.tv_director);
        textViewCasts=(TextView)findViewById(R.id.tv_film_casts);
        textViewGenres=(TextView)findViewById(R.id.tv_film_genres);
        textViewdDate=(TextView)findViewById(R.id.tv_film_day);
        textViewCity=(TextView)findViewById(R.id.tv_film_city);

        relativeLayoutAniContainer=(RelativeLayout)findViewById(R.id.ani_container);
        mLlloading=(LinearLayout)findViewById(R.id.ll_loading);
        mRefresh=(LinearLayout)findViewById(R.id.ll_error_refresh);
        imageView=(ImageView)findViewById(R.id.img_progress);

        textViewFilmTitle=(TextView)findViewById(R.id.tv_film_title);
        textViewFilmSummary=(TextView)findViewById(R.id.tv_film_summary);
        recyclerViewFilmCasts=(RecyclerView)findViewById(R.id.rv_film_cast);

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
                    iDoubanFilmDetailPresenter.getFilmById(id);
                }
            });
        }
       // recyclerViewFilmCasts.setNestedScrollingEnabled(false);
    }

    private void initData(){
        filmDetail=new FilmDetail();

        iDoubanFilmDetailPresenter=new DoubanFilmDetailPresenter(getContext(),this);
        Intent intent=getIntent();
        if(intent!=null){
            id=intent.getStringExtra("id");
            if(!TextUtils.isEmpty(id)){
                iDoubanFilmDetailPresenter.getFilmById(id);
            }
        }
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private void initToolBar(){
        Glide.with(this)
                .load(filmDetail.getImages().getMedium())
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
        //ImageLoaderFactory.getImageLoader().displayGaussian(this,filmDetail.getImages().getMedium(),imageViewToolBg);
        setSupportActionBar(toolbar);//这样点击回退按钮后才有作用
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
        toolbar.setTitle(filmDetail.getTitle());
        toolbar.setSubtitle("主演: "+Utils.formatFilmPeolep(filmDetail.getCasts()));

    }

    private void initHead(){
        ImageLoaderFactory.getImageLoader().displayGaussian(this,filmDetail.getImages().getMedium(),imageViewHeaderBg);
        ImageLoaderFactory.getImageLoader().displayForImageView(this,filmDetail.getImages().getLarge(),imageViewFilmPhoto);
        textViewRatingRate.setText("评分:"+filmDetail.getRating().getAverage());
        textViewRatingNumber.setText(filmDetail.getRatings_count()+"人评分");
        textViewDirector.setText(Utils.formatFilmPeolep(filmDetail.getDirectors()));
        textViewCasts.setText(Utils.formatFilmPeolep(filmDetail.getCasts()));
        textViewGenres.setText(Utils.formatName(filmDetail.getGenres()));
        textViewdDate.setText(filmDetail.getYear());
        textViewCity.setText(Utils.formatName(filmDetail.getCountries()));
    }

    private void initContent(){
        textViewFilmTitle.setText(Utils.formatName(filmDetail.getAka()));
        textViewFilmSummary.setText(filmDetail.getSummary());

        initFilmData(filmDetail);
        CastAdapter castAdapter=new CastAdapter(this);
        castAdapter.setDatas(list);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(recyclerViewFilmCasts.getContext());
        recyclerViewFilmCasts.setLayoutManager(linearLayoutManager);
        recyclerViewFilmCasts.setAdapter(castAdapter);
        alt=filmDetail.getAlt();
        castAdapter.setOnItemClickListener(new CastAdapter.OnItemClickListener() {
            @Override
            public void OnItemClick(View view, int position) {
                Intent intent=new Intent(FilmDetailActivity.this, WebViewActivity.class);
                String alt=list.get(position).getAlt();
                intent.putExtra(WebViewActivity.EXTRA_URL,alt);
                startThActivityByIntent(intent);
            }
        });


    }

    private List<FilmPeople>list=new ArrayList<>();

    private void initFilmData(FilmDetail filmDetail){
        if(filmDetail.getDirectors()!=null&&filmDetail.getDirectors().size()>0){
            for(int i=0;i<filmDetail.getDirectors().size();i++){
                FilmPeople filmPeople=new FilmPeople();
                filmPeople=filmDetail.getDirectors().get(i);

                filmPeople.setType(1);
                list.add(filmPeople);
            }
        }
        if(filmDetail.getCasts()!=null&&filmDetail.getCasts().size()>0){
            for(int i=0;i<filmDetail.getCasts().size();i++){
                FilmPeople filmPeople=new FilmPeople();
                filmPeople=filmDetail.getCasts().get(i);
                filmPeople.setType(2);
                list.add(filmPeople);
            }
        }
    }


}
