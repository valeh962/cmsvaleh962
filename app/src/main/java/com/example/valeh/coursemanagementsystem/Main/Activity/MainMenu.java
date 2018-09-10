package com.example.valeh.coursemanagementsystem.Main.Activity;

import android.app.ActionBar;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;

import com.example.valeh.coursemanagementsystem.Main.Fragment.MainMenuLists.FilterPersons.FilteredPersons;
import com.example.valeh.coursemanagementsystem.Main.Fragment.MainMenuLists.RequestListofTandS.teacher_main_list;
import com.example.valeh.coursemanagementsystem.Main.Fragment.PersonTypeList.Menu_lists_1;
import com.example.valeh.coursemanagementsystem.Main.Fragment.Settings;
import com.example.valeh.coursemanagementsystem.Main.Fragment.home_mainmenu;
import com.example.valeh.coursemanagementsystem.Main.JsonWorks.MyProfile.ImyProfile;
import com.example.valeh.coursemanagementsystem.Main.JsonWorks.MyProfile.MyProfileData;
import com.example.valeh.coursemanagementsystem.R;
import com.jaeger.library.StatusBarUtil;

import me.anwarshahriar.calligrapher.Calligrapher;
import okhttp3.ConnectionSpec;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import android.preference.PreferenceManager;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.TextView;

import java.util.Arrays;

public class MainMenu extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {


    NavigationView navigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        StatusBarUtil.setTransparent(this);
        PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).edit().putString("PIN_ENTER", "1").apply();
        SharedPreferences spreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        Boolean aBoolean = spreferences.getBoolean("SCREEN_PROTECT", false);
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
        if(aBoolean){
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE,
                    WindowManager.LayoutParams.FLAG_SECURE);
        }
        if(!aBoolean){
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_SECURE);
        }
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

