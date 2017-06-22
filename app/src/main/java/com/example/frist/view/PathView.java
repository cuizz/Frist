package com.example.frist.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;

import com.example.frist.R;


/**
 * Created by Administrator on 2017/6/21.
 */

public class PathView extends View{
    private Paint paint;//大圆
    private Paint paints;//小圆
   private int centerWidth;
   private int centerHeight;
    public PathView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setWillNotDraw(false);
        setClickable(true);
        paint=new Paint();
        paints=new Paint();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        centerWidth=w/2;
        centerHeight=h/2;
        super.onSizeChanged(w, h, oldw, oldh);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        /**
         * 画最外层的大圆环
         */
                int radius = 50;
                paint.setColor(getResources().getColor(R.color.colorPrimary)); //设置圆环的颜色
                paint.setStyle(Paint.Style.STROKE); //设置空心
                paint.setStrokeWidth(20); //设置圆环的宽度
                paint.setAntiAlias(true);  //消除锯齿
                canvas.drawCircle(centerWidth, centerHeight, radius, paint); //画出圆环

                paints.setColor(getResources().getColor(R.color.colorAccent)); //设置圆环的颜色
                paints.setStyle(Paint.Style.FILL); //设置空心
                paints.setAntiAlias(true);  //消除锯齿
                canvas.drawCircle(centerWidth+radius, centerHeight, 10, paints); //画出圆环
    }
}
