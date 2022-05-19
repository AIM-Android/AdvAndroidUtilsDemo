package com.advantech.industrysdk;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.advantech.advandroidutils.AdvAndroidUtils;
import com.advantech.advandroidutils.excption.ActivityNotForegroundException;
import com.advantech.advandroidutils.excption.NotSystemAppException;
import com.advantech.advandroidutils.excption.PropertiesNotFoundException;


public class KioskActivity extends AppCompatActivity implements View.OnClickListener, CompoundButton.OnCheckedChangeListener {

    public static final String TAG = "MainActivity";

    TextView statusTv;
    Button startKioskBt, stopKioskBt;
    CheckBox autoStartCb;

    SharePreferenceUtil sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kiosk);

        getSupportActionBar().setTitle("KioskActivity");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        statusTv = findViewById(R.id.kiosk_status);
        startKioskBt = findViewById(R.id.start_kiosk);
        stopKioskBt = findViewById(R.id.stop_kiosk);
        autoStartCb = findViewById(R.id.auto_start_cb);
//        findViewById(R.id.jump).setOnClickListener(this);
        startKioskBt.setOnClickListener(this);
        stopKioskBt.setOnClickListener(this);

        startKioskBt.setBackgroundColor(getResources().getColor(R.color.Blue700));
        stopKioskBt.setBackgroundColor(getResources().getColor(R.color.Blue700));
        startKioskBt.setTextColor(getResources().getColor(R.color.white));
        stopKioskBt.setTextColor(getResources().getColor(R.color.white));

        sp = new SharePreferenceUtil(this, SharePreferenceUtil.KIOSK_STATUS, MODE_PRIVATE);
        if((boolean)sp.getSharedPreference("auto_start_when_boot", false)){
            autoStartCb.setChecked(true);
        }else {
            autoStartCb.setChecked(false);
        }
        if((boolean)sp.getSharedPreference("kiosk_status", false)){
            enterKiosk();
        }else {
            startKioskBt.setBackgroundColor(getResources().getColor(R.color.Blue700));
            startKioskBt.setTextColor(getResources().getColor(R.color.white));
            startKioskBt.setEnabled(true);
            stopKioskBt.setBackgroundColor(getResources().getColor(R.color.Grey200));
            stopKioskBt.setTextColor(getResources().getColor(R.color.Blue900));
            stopKioskBt.setEnabled(false);
            statusTv.setText("Not In Kiosk Mode");
        }

        autoStartCb.setOnCheckedChangeListener(this);
    }

    @Override
    protected void onDestroy() {
        Log.d(TAG, "onDestroy: ");
        //when reboot or shutdown, exit kiosk mode.
        if((boolean)sp.getSharedPreference("kiosk_status", false)){
            exitKiosk();
        }
        super.onDestroy();
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

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.start_kiosk:
                enterKiosk();
                break;
            case R.id.stop_kiosk:
                exitKiosk();
                break;
//            case R.id.jump:
//                startActivity(new Intent(KioskActivity.this, AppManagerActivity.class));
//                break;
        }
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        sp.put("auto_start_when_boot", isChecked);
        if((boolean)sp.getSharedPreference("auto_start_when_boot", false) == isChecked){
            Toast.makeText(this, "success!", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(this, "failed!", Toast.LENGTH_SHORT).show();
            autoStartCb.setChecked(false);
        }
    }


    private void enterKiosk(){
        Log.d(TAG, "enterKiosk: call");
        startKioskBt.setBackgroundColor(getResources(). getColor(R.color.Grey200));
        startKioskBt.setTextColor(getResources().getColor(R.color.Blue900));
        startKioskBt.setEnabled(false);
        stopKioskBt.setBackgroundColor(getResources().getColor(R.color.Grey200));
        stopKioskBt.setTextColor(getResources().getColor(R.color.Blue900));
        stopKioskBt.setEnabled(false);

        boolean inKiosk = false;
        try {
            inKiosk = AdvAndroidUtils.setKiosk(this);
        } catch (NotSystemAppException | ActivityNotForegroundException | PropertiesNotFoundException e) {
            e.printStackTrace();
            Log.d(TAG, "enterKiosk: exception = "+e.getClass().getSimpleName()+" : "+e.getMessage());
            //TODO show a toast
            Toast.makeText(this, "Enter kiosk failed:"+e.getMessage(), Toast.LENGTH_LONG).show();
        } finally {
            if(!inKiosk){
                Log.d(TAG, "enterKiosk: failed");
                startKioskBt.setBackgroundColor(getResources().getColor(R.color.Blue700));
                startKioskBt.setTextColor(getResources().getColor(R.color.white));
                startKioskBt.setEnabled(true);
                statusTv.setText("Not In Kiosk Mode");

                sp.put("kiosk_status", false);
            }else {
                Log.d(TAG, "enterKiosk: success");
                stopKioskBt.setBackgroundColor(getResources().getColor(R.color.Blue700));
                stopKioskBt.setTextColor(getResources().getColor(R.color.white));
                stopKioskBt.setEnabled(true);
                statusTv.setText("Entered Kiosk Mode");

                sp.put("kiosk_status", true);
            }
        }
    }

    private void exitKiosk(){
        Log.d(TAG, "exitKiosk: call");

        startKioskBt.setBackgroundColor(getResources().getColor(R.color.Grey200));
        startKioskBt.setTextColor(getResources().getColor(R.color.Blue900));
        startKioskBt.setEnabled(false);
        stopKioskBt.setBackgroundColor(getResources().getColor(R.color.Grey200));
        stopKioskBt.setTextColor(getResources().getColor(R.color.Blue900));
        stopKioskBt.setEnabled(false);

        AdvAndroidUtils.cancelKiosk(this);

        startKioskBt.setBackgroundColor(getResources().getColor(R.color.Blue700));
        startKioskBt.setTextColor(getResources().getColor(R.color.white));
        startKioskBt.setEnabled(true);
        statusTv.setText("Not In Kiosk Mode");

        sp.put("kiosk_status", false);
    }

}