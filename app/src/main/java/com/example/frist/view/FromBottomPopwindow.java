package com.example.frist.view;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.widget.Toast;

import razerdp.basepopup.BasePopupWindow;
import com.example.frist.R;

/**
 * Created by Administrator on 2017/5/15.
 */

public class FromBottomPopwindow extends BasePopupWindow implements View.OnClickListener{
    View popview;
    Activity context;
    public FromBottomPopwindow(Activity context) {
        super(context);
        this.context=context;
        bindView();

    }
    @Override
    protected Animation initShowAnimation() {
        return null;
    }

    @Override
    protected Animation initExitAnimation() {
        return getTranslateAnimation(0, 500, 300);
    }

    @Override
    public View onCreatePopupView() {
        popview= LayoutInflater.from(getContext()).inflate(R.layout.popupwindow, null);
        return popview;
    }

    @Override
    public View initAnimaView() {
        return popview.findViewById(R.id.popup_anima);
    }

    @Override
    public View getClickToDismissView() {
        return popview.findViewById(R.id.click_to_dismiss);
    }

    @Override
    protected Animator initShowAnimator() {
        ObjectAnimator objectAnimator=ObjectAnimator.ofFloat(initAnimaView(), "translationY", 600f, 0f);
        return objectAnimator;
    }

    private void bindView() {
        if (popview != null) {
            popview.findViewById(R.id.tx_1).setOnClickListener(this);
            popview.findViewById(R.id.tx_2).setOnClickListener(this);
            popview.findViewById(R.id.tx_3).setOnClickListener(this);
        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tx_1:
                this.dismiss();
                Toast.makeText(context,"click tx_1",Toast.LENGTH_SHORT).show();
                break;
            case R.id.tx_2:
                Toast.makeText(context,"click tx_1",Toast.LENGTH_SHORT).show();
                break;
            case R.id.tx_3:
                Toast.makeText(context,"click tx_1",Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
    }
}
