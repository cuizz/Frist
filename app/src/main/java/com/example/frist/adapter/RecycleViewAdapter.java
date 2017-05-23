package com.example.frist.adapter;

import android.graphics.Bitmap;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.frist.R;
import com.example.frist.TApplication;
import com.example.frist.bean.News;
import com.example.frist.bean.Photos;

import java.util.List;

/**
 * Created by Administrator on 2017/4/20.
 */

public class RecycleViewAdapter extends BaseQuickAdapter<Photos> {

    public RecycleViewAdapter(List<Photos>Students) {
        super(R.layout.rece_item,Students);
    }

    @Override
    protected void convert(final BaseViewHolder viewHolder, Photos item) {
        viewHolder.setText(R.id.tv_desc, item.getSetname());
        Glide.with(TApplication.getInstance()).load(item.getClientcover1()).
                asBitmap().into(new SimpleTarget<Bitmap>() {
            @Override
            public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                viewHolder.setImageBitmap(R.id.leftimage, resource);
            }
        });
    }
}