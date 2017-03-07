package com.felix.zhiban.presenterinterface.book;


import com.felix.zhiban.bean.book.Books;

public interface IDoubanBookDetailPresenter {
    void getBookById( String id);

    void getBookByIdSuccess(Books books);

    void getBookByIdReady();

    void getBookByIdFail();
}
