package com.example.xkdemo.tab;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.example.xkdemo.R;

import java.util.ArrayList;
import java.util.List;



public class TabActivity extends AppCompatActivity {
    private List<Fragment> fragments = new ArrayList<>();
    private ViewPager viewPager;
    private String[] titles = new String[]{"推荐", "热门", "财经", "NBA", "科技", "新闻", "人民日报", "这个标题很长很长很长","其他标题1","其他标题22","其他标题333","其他标题4444"};
    private ColorTrackTabLayout tabLayout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_tab);

        tabLayout = findViewById(R.id.tab);
        viewPager = findViewById(R.id.viewPager);

        initData();
    }

    private void initData() {
        fragments.clear();
        for (int i = 0; i < titles.length; i++) {
            fragments.add(new TabFragment());
        }

        viewPager.setAdapter(new MyFragmentPageAdater(getSupportFragmentManager()));

        tabLayout.setupWithViewPager(viewPager);
//        tabLayout.setTabPaddingLeftAndRight(20,20);
//        tabLayout.setTabMarginLeftAndRight(20,20);

//        tabLayout.setLastSelectedTabPosition(4);
//        tabLayout.setCurrentItem(4);
//        viewPager.setOffscreenPageLimit(titles.length);


    }

    private class MyFragmentPageAdater extends FragmentPagerAdapter {

        public MyFragmentPageAdater(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int i) {
            return fragments.get(i);
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
//            return super.getPageTitle(position);
            return titles[position];
        }

        @Override
        public int getCount() {
            return fragments.size();
        }
    }

}
