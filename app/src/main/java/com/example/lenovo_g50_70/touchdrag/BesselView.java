package com.example.lenovo_g50_70.touchdrag;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.View;

/**
 * 四阶以上贝塞尔曲线的计算公式
 * Created by lenovo-G50-70 on 2017/6/13.
 */

public class BesselView extends View {

    private Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private Path mBessel = new Path();

    public BesselView(Context context) {
        super(context);
        init();
    }

    public BesselView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public BesselView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public BesselView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    private void init() {
        Paint paint = mPaint;
        paint.setAntiAlias(true);
        paint.setDither(true);
        paint.setStrokeWidth(10);
        paint.setStyle(Paint.Style.STROKE);
        new Thread(new Runnable() {
            @Override
            public void run() {
                initBessel();
            }
        }).start();
    }

    /**
     * 初始化贝塞尔曲线
     */
    private void initBessel() {
        Path path = mBessel;

        //把点的XY坐标分开再进行运算
        float[] xPoints = new float[]{0, 300, 200, 500, 700};
        float[] yPoints = new float[]{0, 300, 700, 1200, 200};

        //精度值，fps越大，线条越平滑
        int fps = 1000;
        for (int i = 0; i <= fps; i++) {
            float progress = i / (float) fps;
            float x = calculateBezier(progress, xPoints);
            float y = calculateBezier(progress, yPoints);
            path.lineTo(x, y);

            //刷新界面
            postInvalidate();

            //让曲线动起来
            try {
                Thread.sleep(150);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 计算某时刻的贝塞尔曲线所在的值
     * 数组存放的是每条线获取一个关于t的点
     * 通过相邻间连线再取关于t的点，直到最后仅剩唯一的点
     * @param time   进度/时刻
     * @param values 数组
     * @return 某个点的坐标
     */
    public float calculateBezier(float time, float... values) {
        //数组的长度
        final int len = values.length;

        //因为每次运算后，数组长度少一，所以条件如下设定
        for (int i = len - 1; i > 0; i--) {
            //外层
            for (int j = 0; j < i; j++) {
                //相邻两点连线后取得的点的X|Y坐标的值
                values[j] = values[j] + (values[j + 1] - values[j]) * time;
            }
        }

        return values[0];
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawPath(mBessel, mPaint);
    }
}
