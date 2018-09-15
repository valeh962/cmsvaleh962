package com.example.valeh.coursemanagementsystem.Main.Fragment.Login_request;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;


import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.anwarshahriar.calligrapher.Calligrapher;
import okhttp3.ConnectionSpec;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;

import com.example.valeh.coursemanagementsystem.Main.Activity.MainMenu;
import com.example.valeh.coursemanagementsystem.Main.DI.MyApp_classes.MyApp;
import com.example.valeh.coursemanagementsystem.Main.DI.SharedManagement;
import com.example.valeh.coursemanagementsystem.Main.Helpers.RetrofitBuilder;
import com.example.valeh.coursemanagementsystem.Main.JsonWorks.MyProfile.ImyProfile;
import com.example.valeh.coursemanagementsystem.Main.JsonWorks.MyProfile.MyProfileData;
import com.example.valeh.coursemanagementsystem.Main.JsonWorks.MyProfile.MyProfileDataTeacher;
import com.example.valeh.coursemanagementsystem.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

public class login_request2 extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    @BindView(R.id.activated_back_btn) TextView activated_back_btn;
    @BindView(R.id.activated_next_btn) TextView activated_next_btn;

    @Inject
    SharedManagement sharedManagement;
    private OnFragmentInteractionListener mListener;

    public login_request2() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static login_request2 newInstance(String param1, String param2) {
        login_request2 fragment = new login_request2();
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
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_login_request2_student,container,false);
        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
