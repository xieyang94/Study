package xy.com.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Shader;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;

/**
 * author  :   xieyang
 * e-mail  :   1192171830@qq.com
 * time    :   2017/6/7
 * desc    :
 * version :   1.0
 */

public class TextView3 extends AppCompatTextView {
    //画笔
    private Paint mPaint1, mPaint2;
    //控件宽度
    private int mViewWidth = 0;
    //渲染器
    private LinearGradient mLinearGradient;
    //变换矩阵
    private Matrix mGradientMatrix;
    //平移距离
    private int mTranslate = 0;

    public TextView3(Context context) {
        super(context); 
        initView();
    }

    public TextView3(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public TextView3(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    /**
     * 初始化
     */
    private void initView() {
        mPaint1 = new Paint();
    }

    /**
     * 尺寸改变
     */
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        //如果初始控件宽度为0，则获取控件宽度
        if (mViewWidth == 0) {
            mViewWidth = getMeasuredWidth();
            //如果获取的控件宽度>0则执行，否则跳过
            if (mViewWidth > 0) {
                //获取对应的画笔属性
                mPaint1 = getPaint();
                //设置渲染器属性
                mLinearGradient = new LinearGradient(0, 0, mViewWidth, 0, new int[]{
                        Color.BLUE, 0xffffffff, Color.BLUE
                }, null, Shader.TileMode.CLAMP);
                //将渲染器设置到画笔
                mPaint1.setShader(mLinearGradient);
                //获取变换矩阵
                mGradientMatrix = new Matrix();
            }
        }
    }

    /**
     * 扩展绘制
     */
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //判断矩阵是否为空
        if (mGradientMatrix != null) {
            //获取平移距离
            //一次获取控件的1/5
            mTranslate += mViewWidth / 5;
            //如果平移距离超过两倍的控件宽度
            if (mTranslate > 2 * mViewWidth) {
                //超过两倍距离，一次干掉一倍距离
                mTranslate = -mViewWidth;
            }
            //给变换矩阵设置平移距离
            mGradientMatrix.setTranslate(mTranslate, 0);
            //给渲染器设置渲染矩阵
            mLinearGradient.setLocalMatrix(mGradientMatrix);
            //设置演示刷新
            postInvalidateDelayed(100);
        }
    }
}
