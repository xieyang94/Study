package xy.com.view5;

import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AbsListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private MyListView listView;

    private MyAdapter myAdapter;

    private List<String> data = new ArrayList<String>();

    private int lastVisibleItemPosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = (MyListView) findViewById(R.id.listView);
        listView.setEmptyView(findViewById(R.id.textView));
//        testData();
        myAdapter = new MyAdapter(MainActivity.this, data);
        listView.setAdapter(myAdapter);

        //从第十一个开始显示，这个显示的是item
//        listView.setSelection(10);

        //没反应，第一个参数是以px作单位的
//      listView.smoothScrollBy(1,100);

        //没反应，参数是以px作单位的
//        listView.smoothScrollByOffset(2);

        //没反应
//        listView.smoothScrollToPosition(5);

        /**
         * 滑动监听
         */
        listView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        Log.d("TAG", "触摸时操作");
                        break;
                    case MotionEvent.ACTION_MOVE:
                        Log.d("TAG", "移动时操作");
                        break;
                    case MotionEvent.ACTION_UP:
                        Log.d("TAG", "离开时操作");
                        break;
                }


                return false;
            }
        });

        /**
         * 滑动监听
         */
        listView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                switch (scrollState) {
                    case AbsListView.OnScrollListener.SCROLL_STATE_IDLE:
                        Log.d("TAG", "滑动停止时");
                        break;
                    case AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL:
                        Log.d("TAG", "正在滚动");
                        break;
                    case AbsListView.OnScrollListener.SCROLL_STATE_FLING:
                        Log.d("TAG", "手指抛动");
                        break;
                }
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                Log.d("TAG", "滚动");
                if (firstVisibleItem + visibleItemCount == totalItemCount && totalItemCount > 0) {
                    Log.d("TAG", "滚动到了最后一行");
                }

                if (firstVisibleItem > lastVisibleItemPosition) {
                    Log.d("TAG", "上滑");
                } else if (firstVisibleItem < lastVisibleItemPosition) {
                    Log.d("TAG", "下滑");
                }
                lastVisibleItemPosition = firstVisibleItem;

                Log.d("TAG", "可视区域第一个Item的id" + listView.getFirstVisiblePosition());
                Log.d("TAG", "可视区域最后一个Item的id" + listView.getLastVisiblePosition());
            }
        });

    }

    /**
     * 测试数据
     */
    private void testData() {
        for (int i = 0; i < 40; i++) {
            data.add("" + i);
        }
    }

    public void onClick(View view) {
        listView.smoothScrollToPosition(0);
        Snackbar.make(view, "正儿八经", 1000).setAction("胡说八道", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "增加一项", Toast.LENGTH_LONG).show();
                data.add("new");
                myAdapter.notifyDataSetChanged();
            }
        }).show();
    }

    //最低滑动距离
    private int mTouchSlop;
    private int direction;
    private float mFirst, mCurrentY;
    private boolean mShow = true;


    View.OnTouchListener myTouchListener = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    mFirst = event.getY();
                    break;
                case MotionEvent.ACTION_MOVE:
                    mCurrentY = event.getY();
                    if (mCurrentY - mFirst > mTouchSlop) {
                        direction = 0;//down
                    } else if (mFirst - mCurrentY > mTouchSlop) {
                        direction = 1;//up
                    }
                    if (direction == 1) {
                        if (mShow) {
                            toolbarAnim(0);//hide
                            mShow = !mShow;
                        }
                    } else if (direction == 0) {
                        if (!mShow) {
                            toolbarAnim(1);//hide
                            mShow = !mShow;
                        }
                    }
                    break;
                case MotionEvent.ACTION_UP:
                    break;
            }
            return false;
        }
    };

    private ObjectAnimator mAnimator;

    private void toolbarAnim(int flag) {
        if (mAnimator!= null && mAnimator.isRunning()){
            mAnimator.cancel();
        }
        if (flag==0){
//            mAnimator=ObjectAnimator.ofFloat()
        }
    }


}
