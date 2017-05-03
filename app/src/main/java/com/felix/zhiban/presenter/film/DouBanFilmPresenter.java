package com.felix.zhiban.presenter.film;

import android.content.Context;

import com.felix.zhiban.base.BasePresenter;
import com.felix.zhiban.bean.filmlive.FilmLive;
import com.felix.zhiban.bean.top250.Root;
import com.felix.zhiban.model.film.DoubanFilmModel;
import com.felix.zhiban.modelinterface.film.IDoubanFilmModel;
import com.felix.zhiban.presenterinterface.film.IDoubanFilmPresenter;
import com.felix.zhiban.viewinterface.film.ISearchFilmView;

/**
 * Created by XiaGF on 2017/4/30.
 */

public class DouBanFilmPresenter extends BasePresenter implements IDoubanFilmPresenter {

    private ISearchFilmView iSearchFilmView;

    private IDoubanFilmModel iDoubanFilmModel;

    public DouBanFilmPresenter(Context mContext,ISearchFilmView iSearchFilmView) {
        super(mContext);
        this.iSearchFilmView=iSearchFilmView;
        this.iDoubanFilmModel=new DoubanFilmModel(mContext,this);
    }

    @Override
    public void searchFilmLive( boolean isLoadMore) {
        iDoubanFilmModel.initFilmLive(isLoadMore);

    }

    @Override
    public void searchFilmLiveSuccess(FilmLive filmLive, boolean isLoadMore) {
        iSearchFilmView.showFilmContentView();
        iSearchFilmView.searchFilmLiveSuccess(filmLive,isLoadMore);

    }

    @Override
    public void searchFilmLiveFail() {
        iSearchFilmView.showFilmError();

    }

    @Override
    public void searchFilmTop250(int start,int count, boolean isLoadMore) {
        iDoubanFilmModel.initFilmTop250(start,count,isLoadMore);

    }

    @Override
    public void searchFilmTop250Success(Root root, boolean isLoadMore) {
        iSearchFilmView.showFilmContentView();
        iSearchFilmView.searchFilmTop250Success(root,isLoadMore);
    }

    @Override
    public void searchFilmTop250Fail() {
        iSearchFilmView.showFilmError();
    }
}
