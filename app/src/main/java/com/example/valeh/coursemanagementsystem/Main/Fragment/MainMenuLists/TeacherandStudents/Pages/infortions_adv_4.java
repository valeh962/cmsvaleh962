package com.example.valeh.coursemanagementsystem.Main.Fragment.MainMenuLists.TeacherandStudents.Pages;

import android.app.ProgressDialog;
import android.content.Context;
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
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.valeh.coursemanagementsystem.Main.Fragment.MainMenuLists.RequestListofTandS.teacher_main_list;
import com.example.valeh.coursemanagementsystem.Main.Fragment.MainMenuLists.TeacherandStudents.edit_requests_list;
import com.example.valeh.coursemanagementsystem.Main.Fragment.PersonTypeList.Menu_lists;
import com.example.valeh.coursemanagementsystem.Main.Helpers.BaseFragment;
import com.example.valeh.coursemanagementsystem.Main.JsonWorks.AddTandS.AddStudentData;
import com.example.valeh.coursemanagementsystem.Main.JsonWorks.AddTandS.AddTeacherData;
import com.example.valeh.coursemanagementsystem.Main.JsonWorks.AddTandS.IAddTeacher;
import com.example.valeh.coursemanagementsystem.Main.JsonWorks.Login.LoginResponseData;
import com.example.valeh.coursemanagementsystem.R;

import java.util.zip.Inflater;

import es.dmoral.toasty.Toasty;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link infortions_adv_4.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link infortions_adv_4#newInstance} factory method to
 * create an instance of this fragment.
 */
