package com.example.valeh.coursemanagementsystem.Main.Fragment.Request;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import android.os.StrictMode;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;

import es.dmoral.toasty.Toasty;
import me.anwarshahriar.calligrapher.Calligrapher;
import okhttp3.ConnectionSpec;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import okhttp3.internal.connection.RouteException;
import okhttp3.internal.platform.Platform;
import okio.BufferedSink;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import android.provider.SyncStateContract;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.valeh.coursemanagementsystem.Main.Activity.Make_Request;
import com.example.valeh.coursemanagementsystem.Main.Helpers.BaseFragment;
import com.example.valeh.coursemanagementsystem.Main.JsonWorks.MakeRequest.Roles;
import com.example.valeh.coursemanagementsystem.Main.JsonWorks.MakeRequest.Roles_Interface;
import com.example.valeh.coursemanagementsystem.Main.JsonWorks.SendRequest.ReqBody;
import com.example.valeh.coursemanagementsystem.Main.JsonWorks.SendRequest.RolesReq;
import com.example.valeh.coursemanagementsystem.Main.JsonWorks.SendRequest.Send_Request;
import com.example.valeh.coursemanagementsystem.Main.JsonWorks.SendRequest.SubjectReq;
import com.example.valeh.coursemanagementsystem.Main.JsonWorks.Subject.Subject_1;
import com.example.valeh.coursemanagementsystem.Main.JsonWorks.Subject.Subject_Interface;
import com.example.valeh.coursemanagementsystem.R;

