package com.example.frist;

import android.content.Intent;
import android.net.Uri;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

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

public class MainActivity extends AppCompatActivity {
    SwipeRefreshLayout swipeRefreshLayout;
    TextView textView;
    FloatingActionButton button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.first_layout);
       /* List<Student>list=new ArrayList<Student>();
        for(int i=0;i<9;i++){
            Student student=new Student();
           student.setId(Long.valueOf(i));
         student.setName("xuesheng"+i);
           if(i%2==1){
                student.setStuId(1L);
           }else{
               student.setStuId(2L);
           }
           list.add(student);
        }
        GreenDaoManager.getInstance().getSession().getStudentDao().insertInTx(list);*/
       // Teacher teacher=new Teacher(1L,"小名");
       // Teacher teachers=new Teacher(2L,"小红");
        //GreenDaoManager.getInstance().getSession().getTeacherDao().insert(teacher);
        //GreenDaoManager.getInstance().getSession().getTeacherDao().insert(teachers);
        List<Student> sss=GreenDaoManager.getInstance().getSession().getStudentDao().loadAll();
        int ssssss=sss.size();
       Log.i("TAG",ssssss+"");

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        button=(FloatingActionButton)findViewById(R.id.button);
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
        textView = (TextView) findViewById(R.id.textview);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.common_google_signin_btn_icon_dark));
        //toolbar.setLogo(getResources().getDrawable(R.drawable.common_google_signin_btn_icon_dark));
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,ThirdActivity.class);
                MainActivity.this.startActivity(intent);
            }
        });

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(false);
        }

        collapsingToolbarLayout.setTitle("水果");
        textView.setText(getStrings("nima"));
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,SecondActivity.class);
                MainActivity.this.startActivity(intent);
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

}
