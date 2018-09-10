package com.example.valeh.coursemanagementsystem.Main.Activity;


import es.dmoral.toasty.Toasty;
import me.anwarshahriar.calligrapher.Calligrapher;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.os.Bundle;

import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;
import android.widget.Toast;

import com.example.valeh.coursemanagementsystem.Main.Fragment.Login_request.login_request1;
import com.example.valeh.coursemanagementsystem.Main.Helpers.BaseFragment;
import com.example.valeh.coursemanagementsystem.R;

public class Login_Activity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request_2);
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


        Calligrapher calligrapher = new Calligrapher(this);
        calligrapher.setFont(this,"font/CirceRounded-Light.otf",true);


        String userId = LoginRegister.usId;


            Fragment frr;
            frr = new login_request1();
            Bundle bundle = new Bundle();
            bundle.putString("UserID", userId);
            frr.setArguments(bundle);
            replaceFragmentWithAnimation(frr, "login_request1", R.id.myfrg);



    }

    public void replaceFragmentWithAnimation(android.support.v4.app.Fragment fragment, String tag,int place){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.setCustomAnimations(R.anim.enter, R.anim.pop_exit, R.anim.exit, R.anim.pop_enter);
        transaction.replace(place, fragment);
        transaction.addToBackStack(tag);

        transaction.commit();
    }



}
