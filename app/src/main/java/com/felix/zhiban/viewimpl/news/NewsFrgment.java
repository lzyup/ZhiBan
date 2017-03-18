package com.felix.zhiban.viewimpl.news;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.felix.zhiban.R;
import com.felix.zhiban.adapter.NewsViewPagerAdapter;
import com.felix.zhiban.base.BaseFragment;

import java.util.List;

public class NewsFrgment extends BaseFragment {

    private List<Fragment> fragmentList;

    private TodayFragment todayFragment1;
    private TodayFragment todayFragment2;
    private TodayFragment todayFragment3;
    private TodayFragment todayFragment4;

    private TabLayout tabLayout;

    private CoordinatorLayout coordinatorLayout;

    private AppBarLayout appBarLayout;

    private ViewPager viewPager;

    private NewsViewPagerAdapter newsViewPagerAdapter;


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
        initView();
    }

    public static NewsFrgment newsInstance(){
        Bundle args=new Bundle();
        NewsFrgment frgment=new NewsFrgment();
        frgment.setArguments(args);
        return frgment;
    }
    private void initView(){
//        fragmentList=new ArrayList<>();
//        //todayFragment=new TodayFragment();
////        todayFragment1=TodayFragment.newsInstance();
////        todayFragment2=TodayFragment.newsInstance();
////        todayFragment3=TodayFragment.newsInstance();
////        todayFragment4=TodayFragment.newsInstance();
//        fragmentList.add(todayFragment1);
//        fragmentList.add(todayFragment2);
//        fragmentList.add(todayFragment3);
//        fragmentList.add(todayFragment4);
        newsViewPagerAdapter=new NewsViewPagerAdapter(getChildFragmentManager());//fragment里面套fragment用getChildFragmentManager(),Activity里面套fragment用getSupportFragmentManager
        viewPager.setAdapter(newsViewPagerAdapter);
        //设置ViewPager最大缓存的页面个数
        //viewPager.setOffscreenPageLimit(4);

        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        tabLayout.setTabMode(TabLayout.MODE_FIXED);
        //tabLayout.setSelectedTabIndicatorColor();
        //将Tablayout和ViewPager关联起来
        tabLayout.setupWithViewPager(viewPager);



    }
}
