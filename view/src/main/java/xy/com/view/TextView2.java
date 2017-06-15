package xy.com.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
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

public class TextView2 extends AppCompatTextView {
    //画笔
    private Paint mPaint1, mPaint2;

    //矩形区域
    private Rect rect1, rect2;

    public TextView2(Context context) {
        super(context);
        initView();
    }

    public TextView2(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public TextView2(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    /**
     * 初始化
     */
    private void initView() {
        mPaint1 = new Paint();
        mPaint1.setColor(getResources().getColor(android.R.color.holo_blue_light));
        mPaint1.setStyle(Paint.Style.FILL);

        mPaint2 = new Paint();
        mPaint2.setColor(Color.YELLOW);
        mPaint2.setStyle(Paint.Style.FILL);
        rect1 = new Rect(0, 0, getMeasuredWidth(), getMeasuredHeight());
        rect2 = new Rect(0, 0, getMeasuredWidth()-10, getMeasuredHeight()-10);
    }

    /**
     * 扩展绘制
     */
    @Override
    protected void onDraw(Canvas canvas) {
        //绘制外层矩形
        canvas.drawRect(rect1, mPaint1);
        //绘制外层矩形
        canvas.drawRect(rect2, mPaint2);
        canvas.save();
        //绘制文字前平移10px
//        canvas.translate(10, 0);
        //完成父类绘制，即绘制文本
        super.onDraw(canvas);
        //还原画布
        canvas.restore();
    }
}
