package com.example.valeh.coursemanagementsystem.Main.Fragment.Settings_Fragments.Profile;

import android.net.Uri;
import android.os.Bundle;

import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import android.text.InputType;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.valeh.coursemanagementsystem.Main.JsonWorks.MyProfile.ImyProfile;
import com.example.valeh.coursemanagementsystem.Main.JsonWorks.MyProfile.MyProfileData;
import com.example.valeh.coursemanagementsystem.Main.JsonWorks.Subject.Subject_1;
import com.example.valeh.coursemanagementsystem.Main.JsonWorks.Subject.Subject_Interface;
import com.example.valeh.coursemanagementsystem.R;

import java.util.Arrays;
import java.util.List;

import okhttp3.ConnectionSpec;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link MyProfile.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link MyProfile#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MyProfile extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    EditText ed1,ed2,ed3,ed4,ed5,ed6;
    TextView tv2,tv3,tvex;


    private OnFragmentInteractionListener mListener;

    public MyProfile() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static MyProfile newInstance(String param1, String param2) {
        MyProfile fragment = new MyProfile();
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
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_my_profile,container,false);
        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        tvex = view.findViewById(R.id.textView41);
        ed1 = view.findViewById(R.id.editText);
        ed2 = view.findViewById(R.id.editText5);
        ed3 = view.findViewById(R.id.editText4);
        ed4 = view.findViewById(R.id.editText3);
        ed5 = view.findViewById(R.id.editText2);
        ed6 = view.findViewById(R.id.editText10);
        tv2 = view.findViewById(R.id.textView13);
        tv3 = view.findViewById(R.id.textView39);
        tvex.setText("Name & surname");

        fillInfo();

    }

    private void fillInfo() {


                tv2.setText("ID: "+PreferenceManager.getDefaultSharedPreferences(getContext()).getString("UserIdNumber", ""));
                tv3.setText(PreferenceManager.getDefaultSharedPreferences(getContext()).getString("UserType", ""));
                ed1.setText(PreferenceManager.getDefaultSharedPreferences(getContext()).getString("UserName", ""));
                ed2.setText(PreferenceManager.getDefaultSharedPreferences(getContext()).getString("UserSurname", ""));
                ed3.setText(PreferenceManager.getDefaultSharedPreferences(getContext()).getString("UserEmail", ""));
                ed4.setText(PreferenceManager.getDefaultSharedPreferences(getContext()).getString("UserPhone", ""));
                ed5.setText(PreferenceManager.getDefaultSharedPreferences(getContext()).getString("UserAddress", ""));
                ed6.setText(PreferenceManager.getDefaultSharedPreferences(getContext()).getString("UserUniversity", "")+
                ", "+PreferenceManager.getDefaultSharedPreferences(getContext()).getString("UserFaculty", "")+
                ", "+PreferenceManager.getDefaultSharedPreferences(getContext()).getString("UserGrade", ""));

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

    private void disableEditText(EditText editText) {
        editText.setEnabled(false);
        editText.setInputType(InputType.TYPE_NULL);
      //  editText.setFocusable(false);
    }
    private void enableEditText(EditText editText) {
        editText.setEnabled(true);
        editText.setInputType(InputType.TYPE_CLASS_TEXT);
      //  editText.setFocusable(true);
    }
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
