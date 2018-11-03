package com.example.aldrin.riceapp;

import android.app.IntentService;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.Toast;

public class NewService extends IntentService {

    public NewService() { super("MyWorkerThread"); }

    @Override
    public int onStartCommand(@Nullable Intent intent, int flags, int startId) {
        Bundle bundle = intent.getExtras();
        Toast.makeText(NewService.this, "Service started " + bundle.getString("serviceTime"), Toast.LENGTH_LONG).show();

        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        synchronized (this) {
            int count = 0;

            while(count < 10) {
                try {
                    wait(1500);
                    count++;
                }catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            intent = new Intent(getApplicationContext(), AlarmActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
            startActivity(intent);
        }
    }
}
