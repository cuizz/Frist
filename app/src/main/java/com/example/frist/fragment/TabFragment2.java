package com.example.frist.fragment;

import com.bumptech.glide.Glide;
import com.example.frist.R;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Administrator on 2017/5/12.
 */

public class TabFragment2 extends BaseFragment{
    @Override
    public int getLayoutID() {
        return R.layout.tab_fragment;
    }

    @Override
    public void initView() {
        // View view=LayoutInflater.from(getActivity()).inflate(R.layout.tab_fragment,null);
        CircleImageView circleImageView=(CircleImageView) view.findViewById(R.id.circleimage);
        Glide.with(getActivity()).load("http://img.my.csdn.net/uploads/201309/01/1378037235_9280.jpg").into(circleImageView);
    }
}
