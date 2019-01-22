package com.example.aldrin.riceapp;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.HandlerThread;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.concurrent.CountDownLatch;

import org.remoteme.client.api.ArliterestvariablesApi;
import org.remoteme.client.model.VariableSchedulerDto;
import org.remoteme.client.model.VariableSchedulerDto.ModeEnum;
import org.remoteme.client.model.VariableDto.TypeEnum;
import org.remoteme.client.invoker.ApiException;
import org.remoteme.client.model.VariableDto;
import org.remoteme.client.model.VariableSchedulerEntityDto;

public class HomeActivity extends AppCompatActivity {

    private static final String TAG = HomeActivity.class.getName();
    private static final String FILE_NAME = "example.txt";
    private Button btn1;
    private TextView lblTime;
    private Bundle bundle;
    private String _retVal, cookingStatus;
    private static ArliterestvariablesApi variableApi;
    private final CountDownLatch latch = new CountDownLatch(1);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        setViewIds();
        waitForResponse();
        setData();
        load();

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
        lblTime = findViewById(R.id.lblCookLabel);
    }
    private void setData() {
        try{
            wait(5000);
            bundle = getIntent().getExtras();
            _retVal = bundle.getString("time");
            if(!_retVal.isEmpty()) {
                save();
            }
            wait(5000);
        } catch (Exception e) {

            _retVal = "";
            lblTime.setText("");
            e.printStackTrace();
        }
    }

    private void openCupActivity() {
        Log.d(TAG, "openCupActivity: Activity");
        Intent intent = new Intent(HomeActivity.this, CupsActivity.class);

        startActivity(intent);
    }

    protected static ArliterestvariablesApi getVariableApi() {
        if (variableApi == null) {
            variableApi = new ArliterestvariablesApi();

            variableApi.setBasePath(variableApi.getBasePath().replaceAll("https://", "http://"));
        }
        return variableApi;
    }

    public void checkService(View view) {
        if(isMyServiceRunning(getApplicationContext(), NewService.class)) {
//            Toast.makeText(getApplicationContext(), "Service is running", Toast.LENGTH_LONG).show();
        }
        else {
//            Toast.makeText(getApplicationContext(), "Service is not running", Toast.LENGTH_LONG).show();
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

    private void save() {
        FileOutputStream fos = null;
        try
        {
            String timeTxt = _retVal;

            fos = openFileOutput(FILE_NAME, MODE_PRIVATE);
            fos.write(timeTxt.getBytes());
            Toast.makeText(HomeActivity.this, "Save to " + getFilesDir() + "/" + FILE_NAME, Toast.LENGTH_LONG).show();
        }catch (FileNotFoundException e) {
            e.printStackTrace();
        }catch (IOException e) {
            e.printStackTrace();
        } catch(Exception e) {
            e.printStackTrace();
        } finally {
            if(fos != null) {
                try
                {
                    fos.close();
                }catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }
    private void load(){
        FileInputStream fis = null;

        try {
            Log.d("TAG2", "load: " + _retVal);
            if(cookingStatus.equals("true")) {
                fis = openFileInput(FILE_NAME);
                InputStreamReader isr = new InputStreamReader(fis);
                BufferedReader rdr = new BufferedReader(isr);
                StringBuilder stringBuilder = new StringBuilder();
                String timeTxt;

                while((timeTxt = rdr.readLine()) != null) {
                    stringBuilder.append(timeTxt).append("");
                }
                Log.d("TAG2", "load: " + stringBuilder.toString());
                lblTime.setText(stringBuilder.toString());
                btn1.setEnabled(false);
            }
            else {
                btn1.setEnabled(true);
            }

        }catch (FileNotFoundException e){
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(fis != null) {
                try {
                    fis.close();
                }catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void waitForResponse() {
        final String[] d = {"true"};
        try {
            Thread UIThread = new HandlerThread("UIHandler"){
                @Override
                public void run() {
                    try {
                        try {
                            String arToken = "~XFt2FmYgf3dxKTdZpb3CuCZJRTq4Z55FkNSJwQwFry1A64iEvchIs3WTKXezEFh4j";
                            VariableDto v = new VariableDto();
                            v.setName("riceCookerStatus");
                            v.setType(VariableDto.TypeEnum.BOOLEAN);
                            List<VariableDto> list = getVariableApi().getVariables(arToken);
                            d[0] = getVariableApi().getVariableTextValue(v.getName(), v.getType().toString(), arToken);
                            Log.d("TAG1", "run: " + d[0]);

                            cookingStatus = d[0];
                            latch.countDown();

                        }catch (Exception e) {
                            e.printStackTrace();
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            };

            UIThread.start();
            latch.await();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
