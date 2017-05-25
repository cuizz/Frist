package com.example.frist;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.frist.activity.TopBarBaseActivity;
import com.example.frist.adapter.MulRecyAdapter;
import com.example.frist.bean.Photos;
import com.example.frist.http.HttpEvent;
import com.example.frist.http.HttpTask;
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
        setTitle("新闻");
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
                break;
            case 1:
                //responses.clear();
                List<Photos> responses=new Gson().fromJson(event.getContent(),new TypeToken<List<Photos>>(){}.getType());
                adapter.addData(responses);
                swipeRefreshLayout.setRefreshing(false);
                break;
        }
    }
    boolean isErr=false;
    @Override
    public void onLoadMoreRequested() {
        if (adapter.getData().size() < PAGE_SIZE) {
           // adapter.loa;
        } else {
            if (mCurrentCounter >= 100) {
//                    pullToRefreshAdapter.loadMoreEnd();//default visible
                //adapter.loadMoreEnd(mLoadMoreEndGone);//true is gone,false is visible
            } else {
                if (isErr) {
                    adapter.addData(responses);
                    mCurrentCounter = adapter.getData().size();
                   // adapter.loadMoreComplete();
                } else {
                    isErr = true;
                   // Toast.makeText(PullToRefreshUseActivity.this, R.string.network_err, Toast.LENGTH_LONG).show();
                    //adapter.loadMoreFail();

                }
            }
          //  mSwipeRefreshLayout.setEnabled(true);
        }
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