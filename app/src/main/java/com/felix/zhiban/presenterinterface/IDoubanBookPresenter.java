package com.felix.zhiban.presenterinterface;


import com.felix.zhiban.viewinterface.book.ISearchBookByTagView;

public interface IDoubanBookPresenter {
    void searchBookByTag(ISearchBookByTagView iSearchBookByTagView1, String tag, boolean isLoadMore1);
}
