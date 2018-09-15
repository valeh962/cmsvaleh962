package com.example.valeh.coursemanagementsystem.Main.Fragment.MainMenuLists.FilterPersons;

import android.content.Context;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.example.valeh.coursemanagementsystem.Main.DI.MyApp_classes.MyApp;
import com.example.valeh.coursemanagementsystem.Main.DI.SharedManagement;
import com.example.valeh.coursemanagementsystem.Main.Fragment.MainMenuLists.RequestListofTandS.Teacher_details;
import com.example.valeh.coursemanagementsystem.Main.Fragment.MainMenuLists.RequestListofTandS.teacher_main_list_adapter;
import com.example.valeh.coursemanagementsystem.Main.Helpers.BaseFragment;
import com.example.valeh.coursemanagementsystem.Main.Helpers.RetrofitBuilder;
import com.example.valeh.coursemanagementsystem.Main.JsonWorks.MakeRequest.Roles;
import com.example.valeh.coursemanagementsystem.Main.JsonWorks.MakeRequest.Roles_Interface;
import com.example.valeh.coursemanagementsystem.Main.JsonWorks.Subject.Subject_1;
import com.example.valeh.coursemanagementsystem.Main.JsonWorks.Subject.Subject_Interface;
import com.example.valeh.coursemanagementsystem.Main.JsonWorks.Teachers.TeachersAllModel;
import com.example.valeh.coursemanagementsystem.Main.JsonWorks.Teachers.TeachersAll_Interface;
import com.example.valeh.coursemanagementsystem.R;

import org.w3c.dom.Text;

import java.net.UnknownServiceException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

