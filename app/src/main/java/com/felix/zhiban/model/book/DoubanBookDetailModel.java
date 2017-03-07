package com.felix.zhiban.model.book;


import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.felix.zhiban.api.Url;
import com.felix.zhiban.base.BaseModel;
import com.felix.zhiban.bean.book.Books;
import com.felix.zhiban.modelinterface.book.IDoubanBookDetailModel;
import com.felix.zhiban.presenter.book.DoubanBookDetailPresenter;

public class DoubanBookDetailModel extends BaseModel implements IDoubanBookDetailModel {
    private Context context;

    private DoubanBookDetailPresenter doubanBookDetailPresenter;

    public DoubanBookDetailModel(Context mContext,DoubanBookDetailPresenter doubanBookDetailPresenter) {
        super(mContext);
        this.context=mContext;
        this.doubanBookDetailPresenter=doubanBookDetailPresenter;
    }


    @Override
    public void initBookById(String id) {
        GetBookByIdTask getBookByIdTask=new GetBookByIdTask();
        getBookByIdTask.execute(id);

    }


    private class GetBookByIdTask extends AsyncTask<String,Void,Books> {

        @Override
        protected Books doInBackground(String... strings) {
            String id=strings[0];
            Log.v("xgf121","进入获取图书详情");
            Books mBooks =iNetWorkManager.getBookDetail(Url.GETBOOKDETAIL,id);
            return mBooks;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
           // doubanBookDetailPresenter.getBookByIdReady();
        }

        @Override
        protected void onPostExecute(Books books) {
            super.onPostExecute(books);
            if(books==null){

             //   iGetBookDetailView.showError();
                doubanBookDetailPresenter.getBookByIdFail();
            }else{
               // iGetBookDetailView.showContentView();
                //displayBookDetail(iGetBookDetailView, books);
                doubanBookDetailPresenter.getBookByIdSuccess(books);
            }

        }
    }

}
