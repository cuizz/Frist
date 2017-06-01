package com.example.frist.adapter;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.frist.R;
import com.example.frist.TApplication;
import com.example.frist.bean.Photos;

import java.util.List;

/**
 * Created by Administrator on 2017/4/20.
 */

public class RecycleViewAdapter extends BaseQuickAdapter<Photos,BaseViewHolder> {

    public RecycleViewAdapter(List<Photos>phonto) {
        super(R.layout.rece_item,phonto);
    }
   // public RecycleViewAdapter() {
       // super(R.layout.rece_item);
  //  }
    @Override
    protected void convert(final BaseViewHolder viewHolder, Photos item) {
        viewHolder.setText(R.id.tv_desc, item.getSetname());
        Glide.with(TApplication.getInstance()).load(item.getClientcover1()).into((ImageView)viewHolder.getView(R.id.leftimage));
       //glide直接获取bitmap对象
        /*Glide.with(TApplication.getInstance()).load(item.getClientcover1()).
                asBitmap().into(new SimpleTarget<Bitmap>() {
            @Override
            public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                viewHolder.setImageBitmap(R.id.leftimage, resource);
            }
        });*/
    }
}