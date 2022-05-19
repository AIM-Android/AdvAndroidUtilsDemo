# AdvAndroidUtils

AdvAndroidUtils is an android sdk provided by Advantech. **System Permissions API of Android.**

### **Author:**

​		YuBo.Liu

### **Version Update Info:**

| Version | Date       | Description            |
| ------- | ---------- | ---------------------- |
| 1.1.0   | 2022-05-19 | Added app manager API. |
| 1.0.0   | 2022-05-06 | Include kiosk API.     |

##### v1.1.0:

1. Class AppInstallResultReceiver, AppUnInstallResultReceiver added.

2. Interface AppManagerListener added.

3. Exception AppLowerThanCurrentVersionException, InvalidApkFileException added.

4. API installOrUpdateApkSilently, uninstallAppSilently, isAppInstalled, canInstallOrUpdateApk, getApkPkgName, getApkVersionCode, getInstalledAppVersionCode added.

### Class Summary

| Class         | Description                                |
| ------------- | ------------------------------------------ |
| AdvAndroidUtils | Provide a set of kiosk related operations. |
| AppInstallResultReceiver | A receiver to get the install app result, must register in the call project.<br />Support since v1.1.0. |
| AppUnInstallResultReceiver | A receiver to get the uninstall app result, must register in the call project.<br />Support since v1.1.0. |

### Exception Summary

| Exception                           | Description                                                  |
| ----------------------------------- | ------------------------------------------------------------ |
| NotSystemAppException               | Exception will be thrown when it is't a system app.          |
| ActivityNotForegroundException      | Exception will be thrown when activity is't foreground.      |
| PropertiesNotFoundException         | Exception will be thrown when the custom properties were not found. |
| AppLowerThanCurrentVersionException | Exception will be thrown when the apk's version code is lower than the installed one.<br />Support since v1.1.0. |
| InvalidApkFileException             | Exception will be thrown when the file is not an apk file.<br />Support since v1.1.0. |

### Interface Summary

| Interface          | Description                                                  |
| ------------------ | ------------------------------------------------------------ |
| AppManagerListener | A interface for callback when install/uninstall application.<br />Support since v1.1.0. |

### Method Summary

belongs to class AppManagerListener.

| Modifier and Type | Method and Description                                       |
| ----------------- | ------------------------------------------------------------ |
| void              | onInstalled(String filePath)<br />Called when the apk file was installed. |
| void              | onUninstalled(String pkgName)<br />Called when the package was uninstalled |
| void              | onFailed(@AppManagerAction int action, String source, String error)<br />Called when error happened. |

belongs to class AdvAndroidUtils.

| Modifier and Type | Method and Description                                       |
| ----------------- | ------------------------------------------------------------ |
| void              | installOrUpdateApkSilently(@NonNull String filePath, AppManagerListener listener)<br />Install the apk file silently.<br />Support since v1.1.0. |
| void              | uninstallAppSilently(@NonNull String pkg, AppManagerListener listener)<br />Uninstall the app silently.<br />Support since v1.1.0. |
| boolean           | isAppInstalled(@NonNull String packageName)<br />Whether the app has installed.<br />Support since v1.1.0. |
| String            | canInstallOrUpdateApk(@NonNull String filePath)<br />Whether the apk file can install or update, an apk file can be install when it's not installed or it's versioncode is not lower than current installed app.<br />Support since v1.1.0. |
| String            | getApkPkgName(@NonNull String filePath)<br />Get the package name of the apk file.<br />Support since v1.1.0. |
| long              | getApkVersionCode(@NonNull String filePath)<br />gGet the version code of the apk file.<br />Support since v1.1.0. |
| long              | getInstalledAppVersionCode(@NonNull String packageName)<br />Get the version code of the installed app.<br />Support since v1.1.0. |
| static boolean    | setKiosk(@NonNull Activity activity) <br />Lock the Android screen to the task where the target activity is located, and then enter kiosk mode.<br />High safety factor, recommended. |
| static void       | cancelKiosk(@NonNull Activity activity)<br />Leave the kiosk mode set by setKiosk. |
| static boolean    | hideStatusNavBar(@NonNull Context context)<br />Hide the system's status bar and navigation bar. <br />Medium safety factor. |
| static boolean    | showStatusNavBar(@NonNull Context context)<br />Show the system's status bar and navigation bar. |
| static void       | hideStatusNavBarImmersive()<br />Hide the system's status bar and navigation bar immersive, shutdown, sliding up or down will forget the status.<br />Low safety factor. |
| static void       | cancelHideStatusNavBarImmersive()<br />Cancel hiding the bars immersive by hideStatusNavBarImmersive and then restore to default status. |
| static String     | getFullScreenStatus(@NonNull Context context)<br />Get the status of full screen which set by hideStatusNavBar & showStatusNavBar. |
| static String     | getFullScreenStatusImmersive(@NonNull Context context)<br />Get the status of full screen immersive which set by hideStatusNavBarImmersive & cancelHideStatusNavBarImmersive. |

