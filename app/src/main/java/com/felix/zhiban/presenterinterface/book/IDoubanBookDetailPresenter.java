package com.felix.zhiban.presenterinterface.book;


import com.felix.zhiban.viewinterface.book.IGetBookDetailView;

public interface IDoubanBookDetailPresenter {
    void getBookById(IGetBookDetailView iGetBookDetailView1, String id);
}
