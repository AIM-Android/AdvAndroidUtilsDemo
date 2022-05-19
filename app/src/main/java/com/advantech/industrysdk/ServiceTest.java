package com.advantech.industrysdk;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import androidx.annotation.Nullable;

/**
 * @author yuboliu
 * @brief description
 * @date 2022-04-25
 */
public class ServiceTest extends Service {
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
//        AdvAndroidUtils.setLockMode();
    }
}
