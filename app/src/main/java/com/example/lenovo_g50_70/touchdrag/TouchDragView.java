package com.example.lenovo_g50_70.touchdrag;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.View;

/**
 * 触碰拖拽效果
 * Created by lenovo-G50-70 on 2017/6/11.
 */

public class TouchDragView extends View {

    //圆的画笔
    private Paint mCirclePaint;
    //圆的半径
    private int mCircleRadius = 100;

    public TouchDragView(Context context) {
        super(context);
        init();
    }

    public TouchDragView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public TouchDragView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public TouchDragView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    /**
     * 初始化操作
     */
    private void init() {
        //设置默认字体，黑体
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        //抗锯齿
        paint.setAntiAlias(true);
        //仿抖动
        paint.setDither(true);
        //填充样式
        paint.setStyle(Paint.Style.FILL);
        //颜色
        paint.setColor(0xff000000);
        mCirclePaint = paint;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        //中心位置
        float x = getWidth() >> 1;
        float y = getHeight() >> 1;
        canvas.drawCircle(x, y, mCircleRadius, mCirclePaint);
    }
}
