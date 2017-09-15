package com.app.davidwang.androidyishutansuo;

import android.app.Application;
import android.content.Intent;

import com.app.davidwang.androidyishutansuo.activity.StandardActivity;

/**
 * Created by DavidWang on 2017/7/5.
 */

public class CustomApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
    }

    public void myStartActivity() {
        startActivity(new Intent(this, StandardActivity.class));
    }
}
