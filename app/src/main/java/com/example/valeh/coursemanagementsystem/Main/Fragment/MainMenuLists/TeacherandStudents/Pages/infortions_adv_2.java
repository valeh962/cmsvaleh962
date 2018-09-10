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

import com.example.valeh.coursemanagementsystem.Main.Helpers.BaseFragment;
import com.example.valeh.coursemanagementsystem.Main.Helpers.SubjectsRoles;
import com.example.valeh.coursemanagementsystem.R;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link infortions_adv_2.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link infortions_adv_2#newInstance} factory method to
 * create an instance of this fragment.
 */
public class infortions_adv_2 extends BaseFragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    TextView ed1,ed2,ed3;
    TextView button,back;
    String pname,psurname,pemail,pphone,paddress,padditional,ppersontypeid,ppersonid,pid,psubjectid;



    private OnFragmentInteractionListener mListener;

    public infortions_adv_2() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment infortions_adv_2.
     */
    // TODO: Rename and change types and number of parameters
    public static infortions_adv_2 newInstance(String param1, String param2) {
        infortions_adv_2 fragment = new infortions_adv_2();
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

        Bundle bundle = this.getArguments();
        if (bundle != null) {
            pname = bundle.getString("Name");
            psurname= bundle.getString("Surname");
            pemail= bundle.getString("Email");
            pphone= bundle.getString("Phone");
            paddress= bundle.getString("Address");
            padditional= bundle.getString("Additional");
            ppersonid= bundle.getString("PersonId");
            ppersontypeid= bundle.getString("PersonTypeId");
            psubjectid= bundle.getString("SubjectId");
            pid= bundle.getString("Id");
        }


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_infortions_adv_2,container,false);
        return v;
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

        ed1 = view.findViewById(R.id.adv_email);
        ed1.setText(pemail);
        ed2 = view.findViewById(R.id.adv_phone);
        ed2.setText(pphone);
        ed3 = view.findViewById(R.id.adv_address);
        ed3.setText(padditional);
        button = view.findViewById(R.id.adv_next);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Fragment ff = new infortions_adv_4();
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
