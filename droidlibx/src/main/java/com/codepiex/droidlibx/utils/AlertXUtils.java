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

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.util.Log;
import android.widget.Toast;

import com.codepiex.droidlibx.R;

public class AlertXUtils {

    public static final String TAG = "ash_alertx";

    private static AlertDialog alert;
    private static AlertDialog decisionAlert;

    public static void alert(Context c, int titleRes, String message){
        alert(c, R.mipmap.error, titleRes, message, true, null);
    }

    public static void alert(Context c, int iconRes, int titleRes, String message){
        alert(c, iconRes, titleRes, message, true, null);
    }

    public static void alert(Context c, int iconRes, int titleRes, String message, boolean foreground, DialogInterface.OnCancelListener cancelListener){

        if(foreground){

            try{
                if(alert!=null && alert.isShowing()){
                    //======Do nothing
                }
                else{

                    AlertDialog.Builder alert = new AlertDialog.Builder(c);

                    try{
                        alert.setIcon(iconRes);
                    }
                    catch(Resources.NotFoundException e){
                        e.printStackTrace();
                        Log.i(TAG, e.getMessage()+" at alert(...) of Utils");
                    }

                    try{
                        alert.setTitle(titleRes);
                    }
                    catch(Resources.NotFoundException e){
                        e.printStackTrace();
                        Log.i(TAG, e.getMessage()+" at alert(...) of Utils");
                    }

                    alert.setMessage(message);
                    alert.setOnCancelListener(cancelListener);

                    AlertXUtils.alert = alert.create();
                    AlertXUtils.alert.setCanceledOnTouchOutside(true);
                    AlertXUtils.alert.show();

                }
            }
            catch(NullPointerException e){
                e.printStackTrace();
                Log.e(TAG, e.getMessage()+" at alert(...) of Utils");
            }
            catch (Exception e) {
                e.printStackTrace();
                Log.e(TAG, e.getMessage()+" at decisionAlert(...) of Utils");
            }

        }

    }

    public static void alert(Context c, String message){
        alert(c, R.string.error, message);
    }

    public static void alert(Context c, String message, DialogInterface.OnCancelListener cancelListener){
        alert(c, R.mipmap.error, R.string.error, message, true, cancelListener);
    }

    public static void alert(Context c, int title, String message, DialogInterface.OnCancelListener cancelListener){
        alert(c, R.mipmap.error, title, message, true, cancelListener);
    }

    public static void mustAlert(Context c, String message){
        boolean foreground = !AppXUtils.isApplicationSentToBackground(c);
        if(foreground)
            alert(c, R.mipmap.error, R.string.error, message, foreground, null);
        else
            showToast(c, AppXUtils.getAppName(c)+" : "+message, Toast.LENGTH_SHORT);
    }

    public static void showAlertOnMainThread(final Context ctx,final int titleRes, final String text){

        try{
            if(ctx!=null){
                ((Activity)ctx).runOnUiThread(new Runnable() {

                    public void run() {
                        alert(ctx, titleRes, text);
                    }
                });
            }
        }
        catch(NullPointerException e){
            e.printStackTrace();
            Log.e(TAG, e.getMessage()+" At showAlertOnMainThread(...) module of Utils class");
        }
        catch (ClassCastException e) {
            e.printStackTrace();
            Log.e(TAG, e.getMessage()+" At showAlertOnMainThread(...) module of Utils class");
        }
        catch (Exception e) {
            e.printStackTrace();
            Log.e(TAG, e.getMessage()+" At showAlertOnMainThread(...) module of Utils class");
        }

    }

    public static void showAlertOnMainThread(final Context ctx, final String text){
        showAlertOnMainThread(ctx, text, null);
    }

    public static void showAlertOnMainThread(final Context ctx, final String text,final DialogInterface.OnCancelListener listener){

        try{
            ((Activity)ctx).runOnUiThread(new Runnable() {
                public void run() {
                    // TODO Auto-generated method stub
                    alert(ctx, text, listener);
                }
            });
        }
        catch(NullPointerException e){
            e.printStackTrace();
            Log.e(TAG, e.getMessage()+"At showAlertOnMainThread(...) of Utils");
        }
        catch(ClassCastException e){
            e.printStackTrace();
            Log.e(TAG, e.getMessage()+"At showAlertOnMainThread(...) of Utils");
        }
        catch (Exception e) {
            e.printStackTrace();
            Log.e(TAG, e.getMessage()+"At showAlertOnMainThread(...) of Utils");
        }

    }

