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

package com.codepiex.droidlibx.permission;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityCompat.OnRequestPermissionsResultCallback;
import android.support.v4.content.ContextCompat;
import android.text.Html;
import android.util.Log;


import com.codepiex.droidlibx.R;
import com.codepiex.droidlibx.utils.AlertXUtils;
import com.codepiex.droidlibx.utils.AppXUtils;

import java.util.ArrayList;

public class PermissionRequestor implements OnRequestPermissionsResultCallback {

	public static final String TAG = "ash_perm_req";

	public interface PermissionRequestorListener{
		void onPermissionGranted();
		void onPermissionDenied();
	}
	
	private PermissionRequestorListener listener;
	private int requestCode;
	private String permissions[];
	private String rationalMessage;
	
	public static boolean isPermissionAccessible(Context context, String permission){
    	return (ContextCompat.checkSelfPermission(context, permission) == PackageManager.PERMISSION_GRANTED);
    }
	
	public static boolean isPermissionAlreadyDenied(Activity activity, String permission){
    	return ActivityCompat.shouldShowRequestPermissionRationale(activity, permission);
    }

	/**
	 * This constructor is used to initialise permission request with required information as an arguments.
	 *
	 * @param listener PermissionRequestorListener instance which is going to be callback based on user decision.
	 * @param rationalMessage String message stating the necessity of the permissions requested by the app.
	 * @param requestCode A unique identifier int code which will return with Callback method.
	 * @param permissions String array having required list of permissions.
	 * */

	public PermissionRequestor(PermissionRequestorListener listener, String rationalMessage, int requestCode, String... permissions) {
		this.listener 			= listener;
		this.requestCode		= requestCode;
		this.permissions		= permissions;
		this.rationalMessage	= rationalMessage;
		
	}

	/**
	 * This method initiate the request process.
	 * <br></br>
	 *
	 * <b>Note: </b>Make sure the Activity requesting permissions must have implemented OnRequestPermissionsResultCallback interface
	 * Otherwise permission request won't be initiate and also called this class module onRequestPermissionsResult on callback result
	 * to receive this class callbacks.*/

	public void request(Activity activity){

		ArrayList<String> requestedPermissions 	= new ArrayList<String>();
		
		/*
		 * Check and add those permissions in list which need confirmation from user.
		 */
		for (String permission : permissions) {
			if(!isPermissionAccessible(activity, permission)){
				requestedPermissions.add(permission);
			}
		}
		
		permissions = requestedPermissions.toArray(new String[]{});
		
		if(permissions.length>0){
			showRequestPermissionRationaleDialog(activity);
		}
		else if(listener!=null){
			listener.onPermissionGranted();
		}
		
	}
	
	/**
	 * Check to see if any one of the requesting permission already denied.
	 */
	
	private boolean shouldShowRequestPermissionRationale(Activity activity, String[] reqPermissions){
		
		boolean status = false;
		
		for (String reqPermission : reqPermissions) {
			if(isPermissionAlreadyDenied(activity, reqPermission)){
				status = true;
				break;
			}
		}
		
		return status;
	}
	
	private void showRequestPermissionRationaleDialog(final Activity activity){
		
		AlertXUtils.decisionAlertOnMainThread(activity, android.R.drawable.ic_dialog_info, R.string.permission_info, appendNote(activity, rationalMessage),
				activity.getString(R.string.proceed), new OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						requestPermissions(activity, permissions);
					}
				}, activity.getString(R.string.cancel), new OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						if(listener!=null){
							listener.onPermissionDenied();
						}
					}
				});
		
	}
	
	private CharSequence appendNote(Context context, String message){
		return Html.fromHtml(message+"\n\n"+context.getString(R.string.permission_info_note, AppXUtils.getAppName(context)));
	}
	
	private boolean requestPermissions(Activity activity, String[] permissions){
		boolean status = false; 
		try{
			ActivityCompat.requestPermissions(activity, permissions, requestCode);
			status = true;
		}
		catch(NullPointerException e){
			e.printStackTrace();
			Log.e(TAG, e.getMessage()+" At requestPermissions(...) of PermissionRequestor");
		}
		return status;
	}

	@Override
	public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
		
		if(listener!=null) {
			
			boolean allGranted = grantResults.length>0;
			
			for (int result : grantResults) {
				if(result == PackageManager.PERMISSION_DENIED){
					allGranted = false;
					break;
				}
			}
			
            if (allGranted) {
            	listener.onPermissionGranted();
            } else {
            	listener.onPermissionDenied();
            }
            
	    }
		
	}
	
	public int getRequestCode() {
		return requestCode;
	}

}
