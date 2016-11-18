package com.zcwfeng.fastdev.ui.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;

import com.zcwfeng.fastdev.R;
import com.zcwfeng.fastdev.ui.adapter.MyViewPagerAdapter;
import com.zcwfeng.fastdev.ui.fragment.BaseFragment;
import com.zcwfeng.fastdev.ui.fragment.custom_views.CustomViewChartFragment;
import com.zcwfeng.fastdev.ui.fragment.custom_views.CustomViewLayoutFragment;

import java.util.ArrayList;
import java.util.List;
import java.util.WeakHashMap;

public class CustomViewActivity extends BaseActivity {
    private TabLayout mTabTitle;
    private ViewPager mViewPager;
    private MyViewPagerAdapter mAdapter;
    private List<String> mListTitle;//tab名称列表
    private WeakHashMap<String, BaseFragment> mListFragments;
    private CustomViewChartFragment mCustomViewChartFragment;
    private CustomViewLayoutFragment mCustomViewLayoutFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_container_template);

        initViews();
    }

    private void initViews() {
        mListTitle = new ArrayList<>();
        mListTitle.add("芝麻信用评分");
        mListTitle.add("自定义布局");
        mCustomViewChartFragment = CustomViewChartFragment.newInstance();
        mCustomViewLayoutFragment = CustomViewLayoutFragment.newInstance();
        mListFragments = new WeakHashMap<>();
        mListFragments.put(mListTitle.get(0), mCustomViewChartFragment);
        mListFragments.put(mListTitle.get(1), mCustomViewLayoutFragment);
        mTabTitle = (TabLayout) findViewById(R.id.sliding_tabs);
        mAdapter = new MyViewPagerAdapter(getSupportFragmentManager(), mListFragments, mListTitle);
        mViewPager = (ViewPager) findViewById(R.id.template_viewpager);
        mViewPager.setOffscreenPageLimit(2);
        mViewPager.setAdapter(mAdapter);
        mTabTitle.setTabMode(TabLayout.MODE_FIXED);
        mTabTitle.addTab(mTabTitle.newTab().setText(mListTitle.get(0)));
        mTabTitle.addTab(mTabTitle.newTab().setText(mListTitle.get(1)));
        mTabTitle.setupWithViewPager(mViewPager);
        mTabTitle.setClipToPadding(true);
    }


}