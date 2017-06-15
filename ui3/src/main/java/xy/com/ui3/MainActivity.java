package xy.com.ui3;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Pair;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private Intent intent;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.toolBar);
//        toolbar.setLogo(R.mipmap.ic_launcher);
//        toolbar.setTitle("主标题");
//        toolbar.setSubtitle("副标题");
        setSupportActionBar(toolbar);
        //设置导航Icon，必须在setSupportActionBar(toolbar)之后设置
        toolbar.setNavigationIcon(R.mipmap.ic_menu_white_24);
        //添加菜单点击事件
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(toolbar, "Click setNavigationIcon", Snackbar.LENGTH_SHORT).show();
            }
        });
    }

    //minAPI:21-5.0

    /**
     * 设置不同动画效果
     */
    public void explode(View view) {
        intent = new Intent(this, ActivityB.class);
        intent.putExtra("flag", 0);
        startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(this).toBundle());
    }

    /**
     * 设置不同动画效果
     */
    public void slide(View view) {
        intent = new Intent(this, ActivityB.class);
        intent.putExtra("flag", 1);
        startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(this).toBundle());
    }

    /**
     * 设置不同动画效果
     */
    public void fade(View view) {
        intent = new Intent(this, ActivityB.class);
        intent.putExtra("flag", 2);
        startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(this).toBundle());
    }

    /**
     * 设置不同动画效果
     */
    public void share(View view) {
        View fab = findViewById(R.id.fab_button);
        intent = new Intent(this, ActivityB.class);
        intent.putExtra("flag", 0);
//        startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(this).toBundle());
        intent = new Intent(this, ActivityB.class);
        intent.putExtra("flag", 0);
        startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(this, Pair.create(view, "share"), Pair.create(fab, "fab")).toBundle());
    }
}
