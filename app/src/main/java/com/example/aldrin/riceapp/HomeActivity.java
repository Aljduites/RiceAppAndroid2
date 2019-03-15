package com.example.aldrin.riceapp;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.AlertDialog;
import android.app.Service;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.HandlerThread;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.CountDownLatch;

import org.remoteme.client.api.ArliterestvariablesApi;
import org.remoteme.client.model.VariableSchedulerDto;
import org.remoteme.client.model.VariableSchedulerDto.ModeEnum;
import org.remoteme.client.model.VariableDto.TypeEnum;
import org.remoteme.client.invoker.ApiException;
import org.remoteme.client.model.VariableDto;
import org.remoteme.client.model.VariableSchedulerEntityDto;

public class HomeActivity extends AppCompatActivity implements MyEventListener{

    private static final String TAG = HomeActivity.class.getName();
    private static final String FILE_NAME = "example.txt";
    private static final int TEN_MINUTES = 10 * 60 * 1000;
    private RelativeLayout loadingPanel;
    private Button btn1, btnCancel;
    private TextView lblTime, lblMachineStatus, lblRiceLevel;
    private Bundle bundle;
    private String _retVal, cookingStatus, riceStatus;
    private static ArliterestvariablesApi variableApi;
    private final CountDownLatch latch = new CountDownLatch(1);
    private boolean isRecursionEnable = true;
    private Timer timer = new Timer();
    final String[] d = {"1"};
    final String[] output = {null};
    final String[] output2 = {null};
    public static boolean isAlarmed = true;

