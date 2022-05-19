package com.advantech.industrysdk;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class ThirdActivity extends AppCompatActivity {

//    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_others);
        getSupportActionBar().setTitle("ThirdActivity");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

//        textView = findViewById(R.id.text);
//        textView.setText("ThirdActivity");
        findViewById(R.id.jump).setVisibility(View.INVISIBLE);
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

    public void jump(View view) {

    }

}