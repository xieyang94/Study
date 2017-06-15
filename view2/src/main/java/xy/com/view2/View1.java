package xy.com.view2;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Shader;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * author  :   xieyang
 * e-mail  :   1192171830@qq.com
 * time    :   2017/6/7
 * desc    :
 * version :   1.0
 */

public class View1 extends View {
    //矩形个数
    private int mRectCount;
    //矩形高度
    private float currentHeight = 0F;
    //单个矩形高度
    private float mRectHeight;
    //单个矩形宽度
    private float mRectWidth;
    //控件宽度
    private int mWidth;
    //画笔
    private Paint mPaint;
    //间隔
    private int offset;
    //渲染器
    private LinearGradient mLinearGradient;
    //延时时间
    private long mTime;

    public View1(Context context) {
        super(context);
        initView();
    }

    public View1(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public View1(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    /**
     * 初始化
     */
    private void initView() {
        mRectCount = 20;
        offset = 10;
        mTime=300L;
        mPaint = new Paint();
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setColor(Color.BLUE);
    }

    /**
     * 画图
     */
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mRectWidth = (getWidth() - offset) / mRectCount;
        for (int i = 0; i < mRectCount; i++) {
            double mRandom = Math.random();
            currentHeight = (float) (getHeight() * mRandom);
            canvas.drawRect(
                    mRectWidth * i + offset,
                    currentHeight,
                    mRectWidth * (i + 1),
                    getHeight(),
                    mPaint
            );
        }
        postInvalidateDelayed(mTime);
    }

    /**
     * 颜色渲染
     */
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = getWidth();
        mRectHeight = getHeight();
        mRectWidth = (getWidth() - offset) / mRectCount;
        mLinearGradient = new LinearGradient(
                0,
                0,
                mRectWidth,
                mRectHeight,
                new int[]{
                        Color.YELLOW,
                        Color.BLUE,
                        Color.CYAN},
                null,
                Shader.TileMode.CLAMP
        );
        mPaint.setShader(mLinearGradient);
    }
}
