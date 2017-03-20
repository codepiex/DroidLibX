/*
 * Copyright (C) 2016 Eeshan Jamal
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.codepiex.droidlibx.utils;

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
