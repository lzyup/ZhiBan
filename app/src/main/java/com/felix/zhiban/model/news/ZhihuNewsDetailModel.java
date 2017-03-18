package com.felix.zhiban.model.news;


import android.content.Context;
import android.os.AsyncTask;

import com.felix.zhiban.api.Url;
import com.felix.zhiban.base.BaseModel;
import com.felix.zhiban.bean.zhihunews.StroyDetailEntity;
import com.felix.zhiban.modelinterface.news.IZhihuNewsDetailModel;
import com.felix.zhiban.presenter.news.ZhihuNewsDetailPresenter;

public class ZhihuNewsDetailModel extends BaseModel implements IZhihuNewsDetailModel {
    private Context context;

    private ZhihuNewsDetailPresenter zhihuNewsDetailPresenter;

    public ZhihuNewsDetailModel(Context mContext) {
        super(mContext);
    }

    public ZhihuNewsDetailModel(Context mContext, ZhihuNewsDetailPresenter zhihuNewsDetailPresenter){
        super(mContext);
        this.context=mContext;
        this.zhihuNewsDetailPresenter=zhihuNewsDetailPresenter;
    }


    @Override
    public void initNewsDetail(String id) {
        GetNewsDetailTask getNewsDetailTask=new GetNewsDetailTask();
        getNewsDetailTask.execute(id);
    }

private class GetNewsDetailTask extends AsyncTask<String,Void,StroyDetailEntity>{

    @Override
    protected StroyDetailEntity doInBackground(String... strings) {
        String id=strings[0];
        StroyDetailEntity mDetailEntity=iNetWorkManager.getStroyDetail(Url.BASETODETAIL_URL_ZHU,id);
        return mDetailEntity;
    }

    @Override
    protected void onPostExecute(StroyDetailEntity stroyDetailEntity) {
        super.onPostExecute(stroyDetailEntity);
        if(stroyDetailEntity==null){
            zhihuNewsDetailPresenter.getNewsDetailByIdFail();
        }else{
            zhihuNewsDetailPresenter.getNewsDetailByIdSuccess(stroyDetailEntity);
        }

    }
}
}
