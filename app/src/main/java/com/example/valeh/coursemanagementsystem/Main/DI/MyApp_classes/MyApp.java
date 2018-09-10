package com.example.valeh.coursemanagementsystem.Main.DI.MyApp_classes;

import android.app.Application;

import com.example.valeh.coursemanagementsystem.Main.DI.AppModule;
import com.example.valeh.coursemanagementsystem.Main.DI.BasicComponent;

public class MyApp extends Application{

    private static MyApp app;
    private BasicComponent basicComponent;
    private AppModule appModule;

    @Override
    public void onCreate() {
        super.onCreate();
        app = this;
        appModule = new AppModule(this);
        basicComponent = DaggerBasicComponent.builder()
                .appModule(new AppModule(getApplicationContext()))
                .build();

    }

    public static MyApp app(){
        return app;
    }

    public AppModule appModule() {
        return appModule;
    }

    public BasicComponent basicComponent() {
        return basicComponent;
    }
}
