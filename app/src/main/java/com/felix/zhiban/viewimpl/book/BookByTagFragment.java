package com.felix.zhiban.viewimpl.book;


import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
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
import com.felix.zhiban.adapter.BookByTagAdapter;
import com.felix.zhiban.api.BookApiTag;
import com.felix.zhiban.base.BaseFragment;
import com.felix.zhiban.bean.book.BookRoot;
import com.felix.zhiban.bean.book.Books;
import com.felix.zhiban.control.SpacesItemDecoration;
import com.felix.zhiban.presenter.DouBanBookPresenter;
import com.felix.zhiban.presenterinterface.IDoubanBookPresenter;
import com.felix.zhiban.tool.Utils;
import com.felix.zhiban.viewinterface.book.ISearchBookByTagView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import rx.subscriptions.CompositeSubscription;

public class BookByTagFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener,ISearchBookByTagView {

    private SwipeRefreshLayout swipeRefreshLayout;

    private RecyclerView recyclerView;

    private int position;

    private BookByTagAdapter adapter;

    private int lastVisibleItem;

    private LinearLayoutManager mLayoutManager;

    private IDoubanBookPresenter iDoubanBookPresenter;

    private List<String> listTag;

    private List<Books> booksList;

    //搜索书籍的标签
    String tag;
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
    private CompositeSubscription mCompositeSubscription;

    public static BookByTagFragment newInstance(int position,String title){
        Bundle args=new Bundle();
        args.putString("title",title);
        args.putInt("position",position);
        BookByTagFragment fragment=new BookByTagFragment();
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView( inflater,  container,savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_bookbytag, container, false);
        swipeRefreshLayout=(SwipeRefreshLayout)view.findViewById(R.id.id_swiperefreshlayout);
        recyclerView=(RecyclerView)view.findViewById(R.id.recyclerview);
        mLoadingAni=(LinearLayout)view.findViewById(R.id.loadingAni);
        mLlloading=(LinearLayout)view.findViewById(R.id.ll_loading);
        mRefresh=(LinearLayout)view.findViewById(R.id.ll_error_refresh);
        imageView=(ImageView)view.findViewById(R.id.img_progress);

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
//            Bundle args=getArguments();
//        if(args!=null){
//            position=args.getInt("position");
//        }
//        booksList =new ArrayList<>();
//        String[] strTag= BookApiTag.getApiTag(position);
//        listTag= Arrays.asList(strTag);
//        scrollRecycleView();
//        douBanBookPresenter=new DouBanBookPresenter(getContext());
//
//        //swipeRefreshLayout.setColorSchemeColors();
//        swipeRefreshLayout.setOnRefreshListener(this);
//
//        String tag=BookApiTag.getRandomTAG(listTag);
//        douBanBookPresenter.searchBookByTag(this,tag,false);
//        adapter=new BookByTagAdapter(getActivity());
//        mLayoutManager=new GridLayoutManager(getActivity(),3);
//        recyclerView.setLayoutManager(mLayoutManager);
//        SpacesItemDecoration spacesItemDecoration=new SpacesItemDecoration(Utils.dp2px(getActivity(),10),Utils.dp2px(getActivity(),10),Utils.dp2px(getActivity(),10),0);
//        recyclerView.addItemDecoration(spacesItemDecoration);
//        recyclerView.setAdapter(adapter);


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
                    showLoading();
                    iDoubanBookPresenter.searchBookByTag(BookByTagFragment.this,tag,false);
                }
            });
        }

        Bundle args=getArguments();
        if(args!=null){
            position=args.getInt("position");
        }
        booksList =new ArrayList<>();
        String[] strTag= BookApiTag.getApiTag(position);
        listTag= Arrays.asList(strTag);
        scrollRecycleView();
        iDoubanBookPresenter=new DouBanBookPresenter(getContext());

        //swipeRefreshLayout.setColorSchemeColors();
        swipeRefreshLayout.setOnRefreshListener(this);

        tag=BookApiTag.getRandomTAG(listTag);
        iDoubanBookPresenter.searchBookByTag(this,tag,false);
        adapter=new BookByTagAdapter(getActivity());
        mLayoutManager=new GridLayoutManager(getActivity(),3);
        recyclerView.setLayoutManager(mLayoutManager);
        SpacesItemDecoration spacesItemDecoration=new SpacesItemDecoration(Utils.dp2px(getActivity(),10),Utils.dp2px(getActivity(),10),Utils.dp2px(getActivity(),10),0);
        recyclerView.addItemDecoration(spacesItemDecoration);
        recyclerView.setAdapter(adapter);
    }

    //加载完成
    @Override
    protected void showContentView() {
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

    //加载失败
    @Override
    protected void showError() {
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
    public void onRefresh() {
        showLoading();
        listTag=Arrays.asList(BookApiTag.getApiTag(position));
        String tag=BookApiTag.getRandomTAG(listTag);
        iDoubanBookPresenter.searchBookByTag(BookByTagFragment.this,tag,false);
        swipeRefreshLayout.postDelayed(new Runnable() {
            @Override
            public void run() {
                    if(swipeRefreshLayout!=null){
                        swipeRefreshLayout.setRefreshing(false);
                    }
            }
        },2000);

    }

    //正在加载
    @Override
    protected void showLoading() {

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

    private void scrollRecycleView(){
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
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
                                String tag=BookApiTag.getRandomTAG(listTag);

                                iDoubanBookPresenter.searchBookByTag(BookByTagFragment.this,tag,true);

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
    @Override
    public void SearchBookByTagSuccess(BookRoot bookRoot, boolean isLoadMore) {
        showContentView();
        if(bookRoot!=null) {
            if (isLoadMore) {
                booksList.addAll(bookRoot.getBooks());
            } else {
                booksList.clear();
                booksList.addAll(bookRoot.getBooks());
            }
            adapter.setList(booksList);
            adapter.notifyDataSetChanged();
        }else{
            Toast.makeText(getActivity(), "由於接口已經過期，所以新鮮事頁面无法正常顯示", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void SearchBookByTagFail() {

        showError();
    }


}
