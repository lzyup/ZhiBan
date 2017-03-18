package com.felix.zhiban.adapter;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.felix.zhiban.viewimpl.news.TodayFragment;

public class NewsViewPagerAdapter extends FragmentStatePagerAdapter {

    private String[] titles=new String[]{"今日日报","不许无聊","互联网安全","体育日报"};


    public NewsViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {

        return TodayFragment.newsInstance(titles[position]);
    }

    @Override
    public int getCount() {
        return titles.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }
}
