package com.example.valeh.coursemanagementsystem.Main.Activity;

import es.dmoral.toasty.Toasty;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.example.valeh.coursemanagementsystem.Main.Activity.FingerprintClasses.FingerprintAdd;
import com.example.valeh.coursemanagementsystem.Main.Activity.FingerprintClasses.FingerprintAdd_Splash;
import com.example.valeh.coursemanagementsystem.Main.Activity.PinClasses.PinLogin;
import com.example.valeh.coursemanagementsystem.Main.DI.MyApp_classes.MyApp;
import com.example.valeh.coursemanagementsystem.Main.DI.SharedManagement;
import com.example.valeh.coursemanagementsystem.Main.Helpers.NetworkStatus;
import com.example.valeh.coursemanagementsystem.Main.Helpers.ServerConnectionTest;
import com.example.valeh.coursemanagementsystem.Main.JsonWorks.GetTokenForUserType.ITokenUserTypeData;
import com.example.valeh.coursemanagementsystem.Main.JsonWorks.Login.LoginResponseData;
import com.example.valeh.coursemanagementsystem.Main.JsonWorks.OTPRequest.IOTPData;
import com.example.valeh.coursemanagementsystem.R;

import java.net.SocketTimeoutException;
import java.util.concurrent.TimeUnit;

import javax.crypto.SecretKey;
import javax.inject.Inject;

public class SplashScreen extends AppCompatActivity {

    String lastToken,myRole;
    ImageView im1;
    String pin;
    String fingEnable;
    String logout;
    SharedPreferences spreferences2;
    Call<LoginResponseData> callForRole;
    LottieAnimationView animationView;
    @Inject
    SharedManagement sharedManagement;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        MyApp.app().basicComponent().in_SplashScreen(this);

