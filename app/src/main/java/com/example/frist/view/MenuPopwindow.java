package com.example.frist.view;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.DecelerateInterpolator;
import android.widget.RelativeLayout;

import com.example.frist.R;

import razerdp.basepopup.BasePopupWindow;

/**
 * Created by Administrator on 2017/5/15.
 */

public class MenuPopwindow extends BasePopupWindow {
    View popview;

    public MenuPopwindow(Activity context) {
        super(context);
    }

    @Override
    protected Animation initShowAnimation() {
        AnimationSet set = new AnimationSet(true);
        set.setInterpolator(new DecelerateInterpolator());
        set.addAnimation(getScaleAnimation(0, 1, 0, 1, Animation.RELATIVE_TO_SELF, 1, Animation.RELATIVE_TO_SELF, 0));
        set.addAnimation(getDefaultAlphaAnimation());
        return set;
    }

    @Override
    protected Animation initExitAnimation() {
        return null;
    }

    @Override
    public View onCreatePopupView() {
        popview = LayoutInflater.from(getContext()).inflate(R.layout.pop_menu, null);
        return popview;
    }
    @Override
    public void showPopupWindow(View v) {
        setOffsetX(-(getPopupViewWidth() - v.getWidth() / 2));
        setOffsetY(v.getHeight() / 2);
        super.showPopupWindow(v);
    }
    @Override
    public View initAnimaView() {
        View v = popview.findViewById(R.id.popup_contianer);
       /* int width = getScreenWidth() / 3 * 2;
        int height = getPopupViewHeight();
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(width, height);
        v.setLayoutParams(params);*/
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
