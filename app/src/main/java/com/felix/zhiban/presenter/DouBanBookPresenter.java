package com.felix.zhiban.presenter;


import android.content.Context;
import android.os.AsyncTask;

import com.felix.zhiban.api.Url;
import com.felix.zhiban.base.BasePresenter;
import com.felix.zhiban.bean.book.BookRoot;
import com.felix.zhiban.viewinterface.book.ISearchBookByTagView;

public class DouBanBookPresenter extends BasePresenter{
    private  ISearchBookByTagView iSearchBookByTagView;

    private boolean isLoadMore;

    public DouBanBookPresenter(Context mContext) {
        super(mContext);
//        this.iSearchBookByTagView=iSearchBookByTagView;
//        this.iGetBookDetailView=iGetBookDetailView;
//        this.isLoadMore=isLoadMore;
    }

    public void searchBookByTag(ISearchBookByTagView iSearchBookByTagView1,String tag,boolean isLoadMore1){
        iSearchBookByTagView=iSearchBookByTagView1;
        isLoadMore=isLoadMore1;
        SearchBookByTagTask searchBookByTagTask=new SearchBookByTagTask();
        searchBookByTagTask.execute(tag);

    }


    /**
     * 按照tag搜索图书异步任务
     */
    private class SearchBookByTagTask extends AsyncTask<String,Void,BookRoot>{

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
                displaySearchBookFail();
            }else{
                displaySearchBookSuccess(iSearchBookByTagView,bookRoot,isLoadMore);
            }


        }
    }



    private void displaySearchBookSuccess(ISearchBookByTagView iSearchBookByTagView,BookRoot bookRoot,boolean isLoadMore){
        iSearchBookByTagView.SearchBookByTagSuccess(bookRoot,isLoadMore);
    }

    private void displaySearchBookFail(){
        iSearchBookByTagView.SearchBookByTagFail();
    }


}
