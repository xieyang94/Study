package xy.com.study.bean;

import android.graphics.drawable.Drawable;

/**
 * author  :   xieyang
 * e-mail  :   1192171830@qq.com
 * time    :   2017/6/5
 * desc    :
 * version :   1.0
 */

public class PMAppInfo {
    private String appLabel;
    private Drawable appIcon;
    private String pkgName;

    public PMAppInfo() {
    }

    public String getAppLabel() {
        return appLabel;
    }

    public void setAppLabel(String appLabel) {
        this.appLabel = appLabel;
    }

    public Drawable getAppIcon() {
        return appIcon;
    }

    public void setAppIcon(Drawable appIcon) {
        this.appIcon = appIcon;
    }

    public String getPkgName() {
        return pkgName;
    }

    public void setPkgName(String pkgName) {
        this.pkgName = pkgName;
    }
}
