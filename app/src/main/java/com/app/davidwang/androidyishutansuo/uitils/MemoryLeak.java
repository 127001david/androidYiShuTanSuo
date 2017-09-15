package com.app.davidwang.androidyishutansuo.uitils;

import android.content.Context;

/**
 * Created by DavidWang on 2017/8/21.
 */

public class MemoryLeak {
    private Context context;

    private static class SingletonHolder {
        public static MemoryLeak memoryLeak = new MemoryLeak();
    }

    public static MemoryLeak getInstance() {
        return SingletonHolder.memoryLeak;
    }

    public void setContext(Context context) {
        this.context = context;
    }
}
