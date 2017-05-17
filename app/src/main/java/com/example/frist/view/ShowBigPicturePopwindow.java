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
import com.example.frist.Utils;
import com.example.frist.util.GlideImageLoader;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.listener.OnBannerListener;

import razerdp.basepopup.BasePopupWindow;

/**
 * Created by Administrator on 2017/5/15.
 */

public class ShowBigPicturePopwindow extends BasePopupWindow {
    View popview;
    public ShowBigPicturePopwindow(Activity context) {
        super(context);
        bindViews();
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
        popview = LayoutInflater.from(getContext()).inflate(R.layout.showbigpicture, null);
        return popview;
    }

    @Override
    public View initAnimaView() {
        View v = popview.findViewById(R.id.scaleAnim);
        int width = getScreenWidth();
        int height = getScreenHeight()/2;
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(width, height);
        v.setLayoutParams(params);
        return v;
    }

    @Override
    public View getClickToDismissView() {
        return popview.findViewById(R.id.scrollview);
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
    public void bindViews(){
        Banner banner=(Banner) popview.findViewById(R.id.banner);
        //设置图片加载器
        banner.setImageLoader(new GlideImageLoader());
        //设置图片集合
        banner.setImages(Utils.getlist());
        //设置自动轮播，默认为true
        banner.isAutoPlay(true);
        //设置轮播时间
        banner.setDelayTime(5000);
        //设置banner样式
        banner.setBannerStyle(BannerConfig.NUM_INDICATOR);
        banner.setIndicatorGravity(BannerConfig.CENTER);
        //banner设置方法全部调用完毕时最后调用
        banner.start();
        banner.setOnBannerListener(new OnBannerListener() {
            @Override
            public void OnBannerClick(int position) {
                ShowBigPicturePopwindow.this.dismiss();
            }
        });
    }
}
