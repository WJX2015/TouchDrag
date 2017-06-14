package com.example.lenovo_g50_70.touchdrag;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.util.Log;
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
    //圆心XY
    private int mCirclePointX;
    private int mCirclePointY;
    //进度值
    private float mProgress;

    //可拖动的高度
    private int mDragHeight = 800;

    //目标宽度
    private int mTargetWidth;
    //贝塞尔曲线的路径和画笔
    private Path mPath;
    private Paint mPathPaint;
    //重心点最终高度，决定控制点的Y坐标
    private int mTargetGravityHeight;
    //角度变换 0-135度
    private int mTangentAngle = 120;

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

        // 初始化贝塞尔路径和画笔
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        //抗锯齿
        paint.setAntiAlias(true);
        //仿抖动
        paint.setDither(true);
        //填充样式
        paint.setStyle(Paint.Style.FILL);
        //颜色
        paint.setColor(0xff000000);
        mPathPaint = paint;

        mPath = new Path();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {//当进行测量时触发

        //宽度的意图和类型
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int width = MeasureSpec.getSize(widthMeasureSpec);

        //高度的意图和类型
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int height = MeasureSpec.getSize(heightMeasureSpec);

        // 所需高度的最小值 因为要四舍五入，所以加上0.5f
        int minHeight = (int) ((mDragHeight * mProgress + 0.5f) + getPaddingLeft() + getPaddingRight());
        //所需宽度的最小值
        int minWidth = 2 * mCircleRadius + getPaddingTop() + getPaddingBottom();

        //测量出的宽度
        int measureWidth;
        //测量出的高度
        int measureHeight;

        //宽度是一个精确值
        if (widthMode == MeasureSpec.EXACTLY) {
            measureWidth = width;
        } else if (widthMode == MeasureSpec.AT_MOST) {
            //测量出的最大宽度
            measureWidth = Math.min(width, minWidth);
        } else {
            measureWidth = minWidth;
        }

        //高度是一个精确值
        if (heightMode == MeasureSpec.EXACTLY) {
            measureHeight = height;
        } else if (heightMode == MeasureSpec.AT_MOST) {
            //测量出的最大高度
            measureHeight = Math.min(height, minHeight);
        } else {
            measureHeight = minHeight;
        }

        //设置控件的宽度和高度
        setMeasuredDimension(measureWidth, measureHeight);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {//当大小改变时触发
        super.onSizeChanged(w, h, oldw, oldh);
        //当高度变化时，进行路径更新
        updatePathLayout();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //画圆
        canvas.drawCircle(mCirclePointX, mCirclePointY, mCircleRadius, mCirclePaint);
        //画贝塞尔曲线
        canvas.drawPath(mPath, mPathPaint);
    }

    /**
     * 设置进度
     *
     * @param progress
     */
    public void setProgress(float progress) {
        mProgress = progress;
        Log.e("TouchDragView", "P:" + progress);
        //请求重新进行测量
        requestLayout();
    }

    /**
     * 更新我们的路径等相关操作
     */
    private void updatePathLayout() {
        //获取进度
        final float progress = mProgress;
        final Path path = mPath;
        //重置
        path.reset();
    }

    /**
     * @param start    起始值
     * @param end      结束值
     * @param progress 进度
     * @return 当前进度的值
     */
    private float getValueLine(float start, float end, float progress) {
        return (start + (end - start)) * progress;
    }
}
