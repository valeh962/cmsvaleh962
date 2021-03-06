package com.example.valeh.coursemanagementsystem.Main.Fragment.MainMenuLists.TeacherandStudents.Pages;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.valeh.coursemanagementsystem.Main.DI.MyApp_classes.MyApp;
import com.example.valeh.coursemanagementsystem.Main.DI.SharedManagement;
import com.example.valeh.coursemanagementsystem.Main.Helpers.BaseFragment;
import com.example.valeh.coursemanagementsystem.R;

import javax.inject.Inject;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link infortions_adv_1.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link infortions_adv_1#newInstance} factory method to
 * create an instance of this fragment.
 */
public class infortions_adv_1 extends BaseFragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    TextView ed1,ed2;
    TextView button;
    String pname,psurname,pemail,pphone,paddress,padditional,ppersontypeid,ppersonid,pid,psubjectid;

    @Inject
    SharedManagement sharedManagement;

    private OnFragmentInteractionListener mListener;

    public infortions_adv_1() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static infortions_adv_1 newInstance(String param1, String param2) {
        infortions_adv_1 fragment = new infortions_adv_1();
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


        MyApp.app().basicComponent().infort1_inject(this);

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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_infortions_adv_1,container,false);
        return v;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ed1 = view.findViewById(R.id.adv_name);
        ed1.setText(pname);
        ed2 = view.findViewById(R.id.adv_surname);
        ed2.setText(psurname);
        button = view.findViewById(R.id.adv_next);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Fragment ff = new infortions_adv_3();
                replaceFragmentWithAnimation(ff,"adv",R.id.adv_frag);

            }
        });
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

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
