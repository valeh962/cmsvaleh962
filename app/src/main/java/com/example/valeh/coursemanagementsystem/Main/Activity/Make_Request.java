package com.example.valeh.coursemanagementsystem.Main.Activity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.anwarshahriar.calligrapher.Calligrapher;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;


import com.example.valeh.coursemanagementsystem.Main.Fragment.Request.make_request2;
import com.example.valeh.coursemanagementsystem.Main.Fragment.Request.make_request3;
import com.example.valeh.coursemanagementsystem.Main.Fragment.Settings;
import com.example.valeh.coursemanagementsystem.R;
import com.kofigyan.stateprogressbar.StateProgressBar;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;

public class Make_Request extends AppCompatActivity {


    public static boolean requestAlreadySent = false;
    @BindView(R.id.activated_back_btn2) TextView btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
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
        calligrapher.setFont(this, "font/CirceRounded-Light.otf", true);

        ButterKnife.bind(this);

        if (!requestAlreadySent) {
            Fragment frr;
            frr = new make_request2();
            replaceFragmentWithAnimation(frr, "make_request2");
        }
        else{

        Fragment frr;
        frr = new make_request3();
        replaceFragmentWithAnimation(frr, "make_request3");
         }

        }


    @OnClick(R.id.activated_back_btn2)
    public void onclick(View v){
        switch (v.getId()){
            case R.id.activated_back_btn2:
                finish();
                break;
        }
    }





    public void replaceFragmentWithAnimation(android.support.v4.app.Fragment fragment, String tag){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.setCustomAnimations(R.anim.enter, R.anim.exit, R.anim.pop_enter, R.anim.pop_exit);
        transaction.replace(R.id.request_frg, fragment);
        transaction.addToBackStack(tag);
        transaction.commit();
    }

}
