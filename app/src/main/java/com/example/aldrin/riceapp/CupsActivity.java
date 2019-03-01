package com.example.aldrin.riceapp;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

public class CupsActivity extends AppCompatActivity {
    private static final String TAG = CupsActivity.class.getName();
    private Button btn1, btn2, btn3, btn4, btn5, btn6, btnCancel;
    private RelativeLayout relativeLayout;
    private ConnectionDetector connectionDetector;
    private AlertDialog.Builder builder1;
    private AlertDialog alertDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cups);
        Log.d(TAG, "onCreate: CupsActivity");
        setViewIds();

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                relativeLayout.setVisibility(View.VISIBLE);
                isConnected(btn1.getText().toString());
//                openSetTimeActivity(btn1.getText().toString());
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                relativeLayout.setVisibility(View.VISIBLE);
                isConnected(btn2.getText().toString());
//                openSetTimeActivity(btn2.getText().toString());
            }
        });

        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                relativeLayout.setVisibility(View.VISIBLE);
                isConnected(btn3.getText().toString());
//                openSetTimeActivity(btn3.getText().toString());
            }
        });

        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                relativeLayout.setVisibility(View.VISIBLE);
                isConnected(btn4.getText().toString());
//                openSetTimeActivity(btn4.getText().toString());
            }
        });

        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                relativeLayout.setVisibility(View.VISIBLE);
                isConnected(btn5.getText().toString());
//                openSetTimeActivity(btn5.getText().toString());
            }
        });

//        btn6.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                relativeLayout.setVisibility(View.VISIBLE);
//                isConnected(btn6.getText().toString());
//                openSetTimeActivity(btn6.getText().toString());
//            }
//        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                previousActivity();
            }
        });
    }

    private void isConnected(String btn) {
        final String btns = btn;
        connectionDetector = new ConnectionDetector(CupsActivity.this);
//        relativeLayout.setVisibility(View.VISIBLE);
        if(!connectionDetector.isConnected()) {
//            relativeLayout.setVisibility(View.GONE);
            builder1 = new AlertDialog.Builder(CupsActivity.this);
            builder1.setMessage("No connection found!");
            builder1.setCancelable(false);

            builder1.setNeutralButton("Try again", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    isConnected(btns);
                }
            });

            alertDialog = builder1.create();
            alertDialog.setTitle("Alert");
            alertDialog.setIcon(R.drawable.icon);
            alertDialog.show();
        } else {
//            relativeLayout.setVisibility(View.GONE);
            openSetTimeActivity(btns);
        }
    }

    private void setViewIds(){
        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);
        btn3 = findViewById(R.id.btn3);
        btn4 = findViewById(R.id.btn4);
        btn5 = findViewById(R.id.btn5);
//        btn6 = findViewById(R.id.btn6);
        btnCancel = findViewById(R.id.btnCancel2);
//        relativeLayout = findViewById(R.id.loadingPanel1);
//        relativeLayout.setVisibility(View.GONE);
    }

    private void openSetTimeActivity(String str) {
        Intent intent = new Intent(CupsActivity.this, SetTimeActivity.class);
        Bundle bundle = new Bundle();

        bundle.putString("key", str);
        intent.putExtras(bundle);

        startActivity(intent);
    }

    private void previousActivity() {
        Intent intent = new Intent(CupsActivity.this, HomeActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }
}
