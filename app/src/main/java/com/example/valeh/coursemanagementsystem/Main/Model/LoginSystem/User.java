package com.example.valeh.coursemanagementsystem.Main.Model.LoginSystem;

import android.util.Log;

public class User implements IUser{



    String userId;

    public  static  int isvalid;
    Boolean resp = false;
    String booool;


    public User(String userId) {
        this.userId = userId;
    }

    @Override
    public String getUserId() {
        return userId;
    }


    public void validation(){








    }


    @Override
    public int isValidUserId() {


        Log.d("RESPONSE_S", " " + isvalid);


            if (isvalid == 1)
                return 1;
            else
                return 2;
        }






}
