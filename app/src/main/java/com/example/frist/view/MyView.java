package com.example.frist.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.example.frist.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/6/21.
 */

public class MyView extends View{
    private List<String>list=new ArrayList<>();
    private int seeSize = 8;//可见个数

    private int anInt;//每个字母所占的大小；
    private TextPaint textPaint;
    private boolean firstVisible = true;
    private int width;//控件宽度
    private int height;//控件高度
    private Paint paint;//被选中文字的画笔
    private int n;
    private float downX;
    private float anOffset;
    private float selectedTextSize;
    private int selectedColor;
    private float textSize;
    private int textColor;
    private Rect rect = new Rect();
    ;

    private int textWidth = 0;
    private int textHeight = 0;
    private int centerTextHeight = 0;
    public MyView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setWillNotDraw(false);
        setClickable(true);
        paint=new Paint();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                downX=event.getX();
                break;
            case MotionEvent.ACTION_MOVE:
                float scrollX=event.getX();
                anOffset=scrollX-downX;

                if (scrollX > downX) {
                    //向右滑动，当滑动距离大于每个单元的长度时，则改变被选中的文字。
                    if (scrollX - downX >= anInt) {
                        if (n > 0) {
                            anOffset = 0;
                            n = n - 1;
                            downX = scrollX;
                        }
                    }
                } else {

                    //向左滑动，当滑动距离大于每个单元的长度时，则改变被选中的文字。
                    if (downX - scrollX >= anInt) {

                        if (n < list.size() - 1) {
                            anOffset = 0;
                            n = n + 1;
                            downX = scrollX;
                        }
                    }
                }
                invalidate();
                break;
            case MotionEvent.ACTION_UP:
                anOffset=0;
                invalidate();
                break;
        }
        return super.onTouchEvent(event);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        /**
         * 画最外层的大圆环
         */
        int jiaage=10;
        for(int i=0;i<3;i++){
            //圆环的半径
            if(i==0){
                int radius = 50;
                int centre = getWidth()/2; //获取圆心的x坐标
                paint.setColor(getResources().getColor(R.color.colorPrimary)); //设置圆环的颜色
                paint.setStyle(Paint.Style.STROKE); //设置空心
                paint.setStrokeWidth(20); //设置圆环的宽度
                paint.setAntiAlias(true);  //消除锯齿
                canvas.drawCircle(centre, 70, radius, paint); //画出圆环
            }else{
                int radius = 50;
                int centre = getWidth()/2; //获取圆心的x坐标
                paint.setColor(getResources().getColor(R.color.colorAccent)); //设置圆环的颜色
                paint.setStyle(Paint.Style.STROKE); //设置空心
                paint.setStrokeWidth(20); //设置圆环的宽度
                paint.setAntiAlias(true);  //消除锯齿
                canvas.drawCircle((i*2)*70+centre+jiaage*i, 70, radius, paint); //画出圆环
            }

        }

    }
}
