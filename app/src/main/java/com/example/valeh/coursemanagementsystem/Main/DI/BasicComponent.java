package com.example.valeh.coursemanagementsystem.Main.DI;


import com.example.valeh.coursemanagementsystem.Main.Activity.MainMenu;
import com.example.valeh.coursemanagementsystem.Main.Activity.SplashScreen;
import com.example.valeh.coursemanagementsystem.Main.Fragment.Groups.GroupDetails.GroupDetails;
import com.example.valeh.coursemanagementsystem.Main.Fragment.Groups.GroupDetails.GroupDetails_adapter;
import com.example.valeh.coursemanagementsystem.Main.Fragment.Groups.GroupDetails.GroupMemberDetails.GroupMemberDetails;
import com.example.valeh.coursemanagementsystem.Main.Fragment.Groups.GroupDetails.GroupSchedule.GroupSchedule;
import com.example.valeh.coursemanagementsystem.Main.Fragment.Groups.Group_Add.AddNewGroup;
import com.example.valeh.coursemanagementsystem.Main.Fragment.Groups.MyGroups;
import com.example.valeh.coursemanagementsystem.Main.Fragment.Login_request.login_request2;
import com.example.valeh.coursemanagementsystem.Main.Fragment.MainMenuLists.FilterPersons.FilteredPersons;
import com.example.valeh.coursemanagementsystem.Main.Fragment.MainMenuLists.TeacherandStudents.Pages.infortions_adv_1;
import com.example.valeh.coursemanagementsystem.Main.Fragment.MainMenuLists.TeacherandStudents.Pages.infortions_adv_2;
import com.example.valeh.coursemanagementsystem.Main.Fragment.MainMenuLists.TeacherandStudents.Pages.infortions_adv_3;
import com.example.valeh.coursemanagementsystem.Main.Fragment.MainMenuLists.TeacherandStudents.Pages.infortions_adv_4;
import com.example.valeh.coursemanagementsystem.Main.Fragment.MainMenuLists.TeacherandStudents.teachers_accepted;
import com.example.valeh.coursemanagementsystem.Main.Fragment.Members.Member_details;
import com.example.valeh.coursemanagementsystem.Main.Fragment.Members.Member_details_tcr;
import com.example.valeh.coursemanagementsystem.Main.Fragment.Members.Members_students;
import com.example.valeh.coursemanagementsystem.Main.Fragment.Members.Members_teachers;
import com.example.valeh.coursemanagementsystem.Main.Fragment.Settings_Fragments.Profile.MyProfile;

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
    void MyProfile_inject(MyProfile myProfile);
    void GroupDetails_adapter_inject(GroupDetails_adapter groupDetails_adapter);
    void login_request2_inject(login_request2 login_request2);
    void Members_students_inject(Members_students members_students);
    void Members_teachers_inject(Members_teachers members_teachers);
    void GroupMemberDetails_inject(GroupMemberDetails groupMemberDetails);
    void AddNewGroup_inject(AddNewGroup addNewGroup);
    void infort1_inject(infortions_adv_1 infortions_adv_1);
    void infort2_inject(infortions_adv_2 infortions_adv_2);
    void infort3_inject(infortions_adv_3 infortions_adv_3);
    void infort4_inject(infortions_adv_4 infortions_adv_4);
    void teachers_accepted(teachers_accepted teachers_accepted);
    void Member_details_inject(Member_details member_details);
    void Member_details_tcr_inject(Member_details_tcr member_details_tcr);
    void GroupSchedule_inject(GroupSchedule groupSchedule);
}
