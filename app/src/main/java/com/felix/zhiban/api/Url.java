package com.felix.zhiban.api;


public class Url {
    public final static  String BASE_URL_DBAN="https://api.douban.com/";

    public final static String SEARCHBOOKBYTAG=BASE_URL_DBAN+"v2/book/search?tag=";

    public final static String GETBOOKDETAIL=BASE_URL_DBAN+"v2/book/";

    public static int START_TOP250;

    public static int COUNT_TOP250;

    public final static String SEARCHFILMTOP250=BASE_URL_DBAN+"v2/movie/top250?start="+START_TOP250+"&count="+COUNT_TOP250;

    public final static String SEARCHFILMLIVE=BASE_URL_DBAN+"v2/movie/in_theaters";

    public final static String GETFILMDETAIL=BASE_URL_DBAN+"v2/movie/subject/";

    public final static String BASETODETAIL_URL_ZHU="http://news-at.zhihu.com/api/4/news/";

    public final static String ZHU_TODAYURL="http://news-at.zhihu.com/api/4/news/latest";

    public final static String ZHU_BEFOREURL="http://news-at.zhihu.com/api/4/news/before/";

    public final static String ZHU_SPORTURL="http://news-at.zhihu.com/api/4/theme/8";

    public final static String ZHU_INTERESTURL="http://news-at.zhihu.com/api/4/theme/11";

    public final static String ZHU_SAFEURL="http://news-at.zhihu.com/api/4/theme/10";
}
