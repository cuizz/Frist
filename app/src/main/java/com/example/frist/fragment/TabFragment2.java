package com.example.frist.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.example.frist.R;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Administrator on 2017/5/12.
 */

public class TabFragment2 extends Fragment{
    @Override
    public View onCreateView(LayoutInflater inflater,  ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.tab_fragment,container);
        CircleImageView circleImageView=(CircleImageView) view.findViewById(R.id.circleimage);
        Glide.with(getActivity()).load("http://img.my.csdn.net/uploads/201309/01/1378037235_9280.jpg").into(circleImageView);
        return view;
    }
}
