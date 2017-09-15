package com.app.davidwang.androidyishutansuo.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.app.davidwang.androidyishutansuo.R;
import com.app.davidwang.androidyishutansuo.uitils.MemoryLeak;

public class SingleInstanceActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_instance);
        MemoryLeak instance = MemoryLeak.getInstance();

        instance.setContext(this);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        MemoryLeak.getInstance().setContext(null);
    }
}