### Method Details

#### 1. Methods belongs to AppManagerListener:

##### onInstalled

```
void onInstalled(String filePath) 
```

Called when the apk file was installed.

Support since v1.1.0

- Parameters:

​		`filePath` - the installed apk file.

##### onUninstalled

```
void onUninstalled(String pkgName) 
```

Called when the package was uninstalled.

Support since v1.1.0

- Parameters:

​		`pkgName` - the uninstalled package name.

##### onFailed

```
void onFailed(@AppManagerAction int action, String source, String error) 
```

Called when error happened.

Support since v1.1.0

- Parameters:

​		`action` - see{@link AppManagerAction}.

​		`source` - package name(uninstall) or apk file path(install).

​		`error` - error info.

#### 2. Methods belongs to AdvAndroidUtils:

##### installOrUpdateApkSilently

```
public void installOrUpdateApkSilently(@NonNull String filePath, AppManagerListener listener) 
```

Install the apk file silently, provided for system app.

Support since v1.1.0

- Parameters:

​		`filePath` - the apk file path ready to install.

​		`listener` - listener{@link AppManagerListener#onInstalled(String)} will be called when install app success, otherwise, {@link AppManagerListener#onFailed(int, String, String)} will call.

- Throws:

​		NotSystemAppException

​		AppLowerThanCurrentVersionException

​		InvalidApkFileException

##### uninstallAppSilently

```
public void uninstallAppSilently(@NonNull String pkg, AppManagerListener listener) 
```

Uninstall the app silently, provided for system app.

Support since v1.1.0

- Parameters:

​		`pkg` - the package name ready to uninstall.

​		`listener` - listener {@link AppManagerListener#onUninstalled(String)} will be called when uninstall app success, otherwise, {@link AppManagerListener#onFailed(int, String, String)} will call.

- Throws:

​		NotSystemAppException

##### isAppInstalled

```
public boolean isAppInstalled(@NonNull String packageName) 
```

Whether the app has installed, only for system app.

Support since v1.1.0

- Parameters:

​		`packageName` - package name of target app.

- Returns:

​		True when the app was installed.

##### canInstallOrUpdateApk

```
public boolean canInstallOrUpdateApk(@NonNull String filePath) 
```

Whether the apk file can install or update, only for system app.

An apk file can be install when it's not installed or it's versioncode is not lower than current installed app.

Support since v1.1.0

- Parameters:

​		`filePath` - file path of target apk file.

- Returns:

​		True when the apk can install or update

##### getApkPkgName

```
public String getApkPkgName(@NonNull String filePath) 
```

Get the package name of the apk file, only for system app.

Support since v1.1.0

- Parameters:

​		`filePath` - file path of target apk file.

- Returns:

​		Package name.

##### getApkVersionCode

```
public long getApkVersionCode(@NonNull String filePath) 
```

Get the version code of the apk file, only for system app.

Support since v1.1.0

- Parameters:

​		`filePath` - file path of target apk file.

- Returns:

​		The version code of the apk file, or -1 when error.

##### getInstalledAppVersionCode

```
public long getInstalledAppVersionCode(@NonNull String packageName)
```

Get the version code of the installed app, only for system app.

Support since v1.1.0

- Parameters:

​		`filePath` - file path of target apk file.

- Returns:

​		The version code of the installed app, or -1 when the app is not install.

##### setKiosk

```
public static boolean setKiosk(@NonNull Activity activity) 
```

Lock the Android screen to the task where the target activity is located, and then enter kiosk mode. You can't leave the activity task util call cancelKiosk.<br />High safety factor, recommended.

- Parameters:

​		`activity` - target activity want to be locked into kiosk mode, must be a foreground activity.

- Returns:

​		Whether the kiosk mode is set successfully.

- Throws:

​		NotSystemAppException

​		ActivityNotForegroundException

​		PropertiesNotFoundException

- Note:

  The app need to be added into the lock task whitelist by a device owner app, then you can enter kiosk mode silently(device owner will be support later). Otherwise, the system will pop up a dialog to request the lock task permission from the user, please don't refuse the request.

##### cancelKiosk

```
public static void cancelKiosk(@NonNull Activity activity) 
```

Leave the kiosk mode set by setKiosk. 

- Parameters:

​		`activity` - activities in lock task which was set by setKiosk previously.

##### hideStatusNavBar

```
public static boolean hideStatusNavBar(@NonNull Context context)
```

Hide the system's status bar and navigation bar.  Some special operations maybe brings you to other application.<br />Medium safety factor.

- Parameters:

​		`context` - a valid Context object.

- Returns:

​		Whether the bars were hide successfully.

- Throws:

​		NotSystemAppException

​		PropertiesNotFoundException

##### showStatusNavBar

```
public static boolean showStatusNavBar(@NonNull Context context)
```

Show the system's status bar and navigation bar.

- Parameters:

​		`context` - a valid Context object.

- Returns:

​		Whether the bars were shown successfully.

##### hideStatusNavBarImmersive

```
public static void hideStatusNavBarImmersive()
```

Hide the system's status bar and navigation bar immersive. Shutdown, sliding up or down will forget the status.<br />Low safety factor.

##### cancelHideStatusNavBarImmersive

```
public static void cancelHideStatusNavBarImmersive()  
```

Cancel hiding the bars immersive by hideStatusNavBarImmersive and then restore to default status(bars are shown always).

##### getFullScreenStatus

```
public static String getFullScreenStatus(@NonNull Context context) 
```

Get the status of full screen which set by hideStatusNavBar & showStatusNavBar.

- Parameters:

​		`context` - a valid Context object.

- Returns:

​		null(can't get status), "false"(bars were hide), "true"(bars were shown, default).

##### getFullScreenStatusImmersive

```
public static String getFullScreenStatusImmersive(@NonNull Context context)
```

Get the status of full screen immersive which set by hideStatusNavBarImmersive & cancelHideStatusNavBarImmersive.

- Parameters:

​		`context` - a valid Context object.

- Returns:

​		null(can't get status), "immersive=*"(bars were hide immersive), "null"(bars were shown always, default).

### Exception Details

##### NotSystemAppException:				

```
public class NotSystemAppException extends Exception
```

Exception will be thrown when it is't a system app.

Constructor:

`NotSystemAppException(String message)`

- Parameters:

​		`message` - the detail message.

##### ActivityNotForegroundException:		

```
public class ActivityNotForegroundException extends Exception
```

Exception will be thrown when activity isn't foreground.

Constructor:

`ActivityNotForegroundException(String message)`

- Parameters:

​		`message` - the detail message.

##### PropertiesNotFoundException:		

```
public class PropertiesNotFoundException extends Exception
```

Exception will be thrown when the custom properties were not found.

Constructor:

`PropertiesNotFoundExceptionextends(String message)`

- Parameters:

​		`message` - the detail message.

##### AppLowerThanCurrentVersionException:		

```
public class AppLowerThanCurrentVersionException extends Exception
```

Exception will be thrown when the apk's version code is lower than the installed one.

Constructor:

`AppLowerThanCurrentVersionException(String message)`

- Parameters:

​		`message` - the detail message.

##### InvalidApkFileException:		

```
public class InvalidApkFileException extends Exception
```

Exception will be thrown when the file is not an apk file.

Constructor:

`InvalidApkFileException(String message)`

- Parameters:

​		`message` - the detail message.

# Auto Start When Boot Completely

We won't provide the sdk about app start automatically after boot completely.

If you are interested in this feature, please refer to the sample code which is realized by register the boot broadcast.