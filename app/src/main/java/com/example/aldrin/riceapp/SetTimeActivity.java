package com.example.aldrin.riceapp;

import android.app.AlertDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.WebSocket;
import okhttp3.WebSocketListener;
import okio.ByteString;

public class SetTimeActivity extends AppCompatActivity implements TimePickerDialog.OnTimeSetListener {

    private static final String TAG = SetTimeActivity.class.getName();
    private Button btnSetTime, btnCancel, btnOk;
    private TextView lblTime;
    private String _retVal;
    private Bundle bundle;
    private AlertDialog.Builder builder1;
    private AlertDialog alertDialog;
    private OkHttpClient client;
    private WebSocket wsl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_time);
        Log.d(TAG, "onCreate: SetTimeActivity");
        setViewIds();

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
                        startService();
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

    private void setViewIds() {
        Log.d(TAG, "setViewIds: Created");
        btnSetTime = findViewById(R.id.btnTimePicker);
        btnOk = findViewById(R.id.btnOkTime);
        btnCancel = findViewById(R.id.btnCancelTime);
        lblTime = findViewById(R.id.lblCookSetTime);
        bundle = getIntent().getExtras();
        _retVal = bundle.getString("key");
        builder1 = new AlertDialog.Builder(SetTimeActivity.this);
        client = new OkHttpClient();

        Request request = new Request.Builder()
                .url("wss://app.remoteme.org/arLite/~XFt2FmYgf3dxKTdZpb3CuCZJRTq4Z55FkNSJwQwFry1A64iEvchIs3WTKXezEFh4j/ws/v1/1001/")
                .build();

        EchoWebSocketListener listener = new EchoWebSocketListener();
        WebSocket ws = client.newWebSocket(request, listener);

        wsl = ws;
        client.dispatcher().executorService().shutdown();
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

    private void openHome() {
        Log.d(TAG, "openHome: Used");
        Intent intent = new Intent(SetTimeActivity.this, HomeActivity.class);
        Bundle bundle = new Bundle();

        bundle.putString("time", lblTime.getText().toString());
        intent.putExtras(bundle);

        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }


    private final class EchoWebSocketListener extends WebSocketListener {
        private static final int NORMAL_STATUS_CLOSURE = 1000;

        @Override
        public void onOpen(WebSocket webSocket, Response response) {
//            wsl.send("{  \"type\" : \"PingMessage\"}".getBytes().toString());
            wsl.send("{  \"type\" : \"PingMessage\", \"deviceId\" : 1001 }");
            wsl.send("{\n" +
                    "\"type\" : \"VariableObserveMessage\",\n" +
                    "\"deviceId\" : 1001,\n" +
                    "\"variables\" : [ {\n" +
                    "\"name\" : \"button1\",\n" +
                    "\"type\" : \"BOOLEAN\"\n" +
                    "},{\n" +
                    "\"name\" : \"button2\",\n" +
                    "\"type\" : \"BOOLEAN\"\n" +
                    " },{\n" +
                    "\"name\" : \"button3\",\n" +
                    "\"type\" : \"BOOLEAN\"\n" +
                    "},{\n" +
                    "\"name\" : \"relay1\",\n" +
                    "\"type\" : \"BOOLEAN\"\n" +
                    "},{\n" +
                    "\"name\" : \"relay2\",\n" +
                    "\"type\" : \"BOOLEAN\"\n" +
                    "}]\n" +
                    "}");
//            webSocket.close(NORMAL_STATUS_CLOSURE, "Goodbye");

        }

        @Override
        public void onFailure(WebSocket webSocket, Throwable t, Response response) {
        }

        @Override
        public void onMessage(WebSocket webSocket, String text) {
//            Toast.makeText(SetTimeActivity.this, text, Toast.LENGTH_LONG).show();
            output("Receiving: " + text);
        }

        @Override
        public void onMessage(WebSocket webSocket, ByteString bytes) {
            output("Receiving: " + bytes.hex());
        }
    }

    private void output(final String txt) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                lblTime.setText(txt);
            }
        });
    }
    private void returnCups() {
        Log.d(TAG, "returnCups: Clicked");
        Intent intent = new Intent(SetTimeActivity.this, CupsActivity.class);

        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }
}
