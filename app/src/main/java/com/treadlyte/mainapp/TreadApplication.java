package com.treadlyte.mainapp;

import android.app.Application;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.util.Base64;
import android.util.Log;

import com.facebook.FacebookSdk;
import com.parse.Parse;
import com.parse.ParseFacebookUtils;
import com.parse.ParseInstallation;



import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by richardblakeney-williams on 18/08/15.
 */
public class TreadApplication extends Application {

    static final String TAG = "TreadApplication";

    @Override
    public void onCreate() {
        super.onCreate();

        //Hook in FB sdk
        FacebookSdk.sdkInitialize(getApplicationContext());

        //Set up Parse
        Parse.enableLocalDatastore(this);
        Parse.initialize(this, "FpLhJVvrU3NAJzzsZkWVLRLw0fleYUDFkGGsrjtX", "zYdReZv0NcHPxOWl01WrqgZAh8mOqKFNjK4qjNmV");
        ParseInstallation.getCurrentInstallation().saveInBackground();


        //Set up Parse FB shit
        ParseFacebookUtils.initialize(getApplicationContext());
    }
}
