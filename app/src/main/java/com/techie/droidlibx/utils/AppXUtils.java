/*
 * AppXUtils
 * DroidLibX
 *
 * Created By Eeshan Jamal on Oct 03, 2016
 * Copyright (c) 2016 Smart Communities. All rights reserved.
 */


package com.techie.droidlibx.utils;

import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.util.Log;

import java.util.List;

public class AppXUtils {

    public static final String TAG = "ash_appx";

    public static String getAppName(Context pContext) {

        String appName = new String();

        try{
            PackageManager lPackageManager = pContext.getPackageManager();
            ApplicationInfo lApplicationInfo = null;
            lApplicationInfo = lPackageManager.getApplicationInfo(pContext.getApplicationInfo().packageName, 0);
            appName = (lApplicationInfo != null ? lPackageManager.getApplicationLabel(lApplicationInfo).toString() : "Unknown");
        }
        catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            Log.e(TAG, e.getMessage()+" At getAppName(...) of AppXUtils");
        }
        catch (NullPointerException e) {
            e.printStackTrace();
            Log.e(TAG, e.getMessage()+" At getAppName(...) of AppXUtils");
        }
        catch (Exception e) {
            e.printStackTrace();
            Log.e(TAG, e.getMessage()+" At getAppName(...) of AppXUtils");
        }

        return appName;
    }

    public static boolean isApplicationSentToBackground(final Context context) {

        boolean status = false;

        try{
            ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
            List<ActivityManager.RunningTaskInfo> tasks = am.getRunningTasks(1);
            if (!tasks.isEmpty()) {
                ComponentName topActivity = tasks.get(0).topActivity;
                status = !topActivity.getPackageName().equals(context.getPackageName());
            }
        }
        catch(NullPointerException e){
            e.printStackTrace();
            Log.e(TAG, e.getMessage()+" At isApplicationSentToBackground(...) of AppXUtils");
        }
        catch(SecurityException e){
            e.printStackTrace();
            Log.e(TAG, e.getMessage()+" At isApplicationSentToBackground(...) of AppXUtils");
        }
        catch(ArrayIndexOutOfBoundsException e){
            e.printStackTrace();
            Log.e(TAG, e.getMessage()+" At isApplicationSentToBackground(...) of AppXUtils");
        }
        catch (Exception e) {
            e.printStackTrace();
            Log.e(TAG, e.getMessage()+" At isApplicationSentToBackground(...) of AppXUtils");
        }

        return status;

    }
}
