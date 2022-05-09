package com.advantech.advandroidutilsdemo;//package com.advantech.advandroidutils;
//
//import android.app.PendingIntent;
//import android.content.Context;
//import android.content.Intent;
//import android.content.pm.PackageInfo;
//import android.content.pm.PackageInstaller;
//import android.content.pm.PackageManager;
//import android.os.Build;
//
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.IOException;
//import java.io.InputStream;
//import java.io.OutputStream;
//
///**
// * Created by Fengchao.dai on 2020/09/15.
// */
//public class AppManager {
//    private int mSessionId = -1;
//    private PackageInstaller.SessionCallback mSessionCallback;
//    private Context mContext;
//    private static AppManager instance = null;
//
//    private AppManager(Context context) {
//        mContext = context;
//        mSessionCallback = new InstallSessionCallback();
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//            context.getPackageManager().getPackageInstaller().registerSessionCallback(mSessionCallback);
//        }
//    }
//
//    public static AppManager getInstance(Context context) {
//        if (instance == null) {
//            synchronized (AppManager.class) {
//                if (instance == null) {
//                    instance = new AppManager(context);
//                }
//            }
//        }
//        return instance;
//    }
//
//    /**
//     * 根据包名卸载应用
//     *
//     * @param packageName
//     */
//    public void uninstall(String packageName) {
//        Intent broadcastIntent = new Intent(mContext, AppUnInstallResultReceiver.class);
//        PendingIntent pendingIntent = PendingIntent.getBroadcast(mContext, 1,
//                broadcastIntent, PendingIntent.FLAG_UPDATE_CURRENT);
//        PackageInstaller packageInstaller = null;
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//            packageInstaller = mContext.getPackageManager().getPackageInstaller();
//            packageInstaller.uninstall(packageName, pendingIntent.getIntentSender());
//        }
//    }
//
//    /**
//     * 先卸载再安装
//     */
//    public void forceReInstallApp(String packageName, String apkFilePath, String tastType, String tastName, String pkgName, String version) {
//        Intent broadcastIntent = new Intent(mContext, AppForceReInstall.class);
//        broadcastIntent.putExtra("apkFilePath", apkFilePath);
//        broadcastIntent.putExtra("tastType", tastType);
//        broadcastIntent.putExtra("tastName", tastName);
//        broadcastIntent.putExtra("pkgName", pkgName);
//        broadcastIntent.putExtra("version", version);
//        PendingIntent pendingIntent = PendingIntent.getBroadcast(mContext, 1,
//                broadcastIntent, PendingIntent.FLAG_UPDATE_CURRENT);
//        PackageInstaller packageInstaller = null;
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//            packageInstaller = mContext.getPackageManager().getPackageInstaller();
//            packageInstaller.uninstall(packageName, pendingIntent.getIntentSender());
//        }
//    }
//
//    /**
//     * 适配android9的安装方法。
//     * 全部替换安装
//     */
//    public void installApp(String apkFilePath, String tastType, String tastName, String pkgName, String version) {
//        File apkFile = new File(apkFilePath);
//        if (!apkFile.exists()) {
////            Logger.i(apkFilePath + "file not exist!");
//            return;
//        }
//
//        PackageInfo packageInfo = mContext.getPackageManager().getPackageArchiveInfo(apkFilePath, PackageManager.GET_ACTIVITIES | PackageManager.GET_SERVICES);
//        if (packageInfo != null) {
//            String packageName = packageInfo.packageName;
//            int versionCode = packageInfo.versionCode;
//            String versionName = packageInfo.versionName;
//            //Log.d("ApkActivity", "packageName=" + packageName + ", versionCode=" + versionCode + ", versionName=" + versionName);
//        }
//
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//            PackageInstaller packageInstaller = mContext.getPackageManager().getPackageInstaller();
//            PackageInstaller.SessionParams sessionParams = null;
//            sessionParams = new PackageInstaller.SessionParams(PackageInstaller.SessionParams.MODE_FULL_INSTALL);
//            sessionParams.setSize(apkFile.length());
//
//            try {
//                mSessionId = packageInstaller.createSession(sessionParams);
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//            if (mSessionId != -1) {
//                boolean copySuccess = onTransfesApkFile(apkFilePath);
//                if (copySuccess) {
//                    execInstallAPP(apkFilePath, tastType, tastName, pkgName, version);
//                }
//            }
//
//        }
//
//    }
//
//    /**
//     * 通过文件流传输apk
//     *
//     * @param apkFilePath
//     * @return
//     */
//    private boolean onTransfesApkFile(String apkFilePath) {
//        InputStream in = null;
//        OutputStream out = null;
//        PackageInstaller.Session session = null;
//        boolean success = false;
//        try {
//            File apkFile = new File(apkFilePath);
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//                session = mContext.getPackageManager().getPackageInstaller().openSession(mSessionId);
//                out = session.openWrite("base.apk", 0, apkFile.length());
//                in = new FileInputStream(apkFile);
//                int total = 0, c;
//                byte[] buffer = new byte[1024 * 1024];
//                while ((c = in.read(buffer)) != -1) {
//                    total += c;
//                    out.write(buffer, 0, c);
//                }
//                session.fsync(out);
//                success = true;
//            }
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        } finally {
//            if (null != session) {
//                session.close();
//            }
//            try {
//                if (null != out) {
//                    out.close();
//                }
//                if (null != in) {
//                    in.close();
//                }
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//        return success;
//    }
//
//    /**
//     * 执行安装并通知安装结果
//     */
//    private void execInstallAPP(String apkFilePath, String tastType, String tastName, String pkgName, String version) {
//        PackageInstaller.Session session = null;
//        try {
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//                session = mContext.getPackageManager().getPackageInstaller().openSession(mSessionId);
//            }
//            Intent intent = new Intent(mContext, AppInstallResultReceiver.class);
//            intent.putExtra("apkFilePath", apkFilePath);
//            intent.putExtra("tastType", tastType);
//            intent.putExtra("tastName", tastName);
//            intent.putExtra("pkgName", pkgName);
//            intent.putExtra("version", version);
//            PendingIntent pendingIntent = PendingIntent.getBroadcast(mContext,
//                    +1, intent, PendingIntent.FLAG_UPDATE_CURRENT);
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//                session.commit(pendingIntent.getIntentSender());
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        } finally {
//            if (null != session) {
//                session.close();
//            }
//        }
//    }
//
////    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
//    private class InstallSessionCallback extends PackageInstaller.SessionCallback {
//        @Override
//        public void onCreated(int sessionId) {
//            // empty
//        }
//
//        @Override
//        public void onBadgingChanged(int sessionId) {
//            // empty
//        }
//
//        @Override
//        public void onActiveChanged(int sessionId, boolean active) {
//            // empty
//        }
//
//        @Override
//        public void onProgressChanged(int sessionId, float progress) {
//            if (sessionId == mSessionId) {
//                int progres = (int) (Integer.MAX_VALUE * progress);
//            }
//        }
//
//        @Override
//        public void onFinished(int sessionId, boolean success) {
//            // empty, finish is handled by AppInstallResultReceiver
//            if (mSessionId == sessionId) {
//                if (success) {
////                    Logger.i( "onFinished() install succeed");
//                } else {
////                    Logger.i( "onFinished() install failed");
//                }
//
//            }
//        }
//    }
//
//}