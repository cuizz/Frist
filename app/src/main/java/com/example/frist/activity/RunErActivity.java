package com.example.frist.activity;

import android.os.Bundle;
import android.view.Menu;
import android.view.View;

import com.example.frist.R;
import com.example.frist.view.FromBottomPopwindow;

/**
 * Created by Matthew_Chen on 2017/4/14.
 */

public class RunErActivity extends TopBarBaseActivity implements View.OnClickListener{
    @Override
    protected int getContentView() {
        return R.layout.second_layout;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
      //  ButterKnife.bind(this);
        setTitle("新闻","更多");
        setTopLeftButton(R.drawable.ic_arrow_back, new OnClickListener() {
            @Override
            public void onClick() {
                RunErActivity.this.finish();
            }
        });
        addTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FromBottomPopwindow popwindow=new FromBottomPopwindow(RunErActivity.this);
                popwindow.showPopupWindow();
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.add:

                break;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return false;
    }
}





