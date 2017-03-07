package com.felix.zhiban.presenterinterface.book;


import com.felix.zhiban.bean.book.BookRoot;

public interface IDoubanBookPresenter {
    //void searchBookByTag(ISearchBookByTagView iSearchBookByTagView1, String tag, boolean isLoadMore1);
    void searchBookByTag(String tag,boolean isLoadMore);
    void searchBookByTagSuccess(BookRoot bookRoot,boolean isLoadMore);
    void searchBookByTagFail();
}
