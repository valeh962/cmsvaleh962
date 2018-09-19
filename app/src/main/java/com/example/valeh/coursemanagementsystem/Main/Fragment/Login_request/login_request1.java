package com.example.valeh.coursemanagementsystem.Main.Fragment.Login_request;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;


import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import es.dmoral.toasty.Toasty;
import me.anwarshahriar.calligrapher.Calligrapher;

import android.os.CountDownTimer;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.CycleInterpolator;
import android.view.animation.TranslateAnimation;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alimuzaffar.lib.pin.PinEntryEditText;
import com.example.valeh.coursemanagementsystem.Main.Activity.LoginRegister;
import com.example.valeh.coursemanagementsystem.Main.Activity.Login_Activity;
import com.example.valeh.coursemanagementsystem.Main.Helpers.BaseFragment;
import com.example.valeh.coursemanagementsystem.Main.Helpers.NetworkStatus;
import com.example.valeh.coursemanagementsystem.Main.Presenter.LoginSystem.ILoginPresenter;
import com.example.valeh.coursemanagementsystem.Main.Presenter.LoginSystem.LoginPresenter;
import com.example.valeh.coursemanagementsystem.Main.Presenter.OTPSystem.IOTPPresenter;
import com.example.valeh.coursemanagementsystem.Main.Presenter.OTPSystem.OTPPresenter;
import com.example.valeh.coursemanagementsystem.Main.View.LoginSystem.ILoginView;
import com.example.valeh.coursemanagementsystem.Main.View.OTPSystem.IOTPView;
import com.example.valeh.coursemanagementsystem.R;

import java.util.concurrent.TimeUnit;


public class login_request1 extends BaseFragment implements IOTPView, ILoginView {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private OnFragmentInteractionListener mListener;

    TextView tv;
    String pID = LoginRegister.pubID;
    ProgressDialog progressDialog = null;
    ILoginPresenter loginPresenter;
    public static Context contextOfApplication;

    @BindView(R.id.txt_pin_entry) PinEntryEditText pinEntry;
    @BindView(R.id.frameLayout_close_keyboard) FrameLayout framelayout;
    @BindView(R.id.reset_otp) Button reset_otp;

    public static final String TIMER_FORMAT = "%02d:%02d";
    int wrong_pass_time=1;
    CountDownTimer timer_otp ;
    IOTPPresenter iotpPresenter;
    TextView otp_timer ;
    String userId;

    // TODO: Rename and change types and number of parameters
    public static login_request1 newInstance(String param1, String param2) {
        login_request1 fragment = new login_request1();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        contextOfApplication = getContext().getApplicationContext();

        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_login_request1,container,false);
        ButterKnife.bind(this,rootView);
        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

       // Calligrapher calligrapher = new Calligrapher(getActivity());
       // calligrapher.setFont(getActivity(),"font/CirceRounded-Light.otf",true);
        loginPresenter = new LoginPresenter(this);
        iotpPresenter = new OTPPresenter(this);
        tv = view.findViewById(R.id.textView20);

        otp_timer = (TextView)view.findViewById(R.id.timer_otp);

        timer_otp = new CountDownTimer(60000,1000) {

            @Override
            public void onTick(long l) {
                otp_timer.setText("Time left: "+String.format(TIMER_FORMAT,
                        TimeUnit.MILLISECONDS.toMinutes(l) - TimeUnit.HOURS.toMinutes(
                                TimeUnit.MILLISECONDS.toHours(l)),
                        TimeUnit.MILLISECONDS.toSeconds(l) - TimeUnit.MINUTES.toSeconds(
                                TimeUnit.MILLISECONDS.toMinutes(l))));
            }

            @Override
            public void onFinish() {
                otp_timer.setText("Request a new OTP!");
                pinEntry.setVisibility(View.GONE);
                tv.setVisibility(View.GONE);
            }
        }.start();

        Bundle bundle = this.getArguments();
        if (bundle != null) {
            userId = bundle.getString("UserID");
        }

        if (pinEntry != null) {
            pinEntry.setOnPinEnteredListener(new PinEntryEditText.OnPinEnteredListener() {
                @Override
                public void onPinEntered(CharSequence str) {
                    progressDialog = ProgressDialog.show(getActivity(), "Please wait.", "Checking OTP...", true);

                    iotpPresenter.onOTPenter(userId,str.toString());

                }
            });
        }
    }

    @OnClick({R.id.reset_otp,R.id.frameLayout_close_keyboard})
    public void onClick(View v){
        switch (v.getId()){
            case R.id.reset_otp:
                wrong_pass_time = 1;
                progressDialog = ProgressDialog.show(getActivity(), "Please wait.", "Sending new OTP...", true);

                if(NetworkStatus.getInstance(getActivity()).isOnline()) {
                    //  startActivity(new Intent(LoginRegister.this,MainMenu.class));
                    loginPresenter.onLogin(pID);
                }
                else {
                    Toasty.info(getActivity(),
                            "Please make sure if your internet connection is stable!",
                            Toast.LENGTH_SHORT).show();
                }


                break;

            case R.id.frameLayout_close_keyboard:

                getActivity().finish();
                break;
        }
    }

    public static Context getContextOfApplication(){
        return contextOfApplication;
    }

    @Override
    public void onOTPSuccess(String message) {

        Fragment fr = new login_request2();

        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                progressDialog.cancel();
                replaceFragmentWithAnimation(fr,"login_request2",R.id.myfrg);
            }
        }, 2000);
    }

    @Override
    public void onOTPFail(String message) {

        progressDialog.cancel();
        if(wrong_pass_time>=4)
        {
            otp_timer.setText("Wrong OTP entered 4 times, please request for a new OTP!");
            otp_timer.setTextColor(Color.RED);
            timer_otp.cancel();
            pinEntry.setVisibility(View.GONE);

        }

        Toast.makeText(getActivity(), "Pin entered "+wrong_pass_time +" times wrong!", Toast.LENGTH_SHORT).show();
        wrong_pass_time++;
        pinEntry.setText(null);

        Toasty.error(getActivity(),message,Toast.LENGTH_SHORT).show();

    }

    // TODO: Rename method, update argument and hook method into UI event

    @Override
    public void onResume() {
        super.onResume();

        if(getView() == null){
            return;
        }

        getView().setFocusableInTouchMode(true);
        getView().requestFocus();
        getView().setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {

                if (event.getAction() == KeyEvent.ACTION_UP && keyCode == KeyEvent.KEYCODE_BACK){
                    // startActivity(new Intent(getActivity(),LoginRegister.class));
                    new Login_Activity().finish();
                    return true;
                }
                return false;
            }
        });
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onLoginSuccess(String message) {
        tv.setVisibility(View.VISIBLE);
        pinEntry.setVisibility(View.VISIBLE);
        pinEntry.requestFocus();
        otp_timer.setTextColor(Color.WHITE);
        timer_otp.cancel();
        timer_otp.start();
        wrong_pass_time = 1;
        progressDialog.cancel();
        Snackbar.make(getActivity().findViewById(R.id.myfrg),"New OTP sent to your email!",Snackbar.LENGTH_LONG).show();

    }

    @Override
    public void onLoginError(String message) {
        progressDialog.cancel();
        Snackbar.make(getActivity().findViewById(R.id.myfrg),"Error! Please restart application.",Snackbar.LENGTH_LONG).show();

    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
