/*
 * Copyright (c) 2017. 用于测试配置版权信息
 * 谢洋 版权所有
 * XeYang. All Rights Reserved.
 */

package xy.com.wavekit;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.yuan.waveview.WaveView;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    private WaveView waveView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        waveView = (WaveView) findViewById(R.id.waveview);
        waveView.setMode(WaveView.MODE_RECT);
        waveView.setWaveColor(Color.RED);
        waveView.setbgColor(Color.WHITE);
        waveView.setSpeed(WaveView.SPEED_FAST);
        waveView.setProgress(0);
        waveView.setMax(100);
        countDown();
        waveView.setProgressListener(new WaveView.waveProgressListener() {
            @Override
            public void onPorgress(boolean isDone, long progress, long max) {
                if (isDone) {
                    Toast.makeText(MainActivity.this, "Loading completed!!!", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private void countDown() {
        final long count = 0;
        Observable.interval(1, TimeUnit.SECONDS)
                .take(10)//计时次数
                .map(new Func1<Long, Long>() {
                    @Override
                    public Long call(Long integer) {
                        Log.d("TAG", "" + integer);
                        return count + integer;
                    }
                })
                .doOnSubscribe(new Action0() {
                    @Override
                    public void call() {

                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Long>() {

                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(Long aLong) {
                        Log.d("TAG", "进度:" + aLong);
                        waveView.setProgress(aLong);
                    }
                });
    }

}
