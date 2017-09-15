package com.app.davidwang.androidyishutansuo.activity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.app.davidwang.androidyishutansuo.R;
import com.app.davidwang.androidyishutansuo.service.MessengerService;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SingleTaskActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private static final int MSG_SUM = 0x110;

    @Bind(R.id.id_tv_callback)
    TextView idTvCallback;
    @Bind(R.id.id_btn_add)
    Button idBtnAdd;
    @Bind(R.id.root)
    LinearLayout root;

    private Messenger mService;
    private boolean isConn;

    private Messenger messenger = new Messenger(new Handler() {
        @Override
        public void handleMessage(Message msgFromServer) {
            super.handleMessage(msgFromServer);

            switch (msgFromServer.what) {
                case MSG_SUM: {
                    TextView tv = (TextView) root.findViewById(msgFromServer.arg1);
                    tv.setText(tv.getText() + "=>" + msgFromServer.arg2);
                    break;
                }
            }
        }
    });

    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            mService = new Messenger(iBinder);
            isConn = true;
            idTvCallback.setText("connected!");
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            mService = null;
            isConn = false;
            idTvCallback.setText("disconnected!");
        }
    };

    private int mA;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_task);
        ButterKnife.bind(this);

        Intent intent = new Intent(this, MessengerService.class);
        bindService(intent, connection, Context.BIND_AUTO_CREATE);

        Log.d("zhouqi", "SingleTaskActivity onCreate");
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        Log.d("zhouqi", "SingleTaskActivity onNewIntent");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("zhouqi", "SingleTaskActivity onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("zhouqi", "SingleTaskActivity onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("zhouqi", "SingleTaskActivity onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("zhouqi", "SingleTaskActivity onStop");
    }

    @OnClick({R.id.button4, R.id.btn_intent_top, R.id.id_btn_add})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.button4: {
                startActivity(new Intent(this, LaunchModeActivity.class));
                break;
            }
            case R.id.btn_intent_top: {
                startActivity(new Intent(this, SingleTaskActivity.class));
                break;
            }
            case R.id.id_btn_add: {
                int a = mA++;
                int b = (int) (Math.random() * 1000);

                TextView tv = new TextView(this);

                tv.setText(a + " + " + b + " = caculating ...");
                tv.setId(a);
                root.addView(tv);

                Message msgFromClient = Message.obtain(null, MSG_SUM, a, b);

                msgFromClient.replyTo = messenger;

                if (isConn) {
                    try {
                        mService.send(msgFromClient);
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                }

                break;
            }
        }
    }

    @Override
    protected void onDestroy() {
        Log.d("zhouqi", "SingleTaskActivity onDestroy");
        unbindService(connection);
        super.onDestroy();
    }
}
