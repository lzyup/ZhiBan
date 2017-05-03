package com.felix.zhiban.viewinterface.film;

import com.felix.zhiban.bean.filmdetail.FilmDetail;

/**
 * Created by XiaGF on 2017/5/2.
 */

public interface IGetFilmDetailView {
    void getFilmSuccess(FilmDetail filmDetail);

    void showContentView();

    void showError();

    void showLoading();
}
