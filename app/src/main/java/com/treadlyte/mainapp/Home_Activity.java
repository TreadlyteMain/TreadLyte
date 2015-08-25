package com.treadlyte.mainapp;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.facebook.AccessToken;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.widget.ProfilePictureView;
import com.parse.ParseUser;


import org.json.JSONException;
import org.json.JSONObject;

import com.treadlyte.mainapp.messaging.ListUsersActivity;
import com.treadlyte.mainapp.messaging.MessageService;

public class Home_Activity extends AppCompatActivity {

    /**
     * Fragment managing the behaviors, interactions and presentation of the navigation NavigationDrawer.
     */
    private Toolbar mToolbar;
    private DrawerLayout mDrawer;
    private NavigationView nvDrawer;

    private ProfilePictureView userProfilePictureView;


    private TextView userNameView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_activity_mainlayout);

        // Set a Toolbar to replace the ActionBar.
        mToolbar = (Toolbar) findViewById(R.id.toolbar_actionbar);
        setSupportActionBar(mToolbar);

        // Find our drawer view
        mDrawer = (DrawerLayout) findViewById(R.id.drawer_layout);

        // Set the menu icon instead of the launcher icon.
        final ActionBar ab = getSupportActionBar();
        ab.setHomeAsUpIndicator(R.drawable.ic_menu);
        ab.setDisplayHomeAsUpEnabled(true);

        // Find our drawer view
        nvDrawer = (NavigationView) findViewById(R.id.nvView);
        // Setup drawer view
        setupDrawerContent(nvDrawer);

        userProfilePictureView = (ProfilePictureView) findViewById(R.id.userProfilePicture);
        userNameView = (TextView) findViewById(R.id.userName);


        //Fetch Facebook user info if it is logged
        ParseUser currentUser = ParseUser.getCurrentUser();
        if ((currentUser != null) && currentUser.isAuthenticated()) {
            makeMeRequest();
        }
    }


    private void setupDrawerContent(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        selectDrawerItem(menuItem);
                        return true;
                    }
                });
    }

    public void selectDrawerItem(MenuItem menuItem) {
        // Create a new fragment and specify the planet to show based on
        // position
        Fragment fragment = null;
        switch(menuItem.getItemId()) {
            case R.id.explore_drawer_button:
                fragment = new Explore_Fragment();
                break;
            case R.id.messaging_drawer_button:
                Intent intent = new Intent(getApplicationContext(),ListUsersActivity.class);
                startActivity(intent);
                break;
            case R.id.sell_drawer_button:
                fragment = new Sell_Fragment();
                break;
            case R.id.notification_drawer_button:
                fragment = new Notification_Fragment();
                break;
            case R.id.sold_drawer_button:
                fragment = new SoldItems_Fragment();
                break;
            case R.id.purchased_drawer_button:
                fragment = new PurchasedItems_Fragment();
                break;
            case R.id.settings_drawer_button:
                fragment = new Settings_Fragment();
                break;
            case R.id.logout_drawer_button:
                logout();
                break;
            default:
                fragment = new Explore_Fragment();
        }

        int i = menuItem.getItemId();
        if(i != R.id.logout_drawer_button && i != R.id.messaging_drawer_button) {
            // Insert the fragment by replacing any existing fragment
            FragmentManager fragmentManager = getFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.flContent, fragment).commit();
        }

            // Highlight the selected item, update the title, and close the drawer
            menuItem.setChecked(true);
            setTitle(menuItem.getTitle());
            mDrawer.closeDrawers();

    }


    /*
    Gets profile information from Facebook and saves it to parse
     */
    private void makeMeRequest() {
        GraphRequest request = GraphRequest.newMeRequest(AccessToken.getCurrentAccessToken(),
                new GraphRequest.GraphJSONObjectCallback() {
                    @Override
                    public void onCompleted(JSONObject jsonObject, GraphResponse graphResponse) {
                        if (jsonObject != null) {
                            JSONObject userProfile = new JSONObject();

                            try {
                                userProfile.put("facebookId", jsonObject.getLong("id"));
                                userProfile.put("name", jsonObject.getString("name"));

                                if (jsonObject.getString("gender") != null)
                                    userProfile.put("gender", jsonObject.getString("gender"));

                                if (jsonObject.getString("email") != null)
                                    userProfile.put("email", jsonObject.getString("email"));

                                // Save the user profile info in a user property
                                ParseUser currentUser = ParseUser.getCurrentUser();
                                currentUser.put("profile", userProfile);
                                currentUser.saveInBackground();

                                // Show the user info
                                updateViewsWithProfileInfo();
                            } catch (JSONException e) {
                                Log.d(TreadApplication.TAG,
                                        "Error parsing returned user data. " + e);
                            }
                        } else if (graphResponse.getError() != null) {
                            switch (graphResponse.getError().getCategory()) {
                                case LOGIN_RECOVERABLE:
                                    Log.d(TreadApplication.TAG,
                                            "Authentication error: " + graphResponse.getError());
                                    break;

                                case TRANSIENT:
                                    Log.d(TreadApplication.TAG,
                                            "Transient error. Try again. " + graphResponse.getError());
                                    break;

                                case OTHER:
                                    Log.d(TreadApplication.TAG,
                                            "Some other error: " + graphResponse.getError());
                                    break;
                            }
                        }
                    }
                });

        request.executeAsync();
    }

    @Override
    public void onBackPressed() {
        //DO NOT GO BACK TO LOGIN
    }

    private void updateViewsWithProfileInfo() {
        ParseUser currentUser = ParseUser.getCurrentUser();
        if (currentUser.has("profile")) {
            JSONObject userProfile = currentUser.getJSONObject("profile");
            try {

                if (userProfile.has("facebookId")) {
                    userProfilePictureView.setProfileId(userProfile.getString("facebookId"));
                } else {
                    // Show the default, blank user profile picture
                    userProfilePictureView.setProfileId(null);
                }

                if (userProfile.has("name")) {
                    userNameView.setText(userProfile.getString("name"));
                } else {
                    userNameView.setText("");
                }
            } catch (JSONException e) {
                Log.d(TreadApplication.TAG, "Error parsing saved user data.");
            }
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // The action bar home/up action should open or close the drawer.
        switch (item.getItemId()) {
            case android.R.id.home:
                mDrawer.openDrawer(GravityCompat.START);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
    }

    /*
    Method to attach to the facebook button for logging out user
    @param: The view that contains the logout button
     */
    public void onLogoutClick(View v) {
        logout();
    }


    /*
    Logs out user from Parse
     */
    private void logout() {
        // Log the user out
        ParseUser.logOut();

        // Go to the login view
        startLoginActivity();
    }

    /*
    Starts the login activity if the user logs out
     */
    private void startLoginActivity() {
        Intent intent = new Intent(this, LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }


}
