package com.example.aldrin.riceapp;

import android.app.IntentService;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

import org.remoteme.client.api.ArliterestvariablesApi;
import org.remoteme.client.invoker.ApiException;
import org.remoteme.client.model.VariableDto;

import java.text.SimpleDateFormat;
import java.text.DateFormat;
import java.util.Date;
import java.text.ParseException;
import java.util.List;

public class NewService extends IntentService {
    private static ArliterestvariablesApi variableApi;
    public NewService() { super("MyWorkerThread"); }

    @Override
    public int onStartCommand(@Nullable Intent intent, int flags, int startId) {
        Bundle bundle = intent.getExtras();
        Toast.makeText(NewService.this, "Service started " + bundle.getString("serviceTime"), Toast.LENGTH_LONG).show();

        DateFormat df = new SimpleDateFormat("hh:mm aa");

        DateFormat outputformat = new SimpleDateFormat("HH:mm:ss");
        Date date = null;
        String output = null;
        try{
            date= df.parse(bundle.getString("serviceTime"));
            output = outputformat.format(date);
            Toast.makeText(NewService.this, output, Toast.LENGTH_LONG).show();
            Log.d("NewService", "this is " + output);
        }catch(ParseException pe){
            pe.printStackTrace();
        }
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
            final String[] d = {"true"};
            while(!d[0].equals("false")) {
                try {
                    Thread thread = new Thread(new Runnable() {
                        @Override
                        public void run() {

                            try {
                                String arToken = "~XFt2FmYgf3dxKTdZpb3CuCZJRTq4Z55FkNSJwQwFry1A64iEvchIs3WTKXezEFh4j";
                                VariableDto v = new VariableDto();
                                v.setName("riceCookerStatus");
                                v.setType(VariableDto.TypeEnum.BOOLEAN);
                                List<VariableDto> list = getVariableApi().getVariables(arToken);
                                d[0] = getVariableApi().getVariableTextValue(v.getName(), v.getType().toString(), arToken);
                                Log.d("TAG1", "run: " + d[0]);
                            } catch (ApiException e) {
                                e.printStackTrace();
                            }
                        }
                    });
                    thread.start();
                    wait(5000);
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
    protected static ArliterestvariablesApi getVariableApi() {
        if (variableApi == null) {
            variableApi = new ArliterestvariablesApi();

            variableApi.setBasePath(variableApi.getBasePath().replaceAll("https://", "http://"));
        }
        return variableApi;
    }
}
