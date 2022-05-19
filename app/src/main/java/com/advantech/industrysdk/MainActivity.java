package com.advantech.industrysdk;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    public static final String TAG = "MainActivity";

    Button startKioskBt, stopKioskBt;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startKioskBt = findViewById(R.id.kiosk_page);
        stopKioskBt = findViewById(R.id.app_page);
        startKioskBt.setOnClickListener(this);
        stopKioskBt.setOnClickListener(this);

        startKioskBt.setBackgroundColor(getResources().getColor(R.color.Blue700));
        stopKioskBt.setBackgroundColor(getResources().getColor(R.color.Blue700));
        startKioskBt.setTextColor(getResources().getColor(R.color.white));
        stopKioskBt.setTextColor(getResources().getColor(R.color.white));

    }

    @Override
    protected void onDestroy() {
        Log.d(TAG, "onDestroy: ");
        super.onDestroy();
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.kiosk_page:
                startActivity(new Intent(MainActivity.this, KioskActivity.class));
                break;
            case R.id.app_page:
                startActivity(new Intent(MainActivity.this, AppManagerActivity.class));
                break;
        }
    }

}