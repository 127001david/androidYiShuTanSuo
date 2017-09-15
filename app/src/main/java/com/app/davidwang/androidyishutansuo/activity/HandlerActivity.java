package com.app.davidwang.androidyishutansuo.activity;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.app.davidwang.androidyishutansuo.R;

public class HandlerActivity extends AppCompatActivity {

    private Handler handler = new Handler();
    private Runnable r;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_handler);

        r = new Runnable() {
            @Override
            public void run() {
                Toast.makeText(HandlerActivity.this, "run finish", Toast.LENGTH_SHORT).show();
            }
        };
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Log.d("log", "runFinish");
            }
        }, 10000);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if (null != r) {
            handler.removeCallbacks(r);
        }
    }
}
