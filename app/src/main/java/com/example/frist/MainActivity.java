package com.example.frist;

import android.graphics.Bitmap;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.view.menu.MenuBuilder;
import android.support.v7.widget.Toolbar;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.frist.activity.RunErActivity;
import com.example.frist.bean.Photo;
import com.example.frist.util.GreenDaoUtils;
import com.example.frist.view.AutoPopwindow;
import com.example.frist.view.FromBottomPopwindow;
import com.example.frist.view.ScalePopwindow;

import java.lang.reflect.Method;
import java.util.List;

import butterknife.BindBitmap;
import butterknife.BindColor;
import butterknife.BindString;
import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnLongClick;

public class MainActivity extends AppCompatActivity {
    SwipeRefreshLayout swipeRefreshLayout;
    @BindBitmap(R.mipmap.xxxxxx)
    Bitmap bitmap;
    @BindString(R.string.butter_knife)
    String butter_knife;
    @BindColor(R.color.kenife)
    int red;
    @BindView(R.id.textview)
    TextView textView;
    @BindView(R.id.button)
    FloatingActionButton button;

    @OnClick(R.id.button)
    public void onclick(View view) {
        Utils.startActivity(this,RunErActivity.class);
    }

    @BindViews({R.id.knife1, R.id.knife2, R.id.knife3})
    public List<Button> listButton;
    @OnLongClick(R.id.knife2)
    public boolean longClick(View view){
        view.setBackgroundColor(getResources().getColor(R.color.colorAccent));
        return true;
    }
    @OnClick({R.id.knife1, R.id.knife2, R.id.knife3})
    public void onClicks(View view) {
        switch (view.getId()) {
            case R.id.knife1:
                Toast toast=Toast.makeText(this, "knife1", Toast.LENGTH_SHORT);
                View views=LayoutInflater.from(MainActivity.this).inflate(R.layout.toast_view,null);
                TextView tv=(TextView) views.findViewById(R.id.toast_textview);
                tv.setText("自定义toast");
                toast.setView(views);
                toast.setGravity(Gravity.CENTER,0,0);
                toast.show();
                AutoPopwindow autoPopwindow=new AutoPopwindow(this,view,2);
                break;
            case R.id.knife2:
                FromBottomPopwindow bottomPopwindow=new FromBottomPopwindow(this);
                bottomPopwindow.showPopupWindow();
                break;
            case R.id.knife3:
                ScalePopwindow scalePopwindow=new ScalePopwindow(this);
                scalePopwindow.showPopupWindow();
                break;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.first_layout);
        ButterKnife.bind(this);
        //Photo users=new Photo();
       // users.setId(1L);
       // users.setName("haha");
       // GreenDaoUtils.getDaoSession().insert(users);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
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
                            Thread.sleep(1000);
                        } catch (Exception e) {

                        }
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                textView.setText("刷新");
                                swipeRefreshLayout.setRefreshing(false);
                                textView.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {


                                    }
                                });
                            }
                        });
                    }
                }).start();
            }
        });
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back);
        //toolbar.setLogo(getResources().getDrawable(R.drawable.common_google_signin_btn_icon_dark));
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Utils.startActivity(MainActivity.this, ThirdActivity.class);
            }
        });

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(false);
        }

        collapsingToolbarLayout.setTitle(butter_knife);
        textView.setText(getStrings("nima"));
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.menu1:
                        Utils.startActivity(MainActivity.this,SwipeLayoutActivity.class);
                        break;
                    case R.id.menu2:
                        Utils.startActivity(MainActivity.this,WebActivity.class);
                        break;
                    case R.id.menu3:
                        Utils.startActivity(MainActivity.this,TabActivity.class);
                        break;
                    case R.id.menu4:
                        Utils.startActivity(MainActivity.this,SecondActivity.class);
                        break;
                }
                return true;
            }
        });
    }

    private String getStrings(String str) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < 500; i++) {
            stringBuilder.append("nima");
        }
        return stringBuilder.toString();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu); //解析menu布局文件到menu
        return super.onCreateOptionsMenu(menu);
    }
    /**
     * 修改menu中图标在android4.0 以上无法显示的问题
     */
    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        if (menu != null) {
            if (menu.getClass() == MenuBuilder.class) {
                try {
                    Method method = menu.getClass().getDeclaredMethod("setOptionalIconsVisible", Boolean.TYPE);
                    method.setAccessible(true);
                    method.invoke(menu, true);
                } catch (Exception e) {
                }
            }
        }
        return super.onPrepareOptionsMenu(menu);
    }
}
