package com.felix.zhiban.presenter;


import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.felix.zhiban.api.Url;
import com.felix.zhiban.base.BasePresenter;
import com.felix.zhiban.bean.book.Books;
import com.felix.zhiban.bean.book.BookRoot;
import com.felix.zhiban.viewinterface.book.IGetBookDetailView;
import com.felix.zhiban.viewinterface.book.ISearchBookByTagView;

public class DouBanBookPresenter extends BasePresenter{
    private  ISearchBookByTagView iSearchBookByTagView;

    private IGetBookDetailView iGetBookDetailView;


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
     *
     * @param id
     */
    public void getBookById(IGetBookDetailView iGetBookDetailView1,String id){
        iGetBookDetailView=iGetBookDetailView1;
        GetBookByIdTask getBookByIdTask=new GetBookByIdTask();
        getBookByIdTask.execute(id);
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

    /**
     * 根据id获取图书详情异步任务
     */
    private class GetBookByIdTask extends AsyncTask<String,Void,Books>{

        @Override
        protected Books doInBackground(String... strings) {
            String id=strings[0];
            Log.v("xgf121","进入获取图书详情");
            Books mBooks =iNetWorkManager.getBookDetail(Url.GETBOOKDETAIL,id);
            return mBooks;
        }

        @Override
        protected void onPostExecute(Books books) {
            super.onPostExecute(books);
            displayBookDetail(iGetBookDetailView, books);
        }
    }


    private void displaySearchBookSuccess(ISearchBookByTagView iSearchBookByTagView,BookRoot bookRoot,boolean isLoadMore){
        iSearchBookByTagView.SearchBookByTagSuccess(bookRoot,isLoadMore);
    }

    private void displaySearchBookFail(){
        iSearchBookByTagView.SearchBookByTagFail();
    }

    private void displayBookDetail(IGetBookDetailView iGetBookDetailView,Books books){
        Log.v("xgf book","得到books");
        iGetBookDetailView.getBookSuccess(books);
    }

}
