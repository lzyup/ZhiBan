package com.felix.zhiban.modelinterface.film;


public interface IDoubanFilmModel {

    void initFilmLive(boolean isLoadMore);

    void initFilmTop250(int start,int count,boolean isLoadMore);
}
