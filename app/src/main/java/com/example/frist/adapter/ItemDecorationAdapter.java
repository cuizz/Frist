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

public class ItemDecorationAdapter extends BaseQuickAdapter<String,BaseViewHolder> {

    public ItemDecorationAdapter(List<String>phonto) {
        super(R.layout.item_decoration,phonto);
    }
   // public RecycleViewAdapter() {
       // super(R.layout.rece_item);
  //  }
    @Override
    protected void convert(final BaseViewHolder viewHolder, String item) {
        viewHolder.setText(R.id.tv_desc, item);
    }
}