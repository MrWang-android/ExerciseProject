package com.example.myapplication.customview;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;

import androidx.annotation.Nullable;

import com.example.myapplication.R;

public class QQStepView extends View {

    private int norealizationColor = getResources().getColor(R.color.alertdialog_line);

    private int realizationColor = getResources().getColor(R.color.color_dialog_content);
    public int QQtextsize = 20;
    private int QQtextcolor = getResources().getColor(R.color.alertdialog_line);
    private int boderwidth = 10;
    private Paint paint,paint1,textpaint;

    public int getCreenstepint() {
        return creenstepint;
    }

    public void setCreenstepint(int creenstepint) {
        this.creenstepint = creenstepint;
        invalidate();
    }

    public int getMaxstep() {
        return maxstep;
    }

    public void setMaxstep(int maxstep) {
        this.maxstep = maxstep;
    }

    private int  creenstepint =0,maxstep =0;


    public QQStepView(Context context) {
        this(context, null);
    }

    public QQStepView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public QQStepView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.qqstepView);
        norealizationColor = typedArray.getColor(R.styleable.qqstepView_norealizationColor, norealizationColor);
        realizationColor = typedArray.getColor(R.styleable.qqstepView_realizationColor, realizationColor);
        QQtextsize = typedArray.getDimensionPixelSize(R.styleable.qqstepView_qqtextsize, QQtextsize);
        Log.i("QQtextsize",QQtextsize+"");
        QQtextcolor = typedArray.getColor(R.styleable.qqstepView_qqtextcolor, QQtextcolor);
        boderwidth = (int) typedArray.getDimension(R.styleable.qqstepView_boderwidth, boderwidth);
        typedArray.recycle();

        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(norealizationColor);
        paint.setStrokeCap(Paint.Cap.ROUND);
        paint.setStrokeWidth(boderwidth);


        paint1 = new Paint();
        paint1.setAntiAlias(true);
        paint1.setStyle(Paint.Style.STROKE);
        paint1.setColor(realizationColor);
        paint1.setStrokeCap(Paint.Cap.ROUND);
        paint1.setStrokeWidth(boderwidth);

        textpaint = new Paint();
        textpaint.setTextSize(QQtextsize);
        textpaint.setAntiAlias(true);
        textpaint.setColor(realizationColor);
        Log.i("QQtextsize",px2sp(context,QQtextsize)+"");
       // paint.setTextSize(px2sp(context,QQtextsize));


    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthmode = MeasureSpec.getMode(widthMeasureSpec);
        int heightmode = MeasureSpec.getMode(heightMeasureSpec);
        int widthsize = MeasureSpec.getSize(widthMeasureSpec);
        int heightsize = MeasureSpec.getSize(heightMeasureSpec);

        setMeasuredDimension(widthsize > heightsize ? heightsize : widthsize, widthsize > heightsize ? heightsize : widthsize);


    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int center = getWidth() / 2;
        int redius = getWidth() / 2 - dip2px(boderwidth) / 2;


        RectF rectF = new RectF(dip2px(boderwidth) / 2, dip2px(boderwidth) / 2, center + redius, center + redius);

        canvas.drawArc(rectF, 135, 270, false, paint);
        if (creenstepint ==0) return;

        float percentage =(float) creenstepint/maxstep;

        canvas.drawArc(rectF, 135, percentage*270, false, paint1);


        String creenstep = creenstepint+"";
        Rect rect = new Rect();
        textpaint.getTextBounds(creenstep,0,creenstep.length(),rect);

        Log.i("rect.width()",rect.width()+"");
        int dx = getWidth()/2 - rect.width()/2;
        Paint.FontMetricsInt fontMetricsInt = textpaint.getFontMetricsInt();

        int dy = (fontMetricsInt.bottom -fontMetricsInt.top)/2 -fontMetricsInt.bottom ;
        int baseline = getHeight()/2+dy;

        canvas.drawText(creenstep,dx,baseline,textpaint);


    }

    /**
     * 如果XML使用dp，那么Java代码就是用dp转换为px
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    private int dip2px(float dpValue) {
        final float scale = Resources.getSystem().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
     */
    public static int px2dip(int pxValue) {
        final float scale = Resources.getSystem().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    /**
     * 将sp转换为px
     */
    public static int sp2px(Context context, float spValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue * fontScale + 0.5f);
    }
    /**
     * 将px转换为sp
     */
    public static int px2sp(Context context, float pxValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (pxValue / fontScale + 0.5f);
    }
}
