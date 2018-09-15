package com.example.valeh.coursemanagementsystem.Main.Fragment.Groups.GroupDetails;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.valeh.coursemanagementsystem.Main.DI.MyApp_classes.MyApp;
import com.example.valeh.coursemanagementsystem.Main.DI.SharedManagement;
import com.example.valeh.coursemanagementsystem.Main.Fragment.Groups.GroupDetails.GroupMemberDetails.GroupMemberDetails;
import com.example.valeh.coursemanagementsystem.Main.Fragment.Groups.MyGroups_adapter;
import com.example.valeh.coursemanagementsystem.Main.Helpers.BaseFragment;
import com.example.valeh.coursemanagementsystem.Main.Helpers.RetrofitBuilder;
import com.example.valeh.coursemanagementsystem.Main.JsonWorks.Groups.GroupsMainDatum;
import com.example.valeh.coursemanagementsystem.Main.JsonWorks.Groups.IGroupsData;
import com.example.valeh.coursemanagementsystem.Main.JsonWorks.Groups.StudentList;
import com.example.valeh.coursemanagementsystem.R;

import java.util.ArrayList;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link GroupDetails.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link GroupDetails#newInstance} factory method to
 * create an instance of this fragment.
 */
public class GroupDetails extends BaseFragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    GroupDetails_adapter myGroups_adapter;
    RecyclerView recyclerView;
    ProgressBar spinner;
    int groupId;
    View vv;
    int groupidpos,listsize;
    TextView tv1,tv2;
    @Inject
    SharedManagement sharedManagement;

    private OnFragmentInteractionListener mListener;
    private String tokken;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();
    private TextView tv3;

    public GroupDetails() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment GroupDetails.
     */
    // TODO: Rename and change types and number of parameters
    public static GroupDetails newInstance(String param1, String param2) {
        GroupDetails fragment = new GroupDetails();
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
        if(bundle!=null)
            groupId = bundle.getInt("groupId");

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_group_details, container, false);
        return view;
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
        MyApp.app().basicComponent().GroupDetails_inject(this);
        tv1 = view.findViewById(R.id.textView31);
        tv2 = view.findViewById(R.id.textView32);
        tv3 = view.findViewById(R.id.textView37);
        tv2.setVisibility(View.GONE);
        tv1.setText("Group "+sharedManagement.getIntSaved("groupid")+", "+sharedManagement.getStringSaved("grpname"));
        tv3.setText("Teacher: "+sharedManagement.getStringSaved("teachername"));


        tokken = sharedManagement.getStringSaved("TOKEN");
        groupidpos = sharedManagement.getIntSaved("groupidpos");
        vv = view;
        getListStudents(tokken);


    }

    private void getListStudents(String tokken) {

        IGroupsData iGroupsData = RetrofitBuilder.buildRetrofitrx(IGroupsData.BASE_URL).create(IGroupsData.class);
        compositeDisposable.add(iGroupsData.getGroupData(tokken)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::handleresponse,this::handleerror));


    }

    private void handleerror(Throwable throwable) {

    }



    private void handleresponse(ArrayList<GroupsMainDatum> groupsMainData) {

//        ArrayList<GroupsMainDatum> list = groupsMainData;
//        for (int i=0;i<list.size();i++){
            ArrayList<StudentList> list2 = groupsMainData.get(groupidpos).getStudentList();
            listsize = list2.size();
            tv2.setVisibility(View.VISIBLE);
            tv2.setText("Persons enrolled: "+String.valueOf(listsize));
            for (int j=0;j<list2.size();j++){
            recyclerView = vv.findViewById(R.id.groupdet_rec);
            myGroups_adapter = new GroupDetails_adapter(list2);
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
            recyclerView.setLayoutManager(layoutManager);
            recyclerView.setAdapter(myGroups_adapter);
            myGroups_adapter.setOnItemClickListener(new GroupDetails_adapter.OnItemClickListener() {
                @Override
                public void OnItemClick(int position) {

                            sharedManagement.save("teacherstudent","s","string");
                            Log.d("Touched:",list2.get(position).getName());
                            Toast.makeText(getContext(),list2.get(position).getName(),Toast.LENGTH_SHORT).show();
                            Fragment fr = new GroupMemberDetails();
                            Bundle bundle = new Bundle();
                            bundle.putString("memberName",list2.get(position).getName());
                            bundle.putString("memberSurname",list2.get(position).getSurname());
                            bundle.putString("memberPhone",list2.get(position).getPhone());
                            bundle.putString("memberEmail",list2.get(position).getEmail());
                            bundle.putString("memberAddress",list2.get(position).getAddress());
                            bundle.putString("memberUniversity",list2.get(position).getUniversity());
                            bundle.putInt("memberGrade",list2.get(position).getGrade());
                            bundle.putString("memberFaculty",list2.get(position).getFaculty());
                            fr.setArguments(bundle);
                            replaceFragmentWithAnimation(fr,"GroupMemberDetails",R.id.mainmenu_myfrg);



                }
            });
        }
   //     }

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
