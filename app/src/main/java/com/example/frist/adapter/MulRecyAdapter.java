package com.example.frist.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.frist.R;
import com.example.frist.TApplication;
import com.example.frist.bean.Photos;

import java.util.List;

/**
 * Created by Administrator on 2017/5/24.
 */

public class MulRecyAdapter extends BaseMultiItemQuickAdapter<Photos> {

    public MulRecyAdapter(Context context, List data) {
        super(data);
        addItemType(1, R.layout.rece_item);
        addItemType(2, R.layout.rece_mulitem);
        //addItemType(MultipleItem.IMG_TEXT, R.layout.item_img_text_view);
    }

    @Override
    protected void convert(BaseViewHolder helper, Photos item) {
        switch (helper.getItemViewType()) {
            case 1:
                helper.setText(R.id.tv_desc, item.getSetname());
                RelativeLayout.LayoutParams param=new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                RelativeLayout.LayoutParams paramss=new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                WindowManager wms = (WindowManager) TApplication.getInstance()
                        .getSystemService(Context.WINDOW_SERVICE);
                int widths = wms.getDefaultDisplay().getWidth()/3;
                int heights=widths/2+30;
                TextView textView=(TextView)helper.getView(R.id.tv_desc);
                ImageView imageView=(ImageView)helper.getView(R.id.leftimage);
                param.height=heights;
                param.width=widths;
                param.setMargins(0,10,0,10);
                param.setMarginStart(wms.getDefaultDisplay().getWidth()/3*2-20);
                imageView.setLayoutParams(param);
                paramss.rightMargin=wms.getDefaultDisplay().getWidth()/3-15;
                textView.setLayoutParams(paramss);
                Glide.with(TApplication.getInstance()).load(item.getClientcover1()).into(imageView);
                break;
            case 2:
                helper.setText(R.id.tv_desc, item.getSetname());
                LinearLayout.LayoutParams params=new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                WindowManager wm = (WindowManager) TApplication.getInstance()
                        .getSystemService(Context.WINDOW_SERVICE);
                int width = wm.getDefaultDisplay().getWidth()/3-15;
                int height=width/2+30;
                ImageView imageViews=(ImageView)helper.getView(R.id.leftimage);
                params.height=height;
                params.width=width;
                params.setMargins(0,0,0,20);
                ImageView imageView1=(ImageView)helper.getView(R.id.leftimage1);
                ImageView imageView2=(ImageView)helper.getView(R.id.leftimage2);
                imageViews.setLayoutParams(params);
                imageView1.setLayoutParams(params);
                imageView2.setLayoutParams(params);
                Glide.with(TApplication.getInstance()).load(item.getClientcover1()).into(imageViews);
                Glide.with(TApplication.getInstance()).load(item.getCover()).into(imageView1);
                Glide.with(TApplication.getInstance()).load(item.getScover()).into(imageView2);
                break;
        }
    }

}