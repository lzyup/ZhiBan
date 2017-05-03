package com.felix.zhiban.adapter;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.Log;

import com.felix.zhiban.bean.filmlive.FilmLive;
import com.felix.zhiban.viewimpl.film.FilmLiveFragment;
import com.felix.zhiban.viewimpl.film.FilmTop250Fragment;

public class FilmViewPagerAdapter extends FragmentStatePagerAdapter {

    private String[] mTitles=new String[]{"热映榜","TOP250"};

    public FilmViewPagerAdapter(FragmentManager fm, String[] mTitles) {
        super(fm);
        this.mTitles = mTitles;
    }

    public FilmViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }
    @Override
    public CharSequence getPageTitle(int position) {
        return mTitles[position];
    }
    @Override
    public Fragment getItem(int position) {
        Log.v("xgfposition","position="+position);
        if(position==0){
            return FilmLiveFragment.newInstance(position,mTitles[position]);
        }else{
            return FilmTop250Fragment.newInstance(position,mTitles[position]);
        }

    }

    @Override
    public int getCount() {
        return mTitles.length;
    }
}
