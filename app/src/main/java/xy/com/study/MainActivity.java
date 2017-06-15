package xy.com.study;

import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import xy.com.study.adapter.AppInfoAdapter;
import xy.com.study.bean.PMAppInfo;

public class MainActivity extends AppCompatActivity {

    private TextView textView1;

    private StringBuilder stringBuilder;

    private AppInfoAdapter adapter;

    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = (ListView) findViewById(R.id.listView);
        adapter = new AppInfoAdapter(MainActivity.this, getAppInfo(ALL_APP));
        listView.setAdapter(adapter);
    }

    public void onClick1(View view) {
        adapter = new AppInfoAdapter(MainActivity.this, getAppInfo(ALL_APP));
        listView.setAdapter(adapter);
    }

    public void onClick2(View view) {
        adapter = new AppInfoAdapter(MainActivity.this, getAppInfo(SYSTEM_APP));
        listView.setAdapter(adapter);
    }

    public void onClick3(View view) {
        adapter = new AppInfoAdapter(MainActivity.this, getAppInfo(THIRD_APP));
        listView.setAdapter(adapter);
    }

    public void onClick4(View view) {
        adapter = new AppInfoAdapter(MainActivity.this, getAppInfo(SDCARD_APP));
        listView.setAdapter(adapter);
    }

    private PackageManager pm;
    private static final int ALL_APP = 1;
    private static final int SYSTEM_APP = 2;
    private static final int THIRD_APP = 3;
    private static final int SDCARD_APP = 4;

    /**
     * 获取android-APP应用信息
     */
    private List<PMAppInfo> getAppInfo(int flag) {

        //获取PackageManager对象
        pm = this.getPackageManager();
        //获取应用信息
        List<ApplicationInfo> lisApplications = pm.getInstalledApplications(PackageManager.GET_UNINSTALLED_PACKAGES);
        List<PMAppInfo> appInfos = new ArrayList<PMAppInfo>();
        //判断应用类型
        switch (flag) {
            case ALL_APP:
                appInfos.clear();
                for (ApplicationInfo app : lisApplications) {
                    appInfos.add(makeAppInfo(app));
                }
                break;
            case SYSTEM_APP:
                appInfos.clear();
                for (ApplicationInfo app : lisApplications) {
                    if ((app.flags & ApplicationInfo.FLAG_SYSTEM) != 0) {
                        appInfos.add(makeAppInfo(app));
                    }
                }
                break;
            case THIRD_APP:
                appInfos.clear();
                for (ApplicationInfo app : lisApplications) {
                    if ((app.flags & ApplicationInfo.FLAG_SYSTEM) <= 0) {
                        appInfos.add(makeAppInfo(app));
                    } else if ((app.flags & ApplicationInfo.FLAG_UPDATED_SYSTEM_APP) != 0) {
                        appInfos.add(makeAppInfo(app));
                    }
                }
                break;
            case SDCARD_APP:
                appInfos.clear();
                for (ApplicationInfo app : lisApplications) {
                    if ((app.flags & ApplicationInfo.FLAG_EXTERNAL_STORAGE) != 0) {
                        appInfos.add(makeAppInfo(app));
                    }
                }
                break;
            default:
                return null;
        }

        return appInfos;
    }

    /**
     * 保存数据到Bean
     */
    private PMAppInfo makeAppInfo(ApplicationInfo app) {
        PMAppInfo appInfo = new PMAppInfo();
        appInfo.setAppLabel((String) app.loadLabel(pm));
        appInfo.setAppIcon(app.loadIcon(pm));
        appInfo.setPkgName(app.packageName);
        return appInfo;
    }
}
