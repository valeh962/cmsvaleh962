package com.example.valeh.coursemanagementsystem.Main.Fragment.HomeScreens.Student;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.ScaleAnimation;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alespero.expandablecardview.ExpandableCardView;
import com.example.valeh.coursemanagementsystem.Main.JsonWorks.Schedule.ScheduleMockData;
import com.example.valeh.coursemanagementsystem.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import static android.os.Looper.getMainLooper;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Home_student.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Home_student#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Home_student extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    Student_rec_adapter adapter;

    RelativeLayout rel_content1;
    RecyclerView recyclerView1;
    TextView title1,time1;
    ImageView close1;
    FrameLayout frame1;

    RelativeLayout rel_content2;
    RecyclerView recyclerView2;
    TextView title2,time2;
    ImageView close2;
    FrameLayout frame2;

    RelativeLayout rel_content3;
    RecyclerView recyclerView3;
    TextView title3,time3;
    ImageView close3;
    FrameLayout frame3;

    RelativeLayout rel_content4;
    RecyclerView recyclerView4;
    TextView title4,time4;
    ImageView close4;
    FrameLayout frame4;

    RelativeLayout rel_content5;
    RecyclerView recyclerView5;
    TextView title5,time5;
    ImageView close5;
    FrameLayout frame5;

    RelativeLayout rel_content6;
    RecyclerView recyclerView6;
    TextView title6,time6;
    ImageView close6;
    FrameLayout frame6;

    RelativeLayout rel_content7;
    RecyclerView recyclerView7;
    TextView title7,time7;
    ImageView close7;
    FrameLayout frame7;

    SimpleDateFormat sdf = new SimpleDateFormat("d MMMM yyyy");
    String currentDateandTime = sdf.format(new Date());
    SimpleDateFormat sdf1 = new SimpleDateFormat("EEEE", Locale.US);
    SimpleDateFormat sdf2 = new SimpleDateFormat("HH:MM");
    Calendar calendar = Calendar.getInstance();


    String dayOfWeek = sdf1.format(calendar.getTime());
    String currentClock = sdf2.format(calendar.getTime());
    int weekNumber = calendar.get(Calendar.WEEK_OF_YEAR);
    int yearNumber = calendar.get(Calendar.YEAR);
    TextView currentDate,currentDate_hour;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public Home_student() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Home_student.
     */
    // TODO: Rename and change types and number of parameters
    public static Home_student newInstance(String param1, String param2) {
        Home_student fragment = new Home_student();
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
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home_student, container, false);
    }



    @SuppressLint("SetTextI18n")
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        initDays(view);
        currentDate = view.findViewById(R.id.textView58);
        currentDate_hour = view.findViewById(R.id.textView581);
        currentDate.setText(currentDateandTime);

        Handler someHandler = new Handler(getMainLooper());
        someHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                currentDate_hour.setText(new SimpleDateFormat("HH:mm").format(new Date()));
                someHandler.postDelayed(this, 1000);
            }
        }, 10);






    }

    private void initDays(View view) {

        day_setup(view,R.id.rec_days1,R.id.rel_content1,R.id.close1,R.id.title1,"Monday"
                    ,recyclerView1,rel_content1,close1,title1,1,R.id.time1,time1,getStartEndOFWeek(1),R.id.frame1,frame1);
        day_setup(view,R.id.rec_days2,R.id.rel_content2,R.id.close2,R.id.title2,"Tuesday"
                    ,recyclerView2,rel_content2,close2,title2,2,R.id.time2,time2,getStartEndOFWeek(2),R.id.frame2,frame2);
        day_setup(view,R.id.rec_days3,R.id.rel_content3,R.id.close3,R.id.title3,"Wednesday"
                ,recyclerView3,rel_content3,close3,title3,3,R.id.time3,time3,getStartEndOFWeek(3),R.id.frame3,frame3);
        day_setup(view,R.id.rec_days4,R.id.rel_content4,R.id.close4,R.id.title4,"Thursday"
                ,recyclerView4,rel_content4,close4,title4,4,R.id.time4,time4,getStartEndOFWeek(4),R.id.frame4,frame4);
        day_setup(view,R.id.rec_days5,R.id.rel_content5,R.id.close5,R.id.title5,"Friday"
                ,recyclerView5,rel_content5,close5,title5,5,R.id.time5,time5,getStartEndOFWeek(5),R.id.frame5,frame5);
        day_setup(view,R.id.rec_days6,R.id.rel_content6,R.id.close6,R.id.title6,"Saturday"
                ,recyclerView6,rel_content6,close6,title6,6,R.id.time6,time6,getStartEndOFWeek(6),R.id.frame6,frame6);
        day_setup(view,R.id.rec_days7,R.id.rel_content7,R.id.close7,R.id.title7,"Sunday"
                ,recyclerView7,rel_content7,close7,title7,7,R.id.time7,time7,getStartEndOFWeek(7),R.id.frame7,frame7);


    }



    private void day_setup(View view, int rec_days, int rel_content, int close, int title, String titlest,
                           RecyclerView rec_ex, RelativeLayout rel_content_ex, ImageView close_ex, TextView title_ex,int weekday
                            ,int time1_day, TextView time1_ex,String date,int frame1_lay,FrameLayout frame_ex) {
        rec_ex = view.findViewById(rec_days);
        rel_content_ex = view.findViewById(rel_content);
        close_ex = view.findViewById(close);
        title_ex = view.findViewById(title);
        title_ex.setText(titlest);
        time1_ex = view.findViewById(time1_day);
        time1_ex.setText(date);
        frame_ex = view.findViewById(frame1_lay);
        frame_ex.bringToFront();
        RelativeLayout finalRel_content_ex = rel_content_ex;
        Animation fadein = AnimationUtils.loadAnimation(getContext(), R.anim.scale_down);
        Animation fadeout = AnimationUtils.loadAnimation(getContext(), R.anim.scale_up);
        if(titlest.equals(dayOfWeek))
        {
            rel_content_ex.setVisibility(View.VISIBLE);
            title_ex.setTextColor(Color.RED);
            time1_ex.setTextColor(Color.RED);
            title_ex.setCompoundDrawablesWithIntrinsicBounds(0,0,R.drawable.ic_bookmark_red_24dp,0);
        }
        else {
            title_ex.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
            time1_ex.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
            rel_content_ex.setVisibility(View.GONE);
            title_ex.setCompoundDrawablesWithIntrinsicBounds(0,0,0,0);
        }
        frame_ex.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(finalRel_content_ex.getVisibility()==View.GONE){

                    collapse(finalRel_content_ex);

//                    finalRel_content_ex.setVisibility(View.VISIBLE);
                }
                else {

                    expand(finalRel_content_ex);

//                   finalRel_content_ex.setVisibility(View.GONE);
                }

            }
        });
        RelativeLayout finalRel_content_ex1 = rel_content_ex;
        close_ex.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                expand(finalRel_content_ex1);
