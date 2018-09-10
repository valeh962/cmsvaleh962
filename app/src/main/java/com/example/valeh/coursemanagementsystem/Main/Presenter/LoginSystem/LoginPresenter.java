package com.example.valeh.coursemanagementsystem.Main.Presenter.LoginSystem;

import android.util.Log;

import com.example.valeh.coursemanagementsystem.Main.Helpers.RetrofitBuilder;
import com.example.valeh.coursemanagementsystem.Main.JsonWorks.Login.ILoginData;
import com.example.valeh.coursemanagementsystem.Main.JsonWorks.Login.LoginResponseData;
import com.example.valeh.coursemanagementsystem.Main.Model.LoginSystem.IUser;
import com.example.valeh.coursemanagementsystem.Main.Model.LoginSystem.User;
import com.example.valeh.coursemanagementsystem.Main.View.LoginSystem.ILoginView;

import java.net.SocketTimeoutException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class LoginPresenter extends RetrofitBuilder implements ILoginPresenter {

    public ILoginView LoginView;

    public LoginPresenter(ILoginView loginView) {
        LoginView = loginView;
    }

    @Override
    public void onLogin(String userId) {
        if(userId.length()>0) {
        Retrofit retrofit = buildRetrofit(ILoginData.BASE_URL);
        ILoginData iLoginData = retrofit.create(ILoginData.class);
        Call<LoginResponseData> call = iLoginData.sendId(userId);
            call.enqueue(new Callback<LoginResponseData>() {
                @Override
                public void onResponse(Call<LoginResponseData> call, Response<LoginResponseData> response) {

                    if (response.isSuccessful()) {
                        if (response.body().getMessage().equals("true")) {
                            LoginView.onLoginSuccess("Welcome!");
                        } if (response.body().getMessage().equals("false idNumber")) {
                            LoginView.onLoginError("Invalid user identification number!");
                        }
                        Log.d("RESPONSE_S", response.body().getMessage().toString());
                    }
                    else{
                        LoginView.onLoginError("Invalid user identification number!");
                    }
                  }
                @Override
                public void onFailure(Call<LoginResponseData> call, Throwable t) {
                    if(t instanceof SocketTimeoutException){
                        LoginView.onLoginError("Something went wrong, please check your identification number again!");
                    }
                }
            });
        }
        else{
            LoginView.onLoginError("Please write correct ID!");
        }
    }
}
