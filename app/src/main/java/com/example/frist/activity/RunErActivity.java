package com.example.frist.activity;

import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.frist.R;
import com.example.frist.adapter.RecycleAdapter;
import com.example.frist.adapter.RecycleViewAdapter;
import com.example.frist.bean.Photos;
import com.example.frist.bean.Student;
import com.example.frist.bean.Teacher;
import com.example.frist.view.CustomDialog;
import com.example.frist.view.FromBottomPopwindow;

import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableEmitter;
import io.reactivex.FlowableOnSubscribe;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Matthew_Chen on 2017/4/14.
 */

public class RunErActivity extends TopBarBaseActivity implements View.OnClickListener,BaseQuickAdapter.OnItemClickListener{
    CustomDialog dialog;
    TextView tishi;
    List<Student>list=new ArrayList<>();
    List<Photos>photos=new ArrayList<>();
    RecyclerView recyclerView;
    @Override
    protected int getContentView() {
        return R.layout.ceshi_head;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        recyclerView=(RecyclerView)findViewById(R.id.recycle);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        for(int i=0;i<5;i++){
            Photos photo=new Photos();
            photo.setSetname("小明"+i);
            photo.setSetid(""+i);
            if(i==0){
                List<String>strings=new ArrayList<>();
                strings.add("小明获得了100"+i+"美元");
                strings.add("小明获得了200"+i+"美元");
                strings.add("小明获得了300"+i+"美元");
                photo.setPics(strings);
            }else if(i==1){
                List<String>strings=new ArrayList<>();
                strings.add("红红获得了100"+i+"美元");
                strings.add("红红获得了200"+i+"美元");
                strings.add("红红获得了300"+i+"美元");
                photo.setPics(strings);
            }else if(i==2){
                List<String>strings=new ArrayList<>();
                strings.add("莉莉获得了100"+i+"美元");
                strings.add("莉莉获得了200"+i+"美元");
                strings.add("莉莉获得了300"+i+"美元");
                photo.setPics(strings);
            }else if(i==3){
                List<String>strings=new ArrayList<>();
                strings.add("蛋蛋获得了100"+i+"美元");
                strings.add("蛋蛋获得了200"+i+"美元");
                strings.add("蛋蛋获得了300"+i+"美元");
                photo.setPics(strings);
            }else if(i==4){
                List<String>strings=new ArrayList<>();
                strings.add("珍珍获得了100"+i+"美元");
                strings.add("珍珍获得了200"+i+"美元");
                strings.add("珍珍获得了300"+i+"美元");
                photo.setPics(strings);
            }
            photos.add(photo);
        }
        RecycleViewAdapter adapter=new RecycleViewAdapter(photos);
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(this);
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
        List<Teacher>teacher=new ArrayList<>();
        for(int i=0;i<3;i++){
            Teacher tea=new Teacher();
            tea.setName("xiaoming"+i);
            teacher.add(tea);
        }
       // creatFlowable().safeSubscribe(creatSub());
        final String[]str={"java","android","html"};
       /* Flowable.just(str)
                .map(new Function<String[], String>() {
                    @Override
                    public String apply(@NonNull String[] strings) throws Exception {
                        Thread.sleep(5000);
                        return strings[0]+strings[1]+strings[2].toUpperCase();
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(@NonNull String strings) throws Exception {
                        tishi.setText(strings);
                    }
                });*/
        /*Flowable.fromIterable(teacher)
                .map(new Function<Teacher, Student>() {
                    @Override
                    public Student apply(@NonNull Teacher teacher) throws Exception {
                        Student stu=new Student();
                        stu.setName(teacher.getName()+"  android");
                        return stu;
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Consumer<Student>() {
                    @Override
                    public void accept(@NonNull Student student) throws Exception {
                        tishi.setText(student.getName());
                    }
                });
*/
        Flowable.fromIterable(teacher)
                .take(2)
                .map(new Function<Teacher, Student>() {
                    @Override
                    public Student apply(@NonNull Teacher teacher) throws Exception {
                        Student stu=new Student();
                        stu.setName(teacher.getName()+"android");
                        return stu;
                    }
                })
                .filter(new Predicate<Student>() {
                    @Override
                    public boolean test(@NonNull Student student) throws Exception {
                        boolean is = false;
                        if(student.getName().equals("xiaoming0android")){
                            is = true;
                        }
                        return is;
                    }
                })
                .map(new Function<Student, List<Student>>() {
                    @Override
                    public List<Student> apply(@NonNull Student student) throws Exception {
                        list.add(student);
                        return list;
                    }
                })
                .map(new Function<List<Student>, String>() {
                    @Override
                    public String apply(@NonNull List<Student> students) throws Exception {
                        String str="";
                        for(int i=0;i<students.size();i++){
                            str+=students.get(i).getName();
                        }
                        return str;
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(@NonNull String student) throws Exception {
                        tishi.setText(student);
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

    public Subscriber<String> creatSub(){
        return new Subscriber<String>() {
            @Override
            public void onSubscribe(Subscription s) {
                s.request(Long.MAX_VALUE);
            }

            @Override
            public void onNext(String s) {
               tishi.setText(s);
                Log.i("TAG",s);
            }

            @Override
            public void onError(Throwable t) {

            }

            @Override
            public void onComplete() {

            }
        };
    }
    public Flowable<String> creatFlowable(List<String>list){
        return Flowable.fromIterable(list);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return false;
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, final int position) {
        //ReJava的标准连式结构
        Flowable.fromIterable(photos)
                .filter(new Predicate<Photos>() {
                    @Override
                    public boolean test(@NonNull Photos photos) throws Exception {
                        boolean is=false;
                        if(position==Integer.valueOf(photos.getSetid())){
                            is=true;
                        }
                        return is;
                    }
                })
                .flatMap(new Function<Photos, Flowable<String>>() {
                    @Override
                    public Flowable<String> apply(@NonNull Photos photos) throws Exception {
                        return creatFlowable(photos.getPics());
                    }
                })
                .map(new Function<String, String>() {
                    @Override
                    public String apply(@NonNull String s) throws Exception {
                        return s;
                    }
                })
                .reduce(new BiFunction<String, String, String>() {
                    @Override
                    public String apply(@NonNull String s, @NonNull String s2) throws Exception {
                        return s+s2;
                    }
                })
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(@NonNull String s) throws Exception {
                        tishi.setText(s);
                    }
                });
    }
}





