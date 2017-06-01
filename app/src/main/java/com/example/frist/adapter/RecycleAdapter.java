package com.example.frist.adapter;

import android.view.View;

import com.chad.library.adapter.base.BaseSectionQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.frist.R;
import com.example.frist.bean.OrderGoodItem;
import com.example.frist.bean.OrderItem;

import java.util.List;

/**
 * Created by Administrator on 2017/4/20.
 */

public class RecycleAdapter extends BaseSectionQuickAdapter<OrderItem,BaseViewHolder> {
    public RecycleAdapter(int layoutResId, int sectionHeadResId, List data) {
        super(layoutResId, sectionHeadResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, OrderItem item) {
        OrderGoodItem it= (OrderGoodItem)item.t;
       //
        helper.setText(R.id.name, "aaaaaaa");


    }

    @Override
    protected void convertHead(BaseViewHolder helper, final OrderItem item) {
        helper.setText(R.id.section,item.header);

        helper.setOnClickListener(R.id.imageview, new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}