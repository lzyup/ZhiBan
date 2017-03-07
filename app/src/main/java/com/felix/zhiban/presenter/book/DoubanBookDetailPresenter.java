package com.felix.zhiban.presenter.book;


import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.felix.zhiban.api.Url;
import com.felix.zhiban.base.BasePresenter;
import com.felix.zhiban.bean.book.Books;
import com.felix.zhiban.presenterinterface.book.IDoubanBookDetailPresenter;
import com.felix.zhiban.viewinterface.book.IGetBookDetailView;

public class DoubanBookDetailPresenter extends BasePresenter implements IDoubanBookDetailPresenter {

    private IGetBookDetailView iGetBookDetailView;


    public DoubanBookDetailPresenter(Context mContext) {
        super(mContext);
    }

    public DoubanBookDetailPresenter(Context mContext, IGetBookDetailView iGetBookDetailView) {
        super(mContext);
        this.iGetBookDetailView = iGetBookDetailView;
    }

    /**
     *
     * @param id
     */
    @Override
    public void getBookById(IGetBookDetailView iGetBookDetailView1, String id) {
            iGetBookDetailView=iGetBookDetailView1;
            GetBookByIdTask getBookByIdTask=new GetBookByIdTask();
            getBookByIdTask.execute(id);
    }





    /**
     * 根据id获取图书详情异步任务
     */
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
            iGetBookDetailView.showLoading();
        }

        @Override
        protected void onPostExecute(Books books) {
            super.onPostExecute(books);
            if(books==null){

                iGetBookDetailView.showError();
            }else{
                iGetBookDetailView.showContentView();
                displayBookDetail(iGetBookDetailView, books);
            }

        }
    }


    private void displayBookDetail(IGetBookDetailView iGetBookDetailView,Books books){
        Log.v("xgf book","得到books");
        iGetBookDetailView.getBookSuccess(books);
    }

}
