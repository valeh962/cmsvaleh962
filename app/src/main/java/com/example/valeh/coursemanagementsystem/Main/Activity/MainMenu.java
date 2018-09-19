package com.example.valeh.coursemanagementsystem.Main.Activity;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;

import com.example.valeh.coursemanagementsystem.Main.Activity.PinClasses.PinChange;
import com.example.valeh.coursemanagementsystem.Main.DI.MyApp_classes.MyApp;
import com.example.valeh.coursemanagementsystem.Main.DI.SharedManagement;
import com.example.valeh.coursemanagementsystem.Main.Fragment.Groups.GroupDetails.GroupDetails;
import com.example.valeh.coursemanagementsystem.Main.Fragment.Groups.GroupDetails.GroupMemberDetails.GroupMemberDetails;
import com.example.valeh.coursemanagementsystem.Main.Fragment.Groups.GroupDetails.GroupSchedule.GroupSchedule;
import com.example.valeh.coursemanagementsystem.Main.Fragment.Groups.Group_Add.AddNewGroup;
import com.example.valeh.coursemanagementsystem.Main.Fragment.Groups.MyGroups;
import com.example.valeh.coursemanagementsystem.Main.Fragment.HomeScreens.Student.Home_student;
import com.example.valeh.coursemanagementsystem.Main.Fragment.MainMenuLists.FilterPersons.FilteredPersons;
import com.example.valeh.coursemanagementsystem.Main.Fragment.MainMenuLists.RequestListofTandS.Teacher_details;
import com.example.valeh.coursemanagementsystem.Main.Fragment.MainMenuLists.RequestListofTandS.teacher_main_list;
import com.example.valeh.coursemanagementsystem.Main.Fragment.MainMenuLists.TeacherandStudents.Pages.infortions_adv_1;
import com.example.valeh.coursemanagementsystem.Main.Fragment.MainMenuLists.TeacherandStudents.Pages.infortions_adv_2;
import com.example.valeh.coursemanagementsystem.Main.Fragment.MainMenuLists.TeacherandStudents.Pages.infortions_adv_3;
import com.example.valeh.coursemanagementsystem.Main.Fragment.MainMenuLists.TeacherandStudents.Pages.infortions_adv_4;
import com.example.valeh.coursemanagementsystem.Main.Fragment.MainMenuLists.TeacherandStudents.edit_requests_list;
import com.example.valeh.coursemanagementsystem.Main.Fragment.MainMenuLists.TeacherandStudents.teachers_accepted;
import com.example.valeh.coursemanagementsystem.Main.Fragment.Members.Member_details;
import com.example.valeh.coursemanagementsystem.Main.Fragment.Members.Member_details_tcr;
import com.example.valeh.coursemanagementsystem.Main.Fragment.Members.Members_students;
import com.example.valeh.coursemanagementsystem.Main.Fragment.Members.Members_teachers;
import com.example.valeh.coursemanagementsystem.Main.Fragment.PersonTypeList.Menu_lists;
import com.example.valeh.coursemanagementsystem.Main.Fragment.PersonTypeList.Menu_lists_1;
import com.example.valeh.coursemanagementsystem.Main.Fragment.Settings;
import com.example.valeh.coursemanagementsystem.Main.Fragment.Settings_Fragments.Profile.MyProfile;
import com.example.valeh.coursemanagementsystem.Main.Fragment.Settings_Fragments.Profile.MyProfile_A;
import com.example.valeh.coursemanagementsystem.Main.Fragment.home_mainmenu;
import com.example.valeh.coursemanagementsystem.R;
import com.jaeger.library.StatusBarUtil;

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
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import javax.inject.Inject;

