package com.app.davidwang.androidyishutansuo.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.app.davidwang.androidyishutansuo.R;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class LaunchModeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch_mode);
        ButterKnife.bind(this);

        Log.d("zhouqi","LaunchModeActivity onCreate");
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        Log.d("zhouqi","LaunchModeActivity onNewIntent");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("zhouqi","LaunchModeActivity onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("zhouqi","LaunchModeActivity onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("zhouqi","LaunchModeActivity onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("zhouqi","LaunchModeActivity onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("zhouqi","LaunchModeActivity onDestroy");
    }

    @OnClick({R.id.standard, R.id.single_top, R.id.single_task, R.id.single_instance})
    public void onClicked(View view) {
        switch (view.getId()) {
            case R.id.standard: {
//                Intent intent = new Intent(this, SingleInstanceActivity.class);
//                intent.addFlags(FLAG_ACTIVITY_NEW_TASK);
//                getApplicationContext().startActivity(intent);
//                ((CustomApplication) getApplication()).myStartActivity();
                startActivity(new Intent(this, StandardActivity.class));

                break;
            }
            case R.id.single_top:
                startActivity(new Intent(this, SingleTopMainActivity.class));

                break;
            case R.id.single_task:
                startActivity(new Intent(this, SingleTaskActivity.class));
                break;
            case R.id.single_instance:
                Intent intent = new Intent(this, SingleInstanceActivity.class);

                startActivity(intent);

                break;
        }
    }
}
