package com.example.valeh.coursemanagementsystem.Main.Fragment.Groups;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.valeh.coursemanagementsystem.Main.DI.MyApp_classes.MyApp;
import com.example.valeh.coursemanagementsystem.Main.DI.SharedManagement;
import com.example.valeh.coursemanagementsystem.Main.Fragment.Groups.GroupDetails.GroupDetails;
import com.example.valeh.coursemanagementsystem.Main.Helpers.BaseFragment;
import com.example.valeh.coursemanagementsystem.Main.Helpers.RetrofitBuilder;
import com.example.valeh.coursemanagementsystem.Main.JsonWorks.Groups.GroupsMainDatum;
import com.example.valeh.coursemanagementsystem.Main.JsonWorks.Groups.IGroupsData;
import com.example.valeh.coursemanagementsystem.Main.JsonWorks.Groups.StudentList;
import com.example.valeh.coursemanagementsystem.Main.JsonWorks.Groups.Subject;
import com.example.valeh.coursemanagementsystem.R;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link MyGroups.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link MyGroups#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MyGroups extends BaseFragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    MyGroups_adapter myGroups_adapter;
    RecyclerView recyclerView;
    String tokken;
    ProgressBar spinner;
    @Inject
    SharedManagement sharedManagement;
    CompositeDisposable compositeDisposable = new CompositeDisposable();
    View vv;


    private OnFragmentInteractionListener mListener;

    public MyGroups() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MyGroups.
     */
    // TODO: Rename and change types and number of parameters
    public static MyGroups newInstance(String param1, String param2) {
        MyGroups fragment = new MyGroups();
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
        View rootView = inflater.inflate(R.layout.fragment_my_groups,container,false);
        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        MyApp.app().basicComponent().MyGroups_inject(this);
        tokken = sharedManagement.getStringSaved("TOKEN");
        Log.d("TOKEN",tokken);

        vv = view;
        getAllGroups(tokken);


    }

    private void getAllGroups(String tokken) {
        IGroupsData iGroupsData = RetrofitBuilder.buildRetrofitrx(IGroupsData.BASE_URL).create(IGroupsData.class);
        compositeDisposable.add(iGroupsData.getGroupData(tokken)
                                    .subscribeOn(Schedulers.io())
                                    .observeOn(AndroidSchedulers.mainThread())
                                    .subscribe(this::handleresponse,this::handleerror));

    }

    private void handleresponse(ArrayList<GroupsMainDatum> groupsMainData) {
        ArrayList<GroupsMainDatum> list = groupsMainData;
        for (int i=0;i<list.size();i++){
            recyclerView = vv.findViewById(R.id.groups_recycler);
            myGroups_adapter = new MyGroups_adapter(list);
            RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getActivity(),2);
            recyclerView.setLayoutManager(layoutManager);
            recyclerView.setAdapter(myGroups_adapter);
            myGroups_adapter.setOnItemClickListener(new MyGroups_adapter.OnItemClickListener() {
                @Override
                public void OnItemClick(int position) {

                    Toast.makeText(getContext(),list.get(position).getSubject().getId()+" "
                            +list.get(position).getTeacher().getName(),Toast.LENGTH_SHORT).show();

                //    List<Subject> studentLists = list.get(position).getStudentList();


                    Fragment fr = new GroupDetails();
                    Bundle bundle = new Bundle();
                    bundle.putInt("groupId",list.get(position).getId());
                    sharedManagement.save("groupid",list.get(position).getId(),"int");
                    sharedManagement.save("groupidpos",position,"int");
                    sharedManagement.save("grpname",list.get(position).getSubject().getName(),"string");
                 //   bundle.putInt("subjectt",studentLists.get(position).getName());
                 //   bundle.putInt("teacherr",list.get(position).getId());
                    fr.setArguments(bundle);
                    replaceFragmentWithAnimation(fr,"groupDetails",R.id.mainmenu_myfrg);


                }
            });
        }

    }

    private void handleerror(Throwable throwable) {

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
