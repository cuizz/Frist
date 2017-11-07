package com.example.frist.utils;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.frist.R;

/**
 * Created by Administrator on 2017/11/6.
 */

public class MyDecoration extends RecyclerView.ItemDecoration{
    private Context context;
    private int height;
    private Paint dividerPaint;
    public MyDecoration(Context context){
        this.context=context;
        height=context.getResources().getDimensionPixelSize(R.dimen.itemdecoration);
        dividerPaint=new Paint();
        dividerPaint.setColor(context.getResources().getColor(R.color.colorAccent));
    }
    /**
     * recycleView的item之内的布局
     * @param c
     * @param parent
     * @param state
     */
    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDraw(c, parent, state);
        int childCount = parent.getChildCount();
        int left = parent.getPaddingLeft();
        int right = parent.getWidth() - parent.getPaddingRight();

        for (int i = 0; i < childCount - 1; i++) {
            View view = parent.getChildAt(i);
            float top = view.getBottom();
            float bottom = view.getBottom() + height;
            c.drawRect(left, top, right, bottom, dividerPaint);
        }
    }

    /**
     * 覆盖的item之上
     * @param c
     * @param parent
     * @param state
     */

    @Override
    public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDrawOver(c, parent, state);
    }

    /**
     * 偏移量
     * @param outRect
     * @param view
     * @param parent
     * @param state
     */
    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        outRect.bottom=height;
    }
}
