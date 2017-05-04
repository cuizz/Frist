package com.example.frist;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.frist.bean.Student;
import com.example.frist.bean.Teacher;
import com.example.frist.bean.User;
import com.example.frist.util.GreenDaoManager;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;

import java.util.ArrayList;
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
        Intent intent = new Intent(MainActivity.this, SecondActivity.class);
        MainActivity.this.startActivity(intent);
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
                for (Button btn : listButton) {
                    btn.setBackgroundColor(red);
                    btn.setTextColor(getResources().getColor(R.color.colorPrimary));
                }
                break;
            case R.id.knife2:
                Toast.makeText(this, "knife2", Toast.LENGTH_SHORT).show();
                break;
            case R.id.knife3:
                Toast.makeText(this, "knife3", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.first_layout);
        ButterKnife.bind(this);
        // List<Student>list=GreenDaoManager.getInstance().getSession().getStudentDao().loadAll();
        // for(int i=0;i<list.size();i++){
        //    Student student=list.get(i);
        // student.setYear(i+15+"");
        // student.setId(Long.valueOf(i)+1L);
        // list.add(student);
        // }
        // GreenDaoManager.getInstance().getSession().getStudentDao().updateInTx(list);
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
        toolbar.setNavigationIcon(getResources().getDrawable(R.mipmap.for_return));
        //toolbar.setLogo(getResources().getDrawable(R.drawable.common_google_signin_btn_icon_dark));
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ThirdActivity.class);
                MainActivity.this.startActivity(intent);
            }
        });

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(false);
        }

        collapsingToolbarLayout.setTitle(butter_knife);
        textView.setText(getStrings("nima"));
    }

    private String getStrings(String str) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < 500; i++) {
            stringBuilder.append("nima");
        }
        return stringBuilder.toString();
    }

}