//        navigationView.addHeaderView();
//        TextView nav_name = navigationView.findViewById(R.id.textView26);
//        TextView nav_type = navigationView.findViewById(R.id.textView29);
//        nav_name.setText(PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).getString("UserName", ""));
//        nav_type.setText(PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).getString("UserType", ""));
        Fragment frr;
        frr = new home_mainmenu();
        setActionBarTitle("Home");
        replaceFragmentWithAnimation(frr,"home_mainmenu");
        String P_TYPE_N;
        P_TYPE_N = PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).getString("UserRoleId", "");
        if(P_TYPE_N.contains("1") && !P_TYPE_N.contains("2") && !P_TYPE_N.contains("3")){
            hideItem(R.id.editRequests);
            hideItem(R.id.filtered);
            hideItem(R.id.nav_camera);
        }
        if(P_TYPE_N.equals("2") && !P_TYPE_N.contains("1") && !P_TYPE_N.contains("3")){
            hideItem(R.id.filtered);
            hideItem(R.id.editRequests);
           // hideItem(R.id.nav_camera);
        }
        if(P_TYPE_N.equals("2") && P_TYPE_N.contains("1") && !P_TYPE_N.contains("3")){
            hideItem(R.id.filtered);
            hideItem(R.id.editRequests);
            // hideItem(R.id.nav_camera);
        }







    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        }

        Fragment currentFragment = getSupportFragmentManager().findFragmentById(R.id.mainmenu_myfrg);
        if (currentFragment instanceof home_mainmenu) {
            //do your stuff here



           new AlertDialog.Builder(this)
                   .setMessage("Are you sure you want to exit?")
                   .setCancelable(false)
                   .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                       @Override
                       public void onClick(DialogInterface dialogInterface, int i) {

                           if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                               finishAffinity();
                           }
                       }
                   })
                   .setNegativeButton("No",null)
                   .show();
        }
        else{
            super.onBackPressed();

        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_menu, menu);
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

            Fragment frr;
            frr = new Settings();

            Fragment currentFragment = getSupportFragmentManager().findFragmentById(R.id.mainmenu_myfrg);
            if (currentFragment instanceof Settings) {}

            else {
                replaceFragmentWithAnimation(frr, "settings_main");
            }

            return true;
        }

        if (id == R.id.log_out) {
            new AlertDialog.Builder(this)
                    .setMessage("Are you sure you want to logout?")
                    .setCancelable(false)
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                                startActivity(new Intent(MainMenu.this,LoginRegister.class));
                                SharedPreferences spreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                                spreferences.edit().clear().commit();
                                PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).edit().putString("LOGOUT", "1").apply();
                                finish();
                            }
                        }
                    })
                    .setNegativeButton("No",null)
                    .show();


            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.home_1) {
            setActionBarTitle("Home");
            Fragment ft = new home_mainmenu();
          //  replaceFragmentWithAnimation(ft,"home_mainmenu");
            Fragment currentFragment = getSupportFragmentManager().findFragmentById(R.id.mainmenu_myfrg);
            if (currentFragment instanceof home_mainmenu) {}

            else {
                replaceFragmentWithAnimation(ft, "home_mainmenu");
            }
        }

        if(id == R.id.editRequests){
            setActionBarTitle("Edit Requests");
            Fragment frr;
            frr = new com.example.valeh.coursemanagementsystem.Main.Fragment.PersonTypeList.Menu_lists();
            Fragment currentFragment = getSupportFragmentManager().findFragmentById(R.id.mainmenu_myfrg);

            if (currentFragment instanceof com.example.valeh.coursemanagementsystem.Main.Fragment.PersonTypeList.Menu_lists) {}

            else {
                replaceFragmentWithAnimation(frr, "com.example.valeh.coursemanagementsystem.Main.Fragment.PersonTypeList.Menu_lists");
            }

        }

       if (id == R.id.nav_camera) {
            setActionBarTitle("Request List");
            Fragment frr;
            frr = new Menu_lists_1();
            Fragment currentFragment = getSupportFragmentManager().findFragmentById(R.id.mainmenu_myfrg);

           if (currentFragment instanceof Menu_lists_1) {}

           else {
               replaceFragmentWithAnimation(frr, "com.example.valeh.coursemanagementsystem.Main.Fragment.PersonTypeList.Menu_lists");
           }


        }
        if(id == R.id.filtered)
        {
            setActionBarTitle("All persons");
            Fragment frr;
            frr = new FilteredPersons();
            Fragment currentFragment = getSupportFragmentManager().findFragmentById(R.id.mainmenu_myfrg);
            if (currentFragment instanceof FilteredPersons) {}

            else {
                replaceFragmentWithAnimation(frr, "com.example.valeh.coursemanagementsystem.Main.Fragment.PersonTypeList.Menu_lists");
            }
        }


        if (id == R.id.nav_send) {

            new AlertDialog.Builder(this)
                    .setMessage("Are you sure you want to exit?")
                    .setCancelable(false)
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                                finishAffinity();
                            }
                        }
                    })
                    .setNegativeButton("No",null)
                    .show();

        }
        if (id == R.id.action_settings) {
            setActionBarTitle("Settings");
            Fragment frr;
            frr = new Settings();

            Fragment currentFragment = getSupportFragmentManager().findFragmentById(R.id.mainmenu_myfrg);
            if (currentFragment instanceof Settings) {}

            else {
                replaceFragmentWithAnimation(frr, "settings_main");
            }



        }

        if (id == R.id.log_out) {
            new AlertDialog.Builder(this)
                    .setMessage("Are you sure you want to logout?")
                    .setCancelable(false)
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                                startActivity(new Intent(MainMenu.this,LoginRegister.class));
                                SharedPreferences spreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                                spreferences.edit().clear().commit();
                                PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).edit().putString("LOGOUT", "1").apply();
                                finish();
                            }
                        }
                    })
                    .setNegativeButton("No",null)
                    .show();



        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void setActionBarTitle(String title) {
        getSupportActionBar().setTitle(title);
    }

    public void replaceFragmentWithAnimation(android.support.v4.app.Fragment fragment, String tag){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.setCustomAnimations(R.anim.fade_in, R.anim.fade_out, R.anim.fade_in, R.anim.fade_out);
        transaction.replace(R.id.mainmenu_myfrg, fragment);
        transaction.addToBackStack(tag);
        transaction.commit();
    }

    private void hideItem(int id)
    {
        navigationView = (NavigationView) findViewById(R.id.nav_view);
        Menu nav_Menu = navigationView.getMenu();
        nav_Menu.findItem(id).setVisible(false);
    }
}
