# AdvAndroidUtils

AdvAndroidUtils is an android sdk provided by Advantech. **System Permissions API of Android.**

**Version:**

​		1.0.0

**Date:**

​		 2022-05-06

**Author:**

​		YuBo.Liu

### Class Summary

| Class         | Description                                |
| ------------- | ------------------------------------------ |
| AdvAndroidUtils | Provide a set of kiosk related operations. |

### Method Summary

belongs to class AdvAndroidUtils.

| Modifier and Type | Method and Description                                       |
| ----------------- | ------------------------------------------------------------ |
| static boolean    | setKiosk(@NonNull Activity activity) <br />Lock the Android screen to the task where the target activity is located, and then enter kiosk mode.<br />High safety factor, recommended. |
| static void       | cancelKiosk(@NonNull Activity activity)<br />Leave the kiosk mode set by setKiosk. |
| static boolean    | hideStatusNavBar(@NonNull Context context)<br />Hide the system's status bar and navigation bar. <br />Medium safety factor. |
| static boolean    | showStatusNavBar(@NonNull Context context)<br />Show the system's status bar and navigation bar. |
| static void       | hideStatusNavBarImmersive()<br />Hide the system's status bar and navigation bar immersive, shutdown, sliding up or down will forget the status.<br />Low safety factor. |
| static void       | cancelHideStatusNavBarImmersive()<br />Cancel hiding the bars immersive by hideStatusNavBarImmersive and then restore to default status. |
| static String     | getFullScreenStatus(@NonNull Context context)<br />Get the status of full screen which set by hideStatusNavBar & showStatusNavBar. |
| static String     | getFullScreenStatusImmersive(@NonNull Context context)<br />Get the status of full screen immersive which set by hideStatusNavBarImmersive & cancelHideStatusNavBarImmersive. |

### Exception Summary

| Exception                      | Description                                                  |
| ------------------------------ | ------------------------------------------------------------ |
| NotSystemAppException          | Exception will be thrown when it is't a system app.          |
| ActivityNotForegroundException | Exception will be thrown when activity is't foreground.      |
| PropertiesNotFoundException    | Exception will be thrown when the custom properties were not found. |

### Method Details

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
Exception will be thrown when activity isn't foreground.
```

Throw when activity isn't foreground.

Constructor:

`ActivityNotForegroundException(String message)`

- Parameters:

​		`message` - the detail message.

##### PropertiesNotFoundException:		

```
public class PropertiesNotFoundExceptionextends Exception
```

Exception will be thrown when the custom properties were not found.

Constructor:

`PropertiesNotFoundExceptionextends(String message)`

- Parameters:

​		`message` - the detail message.

# Auto Start When Boot Completely

We won't provide the sdk about app start automatically after boot completely.

If you are interested in this feature, please refer to the sample code which is realized by register the boot broadcast.