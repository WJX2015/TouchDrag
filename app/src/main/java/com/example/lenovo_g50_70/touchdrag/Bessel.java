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
 * 贝塞尔曲线 123 阶层
 * Created by lenovo-G50-70 on 2017/6/12.
 */

public class Bessel extends View {

    private Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    ;
    private Path mPath = new Path();

    public Bessel(Context context) {
        super(context);
        init();
    }

    public Bessel(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public Bessel(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public Bessel(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    private void init() {
        //成员变量赋值给局部变量操作，提高效率

        Paint paint = mPaint;
        paint.setAntiAlias(true);
        paint.setDither(true);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(10);

        //一阶贝赛尔曲线
        Path path = mPath;
        path.moveTo(100, 100);
        path.lineTo(300, 300);

        /**
         *  二阶贝塞尔曲线,绝对基于(0,0)开始,可通过lineTo改变
         *  缺点：当每一次结束点变了，参数的值全部要改变
         *  前2参数是控制点,后2参数是最后的点
         *  从(300,300)开始，经过(500,100),最终到达(700,300)
         */
        path.quadTo(500, 0, 700, 300);

        /**
         * 二阶贝塞尔曲线,相对,基于结束点开始
         * 前2参数是控制点,后2参数是最后的点
         * 从(300,300)开始，经过(500,100),最终到达(700,300)
         * 控制点相对起始点移动(200,-200)，X右Y下为正方向
         * 最终点相对起始点移动(400,0)，效果与上实现一致
         *  path.rQuadTo(200,-300,400,0);
         */

        path.moveTo(400, 800);
        /**
         * 三阶贝塞尔曲线,拱起的中心和控制点的X|坐标一致
         * 12参数是第一个控制点,34参数是第二个控制点,56参数是最后的点
         */
        path.cubicTo(550, 400, 650, 1200, 800, 800);

        /*
         *  相对，原理和二阶一样
         *  path.rCubicTo(150, -400, 250, 400, 400, 0);
         */

    }

    ;

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        //一阶赛贝尔曲线(直线)
        //二阶赛贝尔曲线(拱圆)
        //三阶赛贝尔曲线(三角函数图像)
        canvas.drawPath(mPath, mPaint);

        //二阶赛贝尔控制点
        canvas.drawPoint(500, 0, mPaint);
        //三阶赛贝尔控制点
        canvas.drawPoint(550, 400, mPaint);
        canvas.drawPoint(650, 1200, mPaint);
    }
}