public class MainMenu extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    Toolbar toolbar;

    @Inject
    SharedManagement sharedManagement;

    NavigationView navigationView;
    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
        MyApp.app().basicComponent().MainMenu_inject(this);
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
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setCheckedItem(R.id.home_1);
        View navhead = navigationView.getHeaderView(0);
        TextView tv1 = navhead.findViewById(R.id.textView26);
        TextView tv2 = navhead.findViewById(R.id.textView29);
        tv1.setText(sharedManagement.getStringSaved("UserName")+" "+sharedManagement.getStringSaved("UserSurname"));
        tv2.setText(sharedManagement.getStringSaved("UserIdNumber"));
        navhead.setOnClickListener(v -> {

            startActivity(new Intent(MainMenu.this, MyProfile_A.class));
            drawer.closeDrawers();

//            setActionBarTitle("My profile");
//            Fragment ft = new MyProfile();
//            Fragment currentFragment = getSupportFragmentManager().findFragmentById(R.id.mainmenu_myfrg);
//            if (currentFragment instanceof MyProfile) {drawer.closeDrawers();}
//
//            else {
//                replaceFragmentWithAnimation(ft, "home_mainmenu");
//                drawer.closeDrawers();
//            }
        });
        Fragment frr;
        frr = new Home_student();
        setActionBarTitle("Home");
        replaceFragmentWithAnimation(frr,"Home_student");
        String P_TYPE_N;
        P_TYPE_N = PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).getString("UserRoleId", "");
        if(P_TYPE_N.contains("1") && !P_TYPE_N.contains("2") && !P_TYPE_N.contains("3")){
            hideItem(R.id.editRequests);
            hideItem(R.id.nav_camera);
            hideItem(R.id.groupeditor);
            hideItem(R.id.filtered);
            hideItem(R.id.memberteacher);
            hideItem(R.id.membersstudent);
        }
        if(P_TYPE_N.equals("2") && !P_TYPE_N.contains("1") && !P_TYPE_N.contains("3")){
            hideItem(R.id.editRequests);
            hideItem(R.id.memberteacher);
            hideItem(R.id.filtered);
        }
        if(P_TYPE_N.equals("2") && P_TYPE_N.contains("1") && !P_TYPE_N.contains("3")){
            hideItem(R.id.filtered);
            hideItem(R.id.editRequests);
            hideItem(R.id.memberteacher);
        }
       // if(sharedManagement.getStringSaved("SuggestSecurity").equals()){

     //   }

    }



    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        }


        Fragment fr_mainScreen = new Home_student();
        Fragment currentFragment = getSupportFragmentManager().findFragmentById(R.id.mainmenu_myfrg);
        Fragment currentFragment_infortions = getSupportFragmentManager().findFragmentById(R.id.adv_frag);
        if (currentFragment instanceof Home_student) {
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

        if(currentFragment_infortions instanceof infortions_adv_4 && currentFragment instanceof teachers_accepted){
            replaceFragmentWithAnimation_infortions(new infortions_adv_3(),"infortions_adv_3");
        }
        if(currentFragment_infortions instanceof infortions_adv_3 && currentFragment instanceof teachers_accepted){
            replaceFragmentWithAnimation_infortions(new infortions_adv_2(),"infortions_adv_2");
        }
        if(currentFragment_infortions instanceof infortions_adv_2 && currentFragment instanceof teachers_accepted){
            replaceFragmentWithAnimation_infortions(new infortions_adv_1(),"infortions_adv_1");
        }
        if(currentFragment_infortions instanceof infortions_adv_1 && currentFragment instanceof teachers_accepted){
            replaceFragmentWithAnimation(new edit_requests_list(),"edit_requests_list");
        }
        if(currentFragment instanceof Teacher_details){
            replaceFragmentWithAnimation(new teacher_main_list(),"teacher_main_list");
            setActionBarTitle("Edit requests");
        }
        if(currentFragment instanceof GroupMemberDetails){
            replaceFragmentWithAnimation(new GroupDetails(),"GroupDetails");
            setActionBarTitle("Group details");
        }
        if(currentFragment instanceof GroupDetails){
            replaceFragmentWithAnimation(new MyGroups(),"MyGroups");
            setActionBarTitle("My groups");
        }
        if(currentFragment instanceof MyProfile){
            replaceFragmentWithAnimation(new Settings(),"Settings");
            setActionBarTitle("Settings");
        }
        if(currentFragment instanceof Settings){
            replaceFragmentWithAnimation(fr_mainScreen,"Home");
            navigationView.setCheckedItem(R.id.home_1);
            setActionBarTitle("Home");
        }
        if(currentFragment instanceof MyGroups){
            replaceFragmentWithAnimation(fr_mainScreen,"Home");
            navigationView.setCheckedItem(R.id.home_1);
            setActionBarTitle("Home");
        }
        if(currentFragment instanceof AddNewGroup){
            replaceFragmentWithAnimation(fr_mainScreen,"Home");
            navigationView.setCheckedItem(R.id.home_1);
            setActionBarTitle("Home");
        }
        if(currentFragment instanceof edit_requests_list){
            replaceFragmentWithAnimation(new Menu_lists(),"Menu_lists");
            setActionBarTitle("Edit requests");
        }
        if(currentFragment instanceof teacher_main_list){
            replaceFragmentWithAnimation(new Menu_lists_1(),"Menu_lists_1");
            setActionBarTitle("Request list");
        }
        if(currentFragment instanceof FilteredPersons){
            replaceFragmentWithAnimation(fr_mainScreen,"Home");
            navigationView.setCheckedItem(R.id.home_1);
            setActionBarTitle("Home");
        }
        if(currentFragment instanceof Menu_lists){
            replaceFragmentWithAnimation(fr_mainScreen,"Home");
            navigationView.setCheckedItem(R.id.home_1);
            setActionBarTitle("Home");
        }
        if(currentFragment instanceof Menu_lists_1){
            replaceFragmentWithAnimation(fr_mainScreen,"Home");
            navigationView.setCheckedItem(R.id.home_1);
            setActionBarTitle("Home");
        }
        if(currentFragment instanceof Members_teachers){
            replaceFragmentWithAnimation(fr_mainScreen,"Home");
            navigationView.setCheckedItem(R.id.home_1);
            setActionBarTitle("Home");
        }
        if(currentFragment instanceof Members_students){
            replaceFragmentWithAnimation(fr_mainScreen,"Home");
            navigationView.setCheckedItem(R.id.home_1);
            setActionBarTitle("Home");
        }
        if(currentFragment instanceof GroupSchedule){
            replaceFragmentWithAnimation(new GroupDetails(),"GroupDetails");
            setActionBarTitle("Group details");
        }
        if(currentFragment instanceof Teacher_details){
            replaceFragmentWithAnimation(new teacher_main_list(),"Teacher_details");
            setActionBarTitle("Request list");
        }
        if(currentFragment instanceof Member_details){
            replaceFragmentWithAnimation(new Members_students(),"Members_students");
            setActionBarTitle("Students");
        }
        if(currentFragment instanceof Member_details_tcr){
            replaceFragmentWithAnimation(new Members_teachers(),"Members_teachers");
            setActionBarTitle("Teachers");
        }

//        else
//        {
//            replaceFragmentWithAnimation(fr_mainScreen,"Home");
//            setActionBarTitle("Home");
//        }


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {

            setActionBarTitle("Settings");
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

        if(id == R.id.membersstudent){
            setActionBarTitle("Students");
            Fragment ft = new Members_students();
            Fragment currentFragment = getSupportFragmentManager().findFragmentById(R.id.mainmenu_myfrg);
            if (currentFragment instanceof Members_students) {}

            else {
                replaceFragmentWithAnimation(ft, "Members_students");
            }
        }
        if(id == R.id.groupeditor){
            setActionBarTitle("Edit groups");
            Fragment ft = new AddNewGroup();
            Fragment currentFragment = getSupportFragmentManager().findFragmentById(R.id.mainmenu_myfrg);
            if (currentFragment instanceof AddNewGroup) {}

            else {
                replaceFragmentWithAnimation(ft, "AddNewGroup");
            }
        }

        if(id == R.id.memberteacher){
            setActionBarTitle("Teachers");
            Fragment ft = new Members_teachers();
            Fragment currentFragment = getSupportFragmentManager().findFragmentById(R.id.mainmenu_myfrg);
            if (currentFragment instanceof Members_teachers) {}

            else {
                replaceFragmentWithAnimation(ft, "Members_teachers");
            }
        }

        if(id == R.id.groups){
            setActionBarTitle("My groups");
            Fragment ft = new MyGroups();
            Fragment currentFragment = getSupportFragmentManager().findFragmentById(R.id.mainmenu_myfrg);
            if (currentFragment instanceof MyGroups) {}

            else {
                replaceFragmentWithAnimation(ft, "MyGroups");
            }
        }

        if (id == R.id.home_1) {


            setActionBarTitle("Home");
            Fragment ft = new Home_student();
          //  replaceFragmentWithAnimation(ft,"home_mainmenu");
            Fragment currentFragment = getSupportFragmentManager().findFragmentById(R.id.mainmenu_myfrg);
            if (currentFragment instanceof Home_student) {}

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
        transaction.addToBackStack(null);
        transaction.commit();
    }

    public void replaceFragmentWithAnimation_infortions(android.support.v4.app.Fragment fragment, String tag){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.setCustomAnimations(R.anim.fade_in, R.anim.fade_out, R.anim.fade_in, R.anim.fade_out);
        transaction.replace(R.id.adv_frag, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    private void hideItem(int id)
    {
        navigationView = (NavigationView) findViewById(R.id.nav_view);
        Menu nav_Menu = navigationView.getMenu();
        nav_Menu.findItem(id).setVisible(false);
    }
}
