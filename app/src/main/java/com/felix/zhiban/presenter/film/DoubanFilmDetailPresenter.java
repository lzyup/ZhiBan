package com.felix.zhiban.presenter.film;

import android.content.Context;

import com.felix.zhiban.base.BasePresenter;
import com.felix.zhiban.bean.book.Books;
import com.felix.zhiban.bean.filmdetail.FilmDetail;
import com.felix.zhiban.model.film.DoubanFilmDetailModel;
import com.felix.zhiban.modelinterface.film.IDoubanFilmDetailModel;
import com.felix.zhiban.modelinterface.film.IDoubanFilmModel;
import com.felix.zhiban.presenterinterface.book.IDoubanBookDetailPresenter;
import com.felix.zhiban.presenterinterface.film.IDoubanFilmDetailPresenter;
import com.felix.zhiban.viewinterface.film.IGetFilmDetailView;

/**
 * Created by XiaGF on 2017/5/2.
 */

public class DoubanFilmDetailPresenter extends BasePresenter implements IDoubanFilmDetailPresenter {

    private IGetFilmDetailView iGetFilmDetailView;

    private IDoubanFilmDetailModel iDoubanFilmDetailModel;

    public DoubanFilmDetailPresenter(Context mContext,IGetFilmDetailView iGetFilmDetailView) {
        super(mContext);
        this.iGetFilmDetailView=iGetFilmDetailView;
        iDoubanFilmDetailModel= new DoubanFilmDetailModel(mContext,this);
    }


    @Override
    public void getFilmById(String id) {
            iDoubanFilmDetailModel.initFilmById(id);
    }

    @Override
    public void getFilmByIdSuccess(FilmDetail filmDetail) {
            iGetFilmDetailView.showContentView();
            iGetFilmDetailView.getFilmSuccess(filmDetail);
    }

    @Override
    public void getFilmByIdFail() {
            iGetFilmDetailView.showError();
    }
}
