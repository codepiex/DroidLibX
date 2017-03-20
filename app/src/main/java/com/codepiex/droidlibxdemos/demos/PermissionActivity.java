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

package com.codepiex.droidlibxdemos.demos;

import android.Manifest;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.codepiex.droidlibxdemos.R;
import com.codepiex.droidlibx.permission.PermissionRequestor;
import com.codepiex.droidlibx.utils.AppXUtils;

public class PermissionActivity extends AppCompatActivity implements PermissionRequestor.PermissionRequestorListener,
        ActivityCompat.OnRequestPermissionsResultCallback{

    private static final int PERMISSION_VERIFY_REQUEST 	= 100;

    private PermissionRequestor permissionRequestor;
    private FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_permission);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setupPermissionRequest();
            }
        });

    }

    private void setupPermissionRequest(){

        //Step #1
        //Create an instance of PermissionRequestor by passing appropriate arguments.
        permissionRequestor = new PermissionRequestor(this,
                getString(R.string.loc_media_permission_info, AppXUtils.getAppName(this)),
                PERMISSION_VERIFY_REQUEST,
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.WRITE_EXTERNAL_STORAGE);

        //Step #2
        //Request for permissions.
        permissionRequestor.request(this);

    }

    @Override
    public void onPermissionGranted() {
        //This callback gets called if all permission granted.
        Snackbar.make(fab,"All Permissions Granted", Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void onPermissionDenied() {
        //This callback gets called If one or more permission denied.
        Snackbar.make(fab,"One or More Permissions Denied", Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        permissionRequestor.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }
}