        SharedPreferences spreferences1 = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        Boolean aBoolean = spreferences1.getBoolean("SCREEN_PROTECT", false);
        if(aBoolean){
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE,
                    WindowManager.LayoutParams.FLAG_SECURE);
        }
        if(!aBoolean){
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_SECURE);
        }
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        SharedPreferences fingenable = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        fingEnable = fingenable.getString("FINGERENABLED", "");
        spreferences2 = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        lastToken = spreferences2.getString("TOKEN", "");
        logout = spreferences2.getString("LOGOUT","");
        ServerConnectionTest srv = new ServerConnectionTest();
        animationView = findViewById(R.id.gradient_back);
        animationView.setSpeed(0.3f);
        im1 = (ImageView) findViewById(R.id.course_splash_txt);
     //   im2 = (ImageView) findViewById(R.id.course_splash_txt2);

        //   im2 = (ImageView) findViewById(R.id.course_splash_txt2);
        getWindow().getAttributes().windowAnimations = R.style.fade_out;
        Animation fadein = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade_in);
        Animation fadeout = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade_in);
        im1.startAnimation(fadein);



        SharedPreferences spreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        pin = spreferences.getString("PIN", "");

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(5, TimeUnit.SECONDS)
                .readTimeout(5, TimeUnit.SECONDS)
                .writeTimeout(5, TimeUnit.SECONDS)
                .build();
        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .baseUrl(ITokenUserTypeData.BASE_URL)
                .build();
        ITokenUserTypeData iTokenUserTypeData =  retrofit.create(ITokenUserTypeData.class);
        callForRole = iTokenUserTypeData.getType(lastToken);
        if(NetworkStatus.getInstance(this).isOnline()) {
            callForRole.enqueue(new Callback<LoginResponseData>() {
                @Override
                public void onResponse(Call<LoginResponseData> call, Response<LoginResponseData> response) {

                    if (response.isSuccessful()){
                        Log.d("Message", response.body().getMessage());



                    if (!response.body().equals("false")) {
                        myRole = response.body().getMessage();
                        Log.d("myRole", myRole);
                        if (!pin.equals("")) {
                            if (fingEnable.equals("1")) {
                                startActivity(new Intent(SplashScreen.this, FingerprintAdd_Splash.class));
                                finish();
                            } else {
                                PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).edit().putString("PINLOGIN", "4").apply();
                                startActivity(new Intent(SplashScreen.this, PinLogin.class));
                                finish();
                            }
                        } else {
                            PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).edit().putString("myRole", myRole).apply();
                            startActivity(new Intent(SplashScreen.this, MainMenu.class));
                            finish();
                        }
                    } else {
                        if (logout.equals("1")) {
                            startActivity(new Intent(SplashScreen.this, LoginRegister.class));
                            finish();
                        }
                        if (logout.equals("")) {
                            new AlertDialog.Builder(SplashScreen.this)
                                    .setMessage("Your session has expired, please login again to continue.")
                                    .setCancelable(false)
                                    .setPositiveButton("Logout and exit", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialogInterface, int i) {

                                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                                                SharedPreferences spreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                                                spreferences.edit().clear().commit();
                                                PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).edit().putString("LOGOUT", "1").apply();
                                                finishAffinity();
                                            }
                                        }
                                    })
                                    .setNeutralButton("Login", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            startActivity(new Intent(SplashScreen.this, LoginRegister.class));
                                            PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).edit().putString("LOGOUT", "0").apply();

                                            finish();
                                        }
                                    })
                                    .show();
                        }
                    }
                }
                else {

                        startActivity(new Intent(SplashScreen.this, LoginRegister.class));
                        PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).edit().putString("LOGOUT", "0").apply();
                        finish();
                    }
                }
                @Override
                public void onFailure(Call<LoginResponseData> call, Throwable t) {
                    if(t instanceof SocketTimeoutException){
                        // Toasty.info(SplashScreen.this,"Server is unreachable!", Toast.LENGTH_SHORT).show();
                        new AlertDialog.Builder(SplashScreen.this)
                                .setMessage("Service temporarily unavailable.")
                                .setCancelable(false)
                                .setPositiveButton("Logout and exit", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {

                                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                                            SharedPreferences spreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                                            spreferences.edit().clear().commit();
                                            PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).edit().putString("LOGOUT", "1").apply();
                                            finishAffinity();
                                        }
                                    }
                                })
                                .setNegativeButton("Login", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        startActivity(new Intent(SplashScreen.this, LoginRegister.class));
                                        finish();
                                    }
                                })
                                .setNeutralButton("Demo mode", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        chooseCategory();
                                    }
                                })
                                .show();
                    }
                }
            });
           }
        else{
            new AlertDialog.Builder(SplashScreen.this)
                    .setMessage("You do not have internet connection, please connect to internet and try again later.")
                    .setCancelable(false)
                    .setPositiveButton("Exit", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                                finishAffinity();
                            }

                        }
                    })
                    .setNegativeButton("Restart", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            Intent ic = getBaseContext().getPackageManager()
                                    .getLaunchIntentForPackage( getBaseContext().getPackageName() );
                            ic.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            startActivity(ic);
                        }
                    })
                    .setNeutralButton("Demo mode", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            chooseCategory();
                        }
                    })
                    .show();
        }
    }

    private void chooseCategory() {
        AlertDialog.Builder builder = new AlertDialog.Builder(SplashScreen.this);
        builder.setTitle("Choose your category");
        builder.setItems(new CharSequence[]
                        {"Admin", "Teacher", "Student"},
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // The 'which' argument contains the index position
                        // of the selected item
                        switch (which) {
                            case 0:
                                PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).edit().putString("TOKEN", "hello").apply();
                                PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).edit().putString("myRole", "3").apply();
                                loginTest();
                                break;
                            case 1:
                                PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).edit().putString("TOKEN", "hello").apply();

                                PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).edit().putString("myRole", "2").apply();
                                loginTest();
                                break;
                            case 2:
                                PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).edit().putString("TOKEN", "hello").apply();
                                PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).edit().putString("myRole", "1").apply();
                                loginTest();
                                break;

                        }
                    }
                });
        builder.show();

    }


    private void loginTest() {

        if(!pin.equals("")) {
            if(fingEnable.equals("1"))
            {

                startActivity(new Intent(SplashScreen.this, FingerprintAdd_Splash.class));
                finish();
            }
            else {
                PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).edit().putString("TOKEN", "hello").apply();
                PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).edit().putString("PINLOGIN", "4").apply();
                startActivity(new Intent(SplashScreen.this, PinLogin.class));
                finish();
            }
        }
        else{
            startActivity(new Intent(SplashScreen.this, MainMenu.class));
            finish();
            //      startActivity(new Intent(SplashScreen.this, MainMenu.class));
        }
    }
}
