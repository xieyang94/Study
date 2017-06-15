package xy.com.view3;

import android.content.Context;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Scroller;

/**
 * author  :   xieyang
 * e-mail  :   1192171830@qq.com
 * time    :   2017/6/8
 * desc    :
 * version :   1.0
 */

public class ViewGroup1 extends ViewGroup {

    //一屏的高度
    private int mScreenHeight;
    //最后距离
    private int mLastY;
    //滚动高度开始的地方
    private int mStart;
    //滚动高度结束的地方
    private int mEnd;
    //滚动
    private Scroller mScroller;

    public ViewGroup1(Context context) {
        super(context);
        initView(context);
    }

    public ViewGroup1(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public ViewGroup1(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    /**
     * 初始化
     */
    private void initView(Context mContext) {
        //获取一个屏幕的高度
        WindowManager wm = (WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE);
//        mScreenHeight = wm.getDefaultDisplay().getHeight();
        DisplayMetrics dm = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(dm);
        mScreenHeight = dm.heightPixels;
        //滚动类
        mScroller = new Scroller(mContext);
    }

    /**
     * 测量
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        //获取子View的个数
        int count = getChildCount();
        //分别测量子View的宽高
        for (int i = 0; i < count; i++) {
            View childView = getChildAt(i);
            measureChild(childView, widthMeasureSpec, heightMeasureSpec);
        }
    }

    /**
     * 位置
     */
    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        //获取子View的个数
        int childCount = getChildCount();
        //设置ViewGroup的高度
        MarginLayoutParams mlp = (MarginLayoutParams) getLayoutParams();
        mlp.height = mScreenHeight * childCount;
        setLayoutParams(mlp);
        //分别设置子View的位置
        for (int i = 0; i < childCount; i++) {
            View child = getChildAt(i);
            if (child.getVisibility() != View.GONE) {
                child.layout(l, i * mScreenHeight, r, (i + 1) * mScreenHeight);
            }
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int y = (int) event.getY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                //对滚动位置进行设置判断
                //首先获取点下去的位置
                mLastY = y;
                //同时获取滚动时的位置
                mStart = getScrollY();
                break;
            case MotionEvent.ACTION_MOVE:
                //如果滚动没有完成则设置滚动动画
                if (!mScroller.isFinished()) {
                    mScroller.abortAnimation();
                }
                //获取滚动的距离
                int dy = mLastY - y;
                if (getScrollY() < 0) {
                    dy = 0;
                }
                if (getScrollY() > getHeight() - mScreenHeight) {
                    dy = 0;
                }
                scrollBy(0, dy);
                mLastY = y;
                break;
            case MotionEvent.ACTION_UP:
                mEnd = getScrollY();
                int dScrollY = mEnd - mStart;
                if (dScrollY > 0) {
                    //如果滚动距离>0，则往下滚
                    if (dScrollY < mScreenHeight / 3) {
                        //如果滚动距离小于一屏的1/3，则从当前位置开始，滚回到开始地方
                        mScroller.startScroll(
                                0, getScrollY(),
                                0, -dScrollY);
                    } else {
                        //滚动距离大于一屏的1/3，则从当前位置开始，继续滚动到一屏满的位置
                        mScroller.startScroll(
                                0, getScrollY(),
                                0, mScreenHeight - dScrollY);
                    }
                } else {
                    //如果滚动距离<0，则往上滚
                    if (-dScrollY < mScreenHeight / 3) {
                        //如果滚动距离小于一屏的1/3，则从当前位置开始滚回到开始的地方
                        mScroller.startScroll(
                                0, getScrollY(),
                                0, -dScrollY);
                    } else {
                        //如果滚动距离大于一屏的1/3，则从当前位置继续滚动到一屏满的位置
                        mScroller.startScroll(
                                0, getScrollY(),
                                0, -mScreenHeight - dScrollY);
                    }
                }
                break;
        }
        //刷新
        postInvalidate();
        return true;
    }

    /**
     * 调用startScroll()是不会有滚动效果的，只有在computeScroll()获取滚动情况，做出滚动的响应
     * computeScroll在父控件执行drawChild时，会调用这个方法
     */
    @Override
    public void computeScroll() {
        super.computeScroll();
        if (mScroller.computeScrollOffset()) {
            scrollTo(0, mScroller.getCurrY());
            postInvalidate();
        }
    }
}
