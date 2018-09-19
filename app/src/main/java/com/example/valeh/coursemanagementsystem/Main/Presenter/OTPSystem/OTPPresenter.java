package com.example.valeh.coursemanagementsystem.Main.Presenter.OTPSystem;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.preference.PreferenceManager;
import android.util.Log;

import com.example.valeh.coursemanagementsystem.BuildConfig;
import com.example.valeh.coursemanagementsystem.Main.Fragment.Login_request.login_request1;
import com.example.valeh.coursemanagementsystem.Main.Fragment.MainMenuLists.RequestListofTandS.teacher_main_list;
import com.example.valeh.coursemanagementsystem.Main.Helpers.RetrofitBuilder;
import com.example.valeh.coursemanagementsystem.Main.JsonWorks.GetTokenForUserType.ITokenUserTypeData;
import com.example.valeh.coursemanagementsystem.Main.JsonWorks.Login.ILoginData;
import com.example.valeh.coursemanagementsystem.Main.JsonWorks.Login.LoginResponseData;
import com.example.valeh.coursemanagementsystem.Main.JsonWorks.OTPRequest.IOTPData;
import com.example.valeh.coursemanagementsystem.Main.Model.OTPSystem.OTPModel;
import com.example.valeh.coursemanagementsystem.Main.View.OTPSystem.IOTPView;

import java.io.IOException;
import java.security.KeyStore;

import es.dmoral.toasty.Toasty;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

import static android.content.Context.MODE_PRIVATE;

public class OTPPresenter extends RetrofitBuilder implements IOTPPresenter {

    public IOTPView IotpView;
    private String tToken;
    public String myRole;
    teacher_main_list ttt = new teacher_main_list();
    Context applicationContext = login_request1.getContextOfApplication();

    public String getMyRole() {
        return myRole;
    }

    public String gettToken() {
        return tToken;
    }

    public OTPPresenter(IOTPView iotpView) {
        IotpView = iotpView;
    }

    @Override
    public void onOTPenter(String userId, String UserOTP) {



        Retrofit retrofit = buildRetrofit(IOTPData.BASE_URL);
        Log.d("USER_ID",userId);
        Log.d("USER_OTP",UserOTP);

        getToken(retrofit,userId,UserOTP);
      //  tToken = PreferenceManager.getDefaultSharedPreferences(applicationContext).getString("TOKEN", "nothing");

    }

    public void getToken(Retrofit retrofit, String userId, String UserOTP){

        OTPModel otpModel = new OTPModel(UserOTP,userId);
        Log.d("MODEL_USER_ID",otpModel.getIdNumber());
        Log.d("MODEL_USER_OTP",otpModel.getOtp());


        IOTPData iotpData = retrofit.create(IOTPData.class);

        Call<LoginResponseData> call = iotpData.sendId(otpModel);

        call.enqueue(new Callback<LoginResponseData>() {
            @Override
            public void onResponse(Call<LoginResponseData> call, Response<LoginResponseData> response) {

                if(response.isSuccessful()){
                    if (!response.body().getMessage().equals("FALSE OTP")) {
                        IotpView.onOTPSuccess("OTP code is true!");
                        tToken=response.body().getMessage();



                        PreferenceManager.getDefaultSharedPreferences(applicationContext).edit().putString("TOKEN", tToken).apply();
                        Log.d("TOKEN", tToken);
                        getRole(retrofit,tToken);
                    }
                    else
                    {
                        IotpView.onOTPFail("Wrong OTP code!");
                    }
                }
                else
                {
                    IotpView.onOTPFail("Wrong OTP code!");
                }
            }

            @Override
            public void onFailure(Call<LoginResponseData> call, Throwable t) {
                Log.d("RESPONSE_F",t.toString());
            }
        });
    }

    public void getRole(Retrofit retrofit, String tokkken){


        Log.d("tokken", tokkken);
     //   Log.d("myRole", myRole);

        ITokenUserTypeData iTokenUserTypeData =  retrofit.create(ITokenUserTypeData.class);

        Call<LoginResponseData> callForRole = iTokenUserTypeData.getType(tokkken);

        callForRole.enqueue(new Callback<LoginResponseData>() {
            @Override
            public void onResponse(Call<LoginResponseData> call, Response<LoginResponseData> response) {
                if(response.isSuccessful()) {
                    myRole = response.body().getMessage();
                    Log.d("myRole", myRole);
                    PreferenceManager.getDefaultSharedPreferences(applicationContext).edit().putString("myRole", myRole).apply();

                    Log.d("myRole1", response.body().getMessage());

                }
                else{
                    Log.d("myRole1", "failed");
                }
            }

            @Override
            public void onFailure(Call<LoginResponseData> call, Throwable t) {

            }
        });
    }
}
