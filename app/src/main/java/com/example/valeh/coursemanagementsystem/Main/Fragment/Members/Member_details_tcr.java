package com.example.valeh.coursemanagementsystem.Main.Fragment.Members;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.valeh.coursemanagementsystem.Main.DI.MyApp_classes.MyApp;
import com.example.valeh.coursemanagementsystem.Main.DI.SharedManagement;
import com.example.valeh.coursemanagementsystem.R;

import javax.inject.Inject;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Member_details_tcr.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Member_details_tcr#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Member_details_tcr extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    String uname,usurname,uphone,uemail,uaddress,uuniversity,ufaculty,uworkplace,uexp;
    int ugrade,usalary;
    EditText ed1,ed2,ed3,ed4,ed5,ed6;
    TextView tv;
    ImageView im1,im2;
    String chs;

    @Inject
    SharedManagement sharedManagement;

    private OnFragmentInteractionListener mListener;

    public Member_details_tcr() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment GroupMemberDetails.
     */
    // TODO: Rename and change types and number of parameters
    public static Member_details_tcr newInstance(String param1, String param2) {
        Member_details_tcr fragment = new Member_details_tcr();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MyApp.app().basicComponent().Member_details_tcr_inject(this);
        chs = sharedManagement.getStringSaved("teacherstudent");
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

        Bundle bundle = this.getArguments();
        if(bundle!=null){
                uname = bundle.getString("tcname");
                usurname = bundle.getString("tcsurname");
                uphone = bundle.getString("tcphone");
                uemail = bundle.getString("tcEmail");
                uaddress = bundle.getString("tcAddress");
                uworkplace = bundle.getString("tcwork");
                usalary = bundle.getInt("tcsalary");
                uexp = bundle.getString("tcexp");

        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_group_member_details, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);



        ed1 = view.findViewById(R.id.ed1);
        ed2 = view.findViewById(R.id.ed2);
        ed3 = view.findViewById(R.id.ed3);
        ed4 = view.findViewById(R.id.ed4);
        ed5 = view.findViewById(R.id.ed5);
        ed6 = view.findViewById(R.id.ed6);
        im1 = view.findViewById(R.id.imageView16);
        im2 = view.findViewById(R.id.imageView17);
        tv = view.findViewById(R.id.textView43);
        tv.setText("Work place, experience, salary");

            ed1.setText(uname);
            ed2.setText(usurname);
            ed3.setText(uphone);
            ed4.setText(uemail);
            ed5.setText(uaddress);
            ed6.setText("Work place is "+uworkplace + ", Experience " + uexp + ", Salary " + usalary+"AZN");


        ed4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent emailIntent = new Intent(Intent.ACTION_SENDTO);
                emailIntent.setData(Uri.parse("mailto:" + uemail));
                try {
                    startActivity(Intent.createChooser(emailIntent, "Send email using..."));
                } catch (android.content.ActivityNotFoundException ex) {
                    Toast.makeText(getActivity(), "No email clients installed.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        im1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", uphone, null)));
            }
        });
        im2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("sms:"
                        + uphone)));
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
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
