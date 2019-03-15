package com.example.aldrin.riceapp;

import android.os.AsyncTask;
import android.util.Log;

import org.remoteme.client.api.ArliterestvariablesApi;
import org.remoteme.client.model.VariableDto;
import org.remoteme.client.model.VariableSchedulerDto;
import org.remoteme.client.model.VariableSchedulerEntityDto;

import java.util.Arrays;
import java.util.List;

public class CancelAsyncTask extends AsyncTask<Void, Void, Void> {
    private static ArliterestvariablesApi variableApi;
    private Boolean eventError = false;
    private Boolean eventFinish = false;
    private MyEventListener callback = null;
    private String _myDate, _myTime;

    public CancelAsyncTask(String myDate, String myTime, MyEventListener cb) {

        _myDate = myDate;
        _myTime = myTime;
        callback = cb;
    }

    @Override
    protected Void doInBackground(Void... voids) {

        try {

            final String arToken = "~XFt2FmYgf3dxKTdZpb3CuCZJRTq4Z55FkNSJwQwFry1A64iEvchIs3WTKXezEFh4j";
            final VariableDto vS = new VariableDto();
            vS.setName("startButton");
            vS.setType(VariableDto.TypeEnum.INTEGER_BOOLEAN);

            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {

                    try {
                        VariableDto cookStatus = new VariableDto();
                        cookStatus.setName("cookerStatus");
                        cookStatus.setType(VariableDto.TypeEnum.INTEGER);

                        getVariableApi().setVariableTextValue("0", cookStatus.getName(), cookStatus.getType().toString(), arToken);

                        List<VariableSchedulerEntityDto> schedulers = getVariableApi().getSchedulers(vS.getName(), vS.getType().toString(), arToken);

                        VariableSchedulerDto scheduler = new VariableSchedulerDto();
                        scheduler.setCron("string");
                        scheduler.setMode(VariableSchedulerDto.ModeEnum.TIME);
                        scheduler.setTime(_myDate + " " + _myTime);
                        scheduler.setValues(Arrays.asList("0,false"));
                        getVariableApi().updateScheduler(scheduler, schedulers.get(0).getVariableSchedulerId(), arToken);

                        eventFinish = true;
                    }catch (Exception e) {
                        Log.d("CancelAsyncTask1", e.toString());
                        e.printStackTrace();
                        eventError = true;
                    }
                }
            });

            thread.start();
            Thread.sleep(10000);
        }catch (Exception e) {
            Log.d("CancelAsyncTask2", e.toString());
            e.printStackTrace();
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
