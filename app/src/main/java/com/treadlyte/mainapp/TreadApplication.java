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

        //Set up Parse FB shit
        ParseFacebookUtils.initialize(getApplicationContext());
    }

    /**
     * This method is for printing out the hashkey for facebook authentication.
     * GET RID OF THIS, DON"T NEED IT
     *
     */
    public void printHashKey() {

        // Add code to print out the key hash
        try {
            PackageInfo info = getPackageManager().getPackageInfo(
                    "com.treadlyte.mainapp",
                    PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                Log.d("KeyHash:", Base64.encodeToString(md.digest(), Base64.DEFAULT));
            }
        } catch (PackageManager.NameNotFoundException e) {

        } catch (NoSuchAlgorithmException e) {

        }
    }
}
