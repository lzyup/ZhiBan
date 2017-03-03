package com.felix.zhiban.presenterinterface;


import com.felix.zhiban.viewinterface.book.IGetBookDetailView;

public interface IDoubanBookDetailPresenter {
    void getBookById(IGetBookDetailView iGetBookDetailView1, String id);
}
