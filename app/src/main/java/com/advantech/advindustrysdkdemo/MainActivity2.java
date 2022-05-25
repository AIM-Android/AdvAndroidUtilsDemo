package com.advantech.advindustrysdkdemo;

import android.app.admin.DevicePolicyManager;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkCapabilities;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.advantech.advindustrysdk.AdvIndustrySDK;
import com.advantech.advindustrysdk.excption.ActivityNotForegroundException;
import com.advantech.advindustrysdk.excption.NotSystemAppException;
import com.advantech.advindustrysdk.excption.PropertiesNotFoundException;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;

public class MainActivity2 extends AppCompatActivity implements View.OnClickListener {

    static String TAG = "MainActivity";

    DevicePolicyManager devicePolicyManager;
    ComponentName admin;

//    AdvIndustrySDK advAndroidUtils;

    static MainActivity2 activity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        findViewById(R.id.button).setOnClickListener(this);
        findViewById(R.id.button2).setOnClickListener(this);
        findViewById(R.id.button3).setOnClickListener(this);
        findViewById(R.id.button4).setOnClickListener(this);
        findViewById(R.id.button5).setOnClickListener(this);
        findViewById(R.id.button6).setOnClickListener(this);
        findViewById(R.id.button7).setOnClickListener(this);
        findViewById(R.id.button8).setOnClickListener(this);
        findViewById(R.id.button9).setOnClickListener(this);
        findViewById(R.id.button10).setOnClickListener(this);
        findViewById(R.id.button_get1).setOnClickListener(this);
        findViewById(R.id.button_get2).setOnClickListener(this);

        devicePolicyManager = (DevicePolicyManager) getSystemService(Context.DEVICE_POLICY_SERVICE);
        admin = new ComponentName("com.advantech.advindustrysdk", "com.advantech.advindustrysdk.AdminReceiver");

//        advAndroidUtils = new AdvIndustrySDK(this);
//        AdvIndustrySDK.setLockMode(this);

//        IntentFilter filter = new IntentFilter(Intent.ACTION_CONFIGURATION_CHANGED);
////            filter.addAction(Intent.ACTION_USER_SWITCHED);
//        filter.addAction(Intent.ACTION_SCREEN_OFF);
//        registerReceiver(mReceiver, filter);

//        getWindow().getDecorView().requestLayout();

        Log.d(TAG, "onCreate: ");
        activity = this;
        IntentFilter filter = new IntentFilter(Intent.ACTION_CONFIGURATION_CHANGED);
//            filter.addAction(Intent.ACTION_USER_SWITCHED);
        filter.addAction(Intent.ACTION_SCREEN_OFF);
        registerReceiver(lockTaskReceiver, filter);
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                try {
//                    Thread.sleep(5000);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }finally {
//                    if(activity != null){
//                        Log.d(TAG, "run: setKiosk");
//                        try {
//                            boolean b = AdvKioskUtils.setKiosk(activity);
//                            Log.d(TAG, "run: setKiosk = "+b);
//                        }catch (ActivityNotForegroundException e){
//                            Log.d(TAG, "run: setKiosk ActivityNotForegroundException = "+e.getMessage());
//                        } catch (PropertiesNotFoundException e) {
//                            Log.d(TAG, "run: setKiosk PropertiesNotFoundException = "+e.getMessage());
//                        } catch (NotSystemAppException e) {
//                            Log.d(TAG, "run: setKiosk NotSystemAppException = "+e.getMessage());
//                        }
//                    }
//                }
//            }
//        }).start();
    }

    LockTaskReceiver lockTaskReceiver = new LockTaskReceiver();

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart: ");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume: ");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause: ");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop: ");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        activity = null;
        Log.d(TAG, "onDestroy: ");
//        unregisterReceiver(mReceiver);
        unregisterReceiver(lockTaskReceiver);
    }

    private final BroadcastReceiver mReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals(Intent.ACTION_CONFIGURATION_CHANGED)){
                Log.d(TAG, "onReceive: ACTION_CONFIGURATION_CHANGED");
            }else if(intent.getAction().equals(Intent.ACTION_SCREEN_OFF)){
                Log.d(TAG, "onReceive: ACTION_SCREEN_OFF");
            }else {
                Log.d(TAG, "onReceive: others = "+intent.getAction());
            }
        }
    };

    void tt(){
        String ip = null;
        ConnectivityManager conMann = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        if (conMann != null) {
            NetworkInfo networkInfo = conMann.getActiveNetworkInfo();
            if (networkInfo != null && networkInfo.isAvailable()) {
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
                    NetworkCapabilities capabilities = conMann.getNetworkCapabilities(conMann.getActiveNetwork());
                    if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)) {
//                        Logger.d("TYPE_WIFI");
//                        ip = getIpAddress("wlan0");
                    }
                    if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET)) {
//                        Logger.d("TYPE_ETHERNET");
//                        ip = getIpAddress("eth0");
//                        if (ip == null || ip.isEmpty()) {
//                            ip = getIpAddress("eth1");
//                        }
                    }
                    if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_VPN)) {
//                        Logger.d("TYPE_VPN");
//                        ip = getIpAddress("tun0");
                    }
                } else {
                    switch (networkInfo.getType()) {
                        case ConnectivityManager.TYPE_WIFI://wifi
//                            Logger.d("TYPE_WIFI");
//                            ip = getIpAddress("wlan0");
                            break;
                        case ConnectivityManager.TYPE_ETHERNET://Ethernet
//                            Logger.d("TYPE_ETHERNET");
//                            ip = getIpAddress("eth0");
//                            if (ip == null || ip.isEmpty()) {
//                                ip = getIpAddress("eth1");
//                            }
                            break;
                        case ConnectivityManager.TYPE_VPN://VPN
//                            Logger.d("TYPE_VPN");
//                            ip = getIpAddress("tun0");
                            break;
                    }
                }
            }
        }
