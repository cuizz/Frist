package com.example.frist.fragment;

import android.graphics.Bitmap;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.frist.R;
import com.example.frist.bean.Student;
import com.example.frist.util.GreenDaoManager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by Administrator on 2017/5/12.
 */

public class TabFragment extends BaseFragment{
    List<Student> students=new ArrayList<>();
    @BindView(R.id.recyclerview)
    RecyclerView recyclerView;
    @Override
    public int getLayoutID() {
        return R.layout.recycle_item;
    }

    @Override
    public void initView() {
        students= GreenDaoManager.getInstance().getSession().getStudentDao().loadAll();
        LinearLayoutManager mLinearLayoutManager=new LinearLayoutManager(getActivity());
        //解决正常情况下NestedScrollView嵌套RecycleView滑动冲突问题
        //mLinearLayoutManager.setSmoothScrollbarEnabled(true);
       // mLinearLayoutManager.setAutoMeasureEnabled(true);
        recyclerView.setLayoutManager(mLinearLayoutManager);
       // recyclerView.setHasFixedSize(true);
       // recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setAdapter(new QuickAdapter());
    }
    public class QuickAdapter extends BaseQuickAdapter<Student,BaseViewHolder> {
        public QuickAdapter() {
            super(R.layout.rece_item, students);
        }

        @Override
        protected void convert(final BaseViewHolder viewHolder, Student item) {
            viewHolder.setText(R.id.tv_desc, item.getName());
            Glide.with(getActivity()).load("http://img.my.csdn.net/uploads/201309/01/1378037235_7476.jpg").
                    asBitmap().into(new SimpleTarget<Bitmap>() {
                @Override
                public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                    viewHolder.setImageBitmap(R.id.leftimage,resource);
                }
            });
        }
    }
}
