package com.example.valeh.coursemanagementsystem.Main.Fragment.MainMenuLists.TeacherandStudents;

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
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.example.valeh.coursemanagementsystem.Main.Activity.MainMenu;
import com.example.valeh.coursemanagementsystem.Main.DI.MyApp_classes.MyApp;
import com.example.valeh.coursemanagementsystem.Main.DI.SharedManagement;
import com.example.valeh.coursemanagementsystem.Main.Fragment.MainMenuLists.RequestListofTandS.teacher_main_list;
import com.example.valeh.coursemanagementsystem.Main.Fragment.MainMenuLists.TeacherandStudents.Pages.infortions_adv_1;
import com.example.valeh.coursemanagementsystem.Main.Helpers.BaseFragment;
import com.example.valeh.coursemanagementsystem.Main.Helpers.SubjectsRoles;
import com.example.valeh.coursemanagementsystem.Main.JsonWorks.AddTandS.AddStudentData;
import com.example.valeh.coursemanagementsystem.Main.JsonWorks.AddTandS.AddTeacherData;
import com.example.valeh.coursemanagementsystem.Main.JsonWorks.AddTandS.IAddTeacher;
import com.example.valeh.coursemanagementsystem.Main.JsonWorks.ChangeStatus.ChangeStatusBody;
import com.example.valeh.coursemanagementsystem.Main.JsonWorks.ChangeStatus.IChangeStatusData;
import com.example.valeh.coursemanagementsystem.Main.JsonWorks.Login.LoginResponseData;
import com.example.valeh.coursemanagementsystem.R;

import javax.inject.Inject;

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
 * {@link teachers_accepted.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link teachers_accepted#newInstance} factory method to
 * create an instance of this fragment.
 */
public class teachers_accepted extends BaseFragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    @Inject
    SharedManagement sharedManagement;

    TextView tx1,tx2,tx3,tx4,tx5;
    EditText ed1,ed2,ed3;
    ImageView img;
    ProgressDialog progressDialog;
    String ttoken;
    String pname,psurname,pemail,pphone,paddress,padditional,ppersontypeid,ppersonid,pid,psubjectid;

    private OnFragmentInteractionListener mListener;

    public teachers_accepted() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment teachers_accepted.
     */
    // TODO: Rename and change types and number of parameters
    public static teachers_accepted newInstance(String param1, String param2) {
        teachers_accepted fragment = new teachers_accepted();
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

        MyApp.app().basicComponent().teachers_accepted(this);
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

            sharedManagement.save("acceptName",pname,"string");
            sharedManagement.save("acceptSurname",psurname,"string");
            sharedManagement.save("acceptEmail",pemail,"string");
            sharedManagement.save("acceptPhone",pphone,"string");
            sharedManagement.save("acceptAddress",paddress,"string");
            sharedManagement.save("acceptAdditional",padditional,"string");
            sharedManagement.save("acceptPersonId",ppersonid,"string");
            sharedManagement.save("acceptPersonTypeId",ppersontypeid,"string");
            sharedManagement.save("acceptSubjectId",psubjectid,"string");
            sharedManagement.save("acceptId",pid,"string");
        }

//        ((MainMenu) getActivity())
//                .setActionBarTitle(pname+" "+psurname);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_teachers_accepted,container,false);
        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ttoken=  PreferenceManager.getDefaultSharedPreferences(getContext()).getString("TOKEN", "nothing");

        tx4 = view.findViewById(R.id.tcr_name_txt);
        tx5 = view.findViewById(R.id.tcr_type_txt);

        SubjectsRoles sss = new SubjectsRoles();

        tx4.setText(pname + " " + psurname);
        tx5.setText(sss.teacherr(Integer.parseInt(ppersontypeid)));


        Fragment ff = new infortions_adv_1();
        replaceFragmentWithAnimation(ff,"adv_1",R.id.adv_frag);

//        if(ppersontypeid.equals("1")){
//            tx1.setText("University");
//            tx2.setText("Faculty");
//            ed2.setInputType(InputType.TYPE_CLASS_TEXT);
//            tx3.setText("Grade");
//        }
//
//        if(ppersontypeid.equals("2")){
//            tx1.setText("Work place");
//            tx2.setText("Salary");
//            ed2.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL | InputType.TYPE_NUMBER_FLAG_SIGNED);
//            tx3.setText("Experience");
//        }
//

//        img = view.findViewById(R.id.tcr_acc);
//        img.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                final PopupMenu popupMenu = new PopupMenu(getContext(),img);
//                popupMenu.getMenuInflater().inflate(R.menu.add_new,popupMenu.getMenu());
//                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
//                    @Override
//                    public boolean onMenuItemClick(MenuItem menuItem) {
//                        if(menuItem.getItemId() == R.id.tch_add)
//                        {
//                            String role = PreferenceManager.getDefaultSharedPreferences(getContext()).getString("myRole", "");
//                            if(role.equals("2")){
//                                addteacher();
//                            }
//                            if(role.equals("1")){
//                                addstudent();
//                            }
//
//                        }
//                        if(menuItem.getItemId() == R.id.tch_delete)
//                        {
//                            String role = PreferenceManager.getDefaultSharedPreferences(getContext()).getString("myRole", "");
//                            if(role.equals("2")){
//                                deleteteacher();
//                            }
//                            if(role.equals("1")){
//                                deletestudent();
//                            }
//                        }
//                        return true;
//                    }
//                });
//                popupMenu.show();
//            }
//        });


    }

    private void deletestudent() {
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
                    Fragment fr = new teacher_main_list();
                    Toasty.success(getActivity(),"Added", Toast.LENGTH_SHORT).show();

                    Log.d("id",ppersonid);
                    Log.d("id2",ed1.getText().toString());
                    Log.d("id3",ed2.getText().toString());
                    Log.d("id4",ed3.getText().toString());


                    replaceFragmentWithAnimation(fr,"edit_requests_list",R.id.mainmenu_myfrg);
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
                    Fragment fr = new teacher_main_list();
                    Toasty.success(getActivity(),"Added", Toast.LENGTH_SHORT).show();

                    Log.d("id",ppersonid);
                    Log.d("id2",ed1.getText().toString());
                    Log.d("id3",ed2.getText().toString());
                    Log.d("id4",ed3.getText().toString());


                    replaceFragmentWithAnimation(fr,"edit_requests_list",R.id.mainmenu_myfrg);
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
    private void deleteteacher() {


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
