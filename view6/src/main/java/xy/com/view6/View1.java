/*
 * Copyright (c) 2017. 用于测试配置版权信息
 * 谢洋 版权所有
 * XeYang. All Rights Reserved.
 */

package xy.com.view6;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Scroller;

/**
 * author  :   xieyang
 * e-mail  :   1192171830@qq.com
 * time    :   2017/6/12
 * desc    :
 * version :   1.0
 */

public class View1 extends View {

    private Paint mPaint;
    private Scroller mScroller;

    public View1(Context context) {
        super(context);
        initView(context);
    }

    public View1(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public View1(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    private void initView(Context context) {
        mPaint = new Paint();
        mPaint.setColor(Color.BLUE);
        mPaint.setStyle(Paint.Style.FILL);
        mScroller = new Scroller(context);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawRect(new Rect(0, 0, 400, 400), mPaint);
        postInvalidate();
    }

    private int lastX, lastY;

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int rawX = (int) event.getRawX();
        int rawY = (int) event.getRawY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                lastX = rawX;
                lastY = rawY;
                break;
            case MotionEvent.ACTION_MOVE:
                //计算偏移量
                int offsetX = rawX - lastX;
                int offsetY = rawY - lastY;
                //1.layout设置位置
//                layout(getLeft() + offsetX, getTop() + offsetY, getRight() + offsetX, getBottom() + offsetY);
                //2.对移动位置封装的API
//                offsetLeftAndRight(offsetX);
//                offsetTopAndBottom(offsetY);
                //3.LayoutParams--ACTION_UP时没有用到Layout方法
//                LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) getLayoutParams();
//                layoutParams.leftMargin = getLeft() + offsetX;
//                layoutParams.topMargin = getTop() + offsetY;
//                setLayoutParams(layoutParams);
                //4.MarginLayoutParams--效果同上
//                ViewGroup.MarginLayoutParams layoutParams1= (ViewGroup.MarginLayoutParams) getLayoutParams();
//                layoutParams1.leftMargin=getLeft()+offsetX;
//                layoutParams1.topMargin=getTop()+offsetY;
//                setLayoutParams(layoutParams1);
                //5.scrollBy--ACTION_UP就不执行了
//                ((View) getParent()).scrollBy(-offsetX, -offsetY);
                //6.scrollTo
                ((View) getParent()).scrollTo(-lastX, -lastY);
                lastX = rawX;
                lastY = rawY;
                break;
            case MotionEvent.ACTION_UP:
                //回到原点
//                layout(0, 0, 400, 400);
                View viewGroup = ((View) getParent());
                mScroller.startScroll(
                        viewGroup.getScrollX(),
                        viewGroup.getScrollY(),
                        -viewGroup.getScrollX(),
                        -viewGroup.getScrollY());
                invalidate();
                break;
        }
        postInvalidate();


        return true;
    }

    /**
     * 模拟滑动
     */
    @Override
    public void computeScroll() {
        super.computeScroll();
        //判断Scroller是否执行完毕
        if (mScroller.computeScrollOffset()) {
            ((View) getParent()).scrollTo(mScroller.getCurrX(), mScroller.getCurrY());
            invalidate();
        }
    }
}
