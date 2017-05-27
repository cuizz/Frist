package com.example.frist.adapter;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.frist.R;
import com.example.frist.TApplication;
import com.example.frist.bean.HorizontalBean;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Administrator on 2017/4/20.
 */

public class HRecycleViewAdapter extends BaseQuickAdapter<HorizontalBean> {

    public HRecycleViewAdapter(List<HorizontalBean>phonto) {
        super(R.layout.hrece_mulitem,phonto);
    }
   // public RecycleViewAdapter() {
       // super(R.layout.rece_item);
  //  }
    @Override
    protected void convert(final BaseViewHolder viewHolder, HorizontalBean item) {
        viewHolder.setText(R.id.tv_desc, item.getName());
        Glide.with(TApplication.getInstance()).load(item.getImage()).into((CircleImageView)viewHolder.getView(R.id.leftimage));
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