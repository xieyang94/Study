/*
 * Copyright (c) 2017. 用于测试配置版权信息
 * 谢洋 版权所有
 * XeYang. All Rights Reserved.
 */

package xy.com.ui8;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.widget.NumberPicker;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private NumberPicker numberPicker;
    private TextView textView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SearchView searchView = (SearchView) findViewById(R.id.searchView);
        textView = (TextView) findViewById(R.id.textView);
        textView.setText(searchView.getQuery());
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                textView.setText(newText);
                return false;
            }
        });
        numberPicker = (NumberPicker) findViewById(R.id.numberPicker);
        numberPicker.setFormatter(formatter);
        numberPicker.setOnValueChangedListener(valueChangeListener);
        numberPicker.setOnScrollListener(scrollListener);
        numberPicker.setMaxValue(23);
        numberPicker.setMinValue(0);
        numberPicker.setValue(10);



    }

    /**
     * 格式化参数
     */
    private NumberPicker.Formatter formatter = new NumberPicker.Formatter() {
        @Override
        public String format(int value) {
            String tmpStr = String.valueOf(value);
            if (value < 10) {
                tmpStr = "0" + tmpStr;
            }
            return tmpStr;
        }
    };

    /**
     * 数字改变监听
     */
    private NumberPicker.OnValueChangeListener valueChangeListener = new NumberPicker.OnValueChangeListener() {
        @Override
        public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
            Log.d("TAG", "" + picker.getValue());
            Log.d("TAG", "最终:" + newVal);
            textView.setText("" + newVal);
        }
    };

    /**
     * 滑动监听
     */
    private NumberPicker.OnScrollListener scrollListener = new NumberPicker.OnScrollListener() {
        @Override
        public void onScrollStateChange(NumberPicker view, int scrollState) {

        }
    };



}
