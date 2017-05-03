package com.felix.zhiban.presenterinterface.film;

import com.felix.zhiban.bean.filmlive.FilmLive;
import com.felix.zhiban.bean.top250.Root;

/**
 * Created by XiaGF on 2017/4/28.
 */

public interface IDoubanFilmPresenter {

    void searchFilmLive(boolean isLoadMore);

    void searchFilmLiveSuccess(FilmLive filmLive,boolean isLoadMore);

    void searchFilmLiveFail();

    void searchFilmTop250(int start,int count,boolean isLoadMore);

    void searchFilmTop250Success(Root root,boolean isLoadMore);

    void searchFilmTop250Fail();
}
