package com.example.aldrin.riceapp;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextThemeWrapper;
import android.widget.Toast;

public class SplashActivity extends Activity {

    private Intent intent;
    private AlertDialog.Builder builder1;
    private AlertDialog alertDialog;
    private ConnectionDetector connectionDetector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        isConnected();

    }

    private void isConnected() {
        connectionDetector = new ConnectionDetector(SplashActivity.this);
        if(!connectionDetector.isConnected()) {
            builder1 = new AlertDialog.Builder(SplashActivity.this);
            builder1.setMessage("No connection found!");
            builder1.setCancelable(false);

            builder1.setNeutralButton("Try again", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    isConnected();
                }
            });

            alertDialog = builder1.create();
            alertDialog.setTitle("Alert");
            alertDialog.setIcon(R.drawable.icon);
            alertDialog.show();
        }
        else {
            try {
                SharedPreferences preferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
                String storedPassword = preferences.getString("Key_Password", "");
                Long storedExpiration = preferences.getLong("ExpiredTime", -1);

                if(storedExpiration > System.currentTimeMillis()) {

                    intent = new Intent(SplashActivity.this, HomeActivity.class);
                    Handler handler = new Handler();

                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            startActivity(intent);
                            finish();
                        }
                    }, 3000);
                } else {
                    intent = new Intent(SplashActivity.this, MainActivity.class);
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.clear();
                    editor.apply();
                    Handler handler = new Handler();

                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            startActivity(intent);
                            finish();
                        }
                    }, 3000);
                }
            } catch (Exception e) {
                builder1 = new AlertDialog.Builder(SplashActivity.this);
                builder1.setMessage("Something went wrong! Please try again later.");
                builder1.setCancelable(false);

                builder1.setNeutralButton("Try again", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                });

                alertDialog = builder1.create();
                alertDialog.setTitle("Alert");
                alertDialog.setIcon(R.drawable.icon);
                alertDialog.show();
            }
        }
    }
}
