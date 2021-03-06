package com.example.valeh.coursemanagementsystem.Main.Fragment.Groups.Group_Add;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.valeh.coursemanagementsystem.Main.DI.MyApp_classes.MyApp;
import com.example.valeh.coursemanagementsystem.Main.DI.SharedManagement;
import com.example.valeh.coursemanagementsystem.Main.Fragment.Groups.GroupDetails.GroupSchedule.AddSchedule;
import com.example.valeh.coursemanagementsystem.Main.Fragment.Groups.MyGroups_adapter;
import com.example.valeh.coursemanagementsystem.Main.Fragment.Members.Members_adapter;
import com.example.valeh.coursemanagementsystem.Main.Helpers.BaseFragment;
import com.example.valeh.coursemanagementsystem.Main.Helpers.RetrofitBuilder;
import com.example.valeh.coursemanagementsystem.Main.JsonWorks.Groups.AddGroup_Data;
import com.example.valeh.coursemanagementsystem.Main.JsonWorks.Groups.GroupsMainDatum;
import com.example.valeh.coursemanagementsystem.Main.JsonWorks.Groups.IGroupsData;
import com.example.valeh.coursemanagementsystem.Main.JsonWorks.Login.LoginResponseData;
import com.example.valeh.coursemanagementsystem.Main.JsonWorks.Members.IMembersData;
import com.example.valeh.coursemanagementsystem.Main.JsonWorks.Members.Member_Students_data;
import com.example.valeh.coursemanagementsystem.Main.JsonWorks.Members.Member_Teachers_data;
import com.example.valeh.coursemanagementsystem.Main.JsonWorks.Subject.Subject_1;
import com.example.valeh.coursemanagementsystem.Main.JsonWorks.Subject.Subject_Interface;
import com.example.valeh.coursemanagementsystem.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.inject.Inject;

