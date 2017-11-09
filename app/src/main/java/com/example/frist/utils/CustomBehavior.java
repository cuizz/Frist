package com.example.frist.utils;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewPropertyAnimator;
import android.view.animation.DecelerateInterpolator;

/**
 * Created by Administrator on 2017/11/8.
 */

public class CustomBehavior extends CoordinatorLayout.Behavior<View> {

    boolean isAnimate;

    public CustomBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onStartNestedScroll(CoordinatorLayout coordinatorLayout, View child, View directTargetChild, View target, int nestedScrollAxes) {
        return (nestedScrollAxes & ViewCompat.SCROLL_AXIS_VERTICAL) != 0;
    }

    @Override
    public void onNestedPreScroll(CoordinatorLayout coordinatorLayout, View child, View target, int dx, int dy, int[] consumed) {
        if (dy < 0) {
            show(child);
            //AnimatorSet animatorSet = new AnimatorSet();//组合动画
           // ObjectAnimator scaleX=ObjectAnimator.ofFloat(child,"scaleX",0f,1f);
           // ObjectAnimator scaleY=ObjectAnimator.ofFloat(child,"scaleY",0f,1f);
           // animatorSet.setDuration(200);
            //animatorSet.setInterpolator(new DecelerateInterpolator());
           // animatorSet.play(scaleX).with(scaleY);//两个动画同时开始
           // animatorSet.start();
            //child.setVisibility(View.VISIBLE);
        } else {
            hide(child);
        }
    }

    @Override
    public boolean onNestedFling(CoordinatorLayout coordinatorLayout, View child, View target, float velocityX, float velocityY, boolean consumed) {
        return super.onNestedFling(coordinatorLayout, child, target, velocityX, velocityY, consumed);
    }
    //隐藏时的动画
    private void hide(final View view) {
        ViewPropertyAnimator animator = view.animate().scaleY(0).setDuration(200);

        animator.setListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {
                isAnimate = true;
            }

            @Override
            public void onAnimationEnd(Animator animator) {
                view.setVisibility(View.INVISIBLE);
                isAnimate = false;
            }

            @Override
            public void onAnimationCancel(Animator animator) {
                show(view);
            }

            @Override
            public void onAnimationRepeat(Animator animator) {
            }
        });
        animator.start();
    }

    //显示时的动画
    private void show(final View view) {
        ViewPropertyAnimator animator = view.animate().scaleY(1).setDuration(200);
        animator.setListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {
                view.setVisibility(View.VISIBLE);
                isAnimate = true;
            }

            @Override
            public void onAnimationEnd(Animator animator) {
                isAnimate = false;
            }

            @Override
            public void onAnimationCancel(Animator animator) {
                hide(view);
            }

            @Override
            public void onAnimationRepeat(Animator animator) {
            }
        });
        animator.start();
    }
}
