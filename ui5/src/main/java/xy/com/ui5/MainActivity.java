package xy.com.ui5;

import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.AbsListView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Toolbar mToolbar;
    private ListView listView;
    private int lastVisibleItemPosition;
    private MyAdapter myAdapter;

    private List<String> data = new ArrayList<String>();

    private FloatingActionButton floatingActionButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);

        floatingActionButton = (FloatingActionButton) findViewById(R.id.fab_button);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(v, "my action", 1000).show();
            }
        });

        listView = (ListView) findViewById(R.id.listView);

        View header = new View(this);
        header.setLayoutParams(new AbsListView.LayoutParams(AbsListView.LayoutParams.MATCH_PARENT, (int) getResources().getDimension(R.dimen.abc_action_bar_default_height_material)));
        listView.addHeaderView(header);

        testData();
        myAdapter = new MyAdapter(MainActivity.this, data);
        listView.setAdapter(myAdapter);
//        listView.setOnScrollListener(new AbsListView.OnScrollListener() {
//            @Override
//            public void onScrollStateChanged(AbsListView view, int scrollState) {
//                switch (scrollState) {
//                    case AbsListView.OnScrollListener.SCROLL_STATE_IDLE:
//                        Log.d("TAG", "滑动停止时");
//                        break;
//                    case AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL:
//                        Log.d("TAG", "正在滚动");
//                        break;
//                    case AbsListView.OnScrollListener.SCROLL_STATE_FLING:
//                        Log.d("TAG", "手指抛动");
//                        break;
//                }
//            }
//
//            @Override
//            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
//                Log.d("TAG", "滚动");
//                if (firstVisibleItem + visibleItemCount == totalItemCount && totalItemCount > 0) {
//                    Log.d("TAG", "滚动到了最后一行");
//                }
//
//                if (firstVisibleItem > lastVisibleItemPosition) {
//                    toolBarAnim(1);
//                    Log.d("TAG", "上滑");
//                } else if (firstVisibleItem < lastVisibleItemPosition) {
//                    Log.d("TAG", "下滑");
//                    toolBarAnim(0);
//                }
//                lastVisibleItemPosition = firstVisibleItem;
//
//                Log.d("TAG", "可视区域第一个Item的id" + listView.getFirstVisiblePosition());
//                Log.d("TAG", "可视区域最后一个Item的id" + listView.getLastVisiblePosition());
//            }
//        });
        mTouchSlop= ViewConfiguration.get(this).getScaledTouchSlop();
        listView.setOnTouchListener(myTouchListener);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private ObjectAnimator mAnimator,mAnimator2;

    private void toolBarAnim(int flag) {
        if (mAnimator != null && mAnimator.isRunning()) {
            mAnimator.cancel();
        }
        if (mAnimator2 != null && mAnimator2.isRunning()) {
            mAnimator2.cancel();
        }
        if (flag == 0) {
            mAnimator = ObjectAnimator.ofFloat(mToolbar, "translationY", mToolbar.getTranslationY(), 0);
            mAnimator2 = ObjectAnimator.ofFloat(floatingActionButton, "scaleX", floatingActionButton.getScaleX(), 1);
            mAnimator2 = ObjectAnimator.ofFloat(floatingActionButton, "scaleY", floatingActionButton.getScaleY(), 1);
            mAnimator2 = ObjectAnimator.ofFloat(floatingActionButton, "alpha", floatingActionButton.getAlpha(), 1);
        } else {
            mAnimator = ObjectAnimator.ofFloat(mToolbar, "translationY", mToolbar.getTranslationY(), -mToolbar.getHeight());
            mAnimator2 = ObjectAnimator.ofFloat(floatingActionButton, "scaleX", floatingActionButton.getScaleX(), 0);
            mAnimator2 = ObjectAnimator.ofFloat(floatingActionButton, "alpha", floatingActionButton.getAlpha(), 0);
        }
        mAnimator.start();
        mAnimator2.start();
    }

    private void testData() {
        for (int i = 0; i < 40; i++) {
            data.add("" + i);
        }
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
                            toolBarAnim(0);//hide
                            mShow = !mShow;
                        }
                    } else if (direction == 0) {
                        if (!mShow) {
                            toolBarAnim(1);//hide
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

}
