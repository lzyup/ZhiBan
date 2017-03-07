package com.felix.zhiban.model.book;


import android.content.Context;
import android.os.AsyncTask;

import com.felix.zhiban.api.Url;
import com.felix.zhiban.base.BaseModel;
import com.felix.zhiban.bean.book.BookRoot;
import com.felix.zhiban.modelinterface.book.IDoubanBookModel;
import com.felix.zhiban.presenter.book.DouBanBookPresenter;

public class DoubanBookModel extends BaseModel implements IDoubanBookModel {
    private Context context;

    private boolean isLoadMore;

    private DouBanBookPresenter douBanBookPresenter;

    public DoubanBookModel(Context context,DouBanBookPresenter douBanBookPresenter) {
        super(context);
        this.context=context;
        this.douBanBookPresenter=douBanBookPresenter;
    }

    @Override
    public void initBookByTag(String tag, boolean isLoadMore1) {
        isLoadMore=isLoadMore1;
       SearchBookByTagTask searchBookByTagTask=new SearchBookByTagTask();
        searchBookByTagTask.execute(tag);
    }


    /**
     * 按照tag搜索图书异步任务
     */
    private class SearchBookByTagTask extends AsyncTask<String,Void,BookRoot> {

        @Override
        protected BookRoot doInBackground(String... strings) {
            String tag=strings[0];
            // Log.d("XGF121","tag="+tag);
            BookRoot mBookRoot=iNetWorkManager.searchBookByTag(Url.SEARCHBOOKBYTAG,tag);
            return mBookRoot;
        }

        @Override
        protected void onPostExecute(BookRoot bookRoot) {
            super.onPostExecute(bookRoot);
            if(bookRoot==null){
                douBanBookPresenter.searchBookByTagFail();
            }else{
                douBanBookPresenter.searchBookByTagSuccess(bookRoot,isLoadMore);
            }

        }
    }
}
