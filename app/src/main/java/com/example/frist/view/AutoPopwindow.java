package com.example.frist.view;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.PopupWindow;

import com.example.frist.R;

/**
 * Created by Administrator on 2017/5/15.
 */

public class AutoPopwindow {
    Context context;
    PopupWindow popupWindow;
    public  AutoPopwindow(Context context,View v,int location){
        this.context=context;
        initWindow(v,location);
    }
    public void initWindow(View v,int locations){
        View view= LayoutInflater.from(context).inflate(R.layout.autopopwindow,null);
        //获取PopupWindow中View的宽高
        view.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);
       // int measuredWidth = view.getMeasuredWidth();
        int measuredWidth = v.getMeasuredWidth();
        int measuredHeight = view.getMeasuredHeight();
       // int width=measuredWidth/4;
        popupWindow = new PopupWindow(view, measuredWidth, measuredHeight);
        popupWindow.setFocusable(true);//popupwindow设置焦点
        popupWindow.setBackgroundDrawable(new ColorDrawable(0xaa000000));//设置背景
        popupWindow.setOutsideTouchable(true);//点击外面窗口消失
        popupWindow.setAnimationStyle(R.style.pop_anim_style);//设置动画
        // popupWindow.showAsDropDown(v,0,0);
        //获取点击View的坐标
        int[] location = new int[2];
        v.getLocationOnScreen(location);
        switch (locations){
            case 0:
                popupWindow.showAsDropDown(v);//在v的下面
                break;
            case 1:
                //显示在左方
                popupWindow.showAtLocation(v,Gravity.NO_GRAVITY,location[0]-popupWindow.getWidth(),location[1]);
                break;
            case 2:
                //显示在正上方
                popupWindow.showAtLocation(v, Gravity.NO_GRAVITY, (location[0] + v.getWidth() / 2) - measuredWidth / 2, location[1]-measuredHeight);
                break;
            case 3:
                //显示在右方
                popupWindow.showAtLocation(v,Gravity.NO_GRAVITY,location[0]+popupWindow.getWidth(),location[1]);
                break;
            case 4:
                //显示在控件中间
                popupWindow.showAtLocation(v,Gravity.NO_GRAVITY,location[0]+v.getMeasuredWidth()/2,location[1]+v.getMeasuredHeight()/2);
                break;
        }
    }
}
