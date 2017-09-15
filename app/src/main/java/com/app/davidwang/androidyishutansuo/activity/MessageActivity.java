package com.app.davidwang.androidyishutansuo.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.app.davidwang.androidyishutansuo.R;

public class MessageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);
        init();
    }

    private void init() {
        final ThreadLocal<Boolean> threadLocal = new ThreadLocal<>();

        threadLocal.set(true);
        Log.d("thread-local", Thread.currentThread().getName() + " " + threadLocal.get());

        new Thread("Thread1") {
            @Override
            public void run() {
                super.run();

                threadLocal.set(false);
                Log.d("thread-local", getName() + " " + threadLocal.get());
            }
        }.start();

        Looper.prepare();
        Looper.myLooper().quitSafely();

        new Thread("Thread2") {
            @Override
            public void run() {
                super.run();

                Log.d("thread-local", getName() + " " + threadLocal.get());
            }
        }.start();

        Handler handler1 = new Handler(new Handler.Callback() {
            @Override
            public boolean handleMessage(Message msg) {
                return false;
            }
        });

        handler1.post(new Runnable() {
            @Override
            public void run() {

            }
        });

        Message msg = new Message();
        handler1.sendMessage(msg);

        Handler handler2 = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
            }
        };
    }
}
