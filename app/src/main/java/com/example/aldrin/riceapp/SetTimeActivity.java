package com.example.aldrin.riceapp;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.HandlerThread;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import okhttp3.OkHttpClient;
import okhttp3.WebSocket;

import org.joda.time.DateTime;
import org.remoteme.client.api.ArliterestvariablesApi;
import org.remoteme.client.model.VariableSchedulerDto;
import org.remoteme.client.model.VariableSchedulerDto.ModeEnum;
import org.remoteme.client.model.VariableDto.TypeEnum;
import org.remoteme.client.invoker.ApiException;
import org.remoteme.client.model.VariableDto;
import org.remoteme.client.model.VariableSchedulerEntityDto;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.CountDownLatch;

public class SetTimeActivity extends AppCompatActivity implements TimePickerDialog.OnTimeSetListener, MyEventListener {

    private static final String TAG = SetTimeActivity.class.getName();
    private static final int FIVE_MINUTES = 5 * 60 * 1000;
    private RelativeLayout loadingPanel;
    private Button btnSetTime, btnCancel, btnOk, btnToday, btnTom, btnCustom;
    private TextView lblTime, lblCookDate;
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

    private Calendar dateSelected = Calendar.getInstance();
    private DatePickerDialog datePickerDialog;

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

        btnToday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
                    Date today = new Date();
                    DateFormat dateFormat = new SimpleDateFormat("EEE MMM dd HH:mm:ss 'GMT'z yyyy", Locale.US);
                    DateFormat dateOutput = new SimpleDateFormat("dd.MM.yyyy", Locale.US);
                    Date date2 = null;
                    String output2 = null;
                    date2 = dateFormat.parse(today.toString());
                    output2 = dateOutput.format(date2);
                    lblCookDate.setText(output2);
                    btnSetTime.setEnabled(true);

                } catch (ParseException e) {
                    Log.d("TodayError", e.toString());
                    e.printStackTrace();
                }
            }
        });

        btnTom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    Date today = new Date();
                    DateFormat dateFormat = new SimpleDateFormat("EEE MMM dd HH:mm:ss 'GMT'z yyyy", Locale.US);
                    DateFormat dateOutput = new SimpleDateFormat("dd.MM.yyyy", Locale.US);
                    Date date2 = null;
                    String output2 = null;
                    date2 = dateFormat.parse(today.toString());

                    Calendar c = Calendar.getInstance();
                    c.setTime(dateFormat.parse(date2.toString()));
                    c.add(Calendar.DATE, 1);  // number of days to add
                    String temp = dateOutput.format(c.getTime());


                    lblCookDate.setText(temp);
                    btnSetTime.setEnabled(true);

                }catch (Exception e){
                    Log.d("DateError", e.toString());
                    e.printStackTrace();
                }
            }
        });

        btnCustom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    int day = dateSelected.get(Calendar.DAY_OF_MONTH);
                    int month = dateSelected.get(Calendar.MONTH);
                    int year = dateSelected.get(Calendar.YEAR);

                    datePickerDialog = new DatePickerDialog(SetTimeActivity.this, new DatePickerDialog.OnDateSetListener() {
                        @Override
                        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                            lblCookDate.setText((dayOfMonth < 10 ? "0" + dayOfMonth : dayOfMonth)+ "." + (month < 10 ? "0" + (month + 1) : month + 1) + "." + year);
                            btnSetTime.setEnabled(true);
                        }
                    }, year, month, day);
                    datePickerDialog.show();
                }catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                try {

                    //------------------Today---------------------
                    Date today = new Date();
                    DateFormat dateFormat1 = new SimpleDateFormat("EEE MMM dd HH:mm:ss 'GMT'z yyyy", Locale.US);
                    DateFormat dateOutput1 = new SimpleDateFormat("dd.MM.yyyy", Locale.US);
                    Date date3 = null;
                    String output3 = null;
                    date3 = dateFormat1.parse(today.toString());
                    output3 = dateOutput1.format(date3);
                    //--------------------------------------------
                    //----------------Tomorrow-----------------------
                    Date date4 = null;
                    String output4 = null;
                    date4 = dateFormat1.parse(today.toString());

                    Calendar c1 = Calendar.getInstance();
                    c1.setTime(dateFormat1.parse(date4.toString()));
                    c1.add(Calendar.DATE, 1);  // number of days to add
                    String temp = dateOutput1.format(c1.getTime());
                    //-----------------------------------------------

                    long currentTime1 = System.currentTimeMillis() + FIVE_MINUTES;

                    //Format of the date defined in the input String
                    Calendar calendar = Calendar.getInstance();
                    calendar.setTimeInMillis(currentTime1);
                    DateFormat timeformat = new SimpleDateFormat("hh:mm aa", Locale.US);

                    Date date = null;
                    Date date2 = null;
                    date = timeformat.parse(lblTime.getText().toString());
                    String temp1 = timeformat.format(calendar.getTime());
                    date2 = timeformat.parse(temp1);

//                    Log.d("testa", String.valueOf(date.getTime()));
//                    Log.d("testb", String.valueOf(date2.getTime()));

                    //Today
                    if(output3.equals(lblCookDate.getText().toString())) {
                        if(date.getTime() < date2.getTime()) {
                            builder1.setMessage("Time set must be more than 5 minutes");
                            builder1.setCancelable(true);

                            builder1.setPositiveButton("Close", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    dialogInterface.cancel();
                                }
                            });

                            builder1.setNegativeButton("Ok", new DialogInterface.OnClickListener() {
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
                        } else {
                            builder1.setMessage("Are you sure?");
                            builder1.setCancelable(true);

                            builder1.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    loadingPanel.setVisibility(View.VISIBLE);
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
                    } else {
                        builder1.setMessage("Are you sure?");
                        builder1.setCancelable(true);

                        builder1.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                loadingPanel.setVisibility(View.VISIBLE);
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


                } catch (ParseException e) {
                    e.printStackTrace();
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

    private void setViewIds() {
        Log.d(TAG, "setViewIds: Created");
        loadingPanel = findViewById(R.id.loadingPanel2);
        btnSetTime = findViewById(R.id.btnTimePicker);
        btnOk = findViewById(R.id.btnOkTime);
        btnCancel = findViewById(R.id.btnCancelTime);
        btnToday = findViewById(R.id.btnToday);
        btnTom = findViewById(R.id.btnTomorrow);
        btnCustom = findViewById(R.id.btnCustom);
        lblTime = findViewById(R.id.lblCookSetTime);
        lblCookDate = findViewById(R.id.lblCookDate);
        bundle = getIntent().getExtras();
        _retVal = bundle.getString("key");
        builder1 = new AlertDialog.Builder(SetTimeActivity.this);
        Log.d("VALUE", "setViewIds: " + _retVal);
//        relativeLayout = findViewById(R.id.loadingPanel);
    }

    public void startService() {
        new MyAsyncTask(lblTime.getText().toString(), _retVal, lblCookDate.getText().toString(),SetTimeActivity.this).execute();
//        Intent intent = new Intent(SetTimeActivity.this, NewService.class);
//        Bundle bundle = new Bundle();
//
//        bundle.putString("serviceTime", lblTime.getText().toString());
//        intent.putExtras(bundle);
//        startService(intent);
//        openHome();
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
//        sendSetTime(lblTime.getText().toString());
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

    @Override
    public void EventComplete() {
        Intent intent = new Intent(getBaseContext(), NewService.class);
        Bundle bundle = new Bundle();

        Log.d(TAG + " EventComplete: ", "Completed");
        bundle.putString("serviceTime", lblTime.getText().toString());
        intent.putExtras(bundle);
        intent.setAction("startService");
        startService(intent);
        openHome();
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
                new MyAsyncTask(lblTime.getText().toString(), _retVal, lblCookDate.getText().toString(),SetTimeActivity.this).execute();
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
