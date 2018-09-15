package com.example.valeh.coursemanagementsystem.Main.Fragment.Members;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.valeh.coursemanagementsystem.Main.DI.MyApp_classes.MyApp;
import com.example.valeh.coursemanagementsystem.Main.DI.SharedManagement;
import com.example.valeh.coursemanagementsystem.Main.Fragment.Groups.GroupDetails.GroupDetails;
import com.example.valeh.coursemanagementsystem.Main.Fragment.Groups.GroupDetails.GroupMemberDetails.GroupMemberDetails;
import com.example.valeh.coursemanagementsystem.Main.Helpers.BaseFragment;
import com.example.valeh.coursemanagementsystem.Main.Helpers.RetrofitBuilder;
import com.example.valeh.coursemanagementsystem.Main.JsonWorks.Members.IMembersData;
import com.example.valeh.coursemanagementsystem.Main.JsonWorks.Members.Member_Students_data;
import com.example.valeh.coursemanagementsystem.Main.JsonWorks.Members.Member_Teachers_data;
import com.example.valeh.coursemanagementsystem.R;

import java.util.ArrayList;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Members_teachers.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Members_teachers#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Members_teachers extends BaseFragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    RecyclerView recyclerView;
    Members_adapter_tcr members_adapter;
    String tokken;
    @Inject
    SharedManagement sharedManagement;

    private OnFragmentInteractionListener mListener;
    private View vv;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    public Members_teachers() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Members.
     */
    // TODO: Rename and change types and number of parameters
    public static Members_teachers newInstance(String param1, String param2) {
        Members_teachers fragment = new Members_teachers();
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
        View view = inflater.inflate(R.layout.fragment_members, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        MyApp.app().basicComponent().Members_teachers_inject(this);
        tokken = sharedManagement.getStringSaved("TOKEN");
        Log.d("TOKEN",tokken);
        vv = view;
        getAllStudents(tokken);



    }

    private void getAllStudents(String tokken) {

        IMembersData iMembersData = RetrofitBuilder.buildRetrofitrx(IMembersData.BASE_URL_TCR).create(IMembersData.class);
        compositeDisposable.add(iMembersData.getMemberTeachers(tokken)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::handleresponse,this::handleerror));

    }

    private void handleerror(Throwable throwable) {

    }

    private void handleresponse(ArrayList<Member_Teachers_data> member_students_data) {

        ArrayList<Member_Teachers_data> list = member_students_data;
        for (int i=0;i<list.size();i++){
            recyclerView = vv.findViewById(R.id.std_rec);
            members_adapter = new Members_adapter_tcr(list);
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
            recyclerView.setLayoutManager(layoutManager);
            recyclerView.setAdapter(members_adapter);
            members_adapter.setOnItemClickListener(new Members_adapter_tcr.OnItemClickListener() {
                @Override
                public void OnItemClick(int position) {

                    sharedManagement.save("teacherstudent","t","string");
                    Fragment fr = new GroupMemberDetails();
                    Bundle bundle = new Bundle();
                    bundle.putString("tcname",list.get(position).getName());
                    bundle.putString("tcsurname",list.get(position).getSurname());
                    bundle.putString("tcphone",list.get(position).getPhone());
                    bundle.putString("tcEmail",list.get(position).getEmail());
                    bundle.putString("tcAddress",list.get(position).getAddress());
                    bundle.putString("tcwork",list.get(position).getWorkPlace());
                    bundle.putInt("tcsalary",list.get(position).getSalary());
                    bundle.putString("tcexp",list.get(position).getExperience());
                    fr.setArguments(bundle);
                    replaceFragmentWithAnimation(fr,"GroupMemberDetails",R.id.mainmenu_myfrg);


                }
            });
        }

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
