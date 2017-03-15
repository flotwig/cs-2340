package edu.gatech.cs2340.team1waterreporting;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import edu.gatech.cs2340.team1waterreporting.model.Model;
import edu.gatech.cs2340.team1waterreporting.model.UserRole;

/**
 * The main activity of the application, contains other parts via fragments (see
 * onNavigationItemSelected function) and is made visible when the user logs in.
 */
public class MainDrawerActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    Menu mMenu;
    static int MENU_NEW_PURITY_REPORT = 1;
    static int MENU_LIST_PURITY_REPORTS = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_drawer);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        addPrivilegedMenuItems(navigationView.getMenu());
        switchFragment(ListWaterReportsFragment.newInstance());
    }

    private void addPrivilegedMenuItems(Menu menu) {
        switch (Model.getInstance().getCurrentUser().getUserRole()) {
            case ADMIN:
            case MANAGER:
                menu.add(0, MENU_LIST_PURITY_REPORTS, Menu.NONE, "List Water Purity Reports").setIcon(R.drawable.ic_menu_share);
            case WORKER:
                menu.add(0, MENU_NEW_PURITY_REPORT, Menu.NONE, "New Water Purity Report").setIcon(R.drawable.ic_menu_manage);
            case USER:
            default:
        }
    }

    /**
     * Method to populate the user's name and username into the text fields.
     */
    private void populateUserDetails() {
        TextView mName = (TextView) findViewById(R.id.name_view);
        TextView mId = (TextView) findViewById(R.id.username_view);

        mName.setText(Model.getInstance().getCurrentUser().getName());
        mId.setText(Model.getInstance().getCurrentUser().getId());
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        populateUserDetails();
        addPrivilegedMenuItems(menu);

        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_drawer, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        if (id == R.id.nav_logout) {
            onClickLogoutButton(null);
        } else if (id == R.id.nav_water_reports) {
            switchFragment(ListWaterReportsFragment.newInstance());
        } else if (id == R.id.nav_profile) {
            switchFragment(EditProfileFragment.newInstance(null));
        } else if (id == R.id.nav_water_report_map) {
            switchFragment(MapFragment.newInstance());
        } else if (id == MENU_NEW_PURITY_REPORT) {
            switchFragment(NewWaterQualityReportFragment.newInstance());
        } else if (id == MENU_LIST_PURITY_REPORTS) {
            switchFragment(ListWaterPurityReportsFragment.newInstance());
        }

        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    /**
     * onClick handler for logout. Sets current user to null and exits this activity.
     * @param v Logout button
     */
    public void onClickLogoutButton(View v) {
        Model.getInstance().setCurrentUser(null);
        finish();
    }

    /**
     * Switches the fragment currently displayed to the supplied fragment.
     * @param fragment The fragment to swap the main container to.
     */
    public void switchFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_holder, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }
}
