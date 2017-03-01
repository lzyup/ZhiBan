package com.felix.zhiban.adapter;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.felix.zhiban.viewimpl.book.BookByTagFragment;

public class BookViewPagerAdapter extends FragmentStatePagerAdapter {

    private String[] mTitles;
    public BookViewPagerAdapter(FragmentManager fm,String[] mTitles) {
        super(fm);
        this.mTitles=mTitles;
    }

    @Override public CharSequence getPageTitle(int position) {
        return mTitles[position];
    }
    @Override
    public Fragment getItem(int position) {

        return BookByTagFragment.newInstance(position,mTitles[position]);
    }

    @Override
    public int getCount() {
        return mTitles.length;
    }
}
