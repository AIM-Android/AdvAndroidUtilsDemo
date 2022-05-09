package com.advantech.advandroidutilsdemo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import java.util.Objects;

import static android.content.Context.MODE_PRIVATE;

/**
 * @author yuboliu
 * @brief description
 * @date 2022-05-05
 */
public class BootCompleteReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        if (Objects.equals(intent.getAction(), Intent.ACTION_BOOT_COMPLETED)){
            Log.d("BootCompleteReceiver", "onReceive: BootCompleteReceiver");
            SharePreferenceUtil sp = new SharePreferenceUtil(context, SharePreferenceUtil.KIOSK_STATUS, MODE_PRIVATE);
            if((boolean)sp.getSharedPreference("auto_start_when_boot", false)){
                Log.d("BootCompleteReceiver", "onReceive: auto start when boot is true, start main activity...");
                Intent mIntent = new Intent(context, MainActivity.class);
                mIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(mIntent);
            }
        }
    }

}
