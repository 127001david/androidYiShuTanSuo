package com.app.davidwang.androidyishutansuo.service;

import android.app.NotificationManager;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

import com.app.davidwang.androidyishutansuo.IMyAidlInterface;
import com.app.davidwang.androidyishutansuo.Student;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by DavidWang on 2017/7/7.
 */

public class MyService extends Service {
    private final static String TAG = "MyService";
    private static final String PACKAGE_SAYHI = "com.app.davidwang.androidyishutansuo.activity";

    private NotificationManager mNotificationManager;
    private boolean mCanRun = true;
    private final List<Student> mStudents = new ArrayList<>();

    private IMyAidlInterface.Stub mBinder = new IMyAidlInterface.Stub() {
        @Override
        public int getPid() throws RemoteException {
            EventBus.getDefault().post("text eventBus ipc");

            return 0;
        }

        @Override
        public void addStudent(Student student) throws RemoteException {
            synchronized (mStudents) {
                if (!mStudents.contains(student)) {
                    mStudents.add(student);
                }
            }
        }

        @Override
        public List<Student> getStudents() throws RemoteException {
            synchronized (mStudents) {
                return mStudents;
            }
        }

//        @Override
//        public boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
//            String packageName = null;
//            String[] packages = MyService.this.getPackageManager().
//                    getPackagesForUid(getCallingUid());
//            if (packages != null && packages.length > 0) {
//                packageName = packages[0];
//            }
//
//            if (!PACKAGE_SAYHI.equals(packageName)) {
//                return false;
//            }
//
//            return super.onTransact(code, data, reply, flags);
//        }
    };

    @Override
    public void onCreate() {
        Thread thr = new Thread(null, new ServiceWorker(), "BackgroundService");
        thr.start();

        synchronized (mStudents) {
            for (int i = 1; i < 6; i++) {
                Student student = new Student();
                student.name = "student#" + i;
                student.age = i * 5;
                mStudents.add(student);
            }
        }

        mNotificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        super.onCreate();
    }

    @Override
    public IBinder onBind(Intent intent) {
        Log.d(TAG, String.format("on bind,intent = %s", intent.toString()));
        displayNotificationMessage("服务已启动");
        return mBinder;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        mCanRun = false;
        super.onDestroy();
    }

    private void displayNotificationMessage(String message) {
    }

    class ServiceWorker implements Runnable {
        long counter = 0;

        @Override
        public void run() {
            // do background processing here.....
            while (mCanRun) {
                Log.d("scott", "" + counter);
                counter++;
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
