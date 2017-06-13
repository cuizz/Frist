package com.example.frist.activity;

import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

import com.example.frist.R;
import com.example.frist.view.CustomDialog;
import com.example.frist.view.FromBottomPopwindow;

/**
 * Created by Matthew_Chen on 2017/4/14.
 */

public class RunErActivity extends TopBarBaseActivity implements View.OnClickListener{
    CustomDialog dialog;
    TextView tishi;
    @Override
    protected int getContentView() {
        return R.layout.ceshi_head;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        tishi=(TextView) findViewById(R.id.tishi);
      //  ButterKnife.bind(this);
        setTitle("新闻","更多");
        toolbar.setVisibility(View.GONE);
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
                ObjectAnimator translationUp = ObjectAnimator.ofFloat(tishi, "Y", 0, -100);
                translationUp.setDuration(500);
                translationUp.start();
            }
        });
        dialog = new CustomDialog(this,R.style.CustomDialog);
        dialog.setContent("加载中");
        dialog.setDisable(true);
        dialog.show();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1500);
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            dialog.dismiss();
                            ObjectAnimator translationUp = ObjectAnimator.ofFloat(tishi, "Y", 0, 60);
                            translationUp.setDuration(500);
                            translationUp.start();
                        }
                    });
                }catch (Exception e){

                }
            }
        }).start();
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





