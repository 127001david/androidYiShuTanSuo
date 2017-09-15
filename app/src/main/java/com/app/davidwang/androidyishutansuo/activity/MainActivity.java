package com.app.davidwang.androidyishutansuo.activity;

import android.content.Intent;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.app.davidwang.androidyishutansuo.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {
    @Bind(R.id.textView)
    TextView textView;
    @Bind(R.id.button)
    Button button;

    private String s;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("s", s);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        this.s = savedInstanceState.getString("s");

        textView.setText(this.s);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            Toast.makeText(getApplicationContext(), "屏幕切换", Toast.LENGTH_SHORT).show();
        }
    }

    @OnClick({R.id.button, R.id.launch_mode, R.id.view_position
            , R.id.btn_flow_layout, R.id.btn_message, R.id.btn_thread_pool, R.id.btn_handler})
    public void onClicked(View view) {
        switch (view.getId()) {
            case R.id.button: {
                s = "had been saved!";

                Intent intent = new Intent("com.app.davidwang.thread.VIEW");

                intent.setDataAndType(Uri.parse("https://www.google.com.hk:1001/#safe=strict&q=android+隐式启动activity"), "video/mp4");
                intent.addCategory("com.app.davidwang.thread.DEFAULT");

                if (null != intent.resolveActivity(getPackageManager())) {
                    startActivity(intent);
                } else {
                    Toast.makeText(getApplicationContext(), "未找到可响应应用!", Toast.LENGTH_SHORT).show();
                }

                break;
            }
            case R.id.launch_mode: {
                Intent intent = new Intent(this, LaunchModeActivity.class);

                startActivity(intent);

                break;
            }
            case R.id.view_position: {
                startActivity(new Intent(this, ViewActivity.class));
                break;
            }
            case R.id.btn_flow_layout: {
                startActivity(new Intent(this, FlowLayoutActivity.class));
                break;
            }
            case R.id.btn_message: {
                startActivity(new Intent(this, MessageActivity.class));
                break;
            }
            case R.id.btn_thread_pool: {
                startActivity(new Intent(this, ThreadPoolActivity.class));
                break;
            }
            case R.id.btn_handler: {
                startActivity(new Intent(this, HandlerActivity.class));
                break;
            }
        }
    }
}
