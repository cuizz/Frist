package com.example.frist;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.example.frist.fragment.TabFragment;
import com.example.frist.fragment.TabFragment1;
import com.example.frist.fragment.TabFragment2;
import com.example.frist.fragment.TabFragment3;
import com.example.frist.fragment.TabFragment4;
import com.example.frist.util.GlideImageLoader;
import com.example.frist.view.ShowBigPicturePopwindow;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.listener.OnBannerListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/5/12.
 */

public class TabActivity extends AppCompatActivity{
    @BindView(R.id.banner)
    Banner banner;
    @BindView(R.id.viewpager)
    ViewPager viewPager;
    ShowBigPicturePopwindow popwindow=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tab_activity);
        ButterKnife.bind(this);
        TabLayout tableLayout=(TabLayout)findViewById(R.id.tabLayout);
        List<String> titles = new ArrayList<>();
        titles.add("精选");
        titles.add("体育");
        titles.add("巴萨");
        titles.add("购物");
        titles.add("明星");
        for(int i=0;i<titles.size();i++){
            tableLayout.addTab(tableLayout.newTab().setText(titles.get(i)));
        }
        List<Fragment> fragments = new ArrayList<>();
        for(int i=0;i<titles.size();i++){
            if(i==0){
                fragments.add(new TabFragment());
            }else if(i==1){
                fragments.add(new TabFragment1());
            }else if(i==2){
                fragments.add(new TabFragment2());
            }else if(i==3){
                fragments.add(new TabFragment3());
            }else if(i==4){
                fragments.add(new TabFragment4());
            }
        }
        //设置图片加载器
        banner.setImageLoader(new GlideImageLoader());
        //设置图片集合
        banner.setImages(Utils.getlist());
        //设置自动轮播，默认为true
        banner.isAutoPlay(true);
        //设置轮播时间
        banner.setDelayTime(5000);
        //设置banner样式
        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
        banner.setIndicatorGravity(BannerConfig.CENTER);
        //banner设置方法全部调用完毕时最后调用
        banner.start();
        FragmentAdapter mFragmentAdapteradapter =
                new FragmentAdapter(getSupportFragmentManager(), fragments, titles);
        //给ViewPager设置适配器
        viewPager.setAdapter(mFragmentAdapteradapter);
        //将TabLayout和ViewPager关联起来。
        tableLayout.setupWithViewPager(viewPager);
        banner.setOnBannerListener(new OnBannerListener() {
            @Override
            public void OnBannerClick(int position) {
                if(popwindow==null) {
                    popwindow = new ShowBigPicturePopwindow(TabActivity.this);
                }
                popwindow.showPopupWindow();
            }
        });
    }

    class FragmentAdapter extends FragmentStatePagerAdapter {
        private List<Fragment> mFragments;
        private List<String> mTitles;

        public FragmentAdapter(FragmentManager fm, List<Fragment> fragments, List<String> titles) {
            super(fm);
            mFragments = fragments;
            mTitles = titles;
        }

        @Override
        public Fragment getItem(int position) {
            return mFragments.get(position);
        }

        @Override
        public int getCount() {
            return mFragments.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mTitles.get(position);
        }
    }
}
