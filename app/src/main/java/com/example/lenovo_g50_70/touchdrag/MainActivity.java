package com.example.lenovo_g50_70.touchdrag;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    //最大移动距离
    private static final float TOUCH_MOVE_MAX_Y =600;
    //竖直方向的移动距离
    private float mTouchMoveY=0;
    private TouchDragView mDragView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    /**
     * 初始化事件
     */
    private void initView() {
        mDragView= (TouchDragView) findViewById(R.id.touchDrag);

        //根布局实现触碰响应
        findViewById(R.id.activity_main).setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                //得到用户点击意图
                int action = event.getActionMasked();
                switch (action) {
                    case MotionEvent.ACTION_DOWN:
                        mTouchMoveY = event.getY();
                        break;
                    case MotionEvent.ACTION_MOVE:
                        float y = event.getY();

                        //往下拉
                        if (y >= mTouchMoveY) {
                            //移动的距离
                            float moveSize = y - mTouchMoveY;
                            //距离移动到最大的进度
                            float progress =moveSize>=TOUCH_MOVE_MAX_Y?1:moveSize/TOUCH_MOVE_MAX_Y;
                            mDragView.setProgress(progress);
                        }
                        break;
                    case MotionEvent.ACTION_UP:
                        break;
                }
                return true;
            }
        });
    }

}
