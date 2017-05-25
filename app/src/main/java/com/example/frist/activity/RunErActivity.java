package com.example.frist.activity;

import android.os.Bundle;

import com.example.frist.R;

/**
 * Created by Matthew_Chen on 2017/4/14.
 */

public class RunErActivity extends TopBarBaseActivity {
    @Override
    protected int getContentView() {
        return R.layout.second_layout;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
      //  ButterKnife.bind(this);
        setTitle("新闻");
        setTopLeftButton(R.drawable.ic_arrow_back, new OnClickListener() {
            @Override
            public void onClick() {
                RunErActivity.this.finish();
            }
        });
        setTopRightButton("sss", R.drawable.ic_add, new OnClickListener() {
            @Override
            public void onClick() {

            }
        });
    }
}