import es.dmoral.toasty.Toasty;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link AddNewGroup.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link AddNewGroup#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AddNewGroup extends BaseFragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    MyGroups_adapter myGroups_adapter;
    RecyclerView recyclerView;
    RecyclerView recyclerView22;
    Members_adapter members_adapter;
    @Inject
    SharedManagement sharedManagement;
    String tokken;
    View vv;
    Spinner spinner, spinner2;
    TextView addnew;
    private OnFragmentInteractionListener mListener;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();
    ArrayList<String> results1, results2;
    ArrayList<Integer> i1,i2;

    ArrayAdapter<String> adapter1, adapter2;
    private Integer ididid;

    public AddNewGroup() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AddNewGroup.
     */
    // TODO: Rename and change types and number of parameters
    public static AddNewGroup newInstance(String param1, String param2) {
        AddNewGroup fragment = new AddNewGroup();
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
            String mParam1 = getArguments().getString(ARG_PARAM1);
            String mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_new_group, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        MyApp.app().basicComponent().AddNewGroup_inject(this);
        tokken = sharedManagement.getStringSaved("TOKEN");
        Log.d("TOKEN", tokken);
        addnew = view.findViewById(R.id.textView18);
        addnew.setOnClickListener(v -> {
            showAddPopUp(tokken, getContext());
        //    showPopUp(tokken,2,getContext());
        });
        results1 = new ArrayList<>();
        results2 = new ArrayList<>();
        i1 = new ArrayList<>();
        i2 = new ArrayList<>();
        vv = view;
        getAllGroups();

    }


    private void getAllGroups() {

        IGroupsData iGroupsData = RetrofitBuilder.buildRetrofitrx(IGroupsData.BASE_URL).create(IGroupsData.class);
        compositeDisposable.add(iGroupsData.getAllGroupData()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::handleresponse, this::handleerror));

    }

    private void handleresponse(ArrayList<GroupsMainDatum> groupsMainData) {
        for (int i = 0; i < groupsMainData.size(); i++) {
            recyclerView = vv.findViewById(R.id.groups_recycler);
            myGroups_adapter = new MyGroups_adapter(groupsMainData);
            RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getActivity(), 2);
            recyclerView.setLayoutManager(layoutManager);
            recyclerView.setAdapter(myGroups_adapter);
            myGroups_adapter.setOnItemClickListener(position -> showPopUp(tokken, groupsMainData.get(position).getId(),getContext()));

            myGroups_adapter.setOnItemLongClickListener(position -> startActivity(new Intent(getActivity(), AddSchedule.class)));
        }

    }

    private void handleerror(Throwable throwable) {
    }


    private void showAddPopUp(String tokken, Context context) {


        final Dialog dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.add_new_group_popup);
        dialog.setCanceledOnTouchOutside(true);
        dialog.setCancelable(true);
        Objects.requireNonNull(dialog.getWindow()).setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        float dm = 0.5f;
        dialog.getWindow().setDimAmount(dm);


        spinner = dialog.findViewById(R.id.spinner3);
        spinner2 = dialog.findViewById(R.id.spinner4);
        TextView crt = dialog.findViewById(R.id.crt_b);
        fillSpinner(tokken);


        crt.setOnClickListener(v -> {
            AddGroup_Data addGroup_data = new AddGroup_Data(
                    new AddGroup_Data.Subject(i2.get(spinner.getSelectedItemPosition())),
                    new AddGroup_Data.Teacher(i1.get(spinner2.getSelectedItemPosition()))
            );
            IGroupsData postreq = RetrofitBuilder.buildRetrofit(IGroupsData.BASE_URL).create(IGroupsData.class);
            Call<LoginResponseData> call = postreq.createGroup(tokken, addGroup_data);

            call.enqueue(new Callback<LoginResponseData>() {
                @Override
                public void onResponse(@NonNull Call<LoginResponseData> call, @NonNull Response<LoginResponseData> response) {

                    if (response.isSuccessful()) {
                        Toasty.success(Objects.requireNonNull(getActivity()), "Group created!", Toast.LENGTH_SHORT).show();
                        assert response.body() != null;
                        Log.d("MESSAGE", String.valueOf(response.body()));
                        dialog.dismiss();
                    }

                    else {
                        Toasty.error(Objects.requireNonNull(getActivity()), "Error! Please try again later.", Toast.LENGTH_SHORT).show();
                        assert response.body() != null;
                        Log.d("MESSAGE", String.valueOf(response.body()));
                        dialog.dismiss();
                    }

                }

                @Override
                public void onFailure(@NonNull Call<LoginResponseData> call, @NonNull Throwable t) {

                    Toasty.error(Objects.requireNonNull(getActivity()), "Error! Please try again later.", Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
                    //          Log.e("response-failure", call.toString());
                }
            });

        });
        dialog.show();
    }
    private void fillSpinner(String tokken) {


        IMembersData api = RetrofitBuilder.buildRetrofit(IMembersData.BASE_URL_TCR).create(IMembersData.class);
        Call<ArrayList<Member_Teachers_data>> call = api.getMemberTeachersn(tokken);
        adapter1 = new ArrayAdapter<>(Objects.requireNonNull(getActivity()), android.R.layout.simple_spinner_item, results1);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        call.enqueue(new Callback<ArrayList<Member_Teachers_data>>() {
            @Override
            public void onResponse(@NonNull Call<ArrayList<Member_Teachers_data>> call, @NonNull Response<ArrayList<Member_Teachers_data>> response) {

                results1.clear();
                if (response.isSuccessful()) {
                    assert response.body() != null;
                    Log.d("Success", String.valueOf(response.body()));
                    ArrayList<Member_Teachers_data> subject_1s = response.body();
                    assert subject_1s != null;
                    for (int i = 0; i < subject_1s.size(); i++) {
                        results1.add(subject_1s.get(i).getName() + " " + subject_1s.get(i).getSurname());
                        i1.add(subject_1s.get(i).getId());
                    }
                    adapter1.notifyDataSetChanged();
                } else {
                    assert response.body() != null;
                    Log.d("ERROR", String.valueOf(response.body()));
                }
            }

            @Override
            public void onFailure(@NonNull Call<ArrayList<Member_Teachers_data>> call, @NonNull Throwable t) {
                //Toasty.error(getActivity(),t.getMessage(), Toast.LENGTH_SHORT).show();
                Log.d("RESPONSE-ERROR", t.getMessage());
            }
        });
        spinner.setAdapter(adapter1);


        Subject_Interface api2 = RetrofitBuilder.buildRetrofit(Subject_Interface.BASE_URL).create(Subject_Interface.class);
        Call<List<Subject_1>> call2 = api2.getResults();
        adapter2 = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_item, results2);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        call2.enqueue(new Callback<List<Subject_1>>() {
            @Override
            public void onResponse(@NonNull Call<List<Subject_1>> call, @NonNull Response<List<Subject_1>> response) {
                results2.clear();
                List<Subject_1> subject_1s = response.body();
                assert subject_1s != null;
                for (int i = 0; i < subject_1s.size(); i++) {
                    results2.add(subject_1s.get(i).getName());
                    i2.add(subject_1s.get(i).getId());
                }
                adapter2.notifyDataSetChanged();
            }

            @Override
            public void onFailure(@NonNull Call<List<Subject_1>> call, @NonNull Throwable t) {
                //Toasty.error(getActivity(),t.getMessage(), Toast.LENGTH_SHORT).show();
                Log.d("RESPONSE-ERROR", t.getMessage());
            }
        });
        spinner2.setAdapter(adapter2);

    }
    private void showPopUp(String tokken, Integer id,Context context) {

        final Dialog dialog1 = new Dialog(context);
        dialog1.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog1.setContentView(R.layout.students_add_popup);
        dialog1.setCanceledOnTouchOutside(true);
        dialog1.setCancelable(true);
        Objects.requireNonNull(dialog1.getWindow()).setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        float dm = 0.5f;
        dialog1.getWindow().setDimAmount(dm);

        recyclerView22 = dialog1.findViewById(R.id.reccc1);
        TextView tx = dialog1.findViewById(R.id.textView53);
        tx.setOnClickListener(v -> dialog1.dismiss());
        ididid= id;
        fillRecycler(tokken);
        dialog1.show();



    }

    private void fillRecycler(String tokken) {

        IMembersData iMembersData = RetrofitBuilder.buildRetrofitrx(IMembersData.BASE_URL_STD).create(IMembersData.class);
        compositeDisposable.add(iMembersData.getMemberStudents(tokken)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::fillRecyclerResponse,this::fillRecyclerError));

    }

    private void fillRecyclerResponse(ArrayList<Member_Students_data> member_students_data) {

        for (int i = 0; i< member_students_data.size(); i++){

            members_adapter = new Members_adapter(member_students_data);
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
            recyclerView22.setLayoutManager(layoutManager);
            recyclerView22.setAdapter(members_adapter);
            members_adapter.setOnItemClickListener(position -> Objects.requireNonNull(new AlertDialog.Builder(Objects.requireNonNull(getContext()))
                    .setMessage("Add student?")
                    .setCancelable(true)

                    .setPositiveButton("Yes", (dialogInterface, i3) -> addStdToGroup(member_students_data.get(position).getId()))
                    .setNegativeButton("No", null)
                    .show().getWindow()).setLayout(600,400));
        }

    }

    private void addStdToGroup(Integer id2) {

        IGroupsData iGroupsData = RetrofitBuilder.buildRetrofit(IGroupsData.BASE_URL_ADD).create(IGroupsData.class);
        Call<LoginResponseData> call = iGroupsData.addStdToList(tokken,id2,ididid);

        call.enqueue(new Callback<LoginResponseData>() {
            @Override
            public void onResponse(@NonNull Call<LoginResponseData> call, @NonNull Response<LoginResponseData> response) {
               if(response.isSuccessful()) {
                   Toasty.success(Objects.requireNonNull(getActivity()), "Success!", Toast.LENGTH_SHORT).show();

               }


               else {
                   Toasty.error(Objects.requireNonNull(getActivity()),"Student already added!",Toast.LENGTH_SHORT).show();
               }

            }

            @Override
            public void onFailure(@NonNull Call<LoginResponseData> call, @NonNull Throwable t) {

                Toasty.error(Objects.requireNonNull(getActivity()),"Error! Please try again later.",Toast.LENGTH_SHORT).show();
                //          Log.e("response-failure", call.toString());
            }
        });


    }

    private void fillRecyclerError(Throwable throwable) {
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
