package com.example.frist;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.frist.bean.OrderListResponse;
import com.example.frist.http.HttpEvent;
import com.example.frist.http.HttpTask;
import com.example.frist.section.HotelEntityAdapter;
import com.example.frist.section.SectionedSpanSizeLookup;
import com.google.gson.Gson;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.json.JSONObject;

import java.util.HashMap;

public class ThirdActivity extends AppCompatActivity {
    SwipeRefreshLayout swipeRefreshLayout;
    TextView textView;
    FloatingActionButton button;
    private RecyclerView recyclerView;
    private static final String UC_AUTH_KEY = "kWdi?&#LZ[PYjDag4*7z,Rf/|CxpVtN)@09UsvBq";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.thrid_layout);
        EventBus.getDefault().register(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        button=(FloatingActionButton)findViewById(R.id.button);
        recyclerView=(RecyclerView)findViewById(R.id.id_recyclerview);
        CollapsingToolbarLayout collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.coll_toolbar);
        ImageView imageView = (ImageView) findViewById(R.id.imageview);
        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.refresh);
        swipeRefreshLayout.setColorSchemeColors(getResources().getColor(R.color.colorPrimary));
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(2000);
                        } catch (Exception e) {

                        }
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                textView.setText("刷新");
                                swipeRefreshLayout.setRefreshing(false);
                            }
                        });
                    }
                }).start();
            }
        });
      //  textView = (TextView) findViewById(R.id.textview);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_arrow_back));
        //toolbar.setLogo(getResources().getDrawable(R.drawable.common_google_signin_btn_icon_dark));
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ThirdActivity.this.finish();
            }
        });

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(false);
        }

        collapsingToolbarLayout.setTitle("水果");
      //  textView.setText(getStrings("nima"));
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(ThirdActivity.this,SecondActivity.class);
                ThirdActivity.this.startActivity(intent);
            }
        });
        PackageManager packageManager = getPackageManager();
        PackageInfo packInfo = null;
        String appVersion = "";
        try {
            packInfo = packageManager.getPackageInfo(getPackageName(), 0);
            appVersion = packInfo.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            appVersion = "";
            e.printStackTrace();
        }
        String device = "ANDROID_" + android.os.Build.MODEL;
        String releaseVersion = "ANDROID_"
                + android.os.Build.VERSION.RELEASE;
        HashMap<String,String>map=new HashMap<>();
        map.put("osversion",releaseVersion);
        map.put("device",device);
        map.put("appversion",appVersion);
        map.put("name","13964157950");
        map.put("pwd",think_ucenter_md5("123456", UC_AUTH_KEY));
        new HttpTask(this,"haole",1,1,"api/user/login",map).execute();
      //  new Halflisttask2().execute(Utils.URL+"api/user/login");
    }

    private String getStrings(String str) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < 500; i++) {
            stringBuilder.append("nima");
        }
        return stringBuilder.toString();
    }
    private String think_ucenter_md5(String str, String key) {
        return Utils.md5(Utils.sha1(str) + key);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventMainThread(HttpEvent event) {
        switch (event.getTsg()){
            case 1:
                try {
                   JSONObject object= new JSONObject(event.getContent()).getJSONObject("UserToken");
                    HashMap<String,String>map=new HashMap<>();
                    map.put("pageNo","1");
                    map.put("type","all");
                    map.put("token",object.getString("Token"));
                    map.put("pageLen","8");
                    new HttpTask(this,"haole",1,2,"v3/api/custom/order/list",map).execute();
                }catch (Exception e){

                }

                break;

            case 2:
                OrderListResponse responses=new Gson().fromJson(event.getContent(),OrderListResponse.class);
                HotelEntityAdapter mAdapter = new HotelEntityAdapter(this);
                GridLayoutManager manager = new GridLayoutManager(this,2);
                //设置header
                manager.setSpanSizeLookup(new SectionedSpanSizeLookup(mAdapter,manager));
                recyclerView.setLayoutManager(manager);
                recyclerView.setAdapter(mAdapter);
                mAdapter.setData(responses.getOrderList());
                break;
        }
    }

    @Override
    protected void onDestroy() {
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }
}
