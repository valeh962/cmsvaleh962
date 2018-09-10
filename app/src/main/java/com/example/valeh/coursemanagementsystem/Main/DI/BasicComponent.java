package com.example.valeh.coursemanagementsystem.Main.DI;


import com.example.valeh.coursemanagementsystem.Main.Activity.SplashScreen;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {AppModule.class})
public interface BasicComponent {
    void in_SplashScreen(SplashScreen splashScreen);
}
