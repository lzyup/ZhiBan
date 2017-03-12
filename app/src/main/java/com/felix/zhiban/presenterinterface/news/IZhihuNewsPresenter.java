package com.felix.zhiban.presenterinterface.news;


import com.felix.zhiban.bean.zhihunews.StroriesEntity;

import java.util.List;

public interface IZhihuNewsPresenter {
    void getInitNews(boolean isLoadMore);
    void getScrollNews(boolean isLoadMore);
    void getNewsSuccess(List<StroriesEntity>stroriesEntityList, boolean isLoadMore);
    void getNewFail();
    void getNewsFromscrollRecycleView();
}
