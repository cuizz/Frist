package com.example.frist.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.frist.R;
import com.example.frist.adapter.ItemDecorationAdapter;
import com.example.frist.utils.MyDecoration;
import com.example.frist.utils.RightLeftDecoration;
import com.github.stuxuhai.jpinyin.PinyinFormat;
import com.github.stuxuhai.jpinyin.PinyinHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/11/7.
 */

public class ItemDecorationActivity extends TopBarBaseActivity{
    private RecyclerView recyclerView;
    private List<String>data=new ArrayList<>();
    private List<String>datas=new ArrayList<>();
    @Override
    protected int getContentView() {
        return R.layout.recycle_item;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        recyclerView=(RecyclerView) findViewById(R.id.recyclerview);
        String [] citys=getResources().getStringArray(R.array.city);
        StringBuffer buffer=new StringBuffer();
        for(String city:citys){
            try {
                String pinyin= PinyinHelper.convertToPinyinString(city, " ", PinyinFormat.WITHOUT_TONE);
                buffer=buffer.append(pinyin);
            }catch (Exception e){

            }
            datas.add(buffer.toString());
            data.add(city);
            buffer.replace(0,buffer.length(),"");
        }
        RecyclerView.LayoutManager manager=new LinearLayoutManager(this);
        MyDecoration myDecoration=new MyDecoration(this);
        RightLeftDecoration rightLeftDecoration=new RightLeftDecoration(this,datas);
        recyclerView.setLayoutManager(manager);
        recyclerView.addItemDecoration(myDecoration);
        recyclerView.addItemDecoration(rightLeftDecoration);
        ItemDecorationAdapter adapter=new ItemDecorationAdapter(data);
        recyclerView.setAdapter(adapter);
    }
}
