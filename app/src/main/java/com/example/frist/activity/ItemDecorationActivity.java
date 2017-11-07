package com.example.frist.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.frist.R;
import com.example.frist.adapter.ItemDecorationAdapter;
import com.example.frist.utils.MyDecoration;
import com.example.frist.utils.RightLeftDecoration;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/11/7.
 */

public class ItemDecorationActivity extends TopBarBaseActivity{
    private RecyclerView recyclerView;
    private List<String>data=new ArrayList<>();
    @Override
    protected int getContentView() {
        return R.layout.recycle_item;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        recyclerView=(RecyclerView) findViewById(R.id.recyclerview);
        data.add("asd");
        data.add("acd");
        data.add("bsd");
        data.add("gsd");
        data.add("gd");
        data.add("jd");
        data.add("fd");
        data.add("md");
        data.add("md");
        data.add("hsd");
        data.add("vsd");
        data.add("hd");
        data.add("ld");
        RecyclerView.LayoutManager manager=new LinearLayoutManager(this);
        MyDecoration myDecoration=new MyDecoration(this);
        RightLeftDecoration rightLeftDecoration=new RightLeftDecoration(this);
        recyclerView.setLayoutManager(manager);
        recyclerView.addItemDecoration(myDecoration);
        recyclerView.addItemDecoration(rightLeftDecoration);
        ItemDecorationAdapter adapter=new ItemDecorationAdapter(data);
        recyclerView.setAdapter(adapter);
    }
}
