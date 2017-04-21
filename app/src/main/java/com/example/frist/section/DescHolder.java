package com.example.frist.section;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.daimajia.swipe.SwipeLayout;
import com.example.frist.R;

/**
 * Created by lyd10892 on 2016/8/23.
 */

public class DescHolder extends RecyclerView.ViewHolder {
    public TextView descView;
    public ImageView imageview;
    SwipeLayout layout;
    LinearLayout bommot;
    public DescHolder(View itemView) {
        super(itemView);
        initView();
    }

    private void initView() {
        descView = (TextView) itemView.findViewById(R.id.tv_desc);
        imageview=(ImageView)itemView.findViewById(R.id.leftimage);
        layout=(SwipeLayout)itemView.findViewById(R.id.sample1);
        bommot=(LinearLayout)itemView.findViewById(R.id.bottom_wrapper);
    }
}
