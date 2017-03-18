package com.felix.zhiban.viewinterface.news;


import com.felix.zhiban.bean.zhihunews.StroyDetailEntity;

public interface IGetNewsDetailView {

    void getNewsDetail(StroyDetailEntity stroyDetailEntity);

    void showContentView();

    void showError();

    void showLoading();
}
