package com.felix.zhiban.model.film;

import android.content.Context;
import android.os.AsyncTask;

import com.felix.zhiban.api.Url;
import com.felix.zhiban.base.BaseModel;
import com.felix.zhiban.bean.filmdetail.FilmDetail;
import com.felix.zhiban.modelinterface.book.IDoubanBookDetailModel;
import com.felix.zhiban.modelinterface.film.IDoubanFilmDetailModel;
import com.felix.zhiban.presenter.film.DouBanFilmPresenter;
import com.felix.zhiban.presenter.film.DoubanFilmDetailPresenter;
import com.felix.zhiban.presenterinterface.book.IDoubanBookDetailPresenter;
import com.felix.zhiban.presenterinterface.film.IDoubanFilmDetailPresenter;

/**
 * Created by XiaGF on 2017/5/2.
 */

public class DoubanFilmDetailModel extends BaseModel implements IDoubanFilmDetailModel {
    private Context context;

    private IDoubanFilmDetailPresenter iDoubanFilmDetailPresenter;

    public DoubanFilmDetailModel(Context mContext, DoubanFilmDetailPresenter douBanFilmDetailPresenter) {
        super(mContext);
        this.context=mContext;
        this.iDoubanFilmDetailPresenter= douBanFilmDetailPresenter;
    }

    @Override
    public void initFilmById(String id) {
        GetFilmByIdTask getFilmByIdTask=new GetFilmByIdTask();
        getFilmByIdTask.execute(id);

    }

    private class GetFilmByIdTask extends AsyncTask<String,Void,FilmDetail>{

        @Override
        protected FilmDetail doInBackground(String... strings) {
            String id=strings[0];
            FilmDetail filmDetail=iNetWorkManager.getFilmDetail(Url.GETFILMDETAIL,id);
            return filmDetail;
        }

        @Override
        protected void onPostExecute(FilmDetail filmDetail) {
            super.onPostExecute(filmDetail);
            if(filmDetail==null){
                iDoubanFilmDetailPresenter.getFilmByIdFail();
            }else{
                iDoubanFilmDetailPresenter.getFilmByIdSuccess(filmDetail);

            }
        }
    }
}
