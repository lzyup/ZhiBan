package com.felix.zhiban.api;


import com.felix.zhiban.bean.book.Books;
import com.felix.zhiban.bean.book.BookRoot;

public interface INetWorkManager {
    /**
     * 根据Tag获取图书
     * @param url
     * @param tag
     */
    BookRoot searchBookByTag(String url, String tag);


    /**
     * 根据id获取图书详情
     * @param url
     * @param id
     */
    Books getBookDetail(String url, String id);

}
