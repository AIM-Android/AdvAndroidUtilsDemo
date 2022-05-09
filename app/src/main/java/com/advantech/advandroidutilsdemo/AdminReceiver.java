package com.advantech.advandroidutilsdemo;

import android.app.admin.DeviceAdminReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import androidx.annotation.NonNull;


public class AdminReceiver extends DeviceAdminReceiver {

    private static final String TAG = "AdminRecievePO";

    public void setDeviceOwnerAdb() {
        //shell组设置成功
        // adb shell dpm set-device-owner com.advantech.advandroidutils/com.advantech.advandroidutils.AdminReceiver
    }

    @Override
    public void onReceive(@NonNull Context context, @NonNull Intent intent) {

        super.onReceive(context, intent);
    }

    @Override
    public void onEnabled(@NonNull Context context, @NonNull Intent intent) {
        super.onEnabled(context, intent);

    }

    @Override
    public void onDisabled(@NonNull Context context, @NonNull Intent intent) {
        super.onDisabled(context, intent);
        Log.d("DeviceUtils", "onDisabled: I am not device owner app");
    }

}
