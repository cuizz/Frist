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
import com.example.frist.view.WrapContentHeightViewPager;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SecondActivity extends AppCompatActivity {
    @BindView(R.id.banner)
    Banner banner;
    List<String>images=new ArrayList<>();
    List<String>title=new ArrayList<>();
    @BindView(R.id.viewPager)
    WrapContentHeightViewPager viewPager;
    List<Fragment>fragments=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_layout);
        ButterKnife.bind(this);
        TabLayout tableLayout=(TabLayout)findViewById(R.id.tab_FindFragment_title);
        tableLayout.addTab(tableLayout.newTab().setText("java"));
        tableLayout.addTab(tableLayout.newTab().setText("android"));
        tableLayout.addTab(tableLayout.newTab().setText("html"));
        tableLayout.addTab(tableLayout.newTab().setText("css"));
        tableLayout.addTab(tableLayout.newTab().setText("js"));
        images.add("http://img.my.csdn.net/uploads/201309/01/1378037235_3453.jpg");
        images.add("http://img.my.csdn.net/uploads/201309/01/1378037235_7476.jpg");
        images.add("http://img.my.csdn.net/uploads/201309/01/1378037235_9280.jpg");
        images.add("http://img.my.csdn.net/uploads/201309/01/1378037234_3539.jpg");
        images.add("http://img.my.csdn.net/uploads/201309/01/1378037234_6318.jpg");
        title.add("java");
        title.add("android");
        title.add("html");
        for(int i=0;i<title.size();i++){
              fragments.add(new TabFragment());
        }
        //设置图片加载器
        banner.setImageLoader(new GlideImageLoader());
        //设置图片集合
        banner.setImages(images);
        //设置自动轮播，默认为true
        banner.isAutoPlay(true);
        //设置轮播时间
        banner.setDelayTime(5000);
        //设置banner样式
        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
        banner.setIndicatorGravity(BannerConfig.CENTER);
        //banner设置方法全部调用完毕时最后调用
        banner.start();
        viewPager.setAdapter(new ViewPagerAdapter(getSupportFragmentManager(),fragments,title));
       tableLayout.setupWithViewPager(viewPager);
        tableLayout.setTabsFromPagerAdapter(new ViewPagerAdapter(getSupportFragmentManager(),fragments,title));
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
     class ViewPagerAdapter extends FragmentStatePagerAdapter {
        private List<Fragment>  fragments;
        private List<String> titles;
        /**
         * 构造方法
         * @param manager
         * @param fragments
         */
        public ViewPagerAdapter(FragmentManager manager, List<Fragment> fragments,List<String> titles){
            super(manager);
            this.fragments=fragments;
            this.titles=titles;
        }

        @Override
        public int getCount() {
            if (fragments!=null){
                return fragments.size();
            }
            return 0;
        }

        @Override
        public Fragment getItem(int position) {
            if (fragments!=null){
                return fragments.get(position);
           }
           return null;
         /*  Fragment fragment = null;
            if (0 == position) {
                fragment = new TabFragment();
            } else if (1 == position) {
                fragment = new TabFragment1();
            } else if (2 == position) {
                fragment = new TabFragment2();
            }
            return fragment;*/
        }


        @Override
        public CharSequence getPageTitle(int position) {
            if (titles!=null){
                return titles.get(position);
            }
            return "";
        }

    }
}