package com.example.frist;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.frist.activity.TopBarBaseActivity;
import com.example.frist.adapter.RecycleViewAdapter;
import com.example.frist.bean.News;
import com.example.frist.bean.Photos;
import com.example.frist.http.HttpEvent;
import com.example.frist.http.HttpTask;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SecondActivity extends TopBarBaseActivity {

    @BindView(R.id.recycleView)
    RecyclerView recycleView;
    RecycleViewAdapter adapter;
    @Override
    protected int getContentView() {
        return R.layout.second_layout;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        ButterKnife.bind(this);
        setTitle("程序员");
        setTopLeftButton(R.drawable.ic_arrow_back, new OnClickListener() {
            @Override
            public void onClick() {
                SecondActivity.this.finish();
            }
        });
        setTopRightButton("sss", R.drawable.ic_add, new OnClickListener() {
            @Override
            public void onClick() {

            }
        });
        initViews();
    }

    private void initViews() {
        HashMap<String,String> map=new HashMap<>();
        new HttpTask(this,"http://c.3g.163.com/photo/api/list/0096/4GJ60096.json",0,2,"",map).execute();
    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventMainThread(HttpEvent event) {
        switch (event.getTsg()){
            case 2:
                List<Photos> responses=new Gson().fromJson(event.getContent(),new TypeToken<List<Photos>>(){}.getType());
                LinearLayoutManager layoutManager=new LinearLayoutManager(this);
                layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                recycleView.setLayoutManager(layoutManager);
                adapter=new RecycleViewAdapter(responses);
                recycleView.setAdapter(adapter);
                break;
        }
    }
}