package com.example.frist;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.frist.activity.TopBarBaseActivity;
import com.example.frist.adapter.HRecycleViewAdapter;
import com.example.frist.adapter.MulRecyAdapter;
import com.example.frist.bean.Photos;
import com.example.frist.http.HttpEvent;
import com.example.frist.http.HttpTask;
import com.example.frist.view.AutoPopwindow;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.yqritc.recyclerviewflexibledivider.HorizontalDividerItemDecoration;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SecondActivity extends TopBarBaseActivity implements BaseQuickAdapter.RequestLoadMoreListener, SwipeRefreshLayout.OnRefreshListener{
    @BindView(R.id.swipeLayout)
    SwipeRefreshLayout swipeRefreshLayout;
    @BindView(R.id.recycleView)
    RecyclerView recycleView;
    MulRecyAdapter adapter;
    @Override
    protected int getContentView() {
        return R.layout.second_layout;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        EventBus.getDefault().register(this);
        ButterKnife.bind(this);
        setTitle("新闻","");
        setTopLeftButton(R.drawable.ic_arrow_back, new OnClickListener() {
            @Override
            public void onClick() {
                SecondActivity.this.finish();
            }
        });
        setTopRightButton("sss", R.drawable.ic_add, new OnClickListener() {
            @Override
            public void onClick() {
              //  AutoPopwindow popwindow=new AutoPopwindow(SecondActivity.this,toolbar,0);
            }
        });
        initViews();
    }

    private void initViews() {
        HashMap<String,String> map=new HashMap<>();
        new HttpTask(this,"http://c.3g.163.com/photo/api/list/0096/4GJ60096.json",0,2,"",map).execute();
        swipeRefreshLayout.setOnRefreshListener(this);
    }
    List<Photos>responses;
    private static final int PAGE_SIZE = 20;
    private static  int mCurrentCounter = 6;
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventMainThread(HttpEvent event) {
        switch (event.getTsg()){
            case 2:
                responses=new Gson().fromJson(event.getContent(),new TypeToken<List<Photos>>(){}.getType());
                LinearLayoutManager layoutManager=new LinearLayoutManager(this);
                layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                recycleView.setLayoutManager(layoutManager);
                recycleView.addItemDecoration(new HorizontalDividerItemDecoration.Builder(this)
                        .color(R.color.menu_click_bg)
                        .sizeResId(R.dimen.divider)
                        .marginResId(R.dimen.leftmargin, R.dimen.rightmargin)
                        .build());
                adapter=new MulRecyAdapter(SecondActivity.this,responses);
                //adapter.setOnLoadMoreListener(this);
                adapter.openLoadAnimation(BaseQuickAdapter.SLIDEIN_LEFT);
                recycleView.setAdapter(adapter);
                mCurrentCounter = adapter.getData().size();
                View view=LayoutInflater.from(SecondActivity.this).inflate(R.layout.ho_recycle,null);
                RecyclerView recyclerView=(RecyclerView) view.findViewById(R.id.recycleView);
                LinearLayoutManager manager=new LinearLayoutManager(SecondActivity.this);
                manager.setOrientation(LinearLayoutManager.HORIZONTAL);
                recyclerView.setLayoutManager(manager);
                recyclerView.setAdapter(new HRecycleViewAdapter(Utils.getbean()));
                adapter.addHeaderView(view,0);
                break;
            case 1:
                List<Photos> responsess=new Gson().fromJson(event.getContent(),new TypeToken<List<Photos>>(){}.getType());
                //responses.addAll(0,responsess.get(0));
                responses.add(0,responsess.get(0));
                adapter.notifyDataSetChanged();
                swipeRefreshLayout.setRefreshing(false);
                adapter.addFooterView(LayoutInflater.from(SecondActivity.this).inflate(R.layout.footer,null),adapter.getData().size());
                break;
        }
    }
    boolean isErr=false;
    @Override
    public void onLoadMoreRequested() {
        adapter.addData(responses);
        adapter.loadComplete();
    }
    @Override
    public void onRefresh() {
      //  adapter.setEnableLoadMore(false);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
              //  adapter.setNewData(DataServer.getSampleData(PAGE_SIZE));
                //isErr = false;
                //mCurrentCounter = PAGE_SIZE;
                //swipeRefreshLayout.setRefreshing(false);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        HashMap<String,String> map=new HashMap<>();
                        new HttpTask(SecondActivity.this," http://c.3g.163.com/photo/api/list/0096/4GJ60096.json",0,1,"",map).execute();
                       // textView.setText("刷新");
                    }
                });
              //  pullToRefreshAdapter.setEnableLoadMore(true);
            }
        }, 1000);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}