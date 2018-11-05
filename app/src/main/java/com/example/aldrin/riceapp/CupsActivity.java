package com.example.aldrin.riceapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class CupsActivity extends AppCompatActivity {
    private static final String TAG = CupsActivity.class.getName();
    private Button btn1, btn2, btn3, btn4, btn5, btn6, btnCancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cups);
        Log.d(TAG, "onCreate: CupsActivity");
        setViewIds();

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openSetTimeActivity(btn1.getText().toString());
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openSetTimeActivity(btn2.getText().toString());
            }
        });

        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openSetTimeActivity(btn3.getText().toString());
            }
        });

        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openSetTimeActivity(btn4.getText().toString());
            }
        });

        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openSetTimeActivity(btn5.getText().toString());
            }
        });

        btn6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openSetTimeActivity(btn6.getText().toString());
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                previousActivity();
            }
        });
    }

    private void setViewIds(){
        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);
        btn3 = findViewById(R.id.btn3);
        btn4 = findViewById(R.id.btn4);
        btn5 = findViewById(R.id.btn5);
        btn6 = findViewById(R.id.btn6);
        btnCancel = findViewById(R.id.btnCancel2);
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
