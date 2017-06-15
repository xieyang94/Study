package xy.com.view;

import android.content.Context;
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

    }

    /**
     * 测量
     *
     * @param widthMeasureSpec
     * @param heightMeasureSpec
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(measureWidth(widthMeasureSpec), measureHeight(heightMeasureSpec));
    }

    /**
     * 测量宽度
     */
    private int measureWidth(int measureSpec) {
        int result = 0;
        //获取当前模式
        int specMode = MeasureSpec.getMode(measureSpec);
        //获取测量尺寸
        int specSize = MeasureSpec.getSize(measureSpec);
        //对模式进行判断
        //精确模式
        if (specMode == MeasureSpec.EXACTLY) {
            result = specSize;
        } else {
            //非精确测量
            //非精确测量时，给他一个默认值200px
            result = 200;
            if (specMode == MeasureSpec.AT_MOST) {
                result = Math.min(result, specSize);
            }
        }
        return result;
    }

    /**
     * 测量高度
     */
    private int measureHeight(int measureSpec) {
        //初始尺寸
        int result = 0;
        //获取测量模式
        int specMode = MeasureSpec.getMode(measureSpec);
        //获取测量尺寸
        int specSize = MeasureSpec.getSize(measureSpec);
        //判断测量模式
        //精确测量
        if (specMode == MeasureSpec.EXACTLY) {
            result = specSize;
        } else {
            //非精确测量
            //给它一个默认值200px
            result = 200;
            //wrap_content时
            if (specMode == MeasureSpec.AT_MOST) {
                result = Math.min(result, specSize);
            }
        }
        return result;
    }
}