    private AlertDialog.Builder builder1;
    private AlertDialog alertDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        setViewIds();
//        waitForResponse();
        setData();
        runInBackground();

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                timer.cancel();
                openCupActivity();
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                try {
                    long currentTime1 = System.currentTimeMillis() - TEN_MINUTES;
                    final Date today = new Date();

                    //Format of the date defined in the input String
                    Calendar calendar = Calendar.getInstance();
                    calendar.setTimeInMillis(currentTime1);
                    final DateFormat timeformat = new SimpleDateFormat("hh:mm aa", Locale.US);

                    final Date[] date = {null};
                    final Date[] date2 = {null};

                    final DateFormat dateFormat = new SimpleDateFormat("EEE MMM dd HH:mm:ss 'GMT'z yyyy", Locale.US);
                    //Desired format: 24 hour format: Change the pattern as per the need
                    final DateFormat outputformat = new SimpleDateFormat("HH:mm", Locale.US);
                    final DateFormat dateOutput = new SimpleDateFormat("dd.MM.yyyy", Locale.US);

                    date[0] = timeformat.parse(lblTime.getText().toString());
                    date2[0] = dateFormat.parse(today.toString());

                    output[0] = outputformat.format(date[0]);
                    output2[0] = dateOutput.format(date2[0]);

                    Log.d("cancelHere", output2[0] + " " + output[0]);
                    loadingPanel.setVisibility(View.VISIBLE);

                    stopService(new Intent(getBaseContext(), NewService.class));
                    //------------
                    new CancelAsyncTask(output2[0], output[0], HomeActivity.this).execute();

                } catch (Exception e) {
                    Log.d("cancelHere4", e.toString());

                    e.printStackTrace();
                }
            }
        });
    }

    private void setViewIds() {
        Log.d(TAG, "setViewIds: Created");
        btn1 = findViewById(R.id.btnRiceAmount);
        btnCancel = findViewById(R.id.btnCancelCook);
        lblTime = findViewById(R.id.lblCookLabel);
        lblMachineStatus = findViewById(R.id.lblMachineStatus);
        loadingPanel = findViewById(R.id.loadingPanel3);
        lblRiceLevel = findViewById(R.id.lblRiceLevel);
        lblMachineStatus.setText(R.string.standBy1);
        builder1 = new AlertDialog.Builder(HomeActivity.this);
    }
    private void setData() {
        try{
//            wait(5000);
            bundle = getIntent().getExtras();
            _retVal = bundle.getString("time");
            Log.d("TimeHere", _retVal);
//            if(!_retVal.isEmpty()) {
//                save();
//            }
//            wait(5000);
        } catch (Exception e) {

            _retVal = "";
            lblTime.setText("");
            e.printStackTrace();
            Log.d("TimeHere", "Error:" + e.toString());
        }
    }

    private void openCupActivity() {
        Log.d(TAG, "openCupActivity: Activity");
        if(lblRiceLevel.getText().toString().equals("OK")) {
            Intent intent = new Intent(HomeActivity.this, CupsActivity.class);

            startActivity(intent);
        } else {
            Toast.makeText(HomeActivity.this, "Rice level is LOW", Toast.LENGTH_LONG).show();
        }
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
//            Toast.makeText(HomeActivity.this, "Save to " + getFilesDir() + "/" + FILE_NAME, Toast.LENGTH_LONG).show();
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
                            v.setName("cookerStatus");
                            v.setType(TypeEnum.INTEGER);
                            List<VariableDto> list = getVariableApi().getVariables(arToken);
                            d[0] = getVariableApi().getVariableTextValue(v.getName(), v.getType().toString(), arToken);
//                            Log.d("TAG1", "run: " + d[0]);

//                            cookingStatus = d[0];
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
    private void cancelCooking() {
        final String[] d = {"true"};
        try {
            Thread UIThread = new HandlerThread("UIHandler"){
                @Override
                public void run() {
                    try {
                        try {
                            String arToken = "~XFt2FmYgf3dxKTdZpb3CuCZJRTq4Z55FkNSJwQwFry1A64iEvchIs3WTKXezEFh4j";
                            VariableDto v = new VariableDto();
                            v.setName("cookerStatus");
                            v.setType(TypeEnum.INTEGER);
                            List<VariableDto> list = getVariableApi().getVariables(arToken);
                            d[0] = getVariableApi().getVariableTextValue(v.getName(), v.getType().toString(), arToken);
//                            Log.d("TAG1", "run: " + d[0]);

//                            cookingStatus = d[0];
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
    void runInBackground() {
        final String[] d = {"true", "false"};
        final FileInputStream[] fis = {null};
        final String[] remoteSchedulerDate = {""};
        final DateFormat inputDate = new SimpleDateFormat("HH:mm", Locale.US);
        final DateFormat outputDate = new SimpleDateFormat("hh:mm aa", Locale.US);
        final Date[] date1 = new Date[1];
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                try {
                    Thread UIThread = new HandlerThread("UIHandler"){
                        @Override
                        public void run() {
                            try {
                                String arToken = "~XFt2FmYgf3dxKTdZpb3CuCZJRTq4Z55FkNSJwQwFry1A64iEvchIs3WTKXezEFh4j";
                                VariableDto v = new VariableDto();
                                v.setName("cookerStatus");
                                v.setType(TypeEnum.INTEGER);
                                List<VariableDto> list = getVariableApi().getVariables(arToken);
                                d[0] = getVariableApi().getVariableTextValue(v.getName(), v.getType().toString(), arToken);
//                            Log.d("TAG1", "run: " + d[0]);

                                cookingStatus = d[0];

                                VariableDto v1 = new VariableDto();
                                v1.setName("riceLevel");
                                v1.setType(TypeEnum.INTEGER);

                                d[1] = getVariableApi().getVariableTextValue(v1.getName(), v1.getType().toString(), arToken);

                                riceStatus = d[1];

                                VariableDto v2 = new VariableDto();
                                v2.setName("startButton");
                                v2.setType(VariableDto.TypeEnum.INTEGER_BOOLEAN);

                                List<VariableSchedulerEntityDto> schedulers = getVariableApi().getSchedulers(v2.getName(), v2.getType().toString(), arToken);
                                date1[0] = inputDate.parse(schedulers.get(0).getTime().split(" ")[1]);
                                remoteSchedulerDate[0] = outputDate.format(date1[0]);
                                latch.countDown();

                            } catch(Exception e) {
                                Log.d("ErrorYes", e.toString());
                                e.printStackTrace();
                            }
                            synchronized (this) {
                                try {

//                                    fis[0] = openFileInput(FILE_NAME);
//                                    InputStreamReader isr = new InputStreamReader(fis[0]);
//                                    final BufferedReader rdr = new BufferedReader(isr);
//                                    final StringBuilder stringBuilder = new StringBuilder();
//                                    final String[] timeTxt = new String[1];

//                                    wait(5000);

                                    runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            try {

                                                if(riceStatus.equals("1")) {
                                                    lblRiceLevel.setText(R.string.lowStatus2);
                                                } else {
                                                    lblRiceLevel.setText(R.string.lowStatus1);
                                                }

                                                if(cookingStatus.equals("2") || cookingStatus.equals("3") || cookingStatus.equals("4")) {

                                                    lblTime.setText(remoteSchedulerDate[0]);

                                                    lblMachineStatus.setText(R.string.standBy3);
                                                    lblMachineStatus.setBackgroundColor(getResources().getColor(R.color.colorPrepare));
                                                    btn1.setEnabled(false);
                                                    btnCancel.setEnabled(true);
                                                    loadingPanel.setVisibility(View.GONE);

                                                } else if(cookingStatus.equals("1")) {

                                                    lblTime.setText(remoteSchedulerDate[0]);

                                                    lblMachineStatus.setText(R.string.standBy2);
                                                    lblMachineStatus.setBackgroundColor(getResources().getColor(R.color.colorCookStart));
                                                    btn1.setEnabled(false);
                                                    btnCancel.setEnabled(false);
                                                    loadingPanel.setVisibility(View.GONE);
                                                }
                                                else {
                                                    lblTime.setText("");
                                                    lblMachineStatus.setText(R.string.standBy1);
                                                    lblMachineStatus.setBackgroundColor(getResources().getColor(R.color.colorStandby));
                                                    btn1.setEnabled(true);
                                                    btnCancel.setEnabled(false);
                                                    loadingPanel.setVisibility(View.GONE);
                                                }



//                                                if(cookingStatus.equals("2")) {
//                                                    while((timeTxt[0] = rdr.readLine()) != null) {
//                                                        stringBuilder.append(timeTxt[0]).append("");
//                                                    }
////                        Log.d("TAG2", "load: " + stringBuilder.toString());
//                                                    lblTime.setText(stringBuilder.toString());
//
//                                                    lblMachineStatus.setText(R.string.standBy3);
//                                                    lblMachineStatus.setBackgroundColor(getResources().getColor(R.color.colorPrepare));
//                                                    btn1.setEnabled(false);
//                                                    loadingPanel.setVisibility(View.GONE);
//
//                                                } else if(cookingStatus.equals("1")) {
//                                                    while((timeTxt[0] = rdr.readLine()) != null) {
//                                                        stringBuilder.append(timeTxt[0]).append("");
//                                                    }
////                        Log.d("TAG2", "load: " + stringBuilder.toString());
//                                                    lblTime.setText(stringBuilder.toString());
//
//                                                    lblMachineStatus.setText(R.string.standBy2);
//                                                    lblMachineStatus.setBackgroundColor(getResources().getColor(R.color.colorCookStart));
//                                                    btn1.setEnabled(false);
//                                                    btnCancel.setEnabled(false);
//                                                    loadingPanel.setVisibility(View.GONE);
//                                                }
//                                                else {
//                                                    lblTime.setText("");
//                                                    lblMachineStatus.setText(R.string.standBy1);
//                                                    lblMachineStatus.setBackgroundColor(getResources().getColor(R.color.colorStandby));
//                                                    btn1.setEnabled(true);
//                                                    btnCancel.setEnabled(true);
//                                                    loadingPanel.setVisibility(View.GONE);
//
//
//                                                }

                                            }catch (Exception e) {
                                                Log.d("errorH1", e.toString());
                                                e.printStackTrace();
                                            }
                                        }
                                    });
                                } //catch (InterruptedException e) {
                                    //Log.d("errorH2", e.toString());
                                    //e.printStackTrace();
                                //}
//                                catch (FileNotFoundException e) {
//                                    Log.d("errorH3", e.toString());
//                                    e.printStackTrace();
//                                }
                                catch(Exception e) {
                                    Log.d("errorH3", e.toString());
                                    e.printStackTrace();
                                }
                            }

                        }
                    };

                    UIThread.start();
                    latch.await();

                } catch (Exception e) {
                    Log.d("Error1",e.toString());
                    e.printStackTrace();
                } finally {
//                    if(fis[0] != null) {
//                        try {
//                            fis[0].close();
//                        }catch (IOException e) {
//                            Log.d("File2", e.toString());
//                            e.printStackTrace();
//                        }
//                    }
                }
            }
        }, 0, 10000);
    }

    @Override
    public void EventComplete() {
        loadingPanel.setVisibility(View.INVISIBLE);
        lblMachineStatus.setText(R.string.standBy1);
        lblTime.setText("");
    }

    @Override
    public void EventFailed() {
        builder1.setMessage("Something went wrong. Please try again later.");
//        builder1.setCancelable(false);

        builder1.setPositiveButton("Try again", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Log.d(TAG + " EventFailed: ", "Error");
//                loadingPanel.setVisibility(View.GONE);

                new CancelAsyncTask(output2[0], output[0], HomeActivity.this).execute();
            }
        });

        alertDialog = builder1.create();
        alertDialog.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface dialog) {
                ((AlertDialog)dialog).getButton(AlertDialog.BUTTON_NEGATIVE).setEnabled(false);
            }
        });
        alertDialog.show();
    }
}
