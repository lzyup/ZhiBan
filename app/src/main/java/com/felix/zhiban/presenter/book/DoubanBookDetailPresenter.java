package com.felix.zhiban.presenter.book;


import android.content.Context;

import com.felix.zhiban.base.BasePresenter;
import com.felix.zhiban.bean.book.Books;
import com.felix.zhiban.model.book.DoubanBookDetailModel;
import com.felix.zhiban.modelinterface.book.IDoubanBookDetailModel;
import com.felix.zhiban.presenterinterface.book.IDoubanBookDetailPresenter;
import com.felix.zhiban.viewinterface.book.IGetBookDetailView;

public class DoubanBookDetailPresenter extends BasePresenter implements IDoubanBookDetailPresenter {

    private IGetBookDetailView iGetBookDetailView;

    private IDoubanBookDetailModel iDoubanBookDetailModel;

    public DoubanBookDetailPresenter(Context mContext, IGetBookDetailView iGetBookDetailView) {
        super(mContext);
        this.iGetBookDetailView = iGetBookDetailView;
        iDoubanBookDetailModel=new DoubanBookDetailModel(mContext,this);
    }

    /**
     *
     * @param id
     */
    @Override
    public void getBookById( String id) {
        iDoubanBookDetailModel.initBookById(id);

    }

    @Override
    public void getBookByIdSuccess(Books books) {

        iGetBookDetailView.showContentView();
        iGetBookDetailView.getBookSuccess(books);
    }

    @Override
    public void getBookByIdReady() {
        iGetBookDetailView.showLoading();
    }

    @Override
    public void getBookByIdFail() {

        iGetBookDetailView.showError();
    }


}
