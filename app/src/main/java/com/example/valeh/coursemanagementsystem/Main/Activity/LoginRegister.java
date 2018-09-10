package com.example.valeh.coursemanagementsystem.Main.Activity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.example.valeh.coursemanagementsystem.Main.Helpers.NetworkStatus;
import com.example.valeh.coursemanagementsystem.Main.Presenter.LoginSystem.ILoginPresenter;
import com.example.valeh.coursemanagementsystem.Main.Presenter.LoginSystem.LoginPresenter;
import com.example.valeh.coursemanagementsystem.Main.View.LoginSystem.ILoginView;
import com.example.valeh.coursemanagementsystem.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import es.dmoral.toasty.Toasty;
import me.anwarshahriar.calligrapher.Calligrapher;

import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.CycleInterpolator;
import android.view.animation.TranslateAnimation;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;


public class LoginRegister extends AppCompatActivity implements ILoginView {



    @BindView(R.id.make_request_btn) TextView makerequest;
    @BindView(R.id.login_btn) Button login_btn;
    @BindView(R.id.user_email_txt) EditText userid;
    LinearLayout linear2;
    ImageView im;

    ProgressDialog progressDialog = null;
    public static String usId = null;
    ILoginPresenter loginPresenter;
    public static boolean stopThread =false;
    public static String pubID = "null";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_register);
        SharedPreferences spreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        Boolean aBoolean = spreferences.getBoolean("SCREEN_PROTECT", false);
        if(aBoolean){
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE,
                    WindowManager.LayoutParams.FLAG_SECURE);
        }
        if(!aBoolean){
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_SECURE);
        }
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);

        linear2 = findViewById(R.id.layout2);
        im = findViewById(R.id.course_splash_txt3);

        loginPresenter = new LoginPresenter(this);
        ButterKnife.bind(this);
        Calligrapher calligrapher = new Calligrapher(this);
        calligrapher.setFont(this,"font/CirceRounded-Light.otf",true);
        }

    @Override
    public void onBackPressed() {
        finish();
    }

    @OnClick({R.id.make_request_btn,R.id.login_btn})
    public void onClick(View v){
        switch (v.getId()){
            case R.id.make_request_btn:


                if(NetworkStatus.getInstance(this).isOnline()) {
                    startActivity(new Intent(LoginRegister.this, Make_Request.class));
                }
                else{
                    Toasty.info(LoginRegister.this,
                            "Please make sure if your internet connection is stable!",
                            Toast.LENGTH_SHORT).show();
                    }


                break;
            case R.id.login_btn:
                usId = userid.getText().toString();
                progressDialog = ProgressDialog.show(LoginRegister.this, "Please wait.", "Checking your ID...", true);


                hideSoftKeyboard();
                if(NetworkStatus.getInstance(this).isOnline()) {
                  //  startActivity(new Intent(LoginRegister.this,MainMenu.class));
                    loginPresenter.onLogin(usId);

                }
                else {
                    Toasty.info(LoginRegister.this,
                            "Please make sure if your internet connection is stable!",
                            Toast.LENGTH_SHORT).show();
                }

                break;
        }
    }

    @Override
    public void onLoginSuccess(String message) {
       // Toasty.success(this,message,Toast.LENGTH_SHORT).show();
        progressDialog.dismiss();
        pubID = userid.getText().toString();
        startActivity(new Intent(LoginRegister.this,Login_Activity.class));


    }

    @Override
    public void onLoginError(String message) {
        progressDialog.dismiss();
        Toasty.error(this,message,Toast.LENGTH_SHORT).show();
        showSoftKeyboard(userid);
    }



    public void hideSoftKeyboard() {
        if(getCurrentFocus()!=null) {
            InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        }
    }

    public void showSoftKeyboard(View view) {
        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        view.requestFocus();
        inputMethodManager.showSoftInput(view, 0);
    }

}
