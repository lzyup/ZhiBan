package com.felix.zhiban.base;


import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import rx.subscriptions.CompositeSubscription;

public class BaseFragment extends Fragment implements IBaseView {
    private Toast mToast;

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
    @Override
    public void showProgress(String message) {

    }

    @Override
    public void showToast(String msg) {
        if(mToast==null){
            mToast=Toast.makeText(getActivity(),msg,Toast.LENGTH_SHORT);
        }else{
            mToast.setText(msg);
            mToast.setDuration(Toast.LENGTH_LONG);
        }
        mToast.show();;
    }

    @Override
    public Context getContext() {
        return getActivity();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }


//    @Override
//    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        super.onCreateView( inflater,  container,savedInstanceState);
//        View ll=inflater.inflate(R.layout.fragment_base,container,false);
//        mContainer=(RelativeLayout)ll.findViewById(R.id.loading_container);
//        mLlloading=(LinearLayout)ll.findViewById(R.id.ll_loading);
//        mRefresh=(LinearLayout)ll.findViewById(R.id.ll_error_refresh);
//        imageView=(ImageView)ll.findViewById(R.id.img_progress);
//        if(imageView!=null){
//            //加载动画
//            mAnimationDrawable=(AnimationDrawable)imageView.getDrawable();
//            //默认进入页面就开启动画
//            if(!mAnimationDrawable.isRunning()){
//                mAnimationDrawable.start();
//            }
//        }else{
//            Log.d("xgf121","imageview is null");
//        }
//
//        if(mRefresh!=null){
//            mRefresh.setOnClickListener(new NoDoubleClickListener() {
//                @Override
//                public void onNoDoubleClick(View v) {
//
//                }
//            });
//        }else{
//            Log.d("xgf121","refresh is null");
//        }
//
//        return ll;
//    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
//        mLlloading=getView(R.id.ll_loading);
//        mRefresh=getView(R.id.ll_error_refresh);
//        imageView=getView(R.id.img_progress);
//        if(imageView!=null){
//            //加载动画
//            mAnimationDrawable=(AnimationDrawable)imageView.getDrawable();
//            //默认进入页面就开启动画
//            if(!mAnimationDrawable.isRunning()){
//                mAnimationDrawable.start();
//            }
//        }else{
//            Log.d("xgf121","imageview is null");
//        }
//
//        if(mRefresh!=null){
//            mRefresh.setOnClickListener(new NoDoubleClickListener() {
//                @Override
//                public void onNoDoubleClick(View v) {
//
//                }
//            });
//        }else{
//            Log.d("xgf121","refresh is null");
//        }


    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

//        mLlloading=getView(R.id.ll_loading);
//        mRefresh=getView(R.id.ll_error_refresh);
//        imageView=getView(R.id.img_progress);
//        if(imageView!=null){
//            //加载动画
//            mAnimationDrawable=(AnimationDrawable)imageView.getDrawable();
//            //默认进入页面就开启动画
//            if(!mAnimationDrawable.isRunning()){
//                mAnimationDrawable.start();
//            }
//        }else{
//            Log.d("xgf121","imageview is null");
//        }
//
//        if(mRefresh!=null){
//            mRefresh.setOnClickListener(new NoDoubleClickListener() {
//                @Override
//                public void onNoDoubleClick(View v) {
//
//                }
//            });
//        }else{
//            Log.d("xgf121","refresh is null");
//        }


    }

    /**
     * 加载完成的状态
     */
    protected void showContentView(){
//        if ( mLlloading.getVisibility() != View.GONE) {
//            mLlloading.setVisibility(View.GONE);
//        }
//        // 停止动画
//        if (mAnimationDrawable.isRunning()) {
//            mAnimationDrawable.stop();
//        }
//        if (mRefresh.getVisibility() != View.GONE) {
//            mRefresh.setVisibility(View.GONE);
//        }

    }

    protected  void showError(){
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
    protected <T extends View>T getView(int id){
        return (T)getView().findViewById(id);
    }

    /**
     * 加载失败后点击进行刷新重新获取数据
     */
    protected void onRefresh(){

    }

    /**
     * 点击加载失败后显示加载中的状态
     */
    protected void showLoading(){
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
}
