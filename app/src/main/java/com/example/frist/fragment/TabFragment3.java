package com.example.frist.fragment;

import android.app.AlertDialog;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.daimajia.numberprogressbar.NumberProgressBar;
import com.example.frist.R;
import com.example.frist.bean.CheckBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Administrator on 2017/5/12.
 */

public class TabFragment3 extends BaseFragment implements BaseQuickAdapter.OnItemChildClickListener,View.OnClickListener,BaseQuickAdapter.OnItemClickListener {
    List<CheckBean> students = new ArrayList<>();
    boolean isSelect,isvivi=true;
    @BindView(R.id.recyclerview)
    RecyclerView recyclerView;
    QuickAdapter adapters;
    CheckBox checkbox;
    TextView all;
    TextView notall;
    TextView delect,bianyi;
    Unbinder unbinder;
    public void onClick(View view){
        switch (view.getId()){
            case R.id.all:
                setAll();
                checkbox.setChecked(true);
                adapters.notifyDataSetChanged();
                break;
            case R.id.notall:
                setNot();
                checkbox.setChecked(false);
                adapters.notifyDataSetChanged();
                break;
            case R.id.delect:
               for(int i=students.size()-1;i>=0;i--){
                   if(students.get(i).isture()){
                       adapters.remove(i);
                   }
               }
                adapters.notifyDataSetChanged();
                break;
            case R.id.bianji:
                if(isvivi){
                    checkbox.setVisibility(View.VISIBLE);
                    isvivi=false;
                    bianyi.setText("确定");
                }else{
                    bianyi.setText("编译");
                    checkbox.setVisibility(View.GONE);
                    isvivi=true;
                }
                adapters.notifyDataSetChanged();
                break;
        }
    }
    @Override
    public int getLayoutID() {
        return R.layout.recycle_item;
    }

    @Override
    public void initView() {
        // students = GreenDaoManager.getInstance().getSession().getStudentDao().loadAll();
        for (int i = 0; i < 20; i++) {
            CheckBean student = new CheckBean();
            student.setName("展示图片");
            student.setId(Long.valueOf(i));
            student.setYear("");
            student.setIsture(false);
            students.add(student);
        }
        LinearLayoutManager mLinearLayoutManager = new LinearLayoutManager(getActivity());
        //解决正常情况下NestedScrollView嵌套RecycleView滑动冲突问题
        //  mLinearLayoutManager.setSmoothScrollbarEnabled(true);
        //  mLinearLayoutManager.setAutoMeasureEnabled(true);
        recyclerView.setLayoutManager(mLinearLayoutManager);
        // recyclerView.setHasFixedSize(true);
        // recyclerView.setNestedScrollingEnabled(false);
        if (adapters == null) {
            adapters = new QuickAdapter();
            recyclerView.setAdapter(adapters);
        } else {
            adapters.notifyDataSetChanged();
        }
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment3_head, null);
        checkbox=(CheckBox) view.findViewById(R.id.checkbox);
        all=(TextView)view.findViewById(R.id.all);
        all.setOnClickListener(this);
        notall=(TextView)view.findViewById(R.id.notall);
        notall.setOnClickListener(this);
        delect=(TextView)view.findViewById(R.id.delect);
        delect.setOnClickListener(this);
        bianyi=(TextView)view.findViewById(R.id.bianji);
        bianyi.setOnClickListener(this);
        adapters.addHeaderView(view, 0);
        adapters.setOnItemChildClickListener(this);
        adapters.setOnItemClickListener(this);
        adapters.bindToRecyclerView(recyclerView);
        checkbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    setAll();
                    checkbox.setChecked(true);
                }else{
                    if(setNotAll()){
                          setNot();
                        checkbox.setChecked(false);
                    }else{
                        checkbox.setChecked(false);
                    }
                }
                adapters.notifyDataSetChanged();
            }
        });
    }

    @Override
    public boolean onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
        switch (view.getId()) {
            case R.id.downLoad:
                   // NumberProgressBar bar = (NumberProgressBar) adapter.getViewByPosition(recyclerView,position, R.id.firstBar2);
                    //bar.setVisibility(View.GONE);
                   // ImageView imageView = (ImageView) adapter.getViewByPosition(recyclerView, position, R.id.imageView);
                   // Glide.with(getActivity()).load("http://cms-bucket.nosdn.127.net/51080481cc2d48f2b2fdc374e6e2e0c820170523071310.jpeg").into(imageView);
                    students.get(position).setYear("http://cms-bucket.nosdn.127.net/51080481cc2d48f2b2fdc374e6e2e0c820170523071310.jpeg");
                    TextView tv = (TextView) view;
                    tv.setText("美女");
                    students.get(position).setName("美女");
                    adapters.notifyDataSetChanged();
                break;
            case R.id.checkbox:
                CheckBox box = (CheckBox) view;
                if (students.get(position).isture()) {
                    box.setChecked(false);
                    students.get(position).setIsture(false);
                    checkbox.setChecked(false);
                } else {
                    box.setChecked(true);
                    students.get(position).setIsture(true);
                    if(setNotAll()){
                        checkbox.setChecked(true);
                    }else{
                        checkbox.setChecked(false);
                    }
                }
                adapters.notifyDataSetChanged();
                break;
        }
        return false;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        creatDialog("分享","关注");
    }

    public class QuickAdapter extends BaseQuickAdapter<CheckBean, BaseViewHolder> {
        public QuickAdapter() {
            super(R.layout.fragment3_adapter, students);
        }

        @Override
        protected void convert(final BaseViewHolder viewHolder, CheckBean item) {
            viewHolder.setText(R.id.downLoad, item.getName())
                    .addOnClickListener(R.id.downLoad)
                    .setTag(R.id.downLoad, item.getId())
                    .setChecked(R.id.checkbox, item.isture()).addOnClickListener(R.id.checkbox);
            //imageView.setTag(Integer.valueOf(item.getName()),item.getId());
            ImageView imageView = viewHolder.getView(R.id.imageView);
            CheckBox itemcheck = viewHolder.getView(R.id.checkbox);
            if(isvivi){
                itemcheck.setVisibility(View.GONE);
            }else{
                itemcheck.setVisibility(View.VISIBLE);
            }
            Glide.with(getActivity()).load(item.getYear()).placeholder(R.mipmap.ic_launcher).into(imageView);
        }
    }
    public void setAll(){
        for(CheckBean bean:students){
            bean.setIsture(true);
        }
        isSelect=true;
    }
    public void setNot(){
        for(CheckBean bean:students){
            bean.setIsture(false);
        }
        isSelect=false;
    }
    public boolean setNotAll(){
        boolean is=false;
        for(CheckBean bean:students){
           if(bean.isture()){
               is=true;
           }else{
               is=false;
               return is;
           }
        }
        return is;
    }
    public AlertDialog dialog;
    public void creatDialog(String content,String titles){
        AlertDialog.Builder normalDialog =
                new AlertDialog.Builder(getActivity());
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_ysjj, null);
        normalDialog.setView(view);
        TextView title=(TextView) view.findViewById(R.id.title);
        TextView contents=(TextView)view.findViewById(R.id.content);
        TextView quxiao=(TextView)view.findViewById(R.id.quxiao);
        title.setText(titles);
        contents.setText(content);
        quxiao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismissDialog();
            }
        });
        dialog = normalDialog.show();
    }
    public  void dismissDialog() {
        if (dialog != null) {
            dialog.dismiss();
        }
    }
}
