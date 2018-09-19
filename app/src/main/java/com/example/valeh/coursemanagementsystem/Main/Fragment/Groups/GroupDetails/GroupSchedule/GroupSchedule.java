package com.example.valeh.coursemanagementsystem.Main.Fragment.Groups.GroupDetails.GroupSchedule;

import android.annotation.SuppressLint;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.valeh.coursemanagementsystem.Main.DI.MyApp_classes.MyApp;
import com.example.valeh.coursemanagementsystem.Main.DI.SharedManagement;
import com.example.valeh.coursemanagementsystem.Main.Helpers.RetrofitBuilder;
import com.example.valeh.coursemanagementsystem.Main.JsonWorks.GroupSchedule.GroupScheduleData;
import com.example.valeh.coursemanagementsystem.Main.JsonWorks.GroupSchedule.IGroupSchedule;
import com.example.valeh.coursemanagementsystem.R;

import java.util.ArrayList;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link GroupSchedule.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link GroupSchedule#newInstance} factory method to
 * create an instance of this fragment.
 */
public class GroupSchedule extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    TextView txt;
    String grp_id,tokken,grp_name;
    RelativeLayout rel1,rel2,rel3,rel4,rel5,rel6,rel7;

    @Inject
    SharedManagement sharedManagement;

    private OnFragmentInteractionListener mListener;

    public GroupSchedule() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment GroupSchedule.
     */
    // TODO: Rename and change types and number of parameters
    public static GroupSchedule newInstance(String param1, String param2) {
        GroupSchedule fragment = new GroupSchedule();
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

        Bundle bundle = getArguments();
        if(bundle!=null){
            grp_id = bundle.getString("groupId","");
            grp_name = bundle.getString("groupClass","");
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_group_schedule, container, false);
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        MyApp.app().basicComponent().GroupSchedule_inject(this);
        tokken = sharedManagement.getStringSaved("TOKEN");
        rel1 = view.findViewById(R.id.day1);
        rel2 = view.findViewById(R.id.day2);
        rel3 = view.findViewById(R.id.day3);
        rel4 = view.findViewById(R.id.day4);
        rel5 = view.findViewById(R.id.day5);
        rel6 = view.findViewById(R.id.day6);
        rel7 = view.findViewById(R.id.day7);
        txt = view.findViewById(R.id.grp_id_schedule);
        txt.setText("Group "+grp_id+", "+grp_name);

     //   fillDays();

        rel1.setOnClickListener(v -> getSchedule(1));
        rel2.setOnClickListener(v -> getSchedule(2));
        rel3.setOnClickListener(v -> getSchedule(3));
        rel4.setOnClickListener(v -> getSchedule(4));
        rel5.setOnClickListener(v -> getSchedule(5));
        rel6.setOnClickListener(v -> getSchedule(6));
        rel7.setOnClickListener(v -> getSchedule(7));



    }

    private void fillDays() {

        IGroupSchedule iGroupSchedule = RetrofitBuilder.buildRetrofit(IGroupSchedule.BASE_URL).create(IGroupSchedule.class);
        Call<ArrayList<GroupScheduleData>> call = iGroupSchedule.getGroupSchedule(tokken,Integer.parseInt(grp_id));
        call.enqueue(new Callback<ArrayList<GroupScheduleData>>() {
            @Override
            public void onResponse(Call<ArrayList<GroupScheduleData>> call, Response<ArrayList<GroupScheduleData>> response) {
                if(response.isSuccessful()){

                    ArrayList<GroupScheduleData> grp = response.body();
                    for(int i=0;i<grp.size();i++){
                        getScheduleData(grp.get(i).getDay(),grp.get(i).getTime());
                    }

                }
            }

            @Override
            public void onFailure(Call<ArrayList<GroupScheduleData>> call, Throwable t) {

            }
        });

    }

    private void getScheduleData(Integer day, Integer time) {



    }

    private void getSchedule(int i) {




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
