package com.felix.zhiban.viewinterface.news;


import com.felix.zhiban.bean.zhihunews.StroriesEntity;

import java.util.List;

public interface IGetNewsView {
    void getNewsSuccess(List<StroriesEntity> stroriesEntityList,boolean isLoadMore);

    // void getBookFail();

    void showNewsContentView();

    void showNewsError();

    void showNewsLoading();

    void initView();

    void scrollRecycleView();
}
