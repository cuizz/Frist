package com.example.frist.view;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.example.frist.R;

import razerdp.basepopup.BasePopupWindow;

/**
 * Created by Administrator on 2017/5/15.
 */

public class AutoPopwindow extends BasePopupWindow {
    View popview;

    public AutoPopwindow(Activity context) {
        super(context, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        setAutoLocatePopup(true);
    }

    @Override
    protected Animation initShowAnimation() {
        return getDefaultAlphaAnimation();
    }

    @Override
    protected Animation initExitAnimation() {
        return null;
    }

    @Override
    public View onCreatePopupView() {
        popview = LayoutInflater.from(getContext()).inflate(R.layout.autopopwindow, null);
        //LinearLayout v = (LinearLayout) popview.findViewById(R.id.popup_contianer);
        // int width = getScreenWidth() / 3-10;
        // int height = getPopupViewHeight();
        // RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(250, 300);
         //popview.setLayoutParams(params);
        return popview;
    }

    @Override
    public View initAnimaView() {
        View v = popview.findViewById(R.id.popup_anima);
        return null;
    }

    @Override
    public View getClickToDismissView() {
        return null;
    }

    @Override
    protected Animator initShowAnimator() {
       // ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(initAnimaView(), "scaleX", 0f, 1f);
       // ObjectAnimator objectAnimators = ObjectAnimator.ofFloat(initAnimaView(), "scaleY", 0f, 1f);
       // AnimatorSet set = new AnimatorSet();
        //set.play(objectAnimator).with(objectAnimators);
        return null;
    }

    @Override
    protected Animator initExitAnimator() {
       // ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(initAnimaView(), "scaleX", 1f, 0f);
       // ObjectAnimator objectAnimators = ObjectAnimator.ofFloat(initAnimaView(), "scaleY", 1f, 0f);
       // AnimatorSet set = new AnimatorSet();
       // set.play(objectAnimator).with(objectAnimators);
        return null;
    }
}
