package com.felix.zhiban.viewimpl.news;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.felix.zhiban.R;
import com.felix.zhiban.base.BaseFragment;

public class NewsFrgment extends BaseFragment {
    private String [] mTitles;

    private TabLayout tabLayout;

    private CoordinatorLayout coordinatorLayout;

    private AppBarLayout appBarLayout;

    private ViewPager viewPager;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_news,container,false);
        coordinatorLayout=(CoordinatorLayout)view.findViewById(R.id.coordinatorlayout);
        appBarLayout=(AppBarLayout)view.findViewById(R.id.appbarlayout);
        tabLayout=(TabLayout)view.findViewById(R.id.tablayout);
        viewPager=(ViewPager)view.findViewById(R.id.viewpager);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {

        super.onViewCreated(view, savedInstanceState);
    }

    private void initView(){

        mTitles=new String[]{getString(R.string.zhihu_news),getString(R.string.fresh_news)};

    }
}
