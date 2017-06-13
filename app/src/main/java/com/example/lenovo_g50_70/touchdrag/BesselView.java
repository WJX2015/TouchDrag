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
 * Created by lenovo-G50-70 on 2017/6/13.
 */

public class BesselView extends View {

    private Paint mPaint=new Paint(Paint.ANTI_ALIAS_FLAG);
    private Path mBessel=new Path();

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

    private void init(){
        Paint paint=mPaint;
        paint.setAntiAlias(true);
        paint.setDither(true);
        paint.setStrokeWidth(10);
        paint.setStyle(Paint.Style.STROKE);

        Path path =mBessel;
        //初始化四阶贝塞尔曲线
        initBessel();
    }

    private void initBessel() {

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }
}