    public static void decisionAlertOnMainThread(final Context c, final int titleRes,
                                                 final CharSequence message, final String positiveTitle, final DialogInterface.OnClickListener onPositiveClick,
                                                 final String negativeTitle, final DialogInterface.OnClickListener onNegativeClick){
        decisionAlertOnMainThread(c, R.mipmap.error, titleRes, message, positiveTitle, onPositiveClick, negativeTitle, onNegativeClick);
    }

    public static void decisionAlertOnMainThread(final Context c, final int iconRes, final int titleRes,
                                                 final CharSequence message, final String positiveTitle, final DialogInterface.OnClickListener onPositiveClick,
                                                 final String negativeTitle, final DialogInterface.OnClickListener onNegativeClick){

        try{
            ((Activity)c).runOnUiThread(new Runnable() {

                @Override
                public void run() {
                    decisionAlert(c, iconRes, titleRes, message, positiveTitle, onPositiveClick, negativeTitle, onNegativeClick);
                }
            });
        }
        catch(NullPointerException e){
            e.printStackTrace();
            Log.e(TAG, e.getMessage()+"At decisionAlertOnMainThread(...) of Utils");
        }
        catch(ClassCastException e){
            e.printStackTrace();
            Log.e(TAG, e.getMessage()+"At decisionAlertOnMainThread(...) of Utils");
        }
        catch (Exception e) {
            e.printStackTrace();
            Log.e(TAG, e.getMessage()+"At decisionAlertOnMainThread(...) of Utils");
        }

    }

    public static void decisionAlert(Context c, int titleRes, CharSequence message, String positiveTitle, DialogInterface.OnClickListener onPositiveClick, String negativeTitle, DialogInterface.OnClickListener onNegativeClick){
        decisionAlert(c, R.mipmap.error, titleRes, message, positiveTitle, onPositiveClick, negativeTitle, onNegativeClick);
    }

    public static void decisionAlert(Context c, int iconRes, int titleRes, CharSequence message, String positiveTitle, DialogInterface.OnClickListener onPositiveClick, String negativeTitle, DialogInterface.OnClickListener onNegativeClick){

        try{
            if(decisionAlert!=null && decisionAlert.isShowing()){
                //======Do nothing
            }
            else{

                AlertDialog.Builder builder = new AlertDialog.Builder(c);

                try{
                    builder.setIcon(iconRes);
                }
                catch(Resources.NotFoundException e){
                    e.printStackTrace();
                    Log.e(TAG, e.getMessage()+" At decisionAlert(...) of Utils");
                }

                try{
                    builder.setTitle(titleRes);
                }
                catch(Resources.NotFoundException e){
                    e.printStackTrace();
                    Log.e(TAG, e.getMessage()+" At decisionAlert(...) of Utils");
                }

                builder.setMessage(message);
                builder.setCancelable(!(onPositiveClick!=null || onNegativeClick!=null));

                if(onPositiveClick!=null){
                    builder.setPositiveButton(positiveTitle, onPositiveClick);
                }

                if(onNegativeClick!=null){
                    builder.setNegativeButton(negativeTitle, onNegativeClick);
                }

                decisionAlert = builder.create();
                decisionAlert.show();

            }
        }
        catch(NullPointerException e){
            e.printStackTrace();
            Log.e(TAG, e.getMessage()+" at decisionAlert(...) of Utils");
        }
        catch (Exception e) {
            e.printStackTrace();
            Log.e(TAG, e.getMessage()+" at decisionAlert(...) of Utils");
        }

    }


    public static void showToast(Context ctx,String text, int duration){
        try{
            Toast.makeText(ctx, text, duration).show();
        }
        catch(NullPointerException e){
            e.printStackTrace();
            Log.e(TAG, e.getMessage()+"At showToast(...) of Utils");
        }
        catch (Exception e) {
            e.printStackTrace();
            Log.e(TAG, e.getMessage()+"At showToast(...) of Utils");
        }
    }

}
