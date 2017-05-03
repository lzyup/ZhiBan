package com.felix.zhiban.viewinterface.film;

import com.felix.zhiban.base.IBaseView;
import com.felix.zhiban.bean.filmlive.FilmLive;
import com.felix.zhiban.bean.top250.Root;

/**
 * Created by XiaGF on 2017/4/30.
 */

public interface ISearchFilmView extends IBaseView {
    void searchFilmLiveSuccess(FilmLive filmLive,boolean isLoadMore);

    void searchFilmTop250Success(Root root,boolean isLoadMore);

    void showFilmContentView();

    void showFilmError();

    void showFilmLoading();
}
