package com.example.aldrin.riceapp;

import android.content.Context;
import android.content.Intent;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.ebanx.swipebtn.OnStateChangeListener;
import com.ebanx.swipebtn.SwipeButton;

import java.util.Timer;
import java.util.TimerTask;

public class AlarmActivity extends AppCompatActivity {

    private static final String TAG = "AlarmActivity";
    private SwipeButton swipeButton;
    private Timer timer;
    Ringtone ringtone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm);
        setViewIds();
        Log.d(TAG, "onCreate: Created");

        swipeButton.setOnStateChangeListener(new OnStateChangeListener() {
            @Override
            public void onStateChange(boolean active) {
                ringtone.stop();
                RingtoneManager manager = new RingtoneManager(getApplicationContext());
                manager.stopPreviousRingtone();
                timer.cancel();
                AlarmActivity.super.onBackPressed();
            }
        });
    }

    private void setViewIds() {
        swipeButton = findViewById(R.id.btnSwipe);
        Vibrator vibrator = (Vibrator)getSystemService(Context.VIBRATOR_SERVICE);
        vibrator.vibrate(1000);
        ringtone = RingtoneManager.getRingtone(getApplicationContext(),
                RingtoneManager.getDefaultUri(RingtoneManager.TYPE_RINGTONE));
        timer = new Timer();
        if(ringtone != null) {
            ringtone.play();
            timer.scheduleAtFixedRate(new TimerTask() {
                @Override
                public void run() {
                    ringtone.play();
                }
            }, 1000 * 1, 1000 * 1);
        }
    }

    @Override
    public void onBackPressed() {
        Log.d(TAG, "onBackPressed: Clicked");
        moveTaskToBack(true);
    }
}
