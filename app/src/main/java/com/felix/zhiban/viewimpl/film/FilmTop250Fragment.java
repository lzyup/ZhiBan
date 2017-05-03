package com.felix.zhiban.viewimpl.film;


import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.felix.zhiban.R;
import com.felix.zhiban.adapter.FilmLiveAdapter;
import com.felix.zhiban.adapter.FilmTop250Adapter;
import com.felix.zhiban.base.BaseFragment;
import com.felix.zhiban.bean.filmlive.FilmLive;
import com.felix.zhiban.bean.filmusbox.Subject;
import com.felix.zhiban.bean.top250.Root;
import com.felix.zhiban.bean.top250.Subjects;
import com.felix.zhiban.control.SpacesItemDecoration;
import com.felix.zhiban.presenter.film.DouBanFilmPresenter;
import com.felix.zhiban.presenterinterface.film.IDoubanFilmPresenter;
import com.felix.zhiban.tool.Utils;
import com.felix.zhiban.viewinterface.film.ISearchFilmView;

import java.util.ArrayList;
import java.util.List;

public class FilmTop250Fragment extends BaseFragment implements ISearchFilmView, SwipeRefreshLayout.OnRefreshListener {

    private RecyclerView mRecyclerView;

    private SwipeRefreshLayout mSwipeRefreshLayout;

    private int position;

    private FilmTop250Adapter adapter;

    private int lastVisibleItem;

    private int pageCount;

    private final int PAGE_SIZE=10;

    private LinearLayoutManager mLayoutManager;

    private IDoubanFilmPresenter iDoubanFilmPresenter;

    private List<Subjects>filmTopList ;

    //等待加载的动画布局
    private LinearLayout mLoadingAni;
    //加载中
    private LinearLayout mLlloading;
    //加载失败
    private LinearLayout mRefresh;
    //内容布局
    protected RelativeLayout mContainer;

    private ImageView imageView;

    //动画
    private AnimationDrawable mAnimationDrawable;

    public static FilmTop250Fragment newInstance(int position,String title){
        Bundle args=new Bundle();
        args.putString("title",title);
        args.putInt("position",position);
        FilmTop250Fragment filmTop250Fragment=new FilmTop250Fragment();
        filmTop250Fragment.setArguments(args);
        return filmTop250Fragment;
    }

    @Override
    public void searchFilmLiveSuccess(FilmLive filmLive, boolean isLoadMore) {

    }

    @Override
    public void searchFilmTop250Success(Root root, boolean isLoadMore) {
        if(root!=null){
            if(isLoadMore){
                if(root.getSubjects()!=null){
                    filmTopList.addAll(root.getSubjects());
                }

            }else{
                if(root.getSubjects()!=null){
                    filmTopList.clear();
                    filmTopList.addAll(root.getSubjects());
                }

            }
            adapter.setList(filmTopList);
            adapter.notifyDataSetChanged();
        }else{
            Toast.makeText(getActivity(),"由於接口已經過期，所以新鮮事頁面无法正常顯示",Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void showFilmContentView() {
        if(mLoadingAni.getVisibility()==View.VISIBLE){
            mLoadingAni.setVisibility(View.GONE);
        }
        if(mLlloading.getVisibility()==View.VISIBLE){
            mLlloading.setVisibility(View.GONE);
        }
        if(mAnimationDrawable.isRunning()){
            mAnimationDrawable.stop();
        }
        if(mRefresh.getVisibility()==View.VISIBLE){
            mRefresh.setVisibility(View.GONE);
        }
    }

    @Override
    public void showFilmError() {
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
    public void showFilmLoading() {
        if(mLoadingAni.getVisibility()==View.GONE){
            mLoadingAni.setVisibility(View.VISIBLE);
        }
        if(mLlloading.getVisibility()==View.GONE){
            mLlloading.setVisibility(View.VISIBLE);
        }
        if(mRefresh.getVisibility()==View.VISIBLE){
            mRefresh.setVisibility(View.GONE);
        }
        //开始动画
        if(!mAnimationDrawable.isRunning()){
            mAnimationDrawable.start();
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
         super.onCreateView(inflater, container, savedInstanceState);
        View view=inflater.inflate(R.layout.fragment_fim_live,container,false);
        mSwipeRefreshLayout=(SwipeRefreshLayout)view.findViewById(R.id.id_swiperefreshlayout);
        mRecyclerView=(RecyclerView)view.findViewById(R.id.recyclerview);
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
                    showFilmLoading();
                    iDoubanFilmPresenter.searchFilmTop250(pageCount*PAGE_SIZE,PAGE_SIZE,false);
                }
            });
        }
        filmTopList=new ArrayList<>();
        scrollRecycleView();
        iDoubanFilmPresenter=new DouBanFilmPresenter(getContext(),this);
        mSwipeRefreshLayout.setOnRefreshListener(this);
        iDoubanFilmPresenter.searchFilmTop250(pageCount*PAGE_SIZE,PAGE_SIZE,false);
        adapter=new FilmTop250Adapter(getActivity());
        mLayoutManager=new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(mLayoutManager);
        SpacesItemDecoration spacesItemDecoration=new SpacesItemDecoration(Utils.dp2px(getActivity(),10),Utils.dp2px(getActivity(),10),Utils.dp2px(getActivity(),10),0);
        mRecyclerView.addItemDecoration(spacesItemDecoration);
        mRecyclerView.setAdapter(adapter);



    }

    @Override
    public void onRefresh() {
        showFilmLoading();
        iDoubanFilmPresenter.searchFilmTop250(pageCount*PAGE_SIZE,PAGE_SIZE,false);
        mSwipeRefreshLayout.postDelayed(new Runnable() {
            @Override
            public void run() {
                if(mSwipeRefreshLayout!=null){
                    mSwipeRefreshLayout.setRefreshing(false);
                }
            }
        },2000);

    }

    private void scrollRecycleView(){
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if(newState==RecyclerView.SCROLL_STATE_IDLE){
                    lastVisibleItem=mLayoutManager.findLastVisibleItemPosition();
                    if(mLayoutManager.getItemCount()==1){
                        if(adapter!=null){
                            adapter.updateLoadStatus(adapter.LOAD_NONE);
                        }
                        return;
                    }
                    if(lastVisibleItem+1==mLayoutManager.getItemCount()){
                        if(adapter!=null){
                            adapter.updateLoadStatus(adapter.LOAD_PULL_TO);
                            adapter.updateLoadStatus(adapter.LOAD_MORE);
                        }
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                pageCount++;
                                iDoubanFilmPresenter.searchFilmTop250(pageCount*PAGE_SIZE,PAGE_SIZE,true);

                            }
                        },1000);
                    }
                }
            }
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                lastVisibleItem = mLayoutManager.findLastVisibleItemPosition();
            }

        });
    }
}
