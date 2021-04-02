package com.example.myapplication.customview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;

import com.example.myapplication.R;

public class MyText extends View{

    private  String  textcontent;
    private  int textcolor= Color.BLACK;
    private  int textsize =15;

    private Paint paint;
    public MyText(Context context) {
        this(context,null);
        Log.i("textcontent","111111");
    }

    public MyText(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
        Log.i("textcontent","22222");
    }

    public MyText(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {

        super(context, attrs, defStyleAttr);

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.MyText);
        textcolor = typedArray.getColor(R.styleable.MyText_textcolor, textcolor);

        textsize = typedArray.getDimensionPixelSize(R.styleable.MyText_textsize,textsize);
        textcontent = typedArray.getString(R.styleable.MyText_textcontent);
        Log.i("textcontent",textcontent);
        typedArray.recycle();

        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setTextSize(sp2px(context,textsize));
        paint.setColor(textcolor);

    }

    public MyText(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        Log.i("textcontent","4444444");
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthmode = MeasureSpec.getMode(widthMeasureSpec);
        int heighmode = MeasureSpec.getMode(heightMeasureSpec);
        int width = MeasureSpec.getSize(widthMeasureSpec);

        if (widthmode == MeasureSpec.AT_MOST){

            Rect rect = new Rect();

            paint.getTextBounds(textcontent,0,textcontent.length(),rect);
            width = rect.width();
        }
        int height = MeasureSpec.getSize(heightMeasureSpec);
        if (heighmode == MeasureSpec.AT_MOST){

            Rect rect = new Rect();

            paint.getTextBounds(textcontent,0,textcontent.length(),rect);
            height = rect.height();
        }
        setMeasuredDimension(width,height);



    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint.FontMetricsInt fontMetricsInt = paint.getFontMetricsInt();
        // top 是一个负值  bottom 是一个正值    top，bttom的值代表是  bottom是baseLine到文字底部的距离（正值）
        // 必须要清楚的，可以自己打印就好

        Log.i("getHeight1",(fontMetricsInt.bottom - fontMetricsInt.top)/2+"");
        Log.i("getHeight2",getHeight()/2+"");
        int dy = (fontMetricsInt.bottom - fontMetricsInt.top)/2 -fontMetricsInt.bottom;
        int baseLines = getHeight()/2+dy;
        canvas.drawText(textcontent,0,baseLines,paint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return super.onTouchEvent(event);
    }

    /**
     * 将sp转换为px
     */
    public static int sp2px(Context context, float spValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue * fontScale + 0.5f);
    }

    @Override
    protected void dispatchDraw(Canvas canvas) {
        super.dispatchDraw(canvas);
    }
}
