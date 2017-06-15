/*
 * Copyright (c) 2017. 用于测试配置版权信息
 * 谢洋 版权所有
 * XeYang. All Rights Reserved.
 */

package xy.com.colormatrix;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    //色调、饱和度、亮度
    private SeekBar seekBarHue, seekBarSaturation, seekBarLum;
    private ImageView imageView1;
    private static int MAX_VALUE = 255;
    private static int MID_VALUE = 127;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        seekBarHue = (SeekBar) findViewById(R.id.seekbarHue);
        seekBarSaturation = (SeekBar) findViewById(R.id.seekbarSaturation);
        seekBarLum = (SeekBar) findViewById(R.id.seekbarLum);
        imageView1 = (ImageView) findViewById(R.id.imageView1);
        bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.image2);
        seekBarHue.setOnSeekBarChangeListener(seekBarChangeListener);
        seekBarSaturation.setOnSeekBarChangeListener(seekBarChangeListener);
        seekBarLum.setOnSeekBarChangeListener(seekBarChangeListener);
        seekBarHue.setMax(MAX_VALUE);
        seekBarSaturation.setMax(MAX_VALUE);
        seekBarLum.setMax(MAX_VALUE);
        seekBarHue.setProgress(MID_VALUE);
        seekBarSaturation.setProgress(MID_VALUE);
        seekBarLum.setProgress(MID_VALUE);
        imageView1.setImageBitmap(bitmap);

    }

    private float mHue;
    private float mStauration;
    private float mLum;
    private Bitmap bitmap;


    private SeekBar.OnSeekBarChangeListener seekBarChangeListener = new SeekBar.OnSeekBarChangeListener() {
        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            switch (seekBar.getId()) {
                case R.id.seekbarHue:
                    mHue = (progress - MID_VALUE) * 1.0F / MID_VALUE * 180;
                    Toast.makeText(MainActivity.this,"hahhhh",Toast.LENGTH_LONG).show();
                    break;
                case R.id.seekbarSaturation:
                    mStauration = progress * 1.0F / MID_VALUE;
                    break;
                case R.id.seekbarLum:
                    mLum = progress * 1.0F / MID_VALUE;
                    break;
            }
            imageView1.setImageBitmap(handleImageEffect(bitmap, mHue, mStauration, mLum));
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {

        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {

        }
    };

    private Bitmap handleImageEffect(Bitmap bm, float hue, float saturation, float lum) {
        Bitmap bmp = Bitmap.createBitmap(bm.getWidth(), bm.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bmp);
        Paint paint = new Paint();

        ColorMatrix hueMatrix = new ColorMatrix();
        hueMatrix.setRotate(0, hue);
        hueMatrix.setRotate(1, hue);
        hueMatrix.setRotate(2, hue);

        ColorMatrix saturationMatrix = new ColorMatrix();
        saturationMatrix.setSaturation(saturation);

        ColorMatrix lumMatrix = new ColorMatrix();
        lumMatrix.setScale(lum, lum, lum, 1);

        ColorMatrix imageMarix = new ColorMatrix();
        imageMarix.postConcat(hueMatrix);
        imageMarix.postConcat(saturationMatrix);
        imageMarix.postConcat(lumMatrix);

        paint.setColorFilter(new ColorMatrixColorFilter(imageMarix));
        canvas.drawBitmap(bm, 0, 0, paint);

        Log.d("TAG","xy");

        return bmp;
    }

}
