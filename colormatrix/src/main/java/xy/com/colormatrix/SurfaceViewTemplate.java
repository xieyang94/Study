/*
 * Copyright (c) 2017. 用于测试配置版权信息
 * 谢洋 版权所有
 * XeYang. All Rights Reserved.
 */

package xy.com.colormatrix;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * author  :   xieyang
 * e-mail  :   1192171830@qq.com
 * time    :   2017/6/14
 * desc    :
 * version :   1.0
 */

public class SurfaceViewTemplate extends SurfaceView {

    //SurefaceHolder
    private SurfaceHolder mHolder;

    //用于绘图的Canvas
    private Canvas mCanvas;

    //子线程标志位
    private boolean mIsDrawing;

    public SurfaceViewTemplate(Context context) {
        super(context);
        initSurfaceView();
    }

    public SurfaceViewTemplate(Context context, AttributeSet attrs) {
        super(context, attrs);
        initSurfaceView();
    }

    public SurfaceViewTemplate(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initSurfaceView();
    }

    private void initSurfaceView() {
        mHolder = getHolder();
        mHolder.addCallback(callback);
        setFocusable(true);
        setFocusableInTouchMode(true);
        this.setKeepScreenOn(true);
//        mHolder.setFormat(PixelFormat.OPAQUE);
        mPath = new Path();
        mPaint = new Paint();
        mPaint.setColor(Color.BLUE);
        mPaint.setStrokeWidth(5);
        mPaint.setStyle(Paint.Style.STROKE);
    }


    private SurfaceHolder.Callback callback = new SurfaceHolder.Callback() {
        @Override
        public void surfaceCreated(SurfaceHolder holder) {
            mIsDrawing = true;
            new Thread(runnable).start();
        }

        @Override
        public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

        }

        @Override
        public void surfaceDestroyed(SurfaceHolder holder) {
            mIsDrawing = false;
        }
    };

    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            //add
            long start = System.currentTimeMillis();
            while (mIsDrawing) {
                draw();
//                x += 1;
//                y = (int) (100 * Math.sin(x * 2 * Math.PI / 180) + 400);
//                mPath.lineTo(x, y);
            }
            Log.d("TAG", "经过这里");
            long end = System.currentTimeMillis();
            //50-100
            if (end - start < 100) {
                try {
                    Thread.sleep(100 - (end - start));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    };

    private int x;
    private int y;
    private Path mPath;
    private Paint mPaint;

    private void draw() {
        try {
            mCanvas = mHolder.lockCanvas();
            //draw something
            mCanvas.drawColor(Color.WHITE);
            mCanvas.drawPath(mPath, mPaint);
        } catch (Exception e) {

        } finally {
            if (mCanvas != null) {
                mHolder.unlockCanvasAndPost(mCanvas);
            }
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        int x = (int) event.getX();
        int y = (int) event.getY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mPath.lineTo(x, y);
                break;
            case MotionEvent.ACTION_MOVE:
                mPath.lineTo(x, y);
                break;
            case MotionEvent.ACTION_UP:

                break;
        }

        return true;
    }
}
