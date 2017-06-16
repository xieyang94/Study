/*
 * Copyright (c) 2017. 用于测试配置版权信息
 * 谢洋 版权所有
 * XeYang. All Rights Reserved.
 */

package xy.com.infinitecards;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.LinearInterpolator;

import com.bakerj.infinitecards.InfiniteCardView;
import com.bakerj.infinitecards.transformer.DefaultTransformerToBack;
import com.bakerj.infinitecards.transformer.DefaultTransformerToFront;
import com.bakerj.infinitecards.transformer.DefaultZIndexTransformerCommon;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private InfiniteCardView mCardView;
    private MyAdapter myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mCardView = (InfiniteCardView) findViewById(R.id.view);
        myAdapter = new MyAdapter(MainActivity.this, getData());
        mCardView.setAdapter(myAdapter);

        mCardView.setAnimInterpolator(new LinearInterpolator());
        mCardView.setTransformerToFront(new DefaultTransformerToFront());
        mCardView.setTransformerToBack(new DefaultTransformerToBack());
        mCardView.setZIndexTransformerToBack(new DefaultZIndexTransformerCommon());


//        mCardView.setTransformerToBack(new AnimationTransformer() {
//            @Override
//            public void transformAnimation(View view, float fraction, int cardWidth, int cardHeight, int fromPosition, int toPosition) {
//                int positionCount = fromPosition - toPosition;
//                float scale = (0.8f - 0.1f * fromPosition) + (0.1f * fraction * positionCount);
//                ViewHelper.setScaleX(view, scale);
//                ViewHelper.setScaleY(view, scale);
//                if (fraction < 0.5) {
//                    ViewCompat.setRotationX(view, 180 * fraction);
//                } else {
//                    ViewCompat.setRotationX(view, 180 * (1 - fraction));
//                }
//            }
//
//            @Override
//            public void transformInterpolatedAnimation(View view, float fraction, int cardWidth, int cardHeight, int fromPosition, int toPosition) {
//                int positionCount = fromPosition - toPosition;
//                float scale = (0.8f - 0.1f * fromPosition) + (0.1f * fraction * positionCount);
//                ViewHelper.setTranslationY(view, -cardHeight * (0.8f - scale) * 0.5f - cardWidth * (0.02f *
//                        fromPosition - 0.02f * fraction * positionCount));
//            }
//        });
//        mCardView.setZIndexTransformerToBack(new ZIndexTransformer() {
//            @Override
//            public void transformAnimation(CardItem card, float fraction, int cardWidth, int cardHeight, int fromPosition, int toPosition) {
//                if (fraction < 0.4f) {
//                    card.zIndex = 1f + 0.01f * fromPosition;
//                } else {
//                    card.zIndex = 1f + 0.01f * toPosition;
//                }
//            }
//
//            @Override
//            public void transformInterpolatedAnimation(CardItem card, float fraction, int cardWidth, int cardHeight, int fromPosition, int toPosition) {
//
//            }
//        });

    }

    private List<String> getData() {
        List<String> list = new ArrayList<String>();
        list.add("http://img1.3lian.com/img2012/2/0220/12/d/21.jpg");
        list.add("http://img1.3lian.com/img2012/2/0220/12/d/22.jpg");
        list.add("http://img1.3lian.com/img2012/2/0220/12/d/24.jpg");
        list.add("http://img1.3lian.com/img2012/2/0220/12/d/25.jpg");
        list.add("http://img1.3lian.com/img2012/2/0220/12/d/27.jpg");
        return list;

    }
}