//        if (ip == null || ip.isEmpty())
//            return "0.0.0.0";
    }

    public void getEth(View view){
        new Thread(new Runnable() {
            @Override
            public void run() {
                ArrayList<String> availableInterface = new ArrayList<>();
                String [] interfaces = null;
                try {
                    Enumeration nis = NetworkInterface.getNetworkInterfaces();
                    if(nis == null){
                        Log.d(TAG, "getEth: getNetworkInterfaces is null");
                        return;
                    }
                    InetAddress ia = null;
                    while (nis.hasMoreElements()) {
                        NetworkInterface ni = (NetworkInterface) nis.nextElement();
                        Log.d(TAG, "getEth: --------------------- NetworkInterface = "+ni.toString());
                        Enumeration<InetAddress> ias = ni.getInetAddresses();
                        while (ias.hasMoreElements()) {
                            ia = ias.nextElement();
                            Log.d(TAG, "getEth: InetAddress.class = "+ia.getClass().getSimpleName());
                            Log.d(TAG, "getEth: InetAddress.getHostAddress = "+ia.getHostAddress());
                            Log.d(TAG, "getEth: InetAddress.getAddress = "+ Arrays.toString(ia.getAddress()));
                            Log.d(TAG, "getEth: InetAddress.getCanonicalHostName = "+ia.getCanonicalHostName());
                            Log.d(TAG, "getEth: InetAddress.toString = "+ia.toString());

//                    if (ia instanceof Inet6Address) {
//                        continue;// skip ipv6
//                    }
//
//                    String ip = ia.getHostAddress();
//                    Log.d(TAG,"getAllNetInterface,available interface:"+ni.getName()+",address:"+ip);
//                    // 过滤掉127段的ip地址
//                    if (!"127.0.0.1".equals(ip)) {
//                        availableInterface.add(ni.getName());
//                    }
                        }
                    }
                } catch (SocketException e) {
                    e.printStackTrace();
                    Log.d(TAG, "getEth: SocketException = "+e.getMessage());
                }
            }
        }).start();


//        Log.d(TAG,"all interface:"+availableInterface.toString());
//        int size = availableInterface.size();
//        if (size > 0) {
//            interfaces = new String[size];
//            for(int i = 0; i < size; i++) {
//                interfaces[i] = availableInterface.get(i);
//            }
//        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button6:
//                advAndroidUtils.setLockMode(this);
                try {
                    boolean b = AdvIndustrySDK.setKiosk(activity);
                    Log.d(TAG, "onClick: setKiosk = "+b);
                    Toast.makeText(this, "setKiosk:"+b, Toast.LENGTH_SHORT).show();

                }catch (ActivityNotForegroundException e){
                    Log.d(TAG, "onClick: setKiosk ActivityNotForegroundException = "+e.getMessage());
                } catch (PropertiesNotFoundException e) {
                    Log.d(TAG, "onClick: setKiosk PropertiesNotFoundException = "+e.getMessage());
                } catch (NotSystemAppException e) {
                    Log.d(TAG, "onClick: setKiosk NotSystemAppException = "+e.getMessage());
                }
                break;
            case R.id.button7:
//                advAndroidUtils.cancelLockMode(this);
//                try {
                    AdvIndustrySDK.cancelKiosk(this);
//                } catch (NotSystemAppException e) {
//                    e.printStackTrace();
//                }
                Log.d(TAG, "onClick: cancel kiosk");
                break;

            case R.id.button:
                try {
                    boolean b = AdvIndustrySDK.hideStatusNavBar(this);
                    Log.d(TAG, "onClick: hideStatusNavBar = "+b);
                    Toast.makeText(this, "hideStatusNavBar:"+b, Toast.LENGTH_SHORT).show();
                } catch (PropertiesNotFoundException e) {
                    e.printStackTrace();
                } catch (NotSystemAppException e) {
                    e.printStackTrace();
                }
                break;
            case R.id.button2:
//                try {
                boolean b = AdvIndustrySDK.showStatusNavBar(this);
                Log.d(TAG, "onClick: showStatusNavBar = "+b);
                Toast.makeText(this, "showStatusNavBar:"+b, Toast.LENGTH_SHORT).show();
//                    AdvKioskUtils.showStatusNavBarImmersive();
//                } catch (NotSystemAppException e) {
//                    e.printStackTrace();
//                }
                break;

            case R.id.button9:
                AdvIndustrySDK.hideStatusNavBarImmersive();
                break;
            case R.id.button10:
                AdvIndustrySDK.cancelHideStatusNavBarImmersive();
                break;

            case R.id.button3:
                String[] pkgs = new String[]{"com.example.kiosk", "com.advantech.httpdemo", "com.adv.client", "com.advantech.advindustrysdk"};
                devicePolicyManager.setLockTaskPackages(admin, pkgs);
                break;
            case R.id.button4:
                devicePolicyManager.setLockTaskPackages(admin, new String[0]);
                break;
            case R.id.button8:
//                Activity currentForwardActivity = AdvIndustrySDK.getCurrentForwardActivity();
//                Toast.makeText(MainActivity.this, "forward activity:"+(currentForwardActivity==null?"null":currentForwardActivity.getComponentName()), Toast.LENGTH_SHORT).show();
                break;
            case R.id.button5:
                removeDeviceOwner();
                break;

            case R.id.button_get1:
                String fullScreenStatus = AdvIndustrySDK.getFullScreenStatus(this);
                Log.d(TAG, "onClick: getFullScreenStatus = "+fullScreenStatus);
                Toast.makeText(this, "getFullScreenStatus:"+fullScreenStatus, Toast.LENGTH_SHORT).show();
                break;
            case R.id.button_get2:
                String fullScreenStatus2 = AdvIndustrySDK.getFullScreenStatusImmersive(this);
                Log.d(TAG, "onClick: getFullScreenStatusImmersive = "+fullScreenStatus2);
                Toast.makeText(this, "getFullScreenStatusImmersive:"+fullScreenStatus2, Toast.LENGTH_SHORT).show();

                break;
        }
    }
    /**
     * 移除Device Owner权限（相应配置随之失效）
     */
    private void removeDeviceOwner(){
        //TODO 检查一下状态是不是立刻更新！

        if(devicePolicyManager.isDeviceOwnerApp("com.advantech.advindustrysdk")){
            Log.d(TAG, "removeDeviceOwner: securitymdm is device owner now");
        }else {
            Log.d(TAG, "removeDeviceOwner: securitymdm is not device owner now");
            Toast.makeText(this, "app is't device owner", Toast.LENGTH_SHORT).show();
            return;
        }

        try {
            Class<? extends DevicePolicyManager> aClass = devicePolicyManager.getClass();
            Method clearDeviceOwnerApp = aClass.getMethod("clearDeviceOwnerApp", String.class);
            clearDeviceOwnerApp.invoke(devicePolicyManager, "com.advantech.advindustrysdk");
            Log.d(TAG, "removeDeviceOwner: end ");
            Toast.makeText(this, "already removed", Toast.LENGTH_SHORT).show();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
            Log.d(TAG, "removeDeviceOwner: NoSuchMethodException "+e.getMessage());
            Toast.makeText(this, "remove failed:"+e.getMessage(), Toast.LENGTH_SHORT).show();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            Log.d(TAG, "removeDeviceOwner: IllegalAccessException "+e.getMessage());
            Toast.makeText(this, "remove failed:"+e.getMessage(), Toast.LENGTH_SHORT).show();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
            Log.d(TAG, "removeDeviceOwner: InvocationTargetException "+e.getMessage());
            Toast.makeText(this, "remove failed:"+e.getMessage(), Toast.LENGTH_SHORT).show();
        }finally {

            if(!devicePolicyManager.isDeviceOwnerApp("com.advantech.advindustrysdk")){
                Log.d(TAG, "removeDeviceOwner: securitymdm is not device owner!");
            }else {
                Log.d(TAG, "removeDeviceOwner: securitymdm is device owner still!");
            }
            Log.d(TAG, "removeDeviceOwner: let's check user ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");

            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {

            }finally {
                if(!devicePolicyManager.isDeviceOwnerApp("com.advantech.advindustrysdk")){
                    Log.d(TAG, "removeDeviceOwner: securitymdm is not device owner!");
                }else {
                    Log.d(TAG, "removeDeviceOwner: securitymdm is device owner still!");
                }
                Log.d(TAG, "removeDeviceOwner: let's check user ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
            }
        }
    }


    public static class LockTaskReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {

//        if(intent.getAction().equals("adv.utils.locktask.receiver")){
            Log.d(MainActivity.TAG, "onReceive: LockTaskReceiver!");

//            AdvIndustrySDK advAndroidUtils = new AdvIndustrySDK(context);
//            advAndroidUtils.setLockMode(null);
            if(activity != null){
                Log.d(MainActivity.TAG, "onReceive: setKiosk");
                try {
                    AdvIndustrySDK.setKiosk(activity);
                }catch (Exception e){
                    Log.d(TAG, "onReceive: Exception = "+e.getMessage());
                }
            }
//        }
        }
    }
}