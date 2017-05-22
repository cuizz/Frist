package com.example.frist;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.frist.activity.TopBarBaseActivity;
import com.example.frist.adapter.RecycleViewAdapter;
import com.example.frist.bean.Student;
import com.example.frist.util.GreenDaoManager;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SecondActivity extends TopBarBaseActivity {

    @BindView(R.id.recycleView)
    RecyclerView recycleView;
    RecycleViewAdapter adapter;
    List<Student>students;
    @Override
    protected int getContentView() {
        return R.layout.second_layout;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        ButterKnife.bind(this);
        setTitle("程序员");
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
        students= GreenDaoManager.getInstance().getSession().getStudentDao().loadAll();
        LinearLayoutManager layoutManager=new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recycleView.setLayoutManager(layoutManager);
        adapter=new RecycleViewAdapter(students);
        recycleView.setAdapter(adapter);
    }
}