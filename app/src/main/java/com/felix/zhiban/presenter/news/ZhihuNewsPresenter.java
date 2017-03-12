package com.felix.zhiban.presenter.news;


import android.content.Context;

import com.felix.zhiban.base.BasePresenter;
import com.felix.zhiban.bean.zhihunews.StroriesEntity;
import com.felix.zhiban.model.news.ZhihuNewsModel;
import com.felix.zhiban.modelinterface.news.IZhihuNewsModel;
import com.felix.zhiban.presenterinterface.news.IZhihuNewsPresenter;
import com.felix.zhiban.viewinterface.news.IGetNewsView;

import java.util.List;

public class ZhihuNewsPresenter extends BasePresenter implements IZhihuNewsPresenter{

    private IGetNewsView iGetNewsView;

    private IZhihuNewsModel iZhihuNewsModel;

     public ZhihuNewsPresenter(Context mContext,IGetNewsView iGetNewsView) {
        super(mContext);
        this.iGetNewsView=iGetNewsView;
         iZhihuNewsModel=new ZhihuNewsModel(mContext,this);
    }

    @Override
    public void getInitNews(boolean isLoadMore) {
        iGetNewsView.initView();
        iZhihuNewsModel.initTodayNews(isLoadMore);
    }

    @Override
    public void getScrollNews(boolean isLoadMore) {
        iZhihuNewsModel.initTodayNews(isLoadMore);


    }

    @Override
    public void getNewsSuccess(List<StroriesEntity>stroriesEntityList, boolean isLoadMore) {
        iGetNewsView.showNewsContentView();
        iGetNewsView.getNewsSuccess(stroriesEntityList,isLoadMore);
    }

    @Override
    public void getNewFail() {
        iGetNewsView.showNewsError();
    }

    @Override
    public void getNewsFromscrollRecycleView() {
        iGetNewsView.scrollRecycleView();
    }
}
