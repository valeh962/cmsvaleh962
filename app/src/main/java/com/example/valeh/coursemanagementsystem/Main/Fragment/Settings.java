package com.example.valeh.coursemanagementsystem.Main.Fragment;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import android.support.v4.app.Fragment;


import com.example.valeh.coursemanagementsystem.Main.Activity.FingerprintClasses.FingerprintAdd;
import com.example.valeh.coursemanagementsystem.Main.Activity.LoginRegister;
import com.example.valeh.coursemanagementsystem.Main.Activity.PinClasses.PinChange;
import com.example.valeh.coursemanagementsystem.Main.Activity.PinClasses.PinLogin;
import com.example.valeh.coursemanagementsystem.Main.Helpers.BaseFragment;
import com.example.valeh.coursemanagementsystem.Main.Fragment.Settings_Fragments.Profile.MyProfile;
import com.example.valeh.coursemanagementsystem.R;
import com.yakivmospan.scytale.Crypto;
import com.yakivmospan.scytale.Options;
import com.yakivmospan.scytale.Store;

import javax.crypto.SecretKey;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Settings.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Settings#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Settings extends BaseFragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    TextView editPin,deletePin,finger_switch;
    Switch prtc_screen;
    Context context = null;
    String pin;
    Store store;
    String nboo;
    SecretKey key;
    Crypto crypto;

    private OnFragmentInteractionListener mListener;

    public Settings() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static Settings newInstance(String param1, String param2) {
        Settings fragment = new Settings();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);





        getActivity().getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

//        ((MainMenu) getActivity())
//                .setActionBarTitle("Settings");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_settings,container,false);
        return rootView;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        store = new Store(getContext());
        if (!store.hasKey("coursems")) {
            SecretKey key = store.generateSymmetricKey("coursems", null);
        }
        key = store.getSymmetricKey("coursems", null);
        crypto = new Crypto(Options.TRANSFORMATION_SYMMETRIC);
        SharedPreferences spreferences = PreferenceManager.getDefaultSharedPreferences(getContext());
        pin = spreferences.getString("PIN", "");

        SharedPreferences spreferences1 = PreferenceManager.getDefaultSharedPreferences(getContext());
        Boolean aBoolean = spreferences1.getBoolean("SCREEN_PROTECT", false);
        nboo = spreferences1.getString("FINGERENABLED","");
        prtc_screen = view.findViewById(R.id.prtc_screen);
        switchState(aBoolean);
        context = getActivity();
        TextView mydata_settings = view.findViewById(R.id.mydata_settings);
        TextView mydata_logout = view.findViewById(R.id.textView14);
        mydata_settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment frr;
                frr = new MyProfile();
                replaceFragmentWithAnimation(frr,"MyProfile",R.id.mainmenu_myfrg);
            }
        });
        mydata_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AlertDialog.Builder(getContext())
                        .setMessage("Are you sure you want to logout?")
                        .setCancelable(false)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                                    startActivity(new Intent(getActivity(),LoginRegister.class));
                                    SharedPreferences spreferences = PreferenceManager.getDefaultSharedPreferences(getContext());
                                    spreferences.edit().clear().commit();
                                    getActivity().finish();
                                }
                            }
                        })
                        .setNegativeButton("No",null)
                        .show();
            }
        });

        deletePin = view.findViewById(R.id.textView40);

        editPin = view.findViewById(R.id.editPin);

        if(pin.equals(""))
        {
            editPin.setText("   Add access code [PIN]");
            deletePin.setVisibility(View.GONE);
        }
        else
        {
            editPin.setText("   Change access code [PIN]");
            deletePin.setVisibility(View.VISIBLE);
        }
        finger_switch = view.findViewById(R.id.finger_switch);
        if(nboo.equals(""))
        {
            finger_switch.setText("   Enable fingerprint access");
        }
        else
        {
            finger_switch.setText("   Disable fingerprint access");
        }
        editPin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pinSwitch("1");

            }
        });
        deletePin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AlertDialog.Builder(getContext())
                        .setMessage("Do you want to delete PIN?")
                        .setCancelable(false)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                pinSwitch("2");
                            }
                        })
                        .setNegativeButton("No",null)
                        .show();

            }
        });
        finger_switch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fingerprintSwitch();
            }
        });


    }

    private void fingerprintSwitch() {

        if(nboo.equals("1")){
            new AlertDialog.Builder(getContext())
                    .setMessage("Do you want to disable fingerprint login?")
                    .setCancelable(false)
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                              PreferenceManager.getDefaultSharedPreferences(getContext()).edit().putString("FINGENTER", "2").apply();
                              startActivity(new Intent(getActivity(), FingerprintAdd.class));
                        }
                    })
                    .setNegativeButton("No",null)
                    .show();

        }
        if(nboo.equals("")){

                            PreferenceManager.getDefaultSharedPreferences(getContext()).edit().putString("FINGENTER", "1").apply();
                            startActivity(new Intent(getActivity(), FingerprintAdd.class));


        }


    }

    private void pinSwitch(String pinEnable) {
       if(pinEnable.equals("1")){
           if(pin.equals("")){
               PreferenceManager.getDefaultSharedPreferences(getContext()).edit().putString("PINCHANGE", "1").apply();
               startActivity(new Intent(getActivity(), PinChange.class));
           }
           if(!pin.equals("")){
               new AlertDialog.Builder(getContext())
                       .setMessage("Do you want to change PIN?")
                       .setCancelable(false)
                       .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                           @Override
                           public void onClick(DialogInterface dialogInterface, int i) {
                                  PreferenceManager.getDefaultSharedPreferences(getContext()).edit().putString("PINLOGIN", "2").apply();
                                  startActivity(new Intent(getActivity(), PinLogin.class));
                           }
                       })
                       .setNegativeButton("No",null)
                       .show();
           }
       }
       if(pinEnable.equals("2")){
           PreferenceManager.getDefaultSharedPreferences(getContext()).edit().putString("PINLOGIN", "3").apply();
           startActivity(new Intent(getActivity(), PinLogin.class));
       }
    }

    private void switchState(Boolean aBoolean) {
        if(aBoolean){
            prtc_screen.setChecked(true);
            prtc_screen.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if(isChecked){
                        PreferenceManager.getDefaultSharedPreferences(getContext()).edit().putBoolean("SCREEN_PROTECT",true ).apply();
                        Snackbar.make(getActivity().findViewById(android.R.id.content),"This action takes effect after restart!",Snackbar.LENGTH_SHORT).show();
                    }
                    else{

                        PreferenceManager.getDefaultSharedPreferences(getContext()).edit().putBoolean("SCREEN_PROTECT",false ).apply();
                        Snackbar.make(getActivity().findViewById(android.R.id.content),"This action takes effect after restart!",Snackbar.LENGTH_SHORT).show();
                    }
                }
            });
        }
        else{
            prtc_screen.setChecked(false);
            prtc_screen.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if(isChecked){
                        PreferenceManager.getDefaultSharedPreferences(getContext()).edit().putBoolean("SCREEN_PROTECT",true ).apply();
                        Snackbar.make(getActivity().findViewById(android.R.id.content),"This action takes effect after restart!",Snackbar.LENGTH_SHORT).show();
                    }
                    else{

                        PreferenceManager.getDefaultSharedPreferences(getContext()).edit().putBoolean("SCREEN_PROTECT",false ).apply();
                        Snackbar.make(getActivity().findViewById(android.R.id.content),"This action takes effect after restart!",Snackbar.LENGTH_SHORT).show();
                    }
                }
            });
        }

    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }


}
