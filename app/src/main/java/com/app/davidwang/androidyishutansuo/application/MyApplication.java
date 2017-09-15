package com.app.davidwang.androidyishutansuo.application;

import android.app.Application;

import com.squareup.leakcanary.LeakCanary;

/**
 * Created by DavidWang on 2017/8/21.
 */

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        if (!LeakCanary.isInAnalyzerProcess(this)) {
            LeakCanary.install(this);
        }
    }
}
