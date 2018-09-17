package com.example.valeh.coursemanagementsystem.Main.Helpers;

import android.content.Context;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class RetrofitBuilder {



    public static Retrofit buildRetrofit(String BASE_URL){

        OkHttpClient.Builder client = new OkHttpClient.Builder();
        client.connectTimeout(5, TimeUnit.SECONDS);
        client.readTimeout(5, TimeUnit.SECONDS);
        client.writeTimeout(5, TimeUnit.SECONDS);

                 Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                         .client(client.build())
                            .baseUrl(BASE_URL)
                                .build();

                 return retrofit;
    }


    public static Retrofit buildRetrofitrx(String Base_URL) {
        Retrofit retrofitt = null;
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(5, TimeUnit.SECONDS)
                .readTimeout(5, TimeUnit.SECONDS)
                .writeTimeout(5, TimeUnit.SECONDS)
                .build();
            if (retrofitt == null) {
                retrofitt = new Retrofit.Builder()
                        .addConverterFactory(GsonConverterFactory.create())
                        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                        .baseUrl(Base_URL)
                        .client(okHttpClient)
                        .build();
            }
            return retrofitt;
        }


}
