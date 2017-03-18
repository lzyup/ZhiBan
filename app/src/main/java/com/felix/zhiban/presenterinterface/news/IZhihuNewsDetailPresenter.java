package com.felix.zhiban.presenterinterface.news;


import com.felix.zhiban.bean.zhihunews.StroyDetailEntity;

public interface IZhihuNewsDetailPresenter {
    void getNewsDetailById(String id);

    void getNewsDetailByIdSuccess(StroyDetailEntity stroyDetailEntity);

    void getNewsDetailByIdFail();
}
