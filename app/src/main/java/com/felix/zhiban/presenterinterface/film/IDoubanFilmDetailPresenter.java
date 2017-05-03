package com.felix.zhiban.presenterinterface.film;

import com.felix.zhiban.bean.filmdetail.FilmDetail;

/**
 * Created by XiaGF on 2017/5/2.
 */

public interface IDoubanFilmDetailPresenter {
    void getFilmById(String id);

    void getFilmByIdSuccess(FilmDetail filmDetail);

    void getFilmByIdFail();
}