import okhttp3.ConnectionSpec;
import okhttp3.OkHttpClient;
import okhttp3.internal.connection.RouteException;
import okhttp3.internal.platform.Platform;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link FilteredPersons.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link FilteredPersons#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FilteredPersons extends BaseFragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public String tokken;
    private LottieAnimationView spinner;
    private RecyclerView recyclerView;
    private teacher_main_list_adapter mAdapter,mAdapter1;
    Spinner lesson_type,person_type;
    ArrayAdapter<String> adapter1;
    ArrayList<String> results,results1;
    TextView setFilter;
    int listSize=0,listSize1 = 0;
    ArrayList <TeachersAllModel> list = new ArrayList();
    TextView showall;
    private OnFragmentInteractionListener mListener;
    RecyclerView recyclerView1;
    @Inject
    SharedManagement sharedManagement;
    private LinearLayout panel1;
    public FilteredPersons() {
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FilteredPersons.
     */
    // TODO: Rename and change types and number of parameters
    public static FilteredPersons newInstance(String param1, String param2) {
        FilteredPersons fragment = new FilteredPersons();
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
        View rootView = inflater.inflate(R.layout.fragment_filtered_persons,container,false);
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

        MyApp.app().basicComponent().FilteredPersons_inject(this);
        tokken = sharedManagement.getStringSaved("TOKEN");
        Log.d("TOKEN",tokken);

        recyclerView = view.findViewById(R.id.rec11);
        recyclerView1 = view.findViewById(R.id.rec12);
        showall = view.findViewById(R.id.showall);
        spinner = view.findViewById(R.id.progressBar2);
        spinner.setVisibility(View.VISIBLE);
        lesson_type = view.findViewById(R.id.sppp1);
        person_type = view.findViewById(R.id.sppp2);
        setFilter = view.findViewById(R.id.setFilter);

        panel1 = view.findViewById(R.id.panel1);

        results = new ArrayList<>();
        results1 = new ArrayList<>();
        fillSpinner1(view);
        fillSpinner2(view);
        spinner.bringToFront();
        spinner.setVisibility(View.VISIBLE);
        recyclerView.setVisibility(View.GONE);
        fillRecyclerViewAll();
        showall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                spinner.setVisibility(View.VISIBLE);
                recyclerView.setVisibility(View.GONE);
                recyclerView1.setVisibility(View.VISIBLE);
                list.clear();
                fillRecyclerViewAll();
            }
        });
        setFilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                spinner.setVisibility(View.VISIBLE);
                recyclerView.setVisibility(View.VISIBLE);
                recyclerView1.setVisibility(View.GONE);
                list.clear();
                fillRecyclerView(person_type.getSelectedItemPosition() + 1,lesson_type.getSelectedItemPosition()+1,view);
            }
        });
    }



    private void fillRecyclerViewAll() {
        list.clear();
        TeachersAll_Interface api = RetrofitBuilder.buildRetrofitrx(TeachersAll_Interface.BASE_URL).create(TeachersAll_Interface.class);
        Call<ArrayList<TeachersAllModel>> call = api.getNoFilterTeachers(tokken);
        call.enqueue(new Callback<ArrayList<TeachersAllModel>>() {
            @Override
            public void onResponse(Call<ArrayList<TeachersAllModel>> call, Response<ArrayList<TeachersAllModel>> response) {
                if(response.isSuccessful()) {
                    list.clear();
                    spinner.setVisibility(View.GONE);
                    list = response.body();
                    for (int i = 0; i < list.size(); i++) {
                        mAdapter1 = new teacher_main_list_adapter(list);
                        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
                        recyclerView1.setLayoutManager(layoutManager);
                        mAdapter1.setOnItemClickListener(new teacher_main_list_adapter.OnItemClickListener() {
                            @Override
                            public void OnItemClick(int position) {

                                Fragment fr = new Teacher_details();
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

                                replaceFragmentWithAnimation(fr, "teacher_details", R.id.mainmenu_myfrg);
                            }
                        });
                        mAdapter1.notifyDataSetChanged();
                    }
                    listSize = list.size();
                    recyclerView1.setAdapter(mAdapter1);
                }
                else
                {
                    Toast.makeText(getContext(),"Please login again.",Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<ArrayList<TeachersAllModel>>call, Throwable t) {
                //Toasty.error(getActivity(),t.getMessage(), Toast.LENGTH_SHORT).show();
                Log.d("RESPONSE-ERROR",t.getMessage().toString());
            }
        });
    }

    private void fillRecyclerView(int i,int j,View view) {
        list.clear();
        TeachersAll_Interface api = RetrofitBuilder.buildRetrofitrx(TeachersAll_Interface.BASE_URL).create(TeachersAll_Interface.class);
        Call<ArrayList<TeachersAllModel>> call = api.getAllTeachers(tokken,i,j);
        call.enqueue(new Callback<ArrayList<TeachersAllModel>>() {
            @Override
            public void onResponse(Call<ArrayList<TeachersAllModel>> call, Response<ArrayList<TeachersAllModel>> response) {
                list.clear();
                spinner.setVisibility(View.GONE);
                list = response.body();
                for(int i=0;i<list.size();i++) {
                    mAdapter = new teacher_main_list_adapter(list);
                    RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
                    recyclerView.setLayoutManager(layoutManager);
                    mAdapter.setOnItemClickListener(new teacher_main_list_adapter.OnItemClickListener() {
                        @Override
                        public void OnItemClick(int position) {

                            Toast.makeText(getActivity(),list.get(position).getName()+" "+list.get(position).getSurname(),Toast.LENGTH_SHORT).show();

                            Fragment fr = new Teacher_details();
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
                    mAdapter.notifyDataSetChanged();
                }
                recyclerView.setAdapter(mAdapter);
                listSize1 = list.size();
            }
            @Override
            public void onFailure(Call<ArrayList<TeachersAllModel>>call, Throwable t) {
                //Toasty.error(getActivity(),t.getMessage(), Toast.LENGTH_SHORT).show();
                Log.d("RESPONSE-ERROR",t.getMessage().toString());
            }
        });
    }

    private void fillSpinner2(View view) {


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
        adapter1 = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_item, results1);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        call.enqueue(new Callback<List<Roles>>() {
            @Override
            public void onResponse(Call<List<Roles>> call, Response<List<Roles>> response) {
                results1.clear();
                List<Roles> subject_1s = response.body();
                for(int i=0;i<subject_1s.size();i++){
                    results1.add(subject_1s.get(i).getName());
                }
                adapter1.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<Roles>> call, Throwable t) {
                //Toasty.error(getActivity(),t.getMessage(), Toast.LENGTH_SHORT).show();
                Log.d("RESPONSE-ERROR",t.getMessage().toString());
            }
        });
        person_type.setAdapter(adapter1);


    }

    private void fillSpinner1(View view) {
        ArrayList<String> arr = new ArrayList<>();
        arr.add("Not accepted yet");
        arr.add("Accepted persons");
        arr.add("Ignored");

        ArrayAdapter arrayAdapter = new ArrayAdapter<>(getActivity(),android.R.layout.simple_spinner_item,arr);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        lesson_type.setAdapter(arrayAdapter);
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
