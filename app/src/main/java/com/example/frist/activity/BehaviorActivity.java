package com.example.frist.activity;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.example.frist.R;
import com.example.frist.adapter.ItemDecorationAdapter;
import com.example.frist.utils.DataUtil;

/**
 * Created by Matthew_Chen on 2017/4/14.
 */

public  class BehaviorActivity extends AppCompatActivity implements View.OnClickListener{

    public  Toolbar toolbar;
    public RecyclerView recyclerView;
    private Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.behavior);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        recyclerView = (RecyclerView) findViewById(R.id.viewContent);
        button = (Button) findViewById(R.id.button);
        button.setOnClickListener(this);
        toolbar.setTitle("behavior");
        //初始化设置 Toolbar
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(false);
        }
        setSupportActionBar(toolbar);
        RecyclerView.LayoutManager manager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);
        ItemDecorationAdapter adapter=new ItemDecorationAdapter(DataUtil.getData());
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button:
                recyclerView.scrollToPosition(0);
                button.setVisibility(View.INVISIBLE);
                break;
        }
    }
}





