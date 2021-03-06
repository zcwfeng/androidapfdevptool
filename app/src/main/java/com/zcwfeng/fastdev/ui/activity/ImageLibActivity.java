package com.zcwfeng.fastdev.ui.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.util.Log;

import com.zcwfeng.componentlibs.surport.utils.LogFormater;
import com.zcwfeng.fastdev.R;
import com.zcwfeng.fastdev.ndk.NdkJniUtils;
import com.zcwfeng.fastdev.ui.adapter.MyViewPagerAdapter;
import com.zcwfeng.fastdev.ui.fragment.BaseFragment;
import com.zcwfeng.fastdev.ui.fragment.image.AndroidGifDrawableFragment;
import com.zcwfeng.fastdev.ui.fragment.image.FrescoFragment;
import com.zcwfeng.fastdev.ui.fragment.image.GifViewFragment;
import com.zcwfeng.fastdev.ui.fragment.image.GlideFragment;
import com.zcwfeng.fastdev.ui.fragment.image.PicassoFragment;

import java.util.ArrayList;
import java.util.List;
import java.util.WeakHashMap;

public class ImageLibActivity extends BaseActivity {
    private TabLayout mTabTitle;
    private ViewPager mViewPager;
    private MyViewPagerAdapter mAdapter;
    private List<String> mListTitle;//tab名称列表
    private WeakHashMap<String, BaseFragment> mListFragments;
    private GlideFragment mGlideFragment;
    private FrescoFragment mFrescoFragment;
    private PicassoFragment mPicassoFragment;
    private AndroidGifDrawableFragment mAndroidGifDrawableFragment;
    private GifViewFragment mGifViewFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrofit);
        setToolbar(null,"");
        initViews();
    }

    private void initViews() {
        mListTitle = new ArrayList<>();
        mListTitle.add("Glide");
        mListTitle.add("Fresco");
        mListTitle.add("Picasso");
        mListTitle.add("GifDrawable");
        mListTitle.add("GifView");
        mGlideFragment = GlideFragment.newInstance();
        mFrescoFragment = FrescoFragment.newInstance();
        mPicassoFragment = PicassoFragment.newInstance();
        mAndroidGifDrawableFragment = AndroidGifDrawableFragment.newInstance();
        mGifViewFragment = GifViewFragment.newInstance();
        mListFragments = new WeakHashMap<>();
        mListFragments.put(mListTitle.get(0), mGlideFragment);
        mListFragments.put(mListTitle.get(1), mFrescoFragment);
        mListFragments.put(mListTitle.get(2), mPicassoFragment);
        mListFragments.put(mListTitle.get(3), mAndroidGifDrawableFragment);
        mListFragments.put(mListTitle.get(4), mGifViewFragment);

        mTabTitle = (TabLayout) findViewById(R.id.sliding_tabs);

        mAdapter = new MyViewPagerAdapter(getSupportFragmentManager(), mListFragments, mListTitle);
        mViewPager = (ViewPager) findViewById(R.id.retrofit_viewpager);
        mViewPager.setAdapter(mAdapter);
        mTabTitle.setTabMode(TabLayout.MODE_FIXED);
        mTabTitle.addTab(mTabTitle.newTab().setText(mListTitle.get(0)));
        mTabTitle.addTab(mTabTitle.newTab().setText(mListTitle.get(1)));
        mTabTitle.addTab(mTabTitle.newTab().setText(mListTitle.get(2)));
        mTabTitle.addTab(mTabTitle.newTab().setText(mListTitle.get(3)));
        mTabTitle.addTab(mTabTitle.newTab().setText(mListTitle.get(4)));
        mTabTitle.setupWithViewPager(mViewPager);
        mTabTitle.setClipToPadding(true);


        NdkJniUtils instance = NdkJniUtils.getInstance();

        LogFormater.e("jni", instance.getStringFromC("Hellow", "Worldzcwfeng"));


        int data[] = {1,2,3,4,5};
        int result[] = instance.getIntArrayFromC(data);
        for(int i=0;i<result.length;i++) {
            Log.e("java_from_c","i==>"+result[i]);
        }
    }


}
