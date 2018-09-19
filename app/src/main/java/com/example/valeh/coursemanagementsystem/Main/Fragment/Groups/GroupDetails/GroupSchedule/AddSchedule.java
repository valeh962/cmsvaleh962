package com.example.valeh.coursemanagementsystem.Main.Fragment.Groups.GroupDetails.GroupSchedule;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;

import com.example.valeh.coursemanagementsystem.R;

import java.util.Objects;

public class AddSchedule extends AppCompatActivity {

    CheckBox ch1,ch2,ch3,ch4,ch5,ch6,ch7;
    TextView tv1,tv2,tv3,tv4,tv5,tv6,tv7;
    Button setTime;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_schedule);

        Toolbar toolbar = findViewById(R.id.toolbar1);
        setSupportActionBar(toolbar);

        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        final Drawable upArrow = getResources().getDrawable(R.drawable.ic_chevron_left_appbar_24dp);
      //  upArrow.setColorFilter(getResources().getColor(R.color.semi_white_transparant), PorterDuff.Mode.SRC_ATOP);
        getSupportActionBar().setHomeAsUpIndicator(upArrow);
        getSupportActionBar().setTitle("Edit schedule");

        setTime = findViewById(R.id.setTime_btn);

        ch1 = findViewById(R.id.ch1);
        ch2 = findViewById(R.id.ch2);
        ch3 = findViewById(R.id.ch3);
        ch4 = findViewById(R.id.ch4);
        ch5 = findViewById(R.id.ch5);
        ch6 = findViewById(R.id.ch6);
        ch7 = findViewById(R.id.ch7);

        tv1 = findViewById(R.id.cl1);
        tv2 = findViewById(R.id.cl2);
        tv3 = findViewById(R.id.cl3);
        tv4 = findViewById(R.id.cl4);
        tv5 = findViewById(R.id.cl5);
        tv6 = findViewById(R.id.cl6);
        tv7 = findViewById(R.id.cl7);

        tv1.setVisibility(View.GONE);
        tv2.setVisibility(View.GONE);
        tv3.setVisibility(View.GONE);
        tv4.setVisibility(View.GONE);
        tv5.setVisibility(View.GONE);
        tv6.setVisibility(View.GONE);
        tv7.setVisibility(View.GONE);

        ch1.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if(isChecked){ setTime(1,tv1); }
            else { tv1.setVisibility(View.GONE);}});
        ch2.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if(isChecked){ setTime(2,tv2); }
            else { tv2.setVisibility(View.GONE);}});
        ch3.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if(isChecked){ setTime(3,tv3); }
            else { tv3.setVisibility(View.GONE);}});
        ch4.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if(isChecked){ setTime(4,tv4); }
            else { tv4.setVisibility(View.GONE);}});
        ch5.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if(isChecked){ setTime(5,tv5); }
            else { tv5.setVisibility(View.GONE);}});
        ch6.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if(isChecked){ setTime(6,tv6); }
            else { tv6.setVisibility(View.GONE);}});
        ch7.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if(isChecked){ setTime(7,tv7); }
            else { tv7.setVisibility(View.GONE);}});

        setTime.setOnClickListener(v -> {
            postSchedule();
            finish();
        });

    }

    private void postSchedule() {

    }


    @SuppressLint("SetTextI18n")
    private void setTime(int i, TextView tv) {

        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.settime_schedule_popup);
        dialog.setCanceledOnTouchOutside(false);
        dialog.setCancelable(false);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        float dm = 0.5f;
        dialog.getWindow().setDimAmount(dm);

        TimePicker tm = dialog.findViewById(R.id.timepicker1);
        tm.setIs24HourView(true);
        ImageView cls = dialog.findViewById(R.id.cls_timepicker);

        cls.setOnClickListener(v -> {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            switch (i){
                case 1:
                    tv.setVisibility(View.VISIBLE);
                    tv.setText(tm.getHour()+":"+tm.getMinute());
                    dialog.dismiss();
                    break;
                case 2:
                    tv.setVisibility(View.VISIBLE);
                    tv.setText(tm.getHour()+":"+tm.getMinute());
                    dialog.dismiss();
                    break;
                case 3:
                    tv.setVisibility(View.VISIBLE);
                    tv.setText(tm.getHour()+":"+tm.getMinute());
                    dialog.dismiss();
                    break;
                case 4:
                    tv.setVisibility(View.VISIBLE);
                    tv.setText(tm.getHour()+":"+tm.getMinute());
                    dialog.dismiss();
                    break;
                case 5:
                    tv.setVisibility(View.VISIBLE);
                    tv.setText(tm.getHour()+":"+tm.getMinute());
                    dialog.dismiss();
                    break;
                case 6:
                    tv.setVisibility(View.VISIBLE);
                    tv.setText(tm.getHour()+":"+tm.getMinute());
                    dialog.dismiss();
                    break;
                case 7:
                    tv.setVisibility(View.VISIBLE);
                    tv.setText(tm.getHour()+":"+tm.getMinute());
                    dialog.dismiss();
                    break;
            }



            }
        });



        dialog.show();
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
