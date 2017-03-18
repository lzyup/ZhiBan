package com.felix.zhiban.api;


import com.felix.zhiban.bean.book.Books;
import com.felix.zhiban.bean.book.BookRoot;
import com.felix.zhiban.bean.zhihunews.RootEntity;
import com.felix.zhiban.bean.zhihunews.StroyDetailEntity;

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



    /**
     *知乎日报获取最近新闻
     * @param url
     * @return
     */
    RootEntity getLatesNews(String url);

    /**
     * 获取安全新闻
     * @param url
     * @return
     */
    RootEntity getSafety(String url);

    /**
     * 获取新鲜事
     * @param url
     * @return
     */
    RootEntity getInterest(String url);

    /**
     * 获取体育新闻
     * @param url
     * @return
     */
    RootEntity getSport(String url);

    /**
     * 获取新闻详细信息
     * @param url
     * @param id
     * @return
     */
    StroyDetailEntity getStroyDetail(String url,String id);

}
