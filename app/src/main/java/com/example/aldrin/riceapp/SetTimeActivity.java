package com.example.aldrin.riceapp;

import android.app.AlertDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.HandlerThread;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import okhttp3.OkHttpClient;
import okhttp3.WebSocket;

import org.remoteme.client.api.ArliterestvariablesApi;
import org.remoteme.client.model.VariableSchedulerDto;
import org.remoteme.client.model.VariableSchedulerDto.ModeEnum;
import org.remoteme.client.model.VariableDto.TypeEnum;
import org.remoteme.client.invoker.ApiException;
import org.remoteme.client.model.VariableDto;
import org.remoteme.client.model.VariableSchedulerEntityDto;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.CountDownLatch;

public class SetTimeActivity extends AppCompatActivity implements TimePickerDialog.OnTimeSetListener {

    private static final String TAG = SetTimeActivity.class.getName();
    private Button btnSetTime, btnCancel, btnOk;
    private TextView lblTime;
    private String _retVal;
    private Intent intent;
    private Bundle bundle;
    private AlertDialog.Builder builder1;
    private AlertDialog alertDialog;
    private OkHttpClient client;
    private WebSocket wsl;
    private static ArliterestvariablesApi variableApi;
    private volatile String textValue;
    private final CountDownLatch latch = new CountDownLatch(1);
    private final CountDownLatch latch1 = new CountDownLatch(1);
    private ConnectionDetector connectionDetector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_time);
        Log.d(TAG, "onCreate: SetTimeActivity");
        setViewIds();
        waitForResponse();

        btnSetTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Log.d(TAG, "onClick: Clicked");
                DialogFragment timePicker = new TimePickerFragment();
                timePicker.show(getSupportFragmentManager(), "time picker");
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick: Clicked");
                returnCups();
            }
        });

        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                builder1.setMessage("Are you sure?");
                builder1.setCancelable(true);

                builder1.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        isConnected();

                    }
                });

                builder1.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });

                if(lblTime.getText().toString().isEmpty()) {
                    Toast.makeText(SetTimeActivity.this, "Please set cook time.", Toast.LENGTH_LONG).show();
                }
                else{
                    alertDialog = builder1.create();
                    alertDialog.show();
                }

            }
        });
    }

    private void waitForResponse() {

        try
        {
            Thread UIThread = new HandlerThread("UIHandler"){
                @Override
                public void run() {
                    try
                    {
                        try {
                            String arToken = "~XFt2FmYgf3dxKTdZpb3CuCZJRTq4Z55FkNSJwQwFry1A64iEvchIs3WTKXezEFh4j";
                            VariableDto v = new VariableDto();
                            v.setName("button1");
                            v.setType(TypeEnum.BOOLEAN);

                            VariableSchedulerDto scheduler = new VariableSchedulerDto();
                            scheduler.setCron("");
                            scheduler.setMode(ModeEnum.TIME);
                            scheduler.setTime("01.01.2018 12:00");
                            scheduler.setValues(Arrays.asList("true"));

                            List<VariableSchedulerEntityDto> schedulerValue = getVariableApi().getSchedulers(v.getName(),v.getType().toString(), arToken);

                            getVariableApi().setVariableTextValue("false", "button1", "BOOLEAN", arToken);


                            Log.d(TAG + " Tested3", "Deleted");


//                            textValue = getVariableApi().getVariableTextValue("button1", "BOOLEAN", arToken);
//                            Log.d(TAG + " Tested", textValue);
                            latch.countDown();
                        }catch (Exception e) {
                            e.printStackTrace();
                        }
                    }catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            };

            UIThread.start();
            latch.await();

        }catch (Exception e){
            builder1.setMessage("Something went wrong. Please try again later.");
            builder1.setCancelable(true);

            builder1.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    returnCups();
                }
            });

            alertDialog = builder1.create();
            alertDialog.show();
        }
    }

    private String getTextValue() {
        return textValue;
    }


    private void setViewIds() {
        Log.d(TAG, "setViewIds: Created");
        btnSetTime = findViewById(R.id.btnTimePicker);
        btnOk = findViewById(R.id.btnOkTime);
        btnCancel = findViewById(R.id.btnCancelTime);
        lblTime = findViewById(R.id.lblCookSetTime);
        bundle = getIntent().getExtras();
        _retVal = bundle.getString("key");
        builder1 = new AlertDialog.Builder(SetTimeActivity.this);
//        relativeLayout = findViewById(R.id.loadingPanel);
    }

    public void startService() {
        Intent intent = new Intent(SetTimeActivity.this, NewService.class);
        Bundle bundle = new Bundle();

        bundle.putString("serviceTime", lblTime.getText().toString());
        intent.putExtras(bundle);
        startService(intent);
        openHome();
    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        Log.d(TAG, "onTimeSet: Set");
        boolean isPM = (hourOfDay >= 12);
        TextView lblSetTime = findViewById(R.id.lblCookSetTime);

        lblSetTime.setText(String.format("%02d:%02d %s", (hourOfDay == 12 || hourOfDay == 0) ? 12 : hourOfDay % 12, minute, isPM ? "PM" : "AM"));

    }
    private void isConnected() {
        connectionDetector = new ConnectionDetector(SetTimeActivity.this);
        if (!connectionDetector.isConnected()) {
            builder1 = new AlertDialog.Builder(SetTimeActivity.this);
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
        } else {
            startService();
        }
    }
    private void openHome() {
        Log.d(TAG, "openHome: Used");
        Intent intent = new Intent(SetTimeActivity.this, HomeActivity.class);
        Bundle bundle = new Bundle();

        bundle.putString("time", lblTime.getText().toString());
        intent.putExtras(bundle);

        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        sendSetTime(lblTime.getText().toString());
        startActivity(intent);
    }

    private VariableDto createVariable(boolean persistant, boolean scheduled, TypeEnum type, String arToken) throws ApiException {
        VariableDto v = new VariableDto();
        v.setHistory(false);
        v.setName("button1");
        v.setPersistent(persistant);
        v.setScheduled(scheduled);
        v.setType(type);
        getVariableApi().createVariable(v,arToken);
        return v;
    }

    private void sendSetTime(String time) {
        final String time1 = time;
//        try {
//            Thread UIThread1 = new HandlerThread("UIHandler1"){
//                @Override
//                public void run() {
//                    try {
//                        try {
//                            String arToken = "~XFt2FmYgf3dxKTdZpb3CuCZJRTq4Z55FkNSJwQwFry1A64iEvchIs3WTKXezEFh4j";
//                            Date today = new Date();
//                            VariableDto v = new VariableDto();
//                            v.setName("button1");
//                            v.setType(TypeEnum.BOOLEAN);
//
//                            //Format of the date defined in the input String
//                            DateFormat timeformat = new SimpleDateFormat("hh:mm aa", Locale.US);
//                            DateFormat dateFormat = new SimpleDateFormat("EEE MMM dd HH:mm:ss 'GMT'z yyyy", Locale.US);
//                            //Desired format: 24 hour format: Change the pattern as per the need
//                            DateFormat outputformat = new SimpleDateFormat("HH:mm", Locale.US);
//                            DateFormat dateOutput = new SimpleDateFormat("dd.MM.yyyy", Locale.US);
//                            Date date = null;
//                            Date date2 = null;
//                            String output = null;
//                            String output2 = null;
//
//                            date= timeformat.parse(time1);
//                            date2 = dateFormat.parse(today.toString());
//                            //Changing the format of date and storing it in String
//                            output = outputformat.format(date);
//                            output2 = dateOutput.format(date2);
//
//                            // get schedulers
//                            List<VariableSchedulerEntityDto> schedulers = getVariableApi().getSchedulers(v.getName(), v.getType().toString(), arToken);
//                            //get variable
//                            List<VariableDto> variables = getVariableApi().getVariables(arToken);
//                            String textValue = getVariableApi().getVariableTextValue("button1", "BOOLEAN", arToken);
//
//                            VariableSchedulerDto scheduler = new VariableSchedulerDto();
//                            scheduler.setCron("");
//                            scheduler.setMode(ModeEnum.TIME);
//                            scheduler.setTime(output2 + " " + output);
//                            scheduler.setValues(Arrays.asList("true"));
//
//                            Log.d(TAG + " Date: ", output2 + " " + output);
//
//                            if(schedulers.isEmpty()) {
//                                getVariableApi().setVariableTextValue("false", v.getName(), v.getType().toString(), arToken);
//                                getVariableApi().addScheduler(scheduler,v.getName(),v.getType().toString(),arToken);
//                                Log.d(TAG + " Tested1", "Success1");
//                            }
//                            else {
//                                // get schedulers
//                                schedulers = getVariableApi().getSchedulers(v.getName(), v.getType().toString(), arToken);
//
//                                getVariableApi().setVariableTextValue("false", v.getName(), v.getType().toString(), arToken);
//                                scheduler.setTime(output2 + " " + output);
//                                getVariableApi().updateScheduler(scheduler, schedulers.get(0).getVariableSchedulerId(), arToken);
//                                Log.d(TAG + " Tested2", "Success2");
//                            }
//
//    //                        Log.d(TAG + " Testing", textValue);
//    //                        Log.d(TAG + " Testing1: ", schedulers.get(0).getTime());
//                            } catch (Exception e) {
//                                e.printStackTrace();
//                            }
//                        } catch (Exception e) {
//                            e.printStackTrace();
//                        }
//                    };
//            };
//            UIThread1.start();
//            latch1.await();
//        }catch (Exception e) {
//
//        }
        Thread thread = new Thread(new Runnable(){
            public void run() {
                try {
                    try {
                        String arToken = "~XFt2FmYgf3dxKTdZpb3CuCZJRTq4Z55FkNSJwQwFry1A64iEvchIs3WTKXezEFh4j";
                        Date today = new Date();
                        VariableDto v = new VariableDto();
                        v.setName("cookTime");
                        v.setType(TypeEnum.INTEGER_BOOLEAN);

                        VariableDto cookStatus = new VariableDto();
                        cookStatus.setName("riceCookerStatus");
                        cookStatus.setType(TypeEnum.BOOLEAN);

                        //Format of the date defined in the input String
                        DateFormat timeformat = new SimpleDateFormat("hh:mm aa", Locale.US);
                        DateFormat dateFormat = new SimpleDateFormat("EEE MMM dd HH:mm:ss 'GMT'z yyyy", Locale.US);
                        //Desired format: 24 hour format: Change the pattern as per the need
                        DateFormat outputformat = new SimpleDateFormat("HH:mm", Locale.US);
                        DateFormat dateOutput = new SimpleDateFormat("dd.MM.yyyy", Locale.US);
                        Date date = null;
                        Date date2 = null;
                        String output = null;
                        String output2 = null;

                        date= timeformat.parse(time1);
                        date2 = dateFormat.parse(today.toString());
                        //Changing the format of date and storing it in String
                        output = outputformat.format(date);
                        output2 = dateOutput.format(date2);

                        // get schedulers
                        List<VariableSchedulerEntityDto> schedulers = getVariableApi().getSchedulers(v.getName(), v.getType().toString(), arToken);
                        //get variable
                        //List<VariableDto> variables = getVariableApi().getVariables(arToken);
                        //String textValue = getVariableApi().getVariableTextValue("cookTime", "BOOLEAN", arToken);

                        VariableSchedulerDto scheduler = new VariableSchedulerDto();
                        scheduler.setCron("");
                        scheduler.setMode(ModeEnum.TIME);
                        scheduler.setTime(output2 + " " + output);
                        scheduler.setValues(Arrays.asList(_retVal + ",true"));

                        Log.d(TAG + " Date: ", output2 + " " + output);

                        if(schedulers.isEmpty()) {
                            getVariableApi().setVariableTextValue("0,false", v.getName(), v.getType().toString(), arToken);
                            getVariableApi().setVariableTextValue("false", cookStatus.getName(), cookStatus.getType().toString(), arToken);
                            getVariableApi().addScheduler(scheduler,v.getName(),v.getType().toString(),arToken);
                            Log.d(TAG + " Tested1", "Success1");
                        }
                        else {
                            // get schedulers
                            schedulers = getVariableApi().getSchedulers(v.getName(), v.getType().toString(), arToken);

                            getVariableApi().setVariableTextValue("0,false", v.getName(), v.getType().toString(), arToken);
                            scheduler.setTime(output2 + " " + output);
                            getVariableApi().updateScheduler(scheduler, schedulers.get(0).getVariableSchedulerId(), arToken);
                            Log.d(TAG + " Tested2", "Success2");
                        }

//                        Log.d(TAG + " Testing", textValue);
//                        Log.d(TAG + " Testing1: ", schedulers.get(0).getTime());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } catch (Exception e) {
                        e.printStackTrace();
                }
            }
        });

        thread.start();
    }

    protected static ArliterestvariablesApi getVariableApi() {
        if (variableApi == null) {
            variableApi = new ArliterestvariablesApi();

            variableApi.setBasePath(variableApi.getBasePath().replaceAll("https://", "http://"));
        }
        return variableApi;
    }
    private void returnCups() {
        Log.d(TAG, "returnCups: Clicked");
        Intent intent = new Intent(SetTimeActivity.this, CupsActivity.class);

        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }
}
