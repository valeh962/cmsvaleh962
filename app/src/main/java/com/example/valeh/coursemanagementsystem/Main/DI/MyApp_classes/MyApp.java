package com.example.valeh.coursemanagementsystem.Main.DI.MyApp_classes;

import android.app.Application;

import com.example.valeh.coursemanagementsystem.Main.DI.AppModule;
import com.example.valeh.coursemanagementsystem.Main.DI.BasicComponent;
import com.example.valeh.coursemanagementsystem.Main.DI.DaggerBasicComponent;

public class MyApp extends Application{

    private static MyApp app;
    private BasicComponent basicComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        app = this;
        basicComponent = DaggerBasicComponent.builder()
                .appModule(new AppModule(getApplicationContext()))
                .build();

    }

    public static MyApp app(){
        return app;
    }

    public BasicComponent basicComponent() {
        return basicComponent;
    }
}
