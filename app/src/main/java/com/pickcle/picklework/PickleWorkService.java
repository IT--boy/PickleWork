package com.pickcle.picklework;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Intent;
import android.os.Build;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.pickcle.picklework.model.event.RefreshEvent;

import de.greenrobot.event.EventBus;

public class PickleWorkService extends Service implements Runnable {

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        initNoti();
        new Thread(this).start();
        return START_STICKY;
    }

    private void initNoti() {
        NotificationManager mNM = (NotificationManager) getSystemService(Service.NOTIFICATION_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O && mNM != null) {
            NotificationChannel mNotificationChannel = mNM.getNotificationChannel("pk_app");
            if (mNotificationChannel == null) {
                mNotificationChannel = new NotificationChannel("pk_app", "pw", NotificationManager.IMPORTANCE_DEFAULT);
                mNotificationChannel.setDescription("咸菜打工");
                mNM.createNotificationChannel(mNotificationChannel);
            }
        }
        NotificationCompat.Builder nb = new NotificationCompat.Builder(this, "pk_app");//

        nb.setContentTitle("咸菜打工").setTicker("咸菜打工").setSmallIcon(R.drawable.app_icon);
        nb.setContentText("咸菜打工运行中.请保持此通知一直存在");
        //nb.setContent(new RemoteViews(getPackageName(),R.layout.layout));
        nb.setWhen(System.currentTimeMillis());

        Notification notification = nb.build();
        startForeground(1, notification);

    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(60 * 60 * 1000);
                EventBus.getDefault().post(new RefreshEvent());
            } catch (InterruptedException e) {
                Log.e("ZYKJ", "service thread", e);
            }
        }
    }
}
