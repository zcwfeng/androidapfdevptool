package com.zcwfeng.fastdev.ui.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;

import com.zcwfeng.fastdev.R;
import com.zcwfeng.fastdev.ui.adapter.MyViewPagerAdapter;
import com.zcwfeng.fastdev.ui.fragment.BaseFragment;
import com.zcwfeng.fastdev.ui.fragment.request.OkHttpFragment;
import com.zcwfeng.fastdev.ui.fragment.request.RetrofitFragment;
import com.zcwfeng.fastdev.ui.fragment.request.VolleyFragment;

import java.util.ArrayList;
import java.util.List;
import java.util.WeakHashMap;

public class RequestActivity extends BaseActivity {
    private TabLayout mTabTitle;
    private ViewPager mViewPager;
    private MyViewPagerAdapter mAdapter;
    private List<String> mListTitle;//tab名称列表
    private WeakHashMap<String, BaseFragment> mListFragments;
    private RetrofitFragment mRetrofitFrgment;
    private OkHttpFragment mOkHttpFragment;
    private VolleyFragment mVolleyFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrofit);
        setToolbar(null,"");
        initViews();
    }

    private void initViews() {
        mListTitle = new ArrayList<>();
        mListTitle.add("Retrofit");
        mListTitle.add("Okhttp");
        mListTitle.add("Volley");
        mRetrofitFrgment = RetrofitFragment.newInstance();
        mOkHttpFragment = OkHttpFragment.newInstance().newInstance();
        mVolleyFragment = VolleyFragment.newInstance();
        mListFragments = new WeakHashMap<>();
        mListFragments.put(mListTitle.get(0), mRetrofitFrgment);
        mListFragments.put(mListTitle.get(1), mOkHttpFragment);
        mListFragments.put(mListTitle.get(2), mVolleyFragment);
        mTabTitle = (TabLayout) findViewById(R.id.sliding_tabs);
        mAdapter = new MyViewPagerAdapter(getSupportFragmentManager(), mListFragments, mListTitle);
        mViewPager = (ViewPager) findViewById(R.id.retrofit_viewpager);
        mViewPager.setAdapter(mAdapter);
        mTabTitle.setTabMode(TabLayout.MODE_FIXED);
        mTabTitle.addTab(mTabTitle.newTab().setText(mListTitle.get(0)));
        mTabTitle.addTab(mTabTitle.newTab().setText(mListTitle.get(1)));
        mTabTitle.addTab(mTabTitle.newTab().setText(mListTitle.get(2)));
        mTabTitle.setupWithViewPager(mViewPager);
        mTabTitle.setClipToPadding(true);
    }


}
