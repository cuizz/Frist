package com.example.frist.view;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.widget.LinearLayout;

import com.example.frist.R;

import razerdp.basepopup.BasePopupWindow;

/**
 * Created by Administrator on 2017/5/15.
 */

public class ScalePopwindow extends BasePopupWindow {
    View popview;

    public ScalePopwindow(Activity context) {
        super(context);
    }

    @Override
    protected Animation initShowAnimation() {
        return null;
    }

    @Override
    protected Animation initExitAnimation() {
        return null;
    }

    @Override
    public View onCreatePopupView() {
        popview = LayoutInflater.from(getContext()).inflate(R.layout.dialog, null);
        return popview;
    }

    @Override
    public View initAnimaView() {
        View v = popview.findViewById(R.id.scaleAnim);
        int width = getScreenWidth() / 3 * 2;
        int height = getPopupViewHeight();
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(width, height);
        v.setLayoutParams(params);
        return v;
    }

    @Override
    public View getClickToDismissView() {
        return popview.findViewById(R.id.scalePop);
    }

    @Override
    protected Animator initShowAnimator() {
        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(initAnimaView(), "scaleX", 0f, 1f);
        ObjectAnimator objectAnimators = ObjectAnimator.ofFloat(initAnimaView(), "scaleY", 0f, 1f);
        AnimatorSet set = new AnimatorSet();
        set.play(objectAnimator).with(objectAnimators);
        return set;
    }

    @Override
    protected Animator initExitAnimator() {
        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(initAnimaView(), "scaleX", 1f, 0f);
        ObjectAnimator objectAnimators = ObjectAnimator.ofFloat(initAnimaView(), "scaleY", 1f, 0f);
        AnimatorSet set = new AnimatorSet();
        set.play(objectAnimator).with(objectAnimators);
        return set;
    }
}
