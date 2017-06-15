/*
 * Copyright (c) 2017. 用于测试配置版权信息
 * 谢洋 版权所有
 * XeYang. All Rights Reserved.
 */

package xy.com.listview;

import android.graphics.Bitmap;

/**
 * author  :   xieyang
 * e-mail  :   1192171830@qq.com
 * time    :   2017/6/12
 * desc    :
 * version :   1.0
 */

public class ChatItemListViewBean {
    private int type;
    private String text;
    private Bitmap icon;

    public ChatItemListViewBean() {
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Bitmap getIcon() {
        return icon;
    }

    public void setIcon(Bitmap icon) {
        this.icon = icon;
    }
}
