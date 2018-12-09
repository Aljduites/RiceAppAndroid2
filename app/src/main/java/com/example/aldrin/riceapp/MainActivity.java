package com.example.aldrin.riceapp;

import android.annotation.SuppressLint;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.CountDownTimer;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.WebSocket;
import okhttp3.Response;
import okhttp3.WebSocketListener;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getName();
    private static final String REQUESTTAG = "string request first";
    private Button btn1, btn2, btnTest, btnOn, btnOff;
    private EditText editTxt1;
    private RequestQueue req;
    private StringRequest strRequest;
    private OkHttpClient client;
    private WebSocket wsl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setViewIds();



        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick: Clicked");
                if(editTxt1.getText().toString().toUpperCase().equals("TESTING")) {
                    SharedPreferences.Editor editor = getSharedPreferences("MyPrefs", MODE_PRIVATE).edit();
                    editor.putString("Key_Password", "TESTING");
                    editor.putLong("ExpiredTime", System.currentTimeMillis() + TimeUnit.MINUTES.toMillis(5));
                    editor.apply();
                    openHomeActivity();
                }
                else {
                    Toast.makeText(MainActivity.this, "Invalid Password", Toast.LENGTH_LONG).show();
                }

            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick: Clicked");
                editTxt1.setText("");
            }
        });

//        btnTest.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                startWebSocket();
//
//            }
//        });
//        btnOn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                turnOn13();
//            }
//        });
//
//        btnOff.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                turnOff13();
//            }
//        });

/*        btnTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendRequestAndPrintResponse();
            }
        });*/
    }

/*    private void sendRequestAndPrintResponse() {
        req = Volley.newRequestQueue(this);
        String url = "http://192.168.1.101/13/off";
        strReq = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d(TAG, "onResponse: Responded");
                Log.i(TAG,String.format("Response: %s",response.toString()));
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d(TAG, "onErrorResponse: Responded");
                Log.i(TAG,String.format("Response: %s", error.toString()));

                if(error instanceof NoConnectionError) {
                    new AlertDialog.Builder(MainActivity.this).setMessage(
                            "Unable to connect to the server! Please ensure your internet is working!").show();
                }
            }
        });

        strReq.setTag(REQUESTTAG);
        req.add(strReq);
    }*/

/*    @Override
    protected void onStop() {
        super.onStop();
        if(req!=null) {
            req.cancelAll(REQUESTTAG);
        }
    }*/

    private void setViewIds() {
        Log.d(TAG, "setViewIds: Created");
        btn1 = findViewById(R.id.btnOk);
        btn2 = findViewById(R.id.btnCancel);
        //btnTest = findViewById(R.id.btnStart);
        editTxt1 = findViewById(R.id.txtPassword);
//        client = new OkHttpClient();
        //btnOn = findViewById(R.id.btnOn);
        //btnOff = findViewById(R.id.btnOff);
    }

    private void startWebSocket() {
        Request request = new Request.Builder()
                .url("wss://app.remoteme.org/arLite/~XFt2FmYgf3dxKTdZpb3CuCZJRTq4Z55FkNSJwQwFry1A64iEvchIs3WTKXezEFh4j/ws/v1/1001/")
                .build();

        EchoWebSocketListener listener = new EchoWebSocketListener();
        WebSocket ws = client.newWebSocket(request, listener);

        wsl = ws;

        client.dispatcher().executorService().shutdown();
    }

    private void turnOn13() {
        String postBody = "{\n" +
                "\"type\" : \"VariableChangeMessage\",\n" +
                "\"senderDeviceId\" :  1001,\n" +
                "\"ignoreReceivers\" : [ ],\n" +
                "\"states\" : [ \n" +
                "{\n" +
                "\"type\" : \"BOOLEAN\",\n" +
                "\"name\" : \"button1\",\n" +
                "\"data\" : true \n" +
                "}]\n" +
                "}";
        wsl.send(postBody);
    }
    private void turnOff13() {
        String postBody = "{\n" +
                "\"type\" : \"VariableChangeMessage\",\n" +
                "\"senderDeviceId\" :  1001,\n" +
                "\"ignoreReceivers\" : [ ],\n" +
                "\"states\" : [ \n" +
                "{\n" +
                "\"type\" : \"BOOLEAN\",\n" +
                "\"name\" : \"button1\",\n" +
                "\"data\" : false \n" +
                "}]\n" +
                "}";
        wsl.send(postBody);
    }

    private final class EchoWebSocketListener extends WebSocketListener {
        private static final int NORMAL_STATUS_CLOSURE = 1000;

        @Override
        public void onOpen(WebSocket webSocket, Response response) {

        }

        @Override
        public void onFailure(WebSocket webSocket, Throwable t, Response response) {

        }
    }

    private void openHomeActivity() {
        Log.d(TAG, "openHomeActivity: Clicked");
        Intent intent = new Intent(MainActivity.this, HomeActivity.class);
        startActivity(intent);
    }

    public void startService(View view) {
        Intent intent = new Intent(MainActivity.this, NewService.class);
        startService(intent);

        startAlarm(true, false);
    }

    private void startAlarm(boolean isNotification, boolean isOnTime) {
        AlarmManager manager = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
        Intent intent;
        PendingIntent pendingIntent = null;

        if(isNotification) {
            intent = new Intent(MainActivity.this, AlarmService.class);
            pendingIntent = pendingIntent.getBroadcast(MainActivity.this, 0, intent, 0);
        }

        manager.set(AlarmManager.RTC_WAKEUP, SystemClock.elapsedRealtime() + 3000, pendingIntent);

    }

    public void stopService(View view) {

        Intent intent = new Intent(MainActivity.this, NewService.class);
        stopService(intent);
    }

    @Override
    public void onBackPressed() {
        Log.d(TAG, "onBackPassed: Clicked");
        moveTaskToBack(true);
    }
}
