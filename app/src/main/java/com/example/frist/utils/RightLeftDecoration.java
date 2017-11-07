package com.example.frist.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.text.TextPaint;
import android.view.View;

import com.example.frist.R;

/**
 * Created by Administrator on 2017/11/6.
 */

public class RightLeftDecoration extends RecyclerView.ItemDecoration{
    private Context context;
    private int height;
    private TextPaint dividerPaint;
    private int tagWidth;
    private Paint leftPaint;
    private Paint rightPaint;
    public RightLeftDecoration(Context context){
        this.context=context;
        dividerPaint=new TextPaint();
        dividerPaint.setColor(context.getResources().getColor(R.color.white));
        dividerPaint.setTextSize(30);
        leftPaint = new Paint();
        leftPaint.setColor(context.getResources().getColor(R.color.colorAccent));
        rightPaint = new Paint();
        rightPaint.setColor(context.getResources().getColor(R.color.colorPrimary));
        tagWidth = context.getResources().getDimensionPixelSize(R.dimen.toolbar_height);

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
        int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View child = parent.getChildAt(i);
            int pos = parent.getChildAdapterPosition(child);
            boolean isLeft = pos % 2 == 0;
            if (isLeft) {
                float left = child.getLeft();
                float right = left + tagWidth;
                float top = child.getTop();
                float bottom = child.getBottom();
                c.drawRect(left, top, right, bottom, leftPaint);
                c.drawText("Item",tagWidth/2.0F,top+(bottom-top)/2,dividerPaint);
            } else {
                float right = child.getRight();
                float left = right - tagWidth;
                float top = child.getTop();
                float bottom = child.getBottom();
                c.drawRect(left, top, right, bottom, rightPaint);
                c.drawText("Items",child.getWidth()-tagWidth/2.0F,top+(bottom-top)/2,dividerPaint);
                Bitmap bitmap=BitmapFactory.decodeResource(context.getResources(),R.mipmap.face1);
                c.drawBitmap(bitmap,child.getWidth()-tagWidth*2,top+(bottom-top)/2-bitmap.getHeight()/2,rightPaint);
            }
        }
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
