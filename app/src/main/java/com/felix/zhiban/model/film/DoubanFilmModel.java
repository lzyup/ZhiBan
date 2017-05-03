package com.felix.zhiban.model.film;

import android.content.Context;
import android.os.AsyncTask;

import com.felix.zhiban.api.Url;
import com.felix.zhiban.base.BaseModel;
import com.felix.zhiban.bean.filmlive.FilmLive;
import com.felix.zhiban.bean.top250.Root;
import com.felix.zhiban.modelinterface.film.IDoubanFilmModel;
import com.felix.zhiban.presenter.film.DouBanFilmPresenter;

import static com.felix.zhiban.api.Url.COUNT_TOP250;
import static com.felix.zhiban.api.Url.START_TOP250;

/**
 * Created by XiaGF on 2017/4/28.
 */

public class DoubanFilmModel extends BaseModel implements IDoubanFilmModel {

    private Context context;

    private boolean isLoadMore;

    private DouBanFilmPresenter douBanFilmPresenter;


    public DoubanFilmModel(Context mContext,DouBanFilmPresenter douBanFilmPresenter) {
        super(mContext);
        this.context=mContext;
        this.douBanFilmPresenter=douBanFilmPresenter;
    }

    @Override
    public void initFilmLive(boolean isLoadMore1) {
        isLoadMore=isLoadMore1;
        SearchFilmLiveTask searchFilmLiveTask=new SearchFilmLiveTask();
        searchFilmLiveTask.execute();
    }

    @Override
    public void initFilmTop250(int start,int count,boolean isLoadMore1) {
        isLoadMore=isLoadMore1;
        SearchFilmTop250Task searchFilmTop250Task=new SearchFilmTop250Task();
        searchFilmTop250Task.execute(start,count);

    }

    /**
     * 搜索热映电影异步任务
     */
    private class SearchFilmLiveTask extends AsyncTask<String ,Void,FilmLive>{

        @Override
        protected FilmLive doInBackground(String... strings) {
            FilmLive filmLive=iNetWorkManager.searchFilmLive(Url.SEARCHFILMLIVE);
            return filmLive;
        }

        @Override
        protected void onPostExecute(FilmLive filmLive) {
            super.onPostExecute(filmLive);
            if(filmLive==null){
                douBanFilmPresenter.searchFilmLiveFail();
            }else{
                douBanFilmPresenter.searchFilmLiveSuccess(filmLive,isLoadMore);
            }
        }
    }

    private class SearchFilmTop250Task extends  AsyncTask<Integer,Void,Root>{

        @Override
        protected Root doInBackground(Integer... integers) {
            START_TOP250=integers[0];
            COUNT_TOP250=integers[1];
            Root root=iNetWorkManager.searchFilmTop250(Url.SEARCHFILMTOP250);
            return root;
        }

        @Override
        protected void onPostExecute(Root root) {
            super.onPostExecute(root);
            if(root==null){
                douBanFilmPresenter.searchFilmTop250Fail();
            }else{
                douBanFilmPresenter.searchFilmTop250Success(root,isLoadMore);
            }
        }
    }

}
