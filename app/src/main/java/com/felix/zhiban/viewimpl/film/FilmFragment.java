package com.felix.zhiban.viewimpl.film;


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
import com.felix.zhiban.adapter.FilmViewPagerAdapter;
import com.felix.zhiban.base.BaseFragment;

public class FilmFragment extends BaseFragment{
    //TabLayout中的tab标题
    private String[] mTitles;

    private TabLayout tabLayout;

    private CoordinatorLayout coordinatorLayout;

    private AppBarLayout appBarLayout;

    private ViewPager viewPager;

    private FilmViewPagerAdapter mViewPagerAdapter;
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_film,container,false);
        coordinatorLayout=(CoordinatorLayout)view.findViewById(R.id.coordinatorlayout);
        appBarLayout=(AppBarLayout)view.findViewById(R.id.appbarlayout);
        tabLayout=(TabLayout)view.findViewById(R.id.tablayout);
        viewPager=(ViewPager)view.findViewById(R.id.viewpager);
        return view;
    }

    public static FilmFragment newInstance(){
        Bundle args=new Bundle();
        FilmFragment fragment=new FilmFragment();
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
    }

    private void initView(){

        //fragment里面用getChildFragmentManager,Activity里面用FragmentManager
        mViewPagerAdapter=new FilmViewPagerAdapter(getChildFragmentManager());
        viewPager.setAdapter(mViewPagerAdapter);
        //设置ViewPager最大缓存的页面个数
        viewPager.setOffscreenPageLimit(3);


        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        tabLayout.setTabMode(TabLayout.MODE_FIXED);
        //tabLayout.setSelectedTabIndicatorColor();
        //将Tablayout和ViewPager关联起来
        tabLayout.setupWithViewPager(viewPager);


    }

}

