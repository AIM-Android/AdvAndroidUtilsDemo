package com.advantech.advandroidutilsdemo;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class SecondActivity extends AppCompatActivity {

    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_others);
        getSupportActionBar().setTitle("SecondActivity");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        textView = findViewById(R.id.text);
        textView.setText("SecondActivity");
        findViewById(R.id.jump).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SecondActivity.this, ThirdActivity.class));
            }
        });

//        startLockTask();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                // 处理返回逻辑
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void startKiosk(View view) {
        startLockTask();
    }

    public void stopKiosk(View view) {
        stopLockTask();
    }

    public void jump() {
        startActivity(new Intent(SecondActivity.this, ThirdActivity.class));
    }
}