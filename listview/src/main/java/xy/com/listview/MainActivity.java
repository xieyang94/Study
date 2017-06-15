/*
 * Copyright (c) 2017. 用于测试配置版权信息
 * 谢洋 版权所有
 * XeYang. All Rights Reserved.
 */

package xy.com.listview;

import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ListView listView;
    private ChatItemListViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = (ListView) findViewById(R.id.listView);
        adapter = new ChatItemListViewAdapter(MainActivity.this, testData());
        View view = new View(this);
        ViewGroup.LayoutParams lp = new AbsListView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,50);
        view.setLayoutParams(lp);
        listView.addHeaderView(view);
        listView.addFooterView(view);
        listView.setAdapter(adapter);
    }

    private List<ChatItemListViewBean> testData() {
        List<ChatItemListViewBean> beanList = new ArrayList<ChatItemListViewBean>();

        ChatItemListViewBean bean1 = new ChatItemListViewBean();
        bean1.setIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_image_in));
        bean1.setText("嘿，吃了吗您？");
        bean1.setType(0);
        beanList.add(bean1);

        ChatItemListViewBean bean2 = new ChatItemListViewBean();
        bean2.setIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_image_out));
        bean2.setText("没呢，嘛去？");
        bean2.setType(1);
        beanList.add(bean2);

        ChatItemListViewBean bean3 = new ChatItemListViewBean();
        bean3.setIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_image_in));
        bean3.setText("城北唱曲，凑热闹");
        bean3.setType(0);
        beanList.add(bean3);

        ChatItemListViewBean bean4 = new ChatItemListViewBean();
        bean4.setIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_image_in));
        bean4.setText("一块瞅瞅去？");
        bean4.setType(0);
        beanList.add(bean4);

        ChatItemListViewBean bean5 = new ChatItemListViewBean();
        bean5.setIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_image_out));
        bean5.setText("得嘞，跟您一块过去");
        bean5.setType(1);
        beanList.add(bean5);

        ChatItemListViewBean bean6 = new ChatItemListViewBean();
        bean6.setIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_image_out));
        bean6.setText("这天，就好这口");
        bean6.setType(1);
        beanList.add(bean6);

        ChatItemListViewBean bean7 = new ChatItemListViewBean();
        bean7.setIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_image_out));
        bean7.setText("今天唱啥戏啊？");
        bean7.setType(1);
        beanList.add(bean7);

        ChatItemListViewBean bean8 = new ChatItemListViewBean();
        bean8.setIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_image_in));
        bean8.setText("《黄梅戏》");
        bean8.setType(0);
        beanList.add(bean8);

        ChatItemListViewBean bean9 = new ChatItemListViewBean();
        bean9.setIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_image_out));
        bean9.setText("哟，隔壁老吴头也好这口，我给他打个招呼");
        bean9.setType(1);
        beanList.add(bean9);

        ChatItemListViewBean bean10 = new ChatItemListViewBean();
        bean10.setIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_image_in));
        bean10.setText("得嘞");
        bean10.setType(0);
        beanList.add(bean10);

        ChatItemListViewBean bean11 = new ChatItemListViewBean();
        bean11.setIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_image_out));
        bean11.setText("测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试");
        bean11.setType(1);
        beanList.add(bean11);

        ChatItemListViewBean bean12 = new ChatItemListViewBean();
        bean12.setIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_image_in));
        bean12.setText("测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试");
        bean12.setType(0);
        beanList.add(bean12);

        return beanList;

    }
}
