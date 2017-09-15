package com.app.davidwang.androidyishutansuo.service;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;

public class MessengerService extends Service {
    private static final int MSG_SUM = 0x110;

    private Messenger messenger;

    public MessengerService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();

        HandlerThread handlerThread = new HandlerThread("check-message-coming");

        handlerThread.start();

        messenger = new Messenger(new Handler(handlerThread.getLooper()) {
            @Override
            public void handleMessage(Message msgfromClient) {
                super.handleMessage(msgfromClient);

                Message msgToClient = Message.obtain(msgfromClient);

                switch (msgfromClient.what) {
                    case MSG_SUM: {
                        msgToClient.what = MSG_SUM;

                        try {
                            Thread.sleep(1000);
                            msgToClient.arg2 = msgfromClient.arg1 + msgfromClient.arg2;
                            msgfromClient.replyTo.send(msgToClient);
                        } catch (InterruptedException | RemoteException e) {
                            e.printStackTrace();
                        }

                        break;
                    }
                }
            }
        });
    }

    @Override
    public IBinder onBind(Intent intent) {
        return messenger.getBinder();
    }
}
