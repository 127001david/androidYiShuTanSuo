package com.app.davidwang.androidyishutansuo.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.app.davidwang.androidyishutansuo.R;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class SingleTopMainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_top_main);
        ButterKnife.bind(this);
        Log.d("zhouqi","SingleTopMainActivity onCreate");
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        Log.d("zhouqi","SingleTopMainActivity onNewIntent");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("zhouqi","SingleTopMainActivity onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("zhouqi","SingleTopMainActivity onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("zhouqi","SingleTopMainActivity onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("zhouqi","SingleTopMainActivity onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("zhouqi","SingleTopMainActivity onDestroy");
    }

    @OnClick(R.id.button3)
    public void onViewClicked() {
        startActivity(new Intent(this, SingleTopMainActivity.class));
    }
}
