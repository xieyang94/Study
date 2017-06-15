package xy.com.a2048.view;

import android.content.Context;
import android.graphics.Color;
import android.text.TextPaint;
import android.view.Gravity;
import android.widget.FrameLayout;
import android.widget.TextView;

/**
 * author  :   xieyang
 * e-mail  :   1192171830@qq.com
 * time    :   2017/6/6
 * desc    :
 * version :   1.0
 */

public class GameItem extends FrameLayout {

    private int mCardShowNum;
    private TextView mTvNum;

    public GameItem(Context context,int cardShowNum){
        super(context);
        this.mCardShowNum=cardShowNum;
        //初始化item
        initCardItem();
    }

    /**
     * 初始化item
     */
    private void initCardItem(){
        //设置面板背景色，是由Frame拼起来的
        setBackgroundColor(Color.GRAY);
        mTvNum=new TextView(getContext());
        setNum(mCardShowNum);
        //修改5*5时字体太大
//        int gameLines=Config.mSp.getInt(Config.KEY_GAME_LINES,4);
//        if (gameLines==4){
//            mTvNum.setTextSize(35);
//        }else if (gameLines==5){
//            mTvNum.setTextSize(25);
//        }else {
//            mTvNum.setTextSize(20);
//        }
        TextPaint tp=mTvNum.getPaint();
        tp.setFakeBoldText(true);
        mTvNum.setGravity(Gravity.CENTER);



    }

    private void setNum(int num){}

}
