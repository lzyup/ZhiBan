package com.felix.zhiban.presenter.news;


import android.content.Context;

import com.felix.zhiban.base.BasePresenter;
import com.felix.zhiban.bean.zhihunews.StroyDetailEntity;
import com.felix.zhiban.model.news.ZhihuNewsDetailModel;
import com.felix.zhiban.modelinterface.news.IZhihuNewsDetailModel;
import com.felix.zhiban.presenterinterface.news.IZhihuNewsDetailPresenter;
import com.felix.zhiban.viewinterface.news.IGetNewsDetailView;

public class ZhihuNewsDetailPresenter extends BasePresenter implements IZhihuNewsDetailPresenter {
    private IZhihuNewsDetailModel iZhihuNewsDetailModel;

    private IGetNewsDetailView iGetNewsDetailView;

    public ZhihuNewsDetailPresenter(Context mContext,IGetNewsDetailView iGetNewsDetailView) {
        super(mContext);
        this.iGetNewsDetailView=iGetNewsDetailView;
        iZhihuNewsDetailModel=new ZhihuNewsDetailModel(mContext,this);

    }

    @Override
    public void getNewsDetailById(String id) {
        iZhihuNewsDetailModel.initNewsDetail(id);

    }

    @Override
    public void getNewsDetailByIdSuccess(StroyDetailEntity stroyDetailEntity) {
        iGetNewsDetailView.showContentView();
        iGetNewsDetailView.getNewsDetail(stroyDetailEntity);
    }

    @Override
    public void getNewsDetailByIdFail() {
            iGetNewsDetailView.showError();
    }
}
