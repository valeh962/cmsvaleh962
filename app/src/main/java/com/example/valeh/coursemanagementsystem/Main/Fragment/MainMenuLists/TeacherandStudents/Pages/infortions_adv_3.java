package com.example.valeh.coursemanagementsystem.Main.Fragment.MainMenuLists.TeacherandStudents.Pages;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.valeh.coursemanagementsystem.Main.DI.MyApp_classes.MyApp;
import com.example.valeh.coursemanagementsystem.Main.DI.SharedManagement;
import com.example.valeh.coursemanagementsystem.Main.Helpers.BaseFragment;
import com.example.valeh.coursemanagementsystem.Main.Helpers.SubjectsRoles;
import com.example.valeh.coursemanagementsystem.R;

import javax.inject.Inject;


public class infortions_adv_3 extends BaseFragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    TextView ed1,ed2;
    TextView button,back;
    String pname,psurname,pemail,pphone,paddress,padditional,ppersontypeid,ppersonid,pid,psubjectid;

    @Inject
    SharedManagement sharedManagement;

    private OnFragmentInteractionListener mListener;

    public infortions_adv_3() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static infortions_adv_3 newInstance(String param1, String param2) {
        infortions_adv_3 fragment = new infortions_adv_3();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
        MyApp.app().basicComponent().infort3_inject(this);

        pname =     sharedManagement.getStringSaved("acceptName");
        psurname=   sharedManagement.getStringSaved("acceptSurname");
        pemail=     sharedManagement.getStringSaved("acceptEmail");
        pphone=     sharedManagement.getStringSaved("acceptPhone");
        paddress=   sharedManagement.getStringSaved("acceptAddress");
        padditional=sharedManagement.getStringSaved("acceptAdditional");
        ppersonid=  sharedManagement.getStringSaved("acceptPersonId");
        ppersontypeid= sharedManagement.getStringSaved("acceptPersonTypeId");
        psubjectid= sharedManagement.getStringSaved("acceptSubjectId");
        pid=        sharedManagement.getStringSaved("acceptId");


    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ed1 = view.findViewById(R.id.adv_category);
        SubjectsRoles sss = new SubjectsRoles();
        ed1.setText(sss.teacherr(Integer.parseInt(ppersontypeid)));
        ed2 = view.findViewById(R.id.adv_subject);
        ed2.setText(sss.subjectt(Integer.parseInt(psubjectid)));
        button = view.findViewById(R.id.adv_next);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Fragment ff = new infortions_adv_2();
                Bundle bundle = new Bundle();
                bundle.putString("Name", pname);
                bundle.putString("Surname", psurname);
                bundle.putString("Email", pemail);
                bundle.putString("Phone", pphone);
                bundle.putString("PersonTypeId", ppersontypeid);
                bundle.putString("PersonId", ppersonid);
                bundle.putString("SubjectId", psubjectid);
                bundle.putString("Additional", padditional);
                bundle.putString("Address",paddress);
                bundle.putString("Id", pid);
                ff.setArguments(bundle);
                replaceFragmentWithAnimation(ff,"adv",R.id.adv_frag);

            }
        });

//        back.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                onButtonPressed();
//            }
//        });
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_infortions_adv_3,container,false);
        return v;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
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
