package com.felix.zhiban.viewinterface.book;


import com.felix.zhiban.bean.book.Books;

public interface IGetBookDetailView {
    void getBookSuccess(Books books);

   // void getBookFail();

    void showContentView();

    void showError();

    void showLoading();
}
