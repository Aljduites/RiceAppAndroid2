package com.example.aldrin.riceapp;

import android.app.IntentService;
import android.app.Notification;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import org.remoteme.client.api.ArliterestvariablesApi;
import org.remoteme.client.invoker.ApiException;
import org.remoteme.client.model.VariableDto;

import java.text.SimpleDateFormat;
import java.text.DateFormat;
import java.util.Date;
import java.text.ParseException;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class NewService extends IntentService {
    private static ArliterestvariablesApi variableApi;
    static final int NOTIFICATION_ID = 543;
    private Timer timer = new Timer();
    final String[] d = {"1"};

    public static boolean isServiceRunning = false;
    public NewService() { super("MyWorkerThread"); }

    @Override
    public void onCreate() {
        super.onCreate();

        timer.scheduleAtFixedRate(new TimerTask() {

            @Override
            public void run() {
                try {

                    Thread thread = new Thread(new Runnable() {
                        @Override
                        public void run() {

                            try {
                                if(isServiceRunning == true) {
                                    String arToken = "~XFt2FmYgf3dxKTdZpb3CuCZJRTq4Z55FkNSJwQwFry1A64iEvchIs3WTKXezEFh4j";
                                    VariableDto v = new VariableDto();
                                    v.setName("cookerStatus");
                                    v.setType(VariableDto.TypeEnum.INTEGER);
                                    List<VariableDto> list = getVariableApi().getVariables(arToken);
//                                Thread.sleep(5000);
                                    d[0] = getVariableApi().getVariableTextValue(v.getName(), v.getType().toString(), arToken);

                                    Log.d("TAG1", "run: " + d[0]);
                                }
                            } catch (ApiException e) {
                                e.printStackTrace();
                                Log.d("NewService 1", "run: error " + e.toString());
                            }
                        }
                    });
                    thread.start();
//                    this.wait(8000);

                    if(d[0].equals("0")) {
                        timer.cancel();
                        stopMyService();
                        Intent intent = new Intent(NewService.this, AlarmActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);

                        startActivity(intent);
                    }
                }catch (Exception e) {
                    e.printStackTrace();
                    Log.d("NewService 3", "onHandleIntent: error " + e.toString());
                }
            }

        }, 0, 10000);//10 seoonds
        startServiceWithNotification();
    }

    @Override
    public int onStartCommand(@Nullable Intent intent, int flags, int startId) {
        if (intent != null && intent.getAction().equals("startService")) {
            startServiceWithNotification();
        } else {
            stopMyService();
        }
//        Bundle bundle = intent.getExtras();
//        Toast.makeText(NewService.this, "Service started " + bundle.getString("serviceTime"), Toast.LENGTH_LONG).show();

//        DateFormat df = new SimpleDateFormat("hh:mm aa");

//        DateFormat outputformat = new SimpleDateFormat("HH:mm:ss");
//        Date date = null;
//        String output = null;
//        try{
//            date= df.parse(bundle.getString("serviceTime"));
//            output = outputformat.format(date);
//            Toast.makeText(NewService.this, output, Toast.LENGTH_LONG).show();
//            Log.d("NewService", "this is " + output);
//        }catch(ParseException pe){
//            pe.printStackTrace();
//            Log.d("NewService", "this is error" + pe.toString());

//        }
//        return super.onStartCommand(intent, flags, startId);
        return START_STICKY;
    }

    protected static ArliterestvariablesApi getVariableApi() {
        if (variableApi == null) {
            variableApi = new ArliterestvariablesApi();

            variableApi.setBasePath(variableApi.getBasePath().replaceAll("https://", "http://"));
        }
        return variableApi;
    }

    void startServiceWithNotification() {
        if (isServiceRunning) return;
        isServiceRunning = true;
//
//        Intent notificationIntent = new Intent(getApplicationContext(), MyActivity.class);
//        notificationIntent.setAction(C.ACTION_MAIN);  // A string containing the action name
//        notificationIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
//        PendingIntent contentPendingIntent = PendingIntent.getActivity(this, 0, notificationIntent, 0);

        Bitmap icon = BitmapFactory.decodeResource(getResources(), R.drawable.icon);

        Notification notification = new NotificationCompat.Builder(getBaseContext(), "12345")
                .setContentTitle(getResources().getString(R.string.app_name))
                .setTicker(getResources().getString(R.string.app_name))
                .setContentText(getResources().getString(R.string.timerSet))
                .setSmallIcon(R.drawable.icon)
                .setLargeIcon(Bitmap.createScaledBitmap(icon, 128, 128, false))
//                .setContentIntent(contentPendingIntent)
                .setOngoing(true)
//                .setDeleteIntent(contentPendingIntent)  // if needed
                .build();
        notification.flags = notification.flags | Notification.FLAG_NO_CLEAR;     // NO_CLEAR makes the notification stay when the user performs a "delete all" command
        startForeground(NOTIFICATION_ID, notification);
//        waitForResponse();
    }

    void stopMyService() {
        isServiceRunning = false;
        stopForeground(true);
        stopSelf();

    }

}
