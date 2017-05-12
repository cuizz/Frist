package com.example.frist.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.frist.R;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Administrator on 2017/5/12.
 */

public class TabFragment extends Fragment{
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,  Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.tab_fragment,container,false);
       CircleImageView circleImageView=(CircleImageView) view.findViewById(R.id.circleimage);
       // ImageView imageView=(ImageView)view.findViewById(R.id.imageview);
        Glide.with(getActivity()).load("http://img.my.csdn.net/uploads/201309/01/1378037235_3453.jpg").into(circleImageView);
        return view;
    }
}
