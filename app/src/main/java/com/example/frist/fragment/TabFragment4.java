package com.example.frist.fragment;

import android.animation.ObjectAnimator;

import com.example.frist.R;
import com.example.frist.bean.PieData;
import com.example.frist.view.PathView;
import com.example.frist.view.PieView;

import java.util.ArrayList;

import io.reactivex.Flowable;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;

/**
 * Created by Administrator on 2017/5/12.
 */

public class TabFragment4 extends BaseFragment{
    private ObjectAnimator oaAnimator;
    @Override
    public int getLayoutID() {
        return R.layout.yuanhuan;
    }

    @Override
    public void initView() {
        PieView pieView=(PieView) view.findViewById(R.id.pieView);
        ArrayList<PieData>list=new ArrayList<>();
        for(int i=0;i<6;i++){
            PieData data=new PieData("hao"+i,60);
            list.add(data);
        }
        pieView.setData(list);
        PathView pathView=(PathView)view.findViewById(R.id.pathView);

        oaAnimator= ObjectAnimator.ofFloat(pathView, "rotation", 0,360);
        oaAnimator.setDuration(5000);
        oaAnimator.start();
        Flowable.rangeLong(1,10)
                .subscribe(
                        new Consumer<Long>() {
                            @Override
                            public void accept(@NonNull Long aLong) throws Exception {

                            }
                        }
                );
        //View view=LayoutInflater.from(getActivity()).inflate(R.layout.tab_fragment,null);
        //CircleImageView circleImageView=(CircleImageView) view.findViewById(R.id.circleimage);
       // Glide.with(getActivity()).load("http://img.my.csdn.net/uploads/201309/01/1378037234_6318.jpg").into(circleImageView);
    }
}
