package com.example.frist;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.view.LoopViewPager;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class SecondActivity extends AppCompatActivity {
    LoopViewPager viewPager;
    int []imagees=new int[]{R.drawable.pic1,R.drawable.pic3,R.drawable.pic2};
    LinearLayout.LayoutParams layoutParams=new LinearLayout.LayoutParams(20,20);
    List<View>points=new ArrayList<>();
    LinearLayout linearLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.second_layout);
        TabLayout tableLayout=(TabLayout)findViewById(R.id.tab_FindFragment_title);
        viewPager=(LoopViewPager)findViewById(R.id.pager) ;
        linearLayout=(LinearLayout)findViewById(R.id.points);
        tableLayout.addTab(tableLayout.newTab().setText("sssss"));
        tableLayout.addTab(tableLayout.newTab().setText("aaaa"));
        tableLayout.addTab(tableLayout.newTab().setText("aaaa"));
        List<ImageView>list=new ArrayList<>();
        for(int i=0;i<imagees.length;i++){
            ImageView imageView=new ImageView(this);
            imageView.setBackgroundResource(imagees[i]);
            View view=new View(this);
            points.add(view);
            initPoints(i);
            list.add(imageView);
        }

        viewPager.setAdapter(new MyAdapter(this,list));
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                for(int i=0;i<points.size();i++){
                    if(i==position){
                        points.get(i).setBackgroundResource(R.drawable.point2);
                    }else{
                        points.get(i).setBackgroundResource(R.drawable.point1);
                    }
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }
    private void initPoints(int position){
        layoutParams.setMargins(10,0,0,0);
        points.get(position).setLayoutParams(layoutParams);
        for(int i=0;i<points.size();i++){
            if(0==position){
                points.get(i).setBackgroundResource(R.drawable.point2);
            }else{
                points.get(i).setBackgroundResource(R.drawable.point1);
            }
        }
        linearLayout.addView(points.get(position));
    }
    class MyAdapter extends PagerAdapter{
        Context context;
        List<ImageView>list;
        public  MyAdapter(Context context, List<ImageView>list){
            this.context=context;
            this.list=list;
        }
        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            container.addView(list.get(position),0);
            return list.get(position);
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view==object;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView(list.get(position));
        }

        @Override
        public int getCount() {
            return imagees.length;
        }
    }
}