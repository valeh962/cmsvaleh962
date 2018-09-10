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
    Call<LoginResponseData> callForRole;
    LottieAnimationView animationView;
    @Inject
    SharedManagement sharedManagement;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        MyApp.app().basicComponent().SplashScreen_inject(this);
//        sharedManagement.save("DaggerWorkTest","2","string");
//        Log.d("TEST",sharedManagement.getStringSaved("DaggerWorkTest"));
        Boolean aBoolean = sharedManagement.getBooleanSaved("SCREEN_PROTECT");
        if(aBoolean){
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE,
                    WindowManager.LayoutParams.FLAG_SECURE);
        }
        if(!aBoolean){
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_SECURE);
        }
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        fingEnable = sharedManagement.getStringSaved("FINGERENABLED");
        lastToken = sharedManagement.getStringSaved("TOKEN");
        logout = sharedManagement.getStringSaved("LOGOUT");
        ServerConnectionTest srv = new ServerConnectionTest();
        animationView = findViewById(R.id.gradient_back);
        animationView.setSpeed(0.3f);
        im1 = (ImageView) findViewById(R.id.course_splash_txt);
        getWindow().getAttributes().windowAnimations = R.style.fade_out;
        Animation fadein = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade_in);
        im1.startAnimation(fadein);
        pin = sharedManagement.getStringSaved("PIN");
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
                                sharedManagement.save("PINLOGIN",4,"int");
                                startActivity(new Intent(SplashScreen.this, PinLogin.class));
                                finish();
                            }
                        } else {
                            sharedManagement.save("myRole",myRole,"string");
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
                                                sharedManagement.save("LOGOUT","1","string");
                                                finishAffinity();
                                            }
                                        }
                                    })
                                    .setNeutralButton("Login", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            startActivity(new Intent(SplashScreen.this, LoginRegister.class));
                                            sharedManagement.save("LOGOUT","0","string");
                                            finish();
                                        }
                                    })
                                    .show();
                        }
                    }
                }
                else {
                        startActivity(new Intent(SplashScreen.this, LoginRegister.class));
                        sharedManagement.save("LOGOUT","0","string");
                        finish();
                    }
                }
                @Override
                public void onFailure(Call<LoginResponseData> call, Throwable t) {
                    if(t instanceof SocketTimeoutException){
                        new AlertDialog.Builder(SplashScreen.this)
                                .setMessage("Service temporarily unavailable.")
                                .setCancelable(false)
                                .setPositiveButton("Logout and exit", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {

                                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                                            SharedPreferences spreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                                            spreferences.edit().clear().commit();
                                            sharedManagement.save("LOGOUT","1","string");
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
                        switch (which) {
                            case 0:
                                sharedManagement.save("TOKEN","hello","string");
                                sharedManagement.save("myRole","3","string");
                                loginTest();
                                break;
                            case 1:
                                sharedManagement.save("TOKEN","hello","string");
                                sharedManagement.save("myRole","2","string");
                                loginTest();
                                break;
                            case 2:
                                sharedManagement.save("TOKEN","hello","string");
                                sharedManagement.save("myRole","1","string");
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
                sharedManagement.save("TOKEN","hello","string");
                sharedManagement.save("PINLOGIN","4","string");
                startActivity(new Intent(SplashScreen.this, PinLogin.class));
                finish();
            }
        }
        else{
            startActivity(new Intent(SplashScreen.this, MainMenu.class));
            finish();
        }
    }
}
