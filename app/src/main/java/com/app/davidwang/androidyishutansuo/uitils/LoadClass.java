package com.app.davidwang.androidyishutansuo.uitils;

import android.util.Log;

/**
 * Created by DavidWang on 2017/8/4.
 */

public class LoadClass {
    public static int a = 10;

    {
        a = 20;
        Log.d("执行","非静态代码块");
    }

    static {
        a = 30;
        Log.d("执行","静态代码块");
    }

    public LoadClass() {
        a = 40;
        Log.d("执行","构造方法");
    }
}
