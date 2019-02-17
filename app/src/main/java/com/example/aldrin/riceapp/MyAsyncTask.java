package com.example.aldrin.riceapp;

import android.os.AsyncTask;
import android.util.Log;

import org.remoteme.client.api.ArliterestvariablesApi;
import org.remoteme.client.model.VariableDto;
import org.remoteme.client.model.VariableSchedulerDto;
import org.remoteme.client.model.VariableSchedulerEntityDto;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class MyAsyncTask extends AsyncTask<Void, Void, Void> {
    private static final String TAG = MyEventListener.class.getName();
    private static ArliterestvariablesApi variableApi;
    private Boolean eventError = false;
    private Boolean eventFinish = false;
    private MyEventListener callback = null;
    private String time1 = "";
    private String numCups = "";


    public MyAsyncTask(String time, String cups, MyEventListener cb) {
//        super();
        time1 = time;
        numCups = cups;
        callback = cb;

    }
//


    @Override
    protected Void doInBackground(Void... voids) {

        // do stuff

        try {
            Thread thread = new Thread(new Runnable() {

                public void run() {
                    try {
                        try {
                            String arToken = "~XFt2FmYgf3dxKTdZpb3CuCZJRTq4Z55FkNSJwQwFry1A64iEvchIs3WTKXezEFh4j";
                            Date today = new Date();

                            Log.d("VALUE2", "cup" + numCups);
                            VariableDto cups = new VariableDto();
                            cups.setName("cup1");
                            cups.setType(VariableDto.TypeEnum.BOOLEAN);

                            VariableDto v = new VariableDto();
                            v.setName("startButton");
                            v.setType(VariableDto.TypeEnum.INTEGER_BOOLEAN);

                            VariableDto cookStatus = new VariableDto();
                            cookStatus.setName("cookerStatus");
                            cookStatus.setType(VariableDto.TypeEnum.INTEGER);

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

                            date = timeformat.parse(time1);
                            date2 = dateFormat.parse(today.toString());
                            //Changing the format of date and storing it in String
                            output = outputformat.format(date);
                            output2 = dateOutput.format(date2);
                            Log.d("Date1: ", output2 + " " + output);
                            // get schedulers
                            List<VariableSchedulerEntityDto> schedulers = getVariableApi().getSchedulers(v.getName(), v.getType().toString(), arToken);
//                            for (VariableSchedulerEntityDto scheduler : schedulers) {
//                                System.out.println(scheduler.getVariableSchedulerId());
//                            }
                            Log.d("End1: ", "End");
                            //get variable
                            //List<VariableDto> variables = getVariableApi().getVariables(arToken);
                            //String textValue = getVariableApi().getVariableTextValue("cookTime", "BOOLEAN", arToken);
                            Log.d("Date2: ", output2 + " " + output);
                            VariableSchedulerDto scheduler = new VariableSchedulerDto();
                            scheduler.setCron("string");
                            scheduler.setMode(VariableSchedulerDto.ModeEnum.TIME);
                            scheduler.setTime(output2 + " " + output);
                            scheduler.setValues(Arrays.asList(numCups + ",true"));

                            Log.d("Date3: ", output2 + " " + output);

                            if(schedulers.isEmpty()) {
                                getVariableApi().addScheduler(scheduler,v.getName(),v.getType().toString(),arToken);
                                Log.d(TAG + " Tested1", "Success1");

                                getVariableApi().setVariableTextValue("0,false", v.getName(), v.getType().toString(), arToken);
                                Log.d(TAG + " Tested1", "Success2");

                                getVariableApi().setVariableTextValue("2", cookStatus.getName(), cookStatus.getType().toString(), arToken);
                                Log.d(TAG + " Tested1", "Success3");


                                getVariableApi().setVariableTextValue("true", cups.getName(), cups.getType().toString(), arToken);
                                Log.d(TAG + " Tested1", "Success4");
                                eventFinish = true;
                            }
                            else {
                                // get schedulers
                                schedulers = getVariableApi().getSchedulers(v.getName(), v.getType().toString(), arToken);

                                getVariableApi().setVariableTextValue("0,false", v.getName(), v.getType().toString(), arToken);
                                Log.d(TAG + " Tested2", "Success1");

                                scheduler.setTime(output2 + " " + output);

                                getVariableApi().setVariableTextValue("2", cookStatus.getName(), cookStatus.getType().toString(), arToken);
                                Log.d(TAG + " Tested2", "Success2");

                                getVariableApi().setVariableTextValue("true", cups.getName(), cups.getType().toString(), arToken);
                                Log.d(TAG + " Tested2", "Success3");

                                getVariableApi().updateScheduler(scheduler, schedulers.get(0).getVariableSchedulerId(), arToken);
                                Log.d(TAG + " Tested2", "Success4");
                                eventFinish = true;

                            }

//                        Log.d(TAG + " Testing", textValue);
//                        Log.d(TAG + " Testing1: ", schedulers.get(0).getTime());
                        } catch (Exception e) {
                            Log.d("Error1: ", e.toString());
                            e.printStackTrace();

                            eventError = true;
                        }
                    } catch (Exception e) {
                        Log.d("Error2: ", e.toString());
                        e.printStackTrace();

                        eventError = true;
                    }
                }
            });

            thread.start();
            Thread.sleep(10000);
        }catch (Exception e) {
            Log.d("Error2", e.toString());
            eventError = true;
        }

        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        if(eventFinish == true && callback != null) {
            callback.EventComplete();
        }
        else if(eventError == true) {
            callback.EventFailed();
        }
//        super.onPostExecute(aVoid);
    }

    protected static ArliterestvariablesApi getVariableApi() {
        if (variableApi == null) {
            variableApi = new ArliterestvariablesApi();

            variableApi.setBasePath(variableApi.getBasePath().replaceAll("https://", "http://"));
        }
        return variableApi;
    }
}
