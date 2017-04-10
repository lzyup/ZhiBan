package com.felix.zhiban.viewinterface.book;


import com.felix.zhiban.base.IBaseView;
import com.felix.zhiban.bean.book.BookRoot;

public interface ISearchBookByTagView extends IBaseView {
    void SearchBookByTagSuccess(BookRoot bookRoot,boolean isLoadMore);

    //void SearchBookByTagFail();

    void showBooksContentView();

    void showBooksError();

    void showBooksLoading();
}
