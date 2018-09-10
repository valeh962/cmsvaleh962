package com.example.valeh.coursemanagementsystem.Main.Fragment.MainMenuLists.RequestListofTandS;

import android.app.ProgressDialog;
import android.net.Uri;
import android.os.Bundle;

import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.example.valeh.coursemanagementsystem.Main.Activity.MainMenu;
import com.example.valeh.coursemanagementsystem.Main.Helpers.BaseFragment;
import com.example.valeh.coursemanagementsystem.Main.Helpers.SubjectsRoles;
import com.example.valeh.coursemanagementsystem.Main.JsonWorks.ChangeStatus.ChangeStatusBody;
import com.example.valeh.coursemanagementsystem.Main.JsonWorks.ChangeStatus.IChangeStatusData;
import com.example.valeh.coursemanagementsystem.R;

import es.dmoral.toasty.Toasty;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class Teacher_details extends BaseFragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;



    ImageView img;
    String ttoken;
    String pname,psurname,pemail,pphone,paddress,padditional,ppersontypeid,ppersonid,pid,psubjectid;
    ProgressDialog progressDialog;

    public Teacher_details() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static Teacher_details newInstance(String param1, String param2) {
        Teacher_details fragment = new Teacher_details();
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
        getActivity().getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);


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

//        ((MainMenu) getActivity())
//                .setActionBarTitle(pname+" "+psurname);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_teacher_details,container,false);
        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ttoken=  PreferenceManager.getDefaultSharedPreferences(getContext()).getString("TOKEN", "nothing");

        TextView tx1 = view.findViewById(R.id.teacher_name_txt);
        TextView tx2 = view.findViewById(R.id.teacher_type_txt);
        TextView tx3 = view.findViewById(R.id.teacher_email_txt);
        TextView tx4 = view.findViewById(R.id.teacher_phone_txt);
        TextView tx5 = view.findViewById(R.id.teacher_address_txt);
        TextView tx6 = view.findViewById(R.id.teacher_additional_txt);
        TextView tx7 = view.findViewById(R.id.teacher_subject_txt);

        SubjectsRoles sss = new SubjectsRoles();

        tx1.setText(pname+" "+psurname);
        tx2.setText(sss.teacherr(Integer.parseInt(ppersontypeid)));

        tx3.setText(pemail);
        tx4.setText(pphone);
        tx5.setText(paddress);
        tx6.setText(padditional);
        tx7.setText(sss.subjectt(Integer.parseInt(psubjectid)));


        img = view.findViewById(R.id.teacher_edit_menu);
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final PopupMenu popupMenu = new PopupMenu(getContext(),img);
                popupMenu.getMenuInflater().inflate(R.menu.teacher_edit_menu,popupMenu.getMenu());
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        if(menuItem.getItemId() == R.id.teacher_edit)
                        {
                            setAccept();
                        }
                        if(menuItem.getItemId() == R.id.teacher_delete)
                        {
                            setReject();
                        }
                        return true;
                    }
                });
                popupMenu.show();
            }
        });
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    public void setAccept(){
        progressDialog = ProgressDialog.show(getActivity(), "Please wait.", "Accepting...", true);
        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(IChangeStatusData.BASE_URL)
                .build();

        ChangeStatusBody changeStatusBody = new ChangeStatusBody(
                Integer.parseInt(pid),
                2,
                Integer.parseInt(ppersonid)
        );

        IChangeStatusData iChangeStatusData = retrofit.create(IChangeStatusData.class);
        Call<ChangeStatusBody> call = iChangeStatusData.changeStatus(ttoken,changeStatusBody);
        call.enqueue(new Callback<ChangeStatusBody>() {
            @Override
            public void onResponse(Call<ChangeStatusBody> call, Response<ChangeStatusBody> response) {
                if(response.isSuccessful()){
                    Fragment fr = new teacher_main_list();
                    Toasty.success(getActivity(),"Accepted",Toast.LENGTH_SHORT).show();
                    replaceFragmentWithAnimation(fr,"teacher_main_list",R.id.mainmenu_myfrg);
                    progressDialog.cancel();

                }
                else{
                    Toasty.error(getActivity(),"Error while accepting",Toast.LENGTH_SHORT).show();
                    progressDialog.cancel();
                }
            }

            @Override
            public void onFailure(Call<ChangeStatusBody> call, Throwable t) {

            }
        });

    }

    public void setReject(){
        progressDialog = ProgressDialog.show(getActivity(), "Please wait.", "Rejecting...", true);
        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(IChangeStatusData.BASE_URL)
                .build();

        ChangeStatusBody changeStatusBody = new ChangeStatusBody(
                Integer.parseInt(pid),
                3,
                Integer.parseInt(ppersonid)
        );

        IChangeStatusData iChangeStatusData = retrofit.create(IChangeStatusData.class);
        Call<ChangeStatusBody> call = iChangeStatusData.changeStatus(ttoken,changeStatusBody);
        call.enqueue(new Callback<ChangeStatusBody>() {
            @Override
            public void onResponse(Call<ChangeStatusBody> call, Response<ChangeStatusBody> response) {
                if(response.isSuccessful()){
                    Fragment fr = new teacher_main_list();
                    Toasty.success(getActivity(),"Rejected",Toast.LENGTH_SHORT).show();
                    replaceFragmentWithAnimation(fr,"teacher_main_list",R.id.mainmenu_myfrg);
                    progressDialog.cancel();

                }
                else{
                    Toasty.error(getActivity(),"Error while rejecting",Toast.LENGTH_SHORT).show();
                    progressDialog.cancel();
                }
            }

            @Override
            public void onFailure(Call<ChangeStatusBody> call, Throwable t) {

            }
        });


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
