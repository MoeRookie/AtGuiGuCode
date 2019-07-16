package com.liqun.atguigucode;

import android.app.Application;

import org.xutils.x;

public class AtGuiGuApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        // 初始化xUtils3
        x.Ext.init(this);
        // 是否输出debug日志, 开启debug会影响性能.
        x.Ext.setDebug(BuildConfig.DEBUG);
    }
}
