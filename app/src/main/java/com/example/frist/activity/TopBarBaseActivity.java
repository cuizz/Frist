package com.example.frist.activity;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.example.frist.R;

/**
 * Created by Matthew_Chen on 2017/4/14.
 */

public abstract class TopBarBaseActivity extends AppCompatActivity {

   public  Toolbar toolbar;
    FrameLayout viewContent;
    TextView tvTitle;
    TextView addTv;
    OnClickListener onClickListenerTopLeft;
    OnClickListener onClickListenerTopRight;

    int menuResId;
    String menuStr;

    public interface OnClickListener{
        void onClick();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base_top_bar);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        viewContent = (FrameLayout) findViewById(R.id.viewContent);
        toolbar.setTitle("");
        tvTitle = (TextView) findViewById(R.id.tvTitle);
        addTv=(TextView)findViewById(R.id.add);
        //将继承 TopBarBaseActivity 的布局解析到 FrameLayout 里面
        LayoutInflater.from(this).inflate(getContentView(), viewContent);
        //初始化设置 Toolbar
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(false);
        }
        setSupportActionBar(toolbar);
        init(savedInstanceState);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickListenerTopLeft.onClick();
            }
        });
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.menu1:
                       onClickListenerTopRight.onClick();
                        break;
                    case R.id.menu2:

                        break;
                    case R.id.menu3:

                        break;
                    case R.id.menu4:

                        break;
                }
                return true;
            }
        });
    }

    protected void setTitle(String title,String addstr){
        if (!TextUtils.isEmpty(title)){
            tvTitle.setText(title);
        }
        if (!TextUtils.isEmpty(addstr)){
            addTv.setText(addstr);
        }
    }

    protected void setTopLeftButton(){
        setTopLeftButton(R.drawable.ic_arrow_back, null);
    }

    protected void setTopLeftButton(int iconResId, OnClickListener onClickListener){
        toolbar.setNavigationIcon(iconResId);
        this.onClickListenerTopLeft = onClickListener;
    }

    protected void setTopRightButton(String menuStr, OnClickListener onClickListener){
        this.onClickListenerTopRight = onClickListener;
        this.menuStr = menuStr;
    }

    protected void setTopRightButton(String menuStr, int menuResId, OnClickListener onClickListener){
        this.menuResId = menuResId;
        this.menuStr = menuStr;
        this.onClickListenerTopRight = onClickListener;
    }

    protected abstract int getContentView();
    protected abstract void init(Bundle savedInstanceState);

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (menuResId != 0 || !TextUtils.isEmpty(menuStr)){
            getMenuInflater().inflate(R.menu.base_menu, menu);
        }
        return true;
    }

}





