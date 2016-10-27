package com.wanli.com.multitypedemo;

import android.app.Application;

/**
 * Created by wanli on 2016/10/27.
 */

public class BaseApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        MultiTypeInstaller.start();
    }
}
