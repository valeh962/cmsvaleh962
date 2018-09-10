package com.example.valeh.coursemanagementsystem.Main.DI;


import com.example.valeh.coursemanagementsystem.Main.Activity.SplashScreen;
import com.example.valeh.coursemanagementsystem.Main.Fragment.MainMenuLists.FilterPersons.FilteredPersons;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {AppModule.class})
public interface BasicComponent {
    void SplashScreen_inject(SplashScreen splashScreen);
    void FilteredPersons_inject(FilteredPersons filteredPersons);
}
