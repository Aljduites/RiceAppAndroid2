package com.example.aldrin.riceapp;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class HomeActivity extends AppCompatActivity {

    private static final String TAG = HomeActivity.class.getName();
    private Button btn1;
    private TextView lblTime;
    private Bundle bundle;
    private String _retVal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        setViewIds();
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openCupActivity();
            }
        });
    }

    private void setViewIds() {
        Log.d(TAG, "setViewIds: Created");
        btn1 = findViewById(R.id.btnRiceAmount);
        lblTime = findViewById(R.id.lblCookTime);

        try{
            bundle = getIntent().getExtras();
            _retVal = bundle.getString("time");
            lblTime.setText(_retVal.toString());
            btn1.setEnabled(true);

            Boolean isTrue = lblTime.getText().toString().isEmpty();
            if(!isTrue) {
                btn1.setEnabled(false);
            }
        }catch (Exception e) {
            _retVal = "";
            lblTime.setText(_retVal.toString());
        }
    }

    private void openCupActivity() {
        Log.d(TAG, "openCupActivity: Activity");
        Intent intent = new Intent(HomeActivity.this, CupsActivity.class);

        startActivity(intent);
    }

    public void checkService(View view) {
        if(isMyServiceRunning(getApplicationContext(), NewService.class)) {
            Toast.makeText(getApplicationContext(), "Service is running", Toast.LENGTH_LONG).show();
        }
        else {
            Toast.makeText(getApplicationContext(), "Service is not running", Toast.LENGTH_LONG).show();
        }
    }

    private boolean isMyServiceRunning(Context context, Class<? extends Service> serviceClass) {
        final ActivityManager activityManager = (ActivityManager)context.getSystemService(Context.ACTIVITY_SERVICE);
        final List<ActivityManager.RunningServiceInfo> services = activityManager.getRunningServices(Integer.MAX_VALUE);

        for (ActivityManager.RunningServiceInfo runningServiceInfo: services) {
            if(runningServiceInfo.service.getClassName().equals(serviceClass.getName())) {
                return true;
            }
        }

        return false;
    }

    @Override
    public void onBackPressed() {
        Log.d(TAG, "onBackPressed: Clicked");
        moveTaskToBack(true);
    }
}
