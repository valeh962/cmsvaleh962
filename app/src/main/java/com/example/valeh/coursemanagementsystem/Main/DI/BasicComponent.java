package com.example.valeh.coursemanagementsystem.Main.DI;


import com.example.valeh.coursemanagementsystem.Main.Activity.MainMenu;
import com.example.valeh.coursemanagementsystem.Main.Activity.SplashScreen;
import com.example.valeh.coursemanagementsystem.Main.Fragment.Groups.GroupDetails.GroupDetails;
import com.example.valeh.coursemanagementsystem.Main.Fragment.Groups.GroupDetails.GroupDetails_adapter;
import com.example.valeh.coursemanagementsystem.Main.Fragment.Groups.MyGroups;
import com.example.valeh.coursemanagementsystem.Main.Fragment.MainMenuLists.FilterPersons.FilteredPersons;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {AppModule.class})
public interface BasicComponent {
    void SplashScreen_inject(SplashScreen splashScreen);
    void FilteredPersons_inject(FilteredPersons filteredPersons);
    void MainMenu_inject(MainMenu mainMenu);
    void MyGroups_inject(MyGroups myGroups);
    void GroupDetails_inject(GroupDetails groupDetails);
    void GroupDetails_adapter_inject(GroupDetails_adapter groupDetails_adapter);
}
