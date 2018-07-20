package com.location.reminder;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.bignerdranch.android.multiselector.MultiSelector;
import com.location.reminder.com.location.reminder.restcalls.ReminderService;
import com.location.reminder.model.ReminderAdapter;
import com.location.reminder.model.ReminderModel;
import com.location.reminder.util.Constants;

import org.json.JSONArray;

import java.util.ArrayList;

public class HomeActivity extends BaseActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    SharedPreferences sharedpreferences;
    private ListView mList;

    private ReminderAdapter mAdapter;
    private MultiSelector mMultiSelector = new MultiSelector();

    ArrayList<ReminderModel> mItems = new ArrayList<ReminderModel>();

    private boolean isMyServiceRunning(Class<?> serviceClass) {
        ActivityManager manager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE)) {
            if (serviceClass.getName().equals(service.service.getClassName())) {
                return true;
            }
        }
        return false;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        needhomebutton = false;
        setTitle("Location based reminders");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        mList = (ListView) findViewById(R.id.reminder_list);

        sharedpreferences = getSharedPreferences(Constants.sharedPreferences,
                Context.MODE_PRIVATE);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onResume();

            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        View headerLayout =
                navigationView.getHeaderView(0);


        TextView user_fullname = (TextView) headerLayout.findViewById(R.id.username);
        user_fullname.setText(sharedpreferences.getString("name", ""));
        TextView email = (TextView) headerLayout.findViewById(R.id.email);
        email.setText(sharedpreferences.getString("email", ""));
        mAdapter = new ReminderAdapter(this, 0, mItems, sharedpreferences.getString("userid", ""));

        if (!isMyServiceRunning(AppLocationManager.class)) {


            startService(new Intent(this, AppLocationManager.class));
        }

    }

    public void create_new() {

        Intent intent = new Intent(this, CreateNewReminder.class);
        startActivity(intent);

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
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
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


        if (id == R.id.nav_logout) {

            logout_user();
            return true;

        } else if (id == R.id.nav_reminder) {
            create_new();

        } else if (id == R.id.nav_generatecode) {

            Intent intent = new Intent(this, UniqueTokenActivity.class);
            startActivity(intent);
            return true;
        } else if (id == R.id.nav_addcode) {

            Intent intent = new Intent(this, AddTokenActivity.class);
            startActivity(intent);
            return true;
        } else if (id == R.id.nav_Groupmembers) {

            Intent intent = new Intent(this, ShowGroupsActivity.class);
            startActivity(intent);
            return true;
        } else if (id == R.id.nav_Groupmembers) {

            Intent intent = new Intent(this, ShowGroupsActivity.class);
            startActivity(intent);
            return true;
        } else if (id == R.id.nav_preferences) {

            Intent intent = new Intent(this, EnterPreferencesActivity.class);
            startActivity(intent);
            return true;
        } else if (id == R.id.nav_showpreferences) {

            Intent intent = new Intent(this, NearbyPlacesActivity.class);
            startActivity(intent);
            return true;
        } else if (id == R.id.nav_yourpreferences) {

            Intent intent = new Intent(this, YourPreferencesActivity.class);
            startActivity(intent);
            return true;
        } else if (id == R.id.nav_suggestions) {

            Intent intent = new Intent(this, SuggestionsActivity.class);
            startActivity(intent);
            return true;
        } else if (id == R.id.nav_settings) {

            Intent intent = new Intent(this, SettingsActivity.class);
            startActivity(intent);
            return true;
        }


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onResume() {
        super.onResume();
        System.out.println("EXUCUTE THIS");
        new LoadRemindersTask(this).execute(null, null, null);

    }

    private class LoadRemindersTask extends AsyncTask<Void, Void, Void> {

        Activity activity;
        ProgressDialog progressdialog;

        public LoadRemindersTask(Activity activity) {
            this.activity = activity;
        }

        @Override
        protected void onPreExecute() {
            progressdialog = ProgressDialog.show(activity, "",
                    "Please wait...", true);
            mItems.clear();
            mAdapter.notifyDataSetChanged();
        }

        @Override
        protected Void doInBackground(Void... params) {
            ReminderService reminderService = new ReminderService();

            ArrayList<ReminderModel> newitems = reminderService.getReminders(sharedpreferences.getString("userid", ""));

            System.out.println("Number of reminders" + newitems.size());
            if (newitems != null) {

                mItems.addAll(newitems);
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            progressdialog.dismiss();
            mAdapter.notifyDataSetChanged();
            mList.setAdapter(mAdapter);

        }

    }
}
