package com.app.davidwang.androidyishutansuo.activity;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.app.davidwang.androidyishutansuo.IMyAidlInterface;
import com.app.davidwang.androidyishutansuo.R;
import com.app.davidwang.androidyishutansuo.Student;
import com.app.davidwang.androidyishutansuo.service.MyService;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class StandardActivity extends AppCompatActivity {
    private static final String ACTION_BIND_SERVICE = "com.app.davidwang.androidyishutansuo.service.MyService";

    IMyAidlInterface iMyService;
    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            IMyAidlInterface iMyService = IMyAidlInterface.Stub.asInterface(iBinder);

            try {
                List<Student> students = iMyService.getStudents();

                StringBuilder s = new StringBuilder();

                for (Student student : students) {
                    s.append(student.name).append("\n");
                }

                Toast.makeText(StandardActivity.this, s.toString(), Toast.LENGTH_LONG).show();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            iMyService = null;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_standard);
        ButterKnife.bind(this);

        Intent intent = new Intent(this, MyService.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        bindService(intent, connection, BIND_AUTO_CREATE);

        EventBus.getDefault().register(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(String event) {
        Toast.makeText(this, event, Toast.LENGTH_SHORT).show();
    }

    @OnClick(R.id.button2)
    public void onClicked() {
        if (null != iMyService) {
            try {
                iMyService.getPid();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }

        startActivity(new Intent(this, LaunchModeActivity.class));
    }

    @Override
    protected void onDestroy() {
        unbindService(connection);
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }
}