import java.io.IOException;
import java.net.UnknownServiceException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
public class make_request2 extends BaseFragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    ProgressDialog progressBar = null;
    EditText editTextName,editTextSurname,
             editTextEmail,editTextPhone,
             editTextaddress,editTextAdditionalInfo;
    Spinner lesson_type,person_type;
    Button mainmenu;
    TextView tv;
    ArrayList<String> results,results1;
    ArrayList<Integer> results2;
    ArrayAdapter<String> adapter,adapter1;
    String s;
    int keyDel;
    int errorCount=0,errorCount1=0;
    private OnFragmentInteractionListener mListener;

    public make_request2() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static make_request2 newInstance(String param1, String param2) {
        make_request2 fragment = new make_request2();
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
        View rootView = inflater.inflate(R.layout.fragment_make_request2,container,false);
        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Calligrapher calligrapher = new Calligrapher(getActivity());
        calligrapher.setFont(getActivity(),"font/CirceRounded-Light.otf",true);

        results = new ArrayList<String>();
        results1 = new ArrayList<String>();
        results2 = new ArrayList<Integer>();
        lesson_type = view.findViewById(R.id.spinner);
        person_type = view.findViewById(R.id.spinner2);
        mainmenu = (Button)view.findViewById(R.id.request_btn);
        tv = (TextView)view.findViewById(R.id.textView7);

        editTextName = (EditText)view.findViewById(R.id.name_txt);
        editTextSurname = (EditText)view.findViewById(R.id.surname_txt);
        editTextEmail = (EditText)view.findViewById(R.id.email_txt);
        editTextPhone = (EditText)view.findViewById(R.id.phone_txt);
        editTextaddress = (EditText)view.findViewById(R.id.address_txt);
        editTextAdditionalInfo = (EditText)view.findViewById(R.id.addinfo_txt);
        tv.setText("Welcome!");

        editTextName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }
            @Override
            public void afterTextChanged(Editable editable) {
                if(TextUtils.isEmpty(editTextName.getText().toString())){
                    editTextName.setError("This field must be filled!");
                }}
        });
        editTextSurname.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }
            @Override
            public void afterTextChanged(Editable editable) {
                if(TextUtils.isEmpty(editTextSurname.getText().toString())){
                    editTextSurname.setError("This field must be filled!");}
            }
        });
        editTextEmail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }
            @Override
            public void afterTextChanged(Editable editable) {
                CharSequence cemail = editTextEmail.getText().toString();
                if (!android.util.Patterns.EMAIL_ADDRESS.matcher(cemail).matches()){
                    editTextEmail.setError("Enter correct email!");
                    errorCount=1;
                }
                else errorCount =0;
            }
        });
        editTextPhone.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if(editable.length()<9){
                    editTextPhone.setError("Please enter correct phone number!");
                    errorCount1 =1;
                }
                else errorCount1 =0;
            }
        });
        personType();
        lessonType();
        mainmenu.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                if(TextUtils.isEmpty(editTextName.getText()) || TextUtils.isEmpty(editTextSurname.getText()) || TextUtils.isEmpty(editTextEmail.getText()) || TextUtils.isEmpty(editTextPhone.getText()) || TextUtils.isEmpty(editTextaddress.getText()) || TextUtils.isEmpty(editTextAdditionalInfo.getText()))
                {
                    Toasty.info(getActivity(),"You haven't completed all of requirements yet!",Toast.LENGTH_SHORT).show();
                }
                else{
                if(errorCount == 0 && errorCount1==0) {
                    ReqBody reqBody = new ReqBody(
                            editTextName.getText().toString(),
                            editTextSurname.getText().toString(),
                            results2.get(person_type.getSelectedItemPosition()),
                            "(+994)"+editTextPhone.getText().toString(),
                            editTextEmail.getText().toString(),
                            editTextaddress.getText().toString(),
                            lesson_type.getSelectedItemPosition() + 1,
                            editTextAdditionalInfo.getText().toString().trim()
                    );
                    progressBar = ProgressDialog.show(getActivity(), "Please wait.", "Sending your request...", true);

                    try {
                        postinfos(reqBody);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                else
                    Toasty.info(getActivity(),"Please control all the fields if correct!",Toast.LENGTH_SHORT).show();
                }


            }
        });
    }

    // TODO: Rename method, update argument and hook method into UI event

    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }
    private void postinfos(ReqBody reqBody) throws IOException {

        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl(Send_Request.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create());

        Retrofit retrofit = builder.build();

        Send_Request postreq = retrofit.create(Send_Request.class);

        Call<ReqBody> call = postreq.send_details(reqBody);

        call.enqueue(new Callback<ReqBody>() {
            @Override
            public void onResponse(Call<ReqBody> call, Response<ReqBody> response) {
                progressBar.cancel();
                Toasty.success(getActivity(),"Success!",Toast.LENGTH_SHORT).show();
 //               Log.e("response-success", response.body().toString());

                Fragment fr = new make_request3();
                replaceFragmentWithAnimation(fr, "make_request3",R.id.request_frg);

            }

            @Override
            public void onFailure(Call<ReqBody> call, Throwable t) {
                progressBar.cancel();
                Toasty.error(getActivity(),"Error! Please try again later.",Toast.LENGTH_SHORT).show();
      //          Log.e("response-failure", call.toString());
            }
        });
    }
    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }
    @Override
    public void onResume() {
        super.onResume();

        if(getView() == null){
            return;
        }

        getView().setFocusableInTouchMode(true);
        getView().requestFocus();
        getView().setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {

                if (event.getAction() == KeyEvent.ACTION_UP && keyCode == KeyEvent.KEYCODE_BACK){
                    // startActivity(new Intent(getActivity(),LoginRegister.class));
                  getActivity().finish();

                    return true;
                }
                return false;
            }
        });
    }
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    public void personType(){
        if (!Platform.get().isCleartextTrafficPermitted(Subject_Interface.BASE_URL)) {
            throw new RouteException(new UnknownServiceException(
                    "CLEARTEXT communication to " + Roles_Interface.BASE_URL + " not permitted by network security policy"));}
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
        adapter1 = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, results1);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        call.enqueue(new Callback<List<Roles>>() {
            @Override
            public void onResponse(Call<List<Roles>> call, Response<List<Roles>> response) {
                results1.clear();
                results2.clear();
                List<Roles> subject_1s = response.body();
                for(int i=0;i<subject_1s.size();i++){
                    results1.add(subject_1s.get(i).getName());
                    results2.add(subject_1s.get(i).getId());
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

    public void lessonType(){
        if (!Platform.get().isCleartextTrafficPermitted(Subject_Interface.BASE_URL)) {
            throw new RouteException(new UnknownServiceException(
                    "CLEARTEXT communication to " + Subject_Interface.BASE_URL + " not permitted by network security policy"));}
        OkHttpClient client = new OkHttpClient
                .Builder()
                .connectionSpecs(Arrays.asList(ConnectionSpec.MODERN_TLS, ConnectionSpec.CLEARTEXT))
                .build();
        Retrofit retrofit = new Retrofit
                .Builder()
                .baseUrl(Subject_Interface.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();
        Subject_Interface api = retrofit.create(Subject_Interface.class);
        Call<List<Subject_1>> call = api.getResults();
        adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, results);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        call.enqueue(new Callback<List<Subject_1>>() {
            @Override
            public void onResponse(Call<List<Subject_1>> call, Response<List<Subject_1>> response) {
                results.clear();
                List<Subject_1> subject_1s = response.body();
                for(int i=0;i<subject_1s.size();i++){
                    results.add(subject_1s.get(i).getName());
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<Subject_1>> call, Throwable t) {
                //Toasty.error(getActivity(),t.getMessage(), Toast.LENGTH_SHORT).show();
                Log.d("RESPONSE-ERROR",t.getMessage().toString());
            }
        });
        lesson_type.setAdapter(adapter);
    }
   }
