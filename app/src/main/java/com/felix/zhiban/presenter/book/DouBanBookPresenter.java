package com.felix.zhiban.presenter.book;


import android.content.Context;

import com.felix.zhiban.base.BasePresenter;
import com.felix.zhiban.bean.book.BookRoot;
import com.felix.zhiban.model.book.DoubanBookModel;
import com.felix.zhiban.modelinterface.book.IDoubanBookModel;
import com.felix.zhiban.presenterinterface.book.IDoubanBookPresenter;
import com.felix.zhiban.viewinterface.book.ISearchBookByTagView;

public class DouBanBookPresenter extends BasePresenter implements IDoubanBookPresenter {
    private ISearchBookByTagView iSearchBookByTagView;

    private IDoubanBookModel iDoubanBookModel;


    public DouBanBookPresenter(Context mContext, ISearchBookByTagView iSearchBookByTagView) {
        super(mContext);
        this.iSearchBookByTagView = iSearchBookByTagView;
        this.iDoubanBookModel = new DoubanBookModel(mContext, this);
    }

    @Override
    public void searchBookByTag(String tag, boolean isLoadMore) {
        iDoubanBookModel.initBookByTag(tag, isLoadMore);

    }

    @Override
    public void searchBookByTagSuccess(BookRoot bookRoot, boolean isLoadMore) {
        iSearchBookByTagView.showBooksContentView();
        iSearchBookByTagView.SearchBookByTagSuccess(bookRoot, isLoadMore);
    }

    @Override
    public void searchBookByTagFail() {
        //iSearchBookByTagView.SearchBookByTagFail();
        iSearchBookByTagView.showBooksError();
    }


}