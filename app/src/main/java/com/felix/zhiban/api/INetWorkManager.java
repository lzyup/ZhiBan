package com.felix.zhiban.api;


import com.felix.zhiban.bean.book.Books;
import com.felix.zhiban.bean.book.BookRoot;
import com.felix.zhiban.bean.filmdetail.FilmDetail;
import com.felix.zhiban.bean.filmlive.FilmLive;
import com.felix.zhiban.bean.top250.Root;
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
     * 获取热映电影
     * @param url
     * @return
     */
    FilmLive searchFilmLive(String url);

    /**
     * 获取电影top250
     * @param url
     * @return
     */
    Root searchFilmTop250(String url);

    /**
     * 获取电影详情
     * @param url
     * @param id
     * @return
     */
    FilmDetail getFilmDetail(String url,String id);

    //Root -



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
