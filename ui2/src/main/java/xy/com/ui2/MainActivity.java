package xy.com.ui2;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Outline;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.graphics.Palette;
import android.view.View;
import android.view.ViewOutlineProvider;
import android.view.Window;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        View tv_1=findViewById(R.id.tv_1);
        View tv_2=findViewById(R.id.tv_2);
        //获取Outline
        ViewOutlineProvider viewOutlineProvider1=new ViewOutlineProvider() {
            @Override
            public void getOutline(View view, Outline outline) {
                //修改outline为特定形状
                outline.setRoundRect(0,0,view.getWidth(),view.getHeight(),30);
            }
        };
        ViewOutlineProvider viewOutlineProvider2=new ViewOutlineProvider() {
            @Override
            public void getOutline(View view, Outline outline) {
                //修改outline为特定形状
                outline.setOval(0,0,view.getWidth(),view.getHeight());
            }
        };
        //重新设置形状
        tv_1.setOutlineProvider(viewOutlineProvider1);
        tv_2.setOutlineProvider(viewOutlineProvider2);

        Bitmap bitmap= BitmapFactory.decodeResource(getResources(),R.mipmap.aa);

        Palette.Builder builder = Palette.from(bitmap);
        builder.generate();//同步

        builder.generate(new Palette.PaletteAsyncListener() {//异步
            @Override
            public void onGenerated(Palette palette) {
                //通过Palette来获取对应的色调
                Palette.Swatch vibrant=palette.getDarkVibrantSwatch();
                //将颜色设置给标题栏
                getSupportActionBar().setBackgroundDrawable(new ColorDrawable(vibrant.getRgb()));
                //将颜色设置给系统栏
                Window window=getWindow();
                window.setStatusBarColor(vibrant.getRgb());
            }
        });
    }
}
