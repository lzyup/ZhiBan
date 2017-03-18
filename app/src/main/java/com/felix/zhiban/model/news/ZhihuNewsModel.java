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
    public void initTodayNews(boolean isLoadMore1,String title) {
            isLoadMore=isLoadMore1;
           GetNewsTask getNewsTask=new GetNewsTask();
           getNewsTask.execute(title);
    }

    private class GetNewsTask extends AsyncTask<String,Void,RootEntity>{

        @Override
        protected RootEntity doInBackground(String... strings) {
            String title=strings[0];
            RootEntity rootEntity=null;
            if(title.equals("今日日报")){
                rootEntity=iNetWorkManager.getLatesNews(Url.ZHU_TODAYURL);
            }else if(title.equals("不许无聊")){
                 rootEntity=iNetWorkManager.getInterest(Url.ZHU_INTERESTURL);
            }else if(title.equals("互联网安全")){
                rootEntity=iNetWorkManager.getSafety(Url.ZHU_SAFEURL);
            }else if(title.equals("体育日报")){
                rootEntity=iNetWorkManager.getSport(Url.ZHU_SPORTURL);
            }

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
