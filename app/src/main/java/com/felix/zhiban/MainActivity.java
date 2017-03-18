package com.felix.zhiban;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;

import com.felix.zhiban.base.BaseActivity;
import com.felix.zhiban.tool.ThemeUtils;
import com.felix.zhiban.viewimpl.book.BookFragment;
import com.felix.zhiban.viewimpl.book.TestFragment;
import com.felix.zhiban.viewimpl.news.NewsFrgment;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivity extends BaseActivity {
    private CoordinatorLayout coordinatorLayout;

    private AppBarLayout appBarLayout;

    private Toolbar toolbar;

    private ViewPager viewPager;

    private RadioGroup radioGroup;

    private NavigationView navigationView;

    private DrawerLayout drawerLayout;

    private List<Fragment>listFragment;

    private BookFragment bookFragment1;

    private BookFragment bookFragment2;

    private BookFragment bookFragment3;

    private NewsFrgment newsFrgment;

    private TestFragment testFragment1;
    private TestFragment testFragment2;
    private TestFragment testFragment3;

    private int currentFragment;

    private static int defalutThemeColor = Color.rgb(251,91,129);



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        //StatusBarUtil.setColorNoTranslucentForDrawerLayout(MainActivity.this,drawerLayout,defalutThemeColor);
        initEvent();


    }

    private void initView(){
        coordinatorLayout=(CoordinatorLayout)findViewById(R.id.coordinatorlayout);
        appBarLayout=(AppBarLayout)findViewById(R.id.appbarlayout);
        viewPager=(ViewPager)findViewById(R.id.viewpager);
        radioGroup=(RadioGroup)findViewById(R.id.radiogroup);
        navigationView=(NavigationView)findViewById(R.id.design_navigation_view_left);
        drawerLayout=(DrawerLayout)findViewById(R.id.drawerlayout_home);
        toolbar=(Toolbar)findViewById(R.id.toolbar);
        //toolbar.setBackgroundColor(defalutThemeColor);
        //设置Drawerlayout开关指示器,toolbar最左边的icon
        ActionBarDrawerToggle mActionBarDrawerToggle=new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.open,R.string.close);
        mActionBarDrawerToggle.syncState();
        drawerLayout.setDrawerListener(mActionBarDrawerToggle);
        //给NaigationView填充顶部区域,也可在xml中使用app:headerLayout="@layout/header_nav"
        navigationView.inflateHeaderView(R.layout.header_nav);
        View headerView=navigationView.getHeaderView(0);
        CircleImageView sdvHeader=(CircleImageView)headerView.findViewById(R.id.sdv_avatar);
        sdvHeader.setImageResource(R.drawable.ic_avtar);
        navigationView.inflateMenu(R.menu.menu_nav);
        navigationView.setItemIconTintList(ThemeUtils.getNaviItemIconTinkList());
//        setSupportActionBar(toolbar);
//        ActionBar actionBar=getSupportActionBar();
//        if(actionBar!=null){
//            actionBar.setDisplayShowTitleEnabled(false);
//        }

    }

    private void initEvent(){
         listFragment=new ArrayList<>();
//        testFragment1=new TestFragment();
//        testFragment2=new TestFragment();
//        testFragment3=new TestFragment();
//        listFragment.add(testFragment1);
//        listFragment.add(testFragment2);
//        listFragment.add(testFragment3);
        bookFragment1=BookFragment.newInstance();
        bookFragment2=BookFragment.newInstance();
        bookFragment3=BookFragment.newInstance();
        newsFrgment=NewsFrgment.newsInstance();
        listFragment.add(bookFragment1);
        listFragment.add(bookFragment2);
        listFragment.add(newsFrgment);
        viewPager.setOffscreenPageLimit(3);
        viewPager.addOnPageChangeListener(onPageChangeListener);

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i){
                    case R.id.rb_home:
                        Log.v("xgf121","进入one");
                        currentFragment=0;
                        break;
                    case R.id.rb_film:
                        Log.v("xgf121","进入two");
                        currentFragment=1;
                        break;
                    case R.id.rb_book:
                        Log.v("xgf121","进入three");
                        currentFragment=2;
                        break;
                }

                viewPager.setCurrentItem(currentFragment,false);
            }
        });

        viewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return listFragment.get(position);
            }

            @Override
            public int getCount() {
                return listFragment.size();
            }
            @Override
            public void destroyItem(ViewGroup container, int position,
                                    Object object) {
                super.destroyItem(container, position, object);
            }
        });
    }

    private ViewPager.OnPageChangeListener onPageChangeListener= new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            switch (position){
                case 0:
                    radioGroup.check(R.id.rb_home);
                    break;
                case 1:
                    radioGroup.check(R.id.rb_film);
                    break;
                case 2:
                    radioGroup.check(R.id.rb_book);
                    break;
            }

        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };
    @Override
    public String setActName() {
        return null;
    }

    @Override
    public void showToast(String msg) {

    }
}
