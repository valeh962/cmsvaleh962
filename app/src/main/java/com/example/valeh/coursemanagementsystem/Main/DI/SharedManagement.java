package com.example.valeh.coursemanagementsystem.Main.DI;

import android.content.SharedPreferences;

import javax.inject.Inject;

public class SharedManagement {

    private SharedPreferences sharedPreferences;


    public SharedManagement(SharedPreferences sharedPreferences) {
        this.sharedPreferences = sharedPreferences;
    }

    public void save(String key, Object o, String type){

        type = type.toLowerCase();

        if(type.equals("string")) {
            sharedPreferences.edit()
                    .putString(key, o.toString())
                    .apply();
        }
        if(type.equals("int")) {
            sharedPreferences.edit()
                    .putInt(key, Integer.parseInt(o.toString()))
                    .apply();
        }
        if(type.equals("boolean")) {
            sharedPreferences.edit()
                    .putBoolean(key, Boolean.parseBoolean(o.toString()))
                    .apply();
        }
        if(type.equals("float")) {
            sharedPreferences.edit()
                    .putFloat(key, Float.parseFloat(o.toString()))
                    .apply();
        }

    }

    public int getIntSaved(String key){
        int saved = sharedPreferences.getInt(key, 0);
        return saved;
    }
    public String getStringSaved(String key){
        String saved = sharedPreferences.getString(key, "");
        return saved;
    }
    public Boolean getBooleanSaved(String key){
        Boolean saved = sharedPreferences.getBoolean(key, false);
        return saved;
    }
    public float getFloatSaved(String key){
        float saved = sharedPreferences.getFloat(key, 0);
        return saved;
    }

}
