package com.felix.zhiban.model.news;


import android.content.Context;
import android.os.AsyncTask;

import com.felix.zhiban.api.Url;
import com.felix.zhiban.base.BaseModel;
import com.felix.zhiban.bean.zhihunews.RootEntity;
import com.felix.zhiban.modelinterface.news.IZhihuNewsModel;
import com.felix.zhiban.presenter.news.ZhihuNewsPresenter;

public class ZhihuNewsModel extends BaseModel implements IZhihuNewsModel{

    private Context context;

    private ZhihuNewsPresenter zhihuNewsPresenter;

    private boolean isLoadMore;


    public ZhihuNewsModel(Context mContext,ZhihuNewsPresenter zhihuNewsPresenter) {
        super(mContext);
        this.context=mContext;
        this.zhihuNewsPresenter=zhihuNewsPresenter;
    }

    @Override
    public void initTodayNews(boolean isLoadMore1) {
            isLoadMore=isLoadMore1;
           GetNewsTask getNewsTask=new GetNewsTask();
           getNewsTask.execute();
    }

    private class GetNewsTask extends AsyncTask<String,Void,RootEntity>{

        @Override
        protected RootEntity doInBackground(String... strings) {
            RootEntity rootEntity=iNetWorkManager.getLatesNews(Url.ZHU_INTERESTURL);
            return rootEntity;
        }

        @Override
        protected void onPostExecute(RootEntity rootEntity) {
            if(rootEntity!=null){
                zhihuNewsPresenter.getNewsSuccess(rootEntity.getStories(),isLoadMore);
            }else{
                zhihuNewsPresenter.getNewFail();
            }

        }
    }
}
