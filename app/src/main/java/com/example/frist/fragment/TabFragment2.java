package com.example.frist.fragment;

import android.os.Bundle;
import android.os.Environment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.daimajia.numberprogressbar.NumberProgressBar;
import com.example.frist.R;
import com.example.frist.adapter.RecycleViewAdapter;
import com.example.frist.bean.Photos;
import com.example.frist.http.HttpDownloadTask;
import com.example.frist.http.HttpEvent;
import com.example.frist.utils.FileUtils;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.File;
import java.io.IOException;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Administrator on 2017/5/12.
 */

public class TabFragment2 extends BaseFragment {
    @BindView(R.id.button)
    Button button;
    @OnClick(R.id.button)
    public void onClick(View view){
        String path= Environment.getExternalStorageDirectory().getPath()+"/"+"First"+"/"+"down.jpg";
        new HttpDownloadTask(getActivity(),firstBar2,2,"http://cms-bucket.nosdn.127.net/51080481cc2d48f2b2fdc374e6e2e0c820170523071310.jpeg",path).execute();
    }
    @BindView(R.id.firstBar2)
    NumberProgressBar firstBar2;
    @BindView(R.id.webview)
    ImageView imageView;

    @Override
    public int getLayoutID() {
        return R.layout.download;
    }

    @Override
    public void initView() {
        EventBus.getDefault().register(this);
    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventMainThread(HttpEvent event) {
        switch (event.getTsg()){
            case 2:
                try {
                    File file=FileUtils.createFile(Environment.getExternalStorageDirectory().getPath()+"/"+"First"+"/"+"down.jpg");
                    Glide.with(getActivity()).load(file).into(imageView);
                }catch (Exception e){

                }
                break;
        }
    }
}