//        Calligrapher calligrapher = new Calligrapher(getActivity());
//        calligrapher.setFont(getActivity(),"font/CirceRounded-Light.otf",true);
        PreferenceManager.getDefaultSharedPreferences(getContext()).edit().putString("LOGOUT", "0").apply();
        String tokken = PreferenceManager.getDefaultSharedPreferences(getContext()).getString("TOKEN", "");

        MyApp.app().basicComponent().login_request2_inject(this);

        fillInfo(tokken);

        ButterKnife.bind(this,view);
        if (view != null) {
            InputMethodManager imm = (InputMethodManager)getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }


    }

    // TODO: Rename method, update argument and hook method into UI event

    @OnClick({R.id.activated_back_btn,R.id.activated_next_btn})
    public void onClick(View v){
        switch (v.getId()){
            case R.id.activated_back_btn:
                getActivity().finish();
                break;
            case R.id.activated_next_btn:
                startActivity(new Intent(getActivity(),MainMenu.class));
                getActivity().finish();
                break;
        }
    }

    @Override
    public void onResume() {
        super.onResume();

        if(getView() == null){
            return;
        }

        getView().setFocusableInTouchMode(true);
        getView().requestFocus();
        getView().setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {

                if (event.getAction() == KeyEvent.ACTION_UP && keyCode == KeyEvent.KEYCODE_BACK){
                  // startActivity(new Intent(getActivity(),LoginRegister.class));
                   getActivity().finish();
                    return true;
                }
                return false;
            }
        });
    }

    private void fillInfo(String tokken) {

        if(sharedManagement.getStringSaved("myRole").equals("student")) {
            ImyProfile api = RetrofitBuilder.buildRetrofit(ImyProfile.BASE_URl).create(ImyProfile.class);
            Call<MyProfileData> call = api.getUserInfo(tokken);
            call.enqueue(new Callback<MyProfileData>() {
                @Override
                public void onResponse(Call<MyProfileData> call, Response<MyProfileData> response) {
                    String typeName = "";
                    String typeId = "";
                    PreferenceManager.getDefaultSharedPreferences(getContext()).edit().putString("UserName", response.body().getName()).apply();
                    PreferenceManager.getDefaultSharedPreferences(getContext()).edit().putString("UserSurname", response.body().getSurname()).apply();
                    PreferenceManager.getDefaultSharedPreferences(getContext()).edit().putString("UserPhone", response.body().getPhone()).apply();
                    PreferenceManager.getDefaultSharedPreferences(getContext()).edit().putString("UserEmail", response.body().getEmail()).apply();
                    PreferenceManager.getDefaultSharedPreferences(getContext()).edit().putString("UserIdNumber", response.body().getIdNumber()).apply();
                    PreferenceManager.getDefaultSharedPreferences(getContext()).edit().putString("UserType", response.body().getType()).apply();
                    PreferenceManager.getDefaultSharedPreferences(getContext()).edit().putString("UserAddress", response.body().getAddress()).apply();
                    PreferenceManager.getDefaultSharedPreferences(getContext()).edit().putString("UserFaculty", response.body().getFaculty()).apply();
                    PreferenceManager.getDefaultSharedPreferences(getContext()).edit().putString("UserUniversity", response.body().getUniversity()).apply();
                    PreferenceManager.getDefaultSharedPreferences(getContext()).edit().putString("UserGrade", String.valueOf(response.body().getGrade())).apply();

                    List<MyProfileData.RoleList> roleLists = response.body().getRoleList();
                    for (int i = 0; i < roleLists.size(); i++) {
                        typeName += roleLists.get(i).getName() + "";
                        typeId += roleLists.get(i).getId() + ", ";

                    }
                    PreferenceManager.getDefaultSharedPreferences(getContext()).edit().putString("UserRoleName", typeName).apply();
                    PreferenceManager.getDefaultSharedPreferences(getContext()).edit().putString("UserRoleId", typeId).apply();


                }

                @Override
                public void onFailure(Call<MyProfileData> call, Throwable t) {
                    //Toasty.error(getActivity(),t.getMessage(), Toast.LENGTH_SHORT).show();
                    Log.d("RESPONSE-ERROR", t.getMessage().toString());
                }
            });
        }
        if(sharedManagement.getStringSaved("myRole").equals("teacher")){
            ImyProfile api = RetrofitBuilder.buildRetrofit(ImyProfile.BASE_URl).create(ImyProfile.class);
            Call<MyProfileDataTeacher> call = api.getUserInfoTeacher(tokken);
            call.enqueue(new Callback<MyProfileDataTeacher>() {
                @Override
                public void onResponse(Call<MyProfileDataTeacher> call, Response<MyProfileDataTeacher> response) {
                    String typeName = "";
                    String typeId = "";
                    PreferenceManager.getDefaultSharedPreferences(getContext()).edit().putString("UserName", response.body().getName()).apply();
                    PreferenceManager.getDefaultSharedPreferences(getContext()).edit().putString("UserSurname", response.body().getSurname()).apply();
                    PreferenceManager.getDefaultSharedPreferences(getContext()).edit().putString("UserPhone", response.body().getPhone()).apply();
                    PreferenceManager.getDefaultSharedPreferences(getContext()).edit().putString("UserEmail", response.body().getEmail()).apply();
                    PreferenceManager.getDefaultSharedPreferences(getContext()).edit().putString("UserIdNumber", response.body().getIdNumber()).apply();
                    PreferenceManager.getDefaultSharedPreferences(getContext()).edit().putString("UserType", response.body().getType()).apply();
                    PreferenceManager.getDefaultSharedPreferences(getContext()).edit().putString("UserAddress", response.body().getAddress()).apply();
                    PreferenceManager.getDefaultSharedPreferences(getContext()).edit().putString("UserExperience", response.body().getExperience()).apply();
                    PreferenceManager.getDefaultSharedPreferences(getContext()).edit().putInt("UserSalary", response.body().getSalary()).apply();
                    PreferenceManager.getDefaultSharedPreferences(getContext()).edit().putString("UserWorkPlace", String.valueOf(response.body().getWorkPlace())).apply();

                    List<MyProfileDataTeacher.RoleList> roleLists = response.body().getRoleList();
                    for (int i = 0; i < roleLists.size(); i++) {
                        typeName += roleLists.get(i).getName() + "";
                        typeId += roleLists.get(i).getId() + ", ";

                    }
                    PreferenceManager.getDefaultSharedPreferences(getContext()).edit().putString("UserRoleName", typeName).apply();
                    PreferenceManager.getDefaultSharedPreferences(getContext()).edit().putString("UserRoleId", typeId).apply();


                }

                @Override
                public void onFailure(Call<MyProfileDataTeacher> call, Throwable t) {
                    //Toasty.error(getActivity(),t.getMessage(), Toast.LENGTH_SHORT).show();
                    Log.d("RESPONSE-ERROR", t.getMessage().toString());
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
