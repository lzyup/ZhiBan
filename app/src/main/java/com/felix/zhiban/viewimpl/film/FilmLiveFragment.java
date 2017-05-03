package com.felix.zhiban.viewimpl.film;

import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v4.widget.SwipeRefreshLayout.OnRefreshListener;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.felix.zhiban.R;
import com.felix.zhiban.adapter.FilmLiveAdapter;
import com.felix.zhiban.base.BaseFragment;
import com.felix.zhiban.bean.filmlive.FilmLive;
import com.felix.zhiban.bean.filmusbox.Subject;
import com.felix.zhiban.bean.top250.Root;
import com.felix.zhiban.control.SpacesItemDecoration;
import com.felix.zhiban.presenter.film.DouBanFilmPresenter;
import com.felix.zhiban.presenterinterface.film.IDoubanFilmPresenter;
import com.felix.zhiban.tool.Utils;
import com.felix.zhiban.viewinterface.film.ISearchFilmView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by XiaGF on 2017/4/28.
 */

public class FilmLiveFragment extends BaseFragment  implements ISearchFilmView, OnRefreshListener {

    private RecyclerView mRecyclerView;

    private SwipeRefreshLayout mSwipeRefreshLayout;

    private int position;

    private FilmLiveAdapter adapter;

    private int lastVisibleItem;

    private LinearLayoutManager mLayoutManager;

    private IDoubanFilmPresenter iDoubanFilmPresenter;

    private List<Subject> filmLiveList;

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

    public static FilmLiveFragment newInstance(int position,String title){
        Bundle args=new Bundle();
        args.putString("title",title);
        args.putInt("position",position);
        FilmLiveFragment filmLiveFragment=new FilmLiveFragment();
        filmLiveFragment.setArguments(args);
        return filmLiveFragment;
    }


    @Override
    public void searchFilmLiveSuccess(FilmLive filmLive, boolean isLoadMore) {
            if(filmLive!=null){
                if(isLoadMore){
                    filmLiveList.addAll(filmLive.getSubjects());
                }else{
                    filmLiveList.clear();
                    filmLiveList.addAll(filmLive.getSubjects());
                }
                adapter.setList(filmLiveList);
                adapter.notifyDataSetChanged();

            }else{
                Toast.makeText(getActivity(), "由於接口已經過期，所以新鮮事頁面无法正常顯示", Toast.LENGTH_SHORT).show();
            }

    }

    @Override
    public void searchFilmTop250Success(Root root, boolean isLoadMore) {

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
//            mRefresh.setOnClickListener(new NoDoubleClickListener() {
//                @Override
//                public void onNoDoubleClick(View v) {
//                    showLoading();
//                    douBanBookPresenter.searchBookByTag(BookByTagFragment.this,tag,false);
//                }
//            });
            mRefresh.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    showFilmLoading();
                    iDoubanFilmPresenter.searchFilmLive(false);
                }
            });
        }
        filmLiveList=new ArrayList<>();
        scrollRecycleView();
        iDoubanFilmPresenter=new DouBanFilmPresenter(getContext(),this);

        mSwipeRefreshLayout.setOnRefreshListener(this);
        iDoubanFilmPresenter.searchFilmLive(false);
        adapter=new FilmLiveAdapter(getActivity());
        mLayoutManager=new GridLayoutManager(getActivity(),3);
        mRecyclerView.setLayoutManager(mLayoutManager);
        SpacesItemDecoration spacesItemDecoration=new SpacesItemDecoration(Utils.dp2px(getActivity(),10),Utils.dp2px(getActivity(),10),Utils.dp2px(getActivity(),10),0);
        mRecyclerView.addItemDecoration(spacesItemDecoration);
        mRecyclerView.setAdapter(adapter);
    }

    @Override
    public void onRefresh() {
        showFilmLoading();
        iDoubanFilmPresenter.searchFilmLive(false);
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
                               iDoubanFilmPresenter.searchFilmLive(true);

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
