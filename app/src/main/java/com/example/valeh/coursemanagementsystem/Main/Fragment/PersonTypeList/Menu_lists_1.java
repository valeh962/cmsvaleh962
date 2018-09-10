package com.example.valeh.coursemanagementsystem.Main.Fragment.PersonTypeList;

import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ProgressBar;

import com.example.valeh.coursemanagementsystem.Main.Activity.MainMenu;
import com.example.valeh.coursemanagementsystem.Main.Fragment.MainMenuLists.RequestListofTandS.teacher_main_list;
import com.example.valeh.coursemanagementsystem.Main.Helpers.BaseFragment;
import com.example.valeh.coursemanagementsystem.Main.JsonWorks.MakeRequest.Roles;
import com.example.valeh.coursemanagementsystem.Main.JsonWorks.MakeRequest.Roles_Interface;
import com.example.valeh.coursemanagementsystem.R;

import java.net.UnknownServiceException;
import java.util.Arrays;
import java.util.List;

import okhttp3.ConnectionSpec;
import okhttp3.OkHttpClient;
import okhttp3.internal.connection.RouteException;
import okhttp3.internal.platform.Platform;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class Menu_lists_1 extends BaseFragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private RecyclerView recyclerView;
    private Menu_lists_adapter mAdapter;
    private ProgressBar spinner;

    String pt;
    String ptt;
    Bundle bundle;
    List<Roles> clicked;
    Fragment drr = new teacher_main_list();
    Fragment qrr = new com.example.valeh.coursemanagementsystem.Main.Fragment.MainMenuLists.TeacherandStudents.edit_requests_list();
    teacher_main_list ttt = new teacher_main_list();
    String kitt;
    private OnFragmentInteractionListener mListener;

    public Menu_lists_1() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static Menu_lists_1 newInstance(String param1, String param2) {
        Menu_lists_1 fragment = new Menu_lists_1();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((MainMenu) getActivity())
                .setActionBarTitle("Request list");
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
        View rootView = inflater.inflate(R.layout.fragment_main_menu,container,false);
        return rootView;
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        spinner = view.findViewById(R.id.progressBar1);
        spinner.setVisibility(View.VISIBLE);

            if (!Platform.get().isCleartextTrafficPermitted(Roles_Interface.BASE_URL)) {
                throw new RouteException(new UnknownServiceException(
                        "CLEARTEXT communication to " + Roles_Interface.BASE_URL + " not permitted by network security policy"));
            }
            OkHttpClient client = new OkHttpClient
                    .Builder()
                    .connectionSpecs(Arrays.asList(ConnectionSpec.MODERN_TLS, ConnectionSpec.CLEARTEXT))
                    .build();
            Retrofit retrofit = new Retrofit
                    .Builder()
                    .baseUrl(Roles_Interface.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(client)
                    .build();
            Roles_Interface api = retrofit.create(Roles_Interface.class);
            Call<List<Roles>> call = api.getResultsRoles();
            call.enqueue(new Callback<List<Roles>>() {
                @Override
                public void onResponse(Call<List<Roles>> call, Response<List<Roles>> response) {
                    spinner.setVisibility(View.GONE);
                    List<Roles> list = response.body();
                    for (int i = 0; i < list.size(); i++) {
                        // Log.d("RESPONSE-person",results1.get(i).getName());
                        recyclerView = view.findViewById(R.id.person_type_list);
                        mAdapter = new Menu_lists_adapter(list);
                        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
                        recyclerView.setLayoutManager(layoutManager);
                        recyclerView.setAdapter(mAdapter);
                        mAdapter.notifyDataSetChanged();
                        mAdapter.setOnClickListener(new Menu_lists_adapter.OnItemClickListener() {
                            @Override
                            public void OnItemClick(int position) {
                                pt = list.get(position).getName();
                                listclickitem(pt);
                            }
                        });
                    }
                }
                @Override
                public void onFailure(Call<List<Roles>> call, Throwable t) {
                    //Toasty.error(getActivity(),t.getMessage(), Toast.LENGTH_SHORT).show();
                    Log.d("RESPONSE-ERROR", t.getMessage().toString());
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


    public void listclickitem(String i){


            if (i.equals("Teacher")) {
                PreferenceManager.getDefaultSharedPreferences(getContext()).edit().putInt("myRolee1", 2).apply();
                PreferenceManager.getDefaultSharedPreferences(getContext()).edit().putInt("myStatus1", 1).apply();
                replaceFragmentWithAnimation(drr, "teacher_list_main", R.id.mainmenu_myfrg);

            }

            if (i.equals("Student")) {
                PreferenceManager.getDefaultSharedPreferences(getContext()).edit().putInt("myRolee1", 1).apply();
                PreferenceManager.getDefaultSharedPreferences(getContext()).edit().putInt("myStatus1", 1).apply();
                replaceFragmentWithAnimation(drr, "teacher_list_main", R.id.mainmenu_myfrg);
            }
    }
}