public class infortions_adv_4 extends BaseFragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    TextView tx1,tx2,tx3,tx4,tx5;
    EditText ed1,ed2,ed3;
    ImageView img;
    ProgressDialog progressDialog;
    String ttoken;
    String pname,psurname,pemail,pphone,paddress,padditional,ppersontypeid,ppersonid,pid,psubjectid;


    private OnFragmentInteractionListener mListener;

    public infortions_adv_4() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static infortions_adv_4 newInstance(String param1, String param2) {
        infortions_adv_4 fragment = new infortions_adv_4();
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
        View v = inflater.inflate(R.layout.fragment_infortions_adv_4,container,false);
        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ttoken=  PreferenceManager.getDefaultSharedPreferences(getContext()).getString("TOKEN", "nothing");

            tx1 = view.findViewById(R.id.adv_work_txt);
            tx2 = view.findViewById(R.id.adv_salary_txt);
            tx3 = view.findViewById(R.id.adv_exp_txt);
            ed1 = view.findViewById(R.id.adv_work);
            ed2 = view.findViewById(R.id.adv_salary);
            ed3 = view.findViewById(R.id.adv_exp);
            tx4 = view.findViewById(R.id.adv_add);
            tx5 = view.findViewById(R.id.adv_cancel);


        if(ppersontypeid.equals("1")){
            tx1.setText("University");
            tx2.setText("Faculty");
            ed2.setInputType(InputType.TYPE_CLASS_TEXT);
            tx3.setText("Grade");
        }

        if(ppersontypeid.equals("2")){
            tx1.setText("Work place");
            tx2.setText("Salary");
            ed2.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL | InputType.TYPE_NUMBER_FLAG_SIGNED);
            tx3.setText("Experience");
        }


        tx4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String role = ppersontypeid;
                if(role.equals("2")){
                    addteacher();
                }
                if(role.equals("1")){
                    addstudent();
                }
            }
        });
        tx5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment frq = new edit_requests_list();
                replaceFragmentWithAnimation(frq,"edit_requests_list",R.id.mainmenu_myfrg);
                int backStackEntry = getFragmentManager().getBackStackEntryCount();
                if (backStackEntry > 0) {
                    for (int i = 0; i < backStackEntry; i++) {
                        getFragmentManager().popBackStackImmediate();
                    }}
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

    private void addstudent() {
        progressDialog = ProgressDialog.show(getActivity(), "Please wait.", "Adding Student...", true);
        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(IAddTeacher.BASE_URL1)
                .build();

        AddStudentData changeStatusBody = new AddStudentData(
                ppersonid,
                ed1.getText().toString(),
                ed2.getText().toString(),
                ed3.getText().toString()
        );

        Log.d("TOKKKK",ttoken);

        IAddTeacher iChangeStatusData = retrofit.create(IAddTeacher.class);
        Call<LoginResponseData> call = iChangeStatusData.addStudent(ttoken,changeStatusBody);
        call.enqueue(new Callback<LoginResponseData>() {
            @Override
            public void onResponse(Call<LoginResponseData> call, Response<LoginResponseData> response) {
                if(response.isSuccessful()){
                    //           Log.d("RESSS",response.body().toString());
                    if(response.body().getMessage().equals("success")) {
                        Fragment fr = new edit_requests_list();
                        Toasty.success(getActivity(), "Added", Toast.LENGTH_SHORT).show();

                        Log.d("id", ppersonid);
                        Log.d("id2", ed1.getText().toString());
                        Log.d("id3", ed2.getText().toString());
                        Log.d("id4", ed3.getText().toString());


                        replaceFragmentWithAnimation(fr, "edit_requests_list", R.id.mainmenu_myfrg);
                        int backStackEntry = getFragmentManager().getBackStackEntryCount();
                        if (backStackEntry > 0) {
                            for (int i = 0; i < backStackEntry; i++) {
                                getFragmentManager().popBackStackImmediate();
                            }
                        }
                        progressDialog.cancel();
                    }
                    else{
                        Toasty.error(getActivity(),"Already added!",Toast.LENGTH_SHORT).show();
                        progressDialog.cancel();
                    }
                }
                else{
                    Toasty.error(getActivity(),"There is a problem while adding",Toast.LENGTH_SHORT).show();
                    progressDialog.cancel();
                }
            }

            @Override
            public void onFailure(Call<LoginResponseData> call, Throwable t) {
                Toasty.error(getActivity(),"There is a problem while adding!",Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void addteacher() {

        progressDialog = ProgressDialog.show(getActivity(), "Please wait.", "Accepting...", true);
        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(IAddTeacher.BASE_URL)
                .build();

        AddTeacherData changeStatusBody = new AddTeacherData(
                ppersonid,
                ed1.getText().toString(),
                ed2.getText().toString(),
                ed3.getText().toString()
        );

        Log.d("TOKKKK",ttoken);

        IAddTeacher iChangeStatusData = retrofit.create(IAddTeacher.class);
        Call<LoginResponseData> call = iChangeStatusData.addTeacherr(ttoken,changeStatusBody);
        call.enqueue(new Callback<LoginResponseData>() {
            @Override
            public void onResponse(Call<LoginResponseData> call, Response<LoginResponseData> response) {
                if(response.isSuccessful()){
                    //      Log.d("RESSS",response.body().toString());
                    Fragment fr = new edit_requests_list();
                    Toasty.success(getActivity(),"Added", Toast.LENGTH_SHORT).show();

                    Log.d("id",ppersonid);
                    Log.d("id2",ed1.getText().toString());
                    Log.d("id3",ed2.getText().toString());
                    Log.d("id4",ed3.getText().toString());


                    replaceFragmentWithAnimation(fr,"edit_requests_list",R.id.mainmenu_myfrg);
//                    Fragment f1 = new infortions_adv_1();
//                    Fragment f2 = new infortions_adv_2();
//                    Fragment f3 = new infortions_adv_3();
//                    Fragment f4 = new infortions_adv_4();
//
//                    clearBackStack(f1);
//                    clearBackStack(f2);
//                    clearBackStack(f3);
//                    clearBackStack(f4);
                    int backStackEntry = getFragmentManager().getBackStackEntryCount();
                    if (backStackEntry > 0) {
                        for (int i = 0; i < backStackEntry; i++) {
                            getFragmentManager().popBackStackImmediate();
                        }}
                    progressDialog.cancel();

                }
                else{
                    Toasty.error(getActivity(),"Error while accepting",Toast.LENGTH_SHORT).show();
                    progressDialog.cancel();
                }
            }

            @Override
            public void onFailure(Call<LoginResponseData> call, Throwable t) {

            }
        });



    }
}
