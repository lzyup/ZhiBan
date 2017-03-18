package com.felix.zhiban.viewimpl.news;


import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.felix.zhiban.R;
import com.felix.zhiban.adapter.ZhihuNewsAdapter;
import com.felix.zhiban.base.BaseFragment;
import com.felix.zhiban.bean.zhihunews.StroriesEntity;
import com.felix.zhiban.presenter.news.ZhihuNewsPresenter;
import com.felix.zhiban.viewinterface.news.IGetNewsView;

import java.util.ArrayList;
import java.util.List;

public class TodayFragment extends BaseFragment implements IGetNewsView, SwipeRefreshLayout.OnRefreshListener {

    private SwipeRefreshLayout swipeRefreshLayout;

    private RecyclerView recyclerView;

    private ZhihuNewsAdapter zhihuNewsAdapter;

    private ZhihuNewsPresenter zhihuNewsPresenter;

    private int lastVisibleItem;

    private LinearLayoutManager mLayoutManager;

    private List<StroriesEntity>mStroriesEntityList;
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

    private String mTitleNews;

    public static TodayFragment newsInstance(String title){
        Bundle args=new Bundle();
        args.putString("title",title);
        TodayFragment frgment=new TodayFragment();
        frgment.setArguments(args);
        return frgment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView( inflater,  container,savedInstanceState);
        View view=inflater.inflate(R.layout.fragment_today,container,false);
        swipeRefreshLayout=(SwipeRefreshLayout)view.findViewById(R.id.id_swiperefreshlayout_today);
        recyclerView=(RecyclerView)view.findViewById(R.id.lv_item_today);


        relativeLayoutAniContainer=(RelativeLayout)view.findViewById(R.id.ani_container);
        mLoadingAni=(LinearLayout)view.findViewById(R.id.loadingAni);
        mLlloading=(LinearLayout)view.findViewById(R.id.ll_loading);
        mRefresh=(LinearLayout)view.findViewById(R.id.ll_error_refresh);
        imageView=(ImageView)view.findViewById(R.id.img_progress);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if(imageView!=null){
            //加载动画
            mAnimationDrawable=(AnimationDrawable)imageView.getDrawable();
            //默认进入界面就开启动画
            if(!mAnimationDrawable.isRunning()){
                mAnimationDrawable.start();
            }
        }
        if(mRefresh!=null){
            mRefresh.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    showNewsLoading();
                    zhihuNewsPresenter.getInitNews(false,mTitleNews);
                }
            });
        }
        Bundle args=getArguments();
        if(args!=null){
            mTitleNews=args.getString("title");
        }
        zhihuNewsPresenter=new ZhihuNewsPresenter(getContext(),this);
        zhihuNewsPresenter.getInitNews(false,mTitleNews);
        zhihuNewsPresenter.getNewsFromscrollRecycleView();


    }


    @Override
    public void getNewsSuccess(List<StroriesEntity> stroriesEntityList,boolean isLoadMore) {
        showNewsContentView();
        if(isLoadMore){
            mStroriesEntityList.addAll(stroriesEntityList);
        }else{
            mStroriesEntityList.clear();
            mStroriesEntityList.addAll(stroriesEntityList);
        }
        zhihuNewsAdapter.setList(mStroriesEntityList);
        zhihuNewsAdapter.notifyDataSetChanged();

    }

    @Override
    public void showNewsContentView() {
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
    public void showNewsError() {
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
    public void showNewsLoading() {

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
    public void initView() {
        mLayoutManager=new LinearLayoutManager(recyclerView.getContext());
        recyclerView.setLayoutManager(mLayoutManager);
        zhihuNewsAdapter=new ZhihuNewsAdapter(getContext());
        recyclerView.setAdapter(zhihuNewsAdapter);
        swipeRefreshLayout.setOnRefreshListener(this);
        mStroriesEntityList=new ArrayList<>();
    }

    @Override
    public void scrollRecycleView() {
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener(){

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if(newState==RecyclerView.SCROLL_STATE_IDLE){
                    lastVisibleItem=mLayoutManager.findLastVisibleItemPosition();
                    if(mLayoutManager.getItemCount()==1){
                        if(zhihuNewsAdapter!=null){
                            zhihuNewsAdapter.updateLoadStatus(ZhihuNewsAdapter.LOAD_NONE);
                        }
                        return;
                    }
                    if(lastVisibleItem+1==mLayoutManager.getItemCount()){
                        if(zhihuNewsAdapter!=null){
                            zhihuNewsAdapter.updateLoadStatus(ZhihuNewsAdapter.LOAD_PULL_TO);
                            zhihuNewsAdapter.updateLoadStatus(ZhihuNewsAdapter.LOAD_MORE);
                        }
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                zhihuNewsPresenter.getScrollNews(true,mTitleNews);
                            }
                        },1000);
                    }

                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                lastVisibleItem=mLayoutManager.findLastVisibleItemPosition();
            }
        });
    }

    @Override
    public void onRefresh() {
        zhihuNewsPresenter.getScrollNews(false,mTitleNews);
        swipeRefreshLayout.postDelayed(new Runnable() {
            @Override
            public void run() {
                if(swipeRefreshLayout!=null){
                    swipeRefreshLayout.setRefreshing(false);
                }
            }
        },2000);

    }
}
