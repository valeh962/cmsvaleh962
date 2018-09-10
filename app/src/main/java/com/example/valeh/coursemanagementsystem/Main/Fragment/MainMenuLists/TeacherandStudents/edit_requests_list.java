package com.example.valeh.coursemanagementsystem.Main.Fragment.MainMenuLists.TeacherandStudents;

import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.valeh.coursemanagementsystem.Main.Fragment.MainMenuLists.RequestListofTandS.Teacher_details;
import com.example.valeh.coursemanagementsystem.Main.Helpers.BaseFragment;
import com.example.valeh.coursemanagementsystem.Main.JsonWorks.MakeRequest.Roles_Interface;
import com.example.valeh.coursemanagementsystem.Main.JsonWorks.Subject.Subject_Interface;
import com.example.valeh.coursemanagementsystem.Main.JsonWorks.Teachers.TeachersAllModel;
import com.example.valeh.coursemanagementsystem.Main.JsonWorks.Teachers.TeachersAll_Interface;
import com.example.valeh.coursemanagementsystem.R;

import java.net.UnknownServiceException;
import java.util.ArrayList;
import java.util.Arrays;

import okhttp3.ConnectionSpec;
import okhttp3.OkHttpClient;
import okhttp3.internal.connection.RouteException;
import okhttp3.internal.platform.Platform;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class edit_requests_list extends BaseFragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    public String tokken;
    int P_TYPE_N;
    int P_STAT_N;
    private ProgressBar spinner;
    private RecyclerView recyclerView;
    private teacher_main_list_adapter mAdapter;
    private ArrayList<TeachersAllModel> listItems;






    private OnFragmentInteractionListener mListener;

    public edit_requests_list() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment edit_requests_list.
     */
    // TODO: Rename and change types and number of parameters
    public static edit_requests_list newInstance(String param1, String param2) {
        edit_requests_list fragment = new edit_requests_list();
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

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_teacher_main_list,container,false);
        return rootView;
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
        spinner = view.findViewById(R.id.progressBar1);
        spinner.setVisibility(View.VISIBLE);


        tokken =  PreferenceManager.getDefaultSharedPreferences(getContext()).getString("TOKEN", "nothing");
        P_TYPE_N = PreferenceManager.getDefaultSharedPreferences(getContext()).getInt("myRolee", 0);
        P_STAT_N = PreferenceManager.getDefaultSharedPreferences(getContext()).getInt("myStatus", 2);
//
//        Toast.makeText(getActivity(),tokken,Toast.LENGTH_SHORT).show();
//        Toast.makeText(getActivity(),String.valueOf(P_TYPE_N),Toast.LENGTH_SHORT).show();

        if (!Platform.get().isCleartextTrafficPermitted(Subject_Interface.BASE_URL)) {
            throw new RouteException(new UnknownServiceException(
                    "CLEARTEXT communication to " + Roles_Interface.BASE_URL + " not permitted by network security policy"));}
        OkHttpClient client = new OkHttpClient
                .Builder()
                .connectionSpecs(Arrays.asList(ConnectionSpec.MODERN_TLS, ConnectionSpec.CLEARTEXT))
                .build();
        Retrofit retrofit = new Retrofit
                .Builder()
                .baseUrl(TeachersAll_Interface.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();
        TeachersAll_Interface api = retrofit.create(TeachersAll_Interface.class);
        Call<ArrayList<TeachersAllModel>> call = api.getAllTeachers(tokken,P_TYPE_N,2);

        call.enqueue(new Callback<ArrayList<TeachersAllModel>>() {
            @Override
            public void onResponse(Call<ArrayList<TeachersAllModel>> call, Response<ArrayList<TeachersAllModel>> response) {
              //  results1.clear();
                spinner.setVisibility(View.GONE);
                ArrayList <TeachersAllModel> list = response.body();
                for(int i=0;i<list.size();i++) {
                    if(i==0){
                //        addNotiff();
                    }
                    recyclerView = view.findViewById(R.id.teacher_recycler);
                    mAdapter = new teacher_main_list_adapter(list);
                    RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
                    recyclerView.setLayoutManager(layoutManager);
                    recyclerView.setAdapter(mAdapter);
                    mAdapter.setOnItemClickListener(new teacher_main_list_adapter.OnItemClickListener() {
                        @Override
                        public void OnItemClick(int position) {

                            Toast.makeText(getActivity(),list.get(position).getName()+" "+list.get(position).getSurname(),Toast.LENGTH_SHORT).show();

                            Fragment fr = new teachers_accepted();
                            Bundle bundle = new Bundle();
                            bundle.putString("Name", list.get(position).getName().toString());
                            bundle.putString("Surname", list.get(position).getSurname().toString());
                            bundle.putString("Email", list.get(position).getEmail().toString());
                            bundle.putString("Phone", list.get(position).getPhone().toString());
                            bundle.putString("PersonTypeId", list.get(position).getPersonTypeId().toString());
                            bundle.putString("PersonId", list.get(position).getPersonId().toString());
                            bundle.putString("SubjectId", list.get(position).getSubjectId().toString());
                            bundle.putString("Additional", list.get(position).getAddInfo().toString());
                            bundle.putString("Address", list.get(position).getAddress().toString());
                            bundle.putString("Id", list.get(position).getId().toString());
                            fr.setArguments(bundle);
                            replaceFragmentWithAnimation(fr,"teacher_details",R.id.mainmenu_myfrg);



                        }
                    });
                }

            }
            @Override
            public void onFailure(Call<ArrayList<TeachersAllModel>>call, Throwable t) {
                //Toasty.error(getActivity(),t.getMessage(), Toast.LENGTH_SHORT).show();
                Log.d("RESPONSE-ERROR",t.getMessage().toString());
            }
        });




    }

    private void addNotiff() {

        Snackbar.make(getActivity().findViewById(R.id.mainmenu_myfrg),"No requests.",Snackbar.LENGTH_LONG).show();

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
