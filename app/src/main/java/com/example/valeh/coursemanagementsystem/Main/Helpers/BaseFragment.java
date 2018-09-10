package com.example.valeh.coursemanagementsystem.Main.Helpers;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.example.valeh.coursemanagementsystem.Main.Activity.MainMenu;
import com.example.valeh.coursemanagementsystem.R;


public class BaseFragment extends Fragment {

    public void replaceFragmentWithAnimation(android.support.v4.app.Fragment fragment, String tag, int layout){
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.setCustomAnimations(R.anim.fade_in, R.anim.fade_out, R.anim.fade_in, R.anim.fade_out);
        transaction.replace(layout, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
    public void replaceFragmentWithAnimation1(android.support.v4.app.Fragment fragment, String tag, int layout){

        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.setCustomAnimations(R.anim.enter, R.anim.exit, R.anim.enter, R.anim.exit);
        transaction.replace(layout, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
    public void clearBackStack(android.support.v4.app.Fragment fragment) {
        FragmentManager manager = getActivity().getSupportFragmentManager();
        FragmentTransaction trans = manager.beginTransaction();
        trans.remove(fragment);
        trans.commit();
        manager.popBackStack();
    }
}
