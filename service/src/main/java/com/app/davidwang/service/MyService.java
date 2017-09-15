package com.app.davidwang.service;

import android.app.NotificationManager;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class MyService extends Service {
    private final static String TAG = "MyService";
    private static final String PACKAGE_SAYHI = "com.example.test";

    private NotificationManager _notificationManager;
    private boolean _canRun = true;
    private List<IStudent> _students = new ArrayList<>();

    private final IMyService.Stub iBinder = new IMyService.Stub() {
        @Override
        public List<IStudent> getStudent() throws RemoteException {
            return null;
        }

        @Override
        public void addStudent(IStudent student) throws RemoteException {

        }
    };

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}

