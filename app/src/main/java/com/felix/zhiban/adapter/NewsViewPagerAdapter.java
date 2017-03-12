package com.felix.zhiban.adapter;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.List;

public class NewsViewPagerAdapter extends FragmentStatePagerAdapter {

    private String[] titles=new String[]{"今日日报","不许无聊","互联网安全","体育日报"};

    private List<Fragment>fragments;

    public NewsViewPagerAdapter(FragmentManager fm,List<Fragment>fragments) {
        super(fm);
        this.fragments=fragments;
    }

    @Override
    public Fragment getItem(int position) {

        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }
}
