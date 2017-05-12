package com.example.frist;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.frist.fragment.TabFragment;
import com.example.frist.fragment.TabFragment1;
import com.example.frist.fragment.TabFragment2;
import com.example.frist.fragment.TabFragment3;
import com.example.frist.fragment.TabFragment4;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.loader.ImageLoader;

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
    List<String> images=new ArrayList<>();
   // @BindView(R.id.viewPager)
    ViewPager viewPager;
    List<Fragment>fragments=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tab_activity);
        ButterKnife.bind(this);
        TabLayout tableLayout=(TabLayout)findViewById(R.id.tab_FindFragment_title);
        viewPager=(ViewPager)findViewById(R.id.viewPager);

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
            fragments.add(new TabFragment());
        }
        FragmentAdapter mFragmentAdapteradapter =
                new FragmentAdapter(getSupportFragmentManager(), fragments, titles);
        //给ViewPager设置适配器
        viewPager.setAdapter(mFragmentAdapteradapter);
        //将TabLayout和ViewPager关联起来。
        tableLayout.setupWithViewPager(viewPager);
        //给TabLayout设置适配器
        tableLayout.setTabsFromPagerAdapter(mFragmentAdapteradapter);
    }

    class GlideImageLoader extends ImageLoader {
        @Override
        public void displayImage(Context context, Object path, ImageView imageView) {
            /**
             注意：
             1.图片加载器由自己选择，这里不限制，只是提供几种使用方法
             2.返回的图片路径为Object类型，由于不能确定你到底使用的那种图片加载器，
             传输的到的是什么格式，那么这种就使用Object接收和返回，你只需要强转成你传输的类型就行，
             切记不要胡乱强转！
             */

            //Glide 加载图片简单用法
            Glide.with(context).load(path).into(imageView);

            //Picasso 加载图片简单用法
            // Picasso.with(context).load(path).into(imageView);

            //用fresco加载图片简单用法，记得要写下面的createImageView方法
            //  Uri uri = Uri.parse((String) path);
            // imageView.setImageURI(uri);
        }

        //提供createImageView 方法，如果不用可以不重写这个方法，主要是方便自定义ImageView的创建
        @Override
        public ImageView createImageView(Context context) {
            //使用fresco，需要创建它提供的ImageView，当然你也可以用自己自定义的具有图片加载功能的ImageView
            //SimpleDraweeView simpleDraweeView=new SimpleDraweeView(context);
            return null;
        }
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