//                finalRel_content_ex.startAnimation(fadeout);
//                finalRel_content_ex1.setVisibility(View.GONE);
            }
        });
        ArrayList<ScheduleMockData> arrayList = new ArrayList<>();
        arrayList = getDataForDays(1,titlest);
        adapter = new Student_rec_adapter(arrayList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        rec_ex.setLayoutManager(layoutManager);
        rec_ex.setAdapter(adapter);

        ArrayList<ScheduleMockData> finalArrayList = arrayList;
        adapter.setOnItemClickListener(new Student_rec_adapter.OnItemClickListener() {
            @Override
            public void OnItemClick(int position) {
                String sch_subject = finalArrayList.get(position).getSubj();
                String sch_time = finalArrayList.get(position).getClock();
                showHomework(sch_subject,sch_time);
            }
        });
    }

    private void showHomework(String sch_subject, String sch_time) {

        Dialog dialog = new Dialog(getContext());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.homework_layout_popup);
        dialog.setCanceledOnTouchOutside(true);
        dialog.setCancelable(true);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        float dm = 0.9f;
        dialog.getWindow().setDimAmount(dm);

        TextView tv_title = dialog.findViewById(R.id.textView61);
        tv_title.setText(sch_subject);
        TextView tv_clock = dialog.findViewById(R.id.textView62);
        tv_clock.setText(sch_time);

        TextView tv_homework = dialog.findViewById(R.id.textView60);
        for(int i=0;i<40;i++)
        tv_homework.append("test");

        ImageView close = dialog.findViewById(R.id.imageView18);
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialog.show();

    }

    public ArrayList<ScheduleMockData> getDataForDays(int weekday,String st){

        ArrayList<ScheduleMockData> arr = new ArrayList<>();
        for (int i=0;i<3;i++) {
            ScheduleMockData sch = new ScheduleMockData(
                    st+", Java",
                    "14:00"
            );
            arr.add(sch);
        }

        return arr;
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

    public void expand(final View v) {
        Animation fadein = AnimationUtils.loadAnimation(getContext(), R.anim.fade_in);
        Animation fadeout = AnimationUtils.loadAnimation(getContext(), R.anim.fade_out);
        ScaleAnimation scaleAnimation = new ScaleAnimation(1, 1, 1, 0, 0, 0);
        scaleAnimation.setDuration(350);
        scaleAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                v.startAnimation(fadeout);
                v.setVisibility(View.GONE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        v.startAnimation(scaleAnimation);
    }

    public void collapse(final View v) {
        Animation fadein = AnimationUtils.loadAnimation(getContext(), R.anim.fade_in);
        Animation fadeout = AnimationUtils.loadAnimation(getContext(), R.anim.fade_out);
        ScaleAnimation scaleAnimation = new ScaleAnimation(1, 1, 0, 1, 0, 0);
        scaleAnimation.setDuration(350);
        scaleAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {


            }

            @Override
            public void onAnimationEnd(Animation animation) {
                v.startAnimation(fadein);
                v.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        v.startAnimation(scaleAnimation);
    }


    public String getStartEndOFWeek(int day){

        int enterWeek = weekNumber;
        int enterYear = yearNumber;
        Calendar calendar = Calendar.getInstance();
        calendar.clear();
        calendar.set(Calendar.WEEK_OF_YEAR, enterWeek);
        calendar.set(Calendar.YEAR, enterYear);

        SimpleDateFormat formatter = new SimpleDateFormat("d MMMM yyyy"); // PST`
        Date startDate = calendar.getTime();
        String startDateInStr = formatter.format(startDate);
     //   System.out.println("...date..."+startDateInStr);
        Log.d("WEEKDAY_start",startDateInStr);

       // Log.d("WEEKDAY_end",endDaString);
     //   System.out.println("...date..."+endDaString);
        Date enddate;
        String endDaString;
        switch (day){
            case 1:
                calendar.add(Calendar.DATE, 1);
                enddate = calendar.getTime();
                endDaString = formatter.format(enddate);
                return endDaString;

            case 2:
                calendar.add(Calendar.DATE, 2);
                enddate = calendar.getTime();
                endDaString = formatter.format(enddate);
                return endDaString;

            case 3:
                calendar.add(Calendar.DATE, 3);
                enddate = calendar.getTime();
                endDaString = formatter.format(enddate);
                return endDaString;

            case 4:
                calendar.add(Calendar.DATE, 4);
                enddate = calendar.getTime();
                endDaString = formatter.format(enddate);
                return endDaString;

            case 5:
                calendar.add(Calendar.DATE, 5);
                enddate = calendar.getTime();
                endDaString = formatter.format(enddate);
                return endDaString;

            case 6:
                calendar.add(Calendar.DATE, 6);
                enddate = calendar.getTime();
                endDaString = formatter.format(enddate);
                return endDaString;

            case 7:
                calendar.add(Calendar.DATE, 7);
                enddate = calendar.getTime();
                endDaString = formatter.format(enddate);
                return endDaString;

        }
        return "0";

    }

}
