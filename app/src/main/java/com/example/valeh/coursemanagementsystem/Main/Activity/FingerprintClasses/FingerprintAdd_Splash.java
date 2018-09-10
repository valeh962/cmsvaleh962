package com.example.valeh.coursemanagementsystem.Main.Activity.FingerprintClasses;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.hardware.fingerprint.FingerprintManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewSwitcher;

import com.example.valeh.coursemanagementsystem.Main.Activity.MainMenu;
import com.example.valeh.coursemanagementsystem.Main.Activity.PinClasses.PinChange;
import com.example.valeh.coursemanagementsystem.Main.Activity.PinClasses.PinLogin;
import com.example.valeh.coursemanagementsystem.R;
import com.multidots.fingerprintauth.AuthErrorCodes;
import com.multidots.fingerprintauth.FingerPrintAuthCallback;
import com.multidots.fingerprintauth.FingerPrintAuthHelper;

import es.dmoral.toasty.Toasty;

public class FingerprintAdd_Splash extends AppCompatActivity implements FingerPrintAuthCallback {

    TextView byPin;
    private FingerPrintAuthHelper mFingerPrintAuthHelper;
    private String encpin;
    ViewSwitcher viewSwitcher;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fingerprint_add);
        byPin = findViewById(R.id.textView10);
        viewSwitcher = findViewById(R.id.viewswitch1);
        SharedPreferences spreferences1 = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        Boolean aBoolean = spreferences1.getBoolean("SCREEN_PROTECT", false);
        //   mFingerPrintAuthHelper = FingerPrintAuthHelper.getHelper(this, this);
        if(aBoolean){
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE,
                    WindowManager.LayoutParams.FLAG_SECURE);
        }
        if(!aBoolean){
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_SECURE);
        }
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        mFingerPrintAuthHelper = FingerPrintAuthHelper.getHelper(this, this);
        SharedPreferences spreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        encpin = spreferences.getString("PIN", "");

        byPin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).edit().putString("PINLOGIN", "4").apply();
                startActivity(new Intent(FingerprintAdd_Splash.this,PinLogin.class));
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        //start finger print authentication
        mFingerPrintAuthHelper.startAuth();
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            mFingerPrintAuthHelper.stopAuth();
        }
    }

    @Override
    public void onNoFingerPrintHardwareFound() {
        //Device does not have finger print scanner.
        Toasty.info(getApplicationContext(),"No fingerprint hardware!", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNoFingerPrintRegistered() {
       // startActivity(new Intent(android.provider.Settings.ACTION_SECURITY_SETTINGS));
        //There are no finger prints registered on this device.
        new AlertDialog.Builder(this)
                .setMessage("You haven't registered any fingerprints, do you want to enroll now?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        startActivity(new Intent(android.provider.Settings.ACTION_SECURITY_SETTINGS));
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).edit().putString("PINLOGIN", "4").apply();
                        startActivity(new Intent(FingerprintAdd_Splash.this,PinLogin.class));
                    }
                })
                .show();

    }

    @Override
    public void onBelowMarshmallow() {
        //Device running below API 23 version of android that does not support finger print authentication.
        Toasty.info(getApplicationContext(),"No fingerprint hardware!",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onAuthSuccess(FingerprintManager.CryptoObject cryptoObject) {
        viewSwitcher.showNext();
        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
    if(!encpin.equals("")){
        //viewSwitcher.showNext();
        startActivity(new Intent(FingerprintAdd_Splash.this, MainMenu.class));
    }
            }
        }, 500);
    }

    @Override
    public void onAuthFailed(int errorCode, String errorMessage) {
        switch (errorCode) {    //Parse the error code for recoverable/non recoverable error.
            case AuthErrorCodes.CANNOT_RECOGNIZE_ERROR:
                Toasty.info(getApplicationContext(),"Can not recognize fingerprint!",Toast.LENGTH_SHORT).show();
                break;
            case AuthErrorCodes.NON_RECOVERABLE_ERROR:
                //This is not recoverable error. Try other options for user authentication. like pin, password.
                break;
            case AuthErrorCodes.RECOVERABLE_ERROR:
                //Any recoverable error. Display message to the user.
                break;
        }
    }
}
