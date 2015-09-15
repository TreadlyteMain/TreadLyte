import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.view.MenuItem;

import com.parse.ParseUser;
import com.treadlyte.mainapp.Explore_Fragment;
import com.treadlyte.mainapp.LoginActivity;
import com.treadlyte.mainapp.Notification_Fragment;
import com.treadlyte.mainapp.PurchasedItems_Fragment;
import com.treadlyte.mainapp.R;
import com.treadlyte.mainapp.Sell_Fragment;
import com.treadlyte.mainapp.Settings_Fragment;
import com.treadlyte.mainapp.SoldItems_Fragment;
import com.treadlyte.mainapp.messaging.ListUsersActivity;

/**
 * Created by richardblakeney-williams on 15/09/15.
 */
public class Drawer_Activity extends Activity {

    private DrawerLayout mDrawer;
    private NavigationView nvDrawer;


    //TODO: This activity will have the drawer shit in it so I can extend any activity to include the
    // the same drawer.

    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        // Find our drawer view
        mDrawer = (DrawerLayout) findViewById(R.id.drawer_layout);
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
