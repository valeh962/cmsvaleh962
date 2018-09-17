package com.example.valeh.coursemanagementsystem.Main.Fragment.HomeScreens.Student;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.valeh.coursemanagementsystem.Main.Fragment.Groups.MyGroups_adapter;
import com.example.valeh.coursemanagementsystem.Main.JsonWorks.Schedule.ScheduleMockData;
import com.example.valeh.coursemanagementsystem.R;

import java.util.ArrayList;

public class Student_rec_adapter extends RecyclerView.Adapter<Student_rec_adapter.MainViewHolder> {

    ArrayList<ScheduleMockData> list;
    OnItemClickListener listener;

    public interface OnItemClickListener {
        void OnItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener){
        listener = onItemClickListener;
    }

    @NonNull
    @Override
    public MainViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.weekdays_rec_item,viewGroup,false );
        MainViewHolder mvh = new MainViewHolder(v,listener);

        return mvh;
    }

    @Override
    public void onBindViewHolder(@NonNull MainViewHolder mainViewHolder, int i) {

        ScheduleMockData scheduleMockData = list.get(i);
       mainViewHolder.subject.setText(scheduleMockData.getSubj());
       mainViewHolder.clock.setText(scheduleMockData.getClock());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public Student_rec_adapter (ArrayList<ScheduleMockData> lists){
        list = lists;
    }

    public class MainViewHolder extends RecyclerView.ViewHolder {

        TextView subject,clock;

        public MainViewHolder(@NonNull View itemView, OnItemClickListener listener) {
            super(itemView);

            subject = itemView.findViewById(R.id.textView55);
            clock = itemView.findViewById(R.id.textView56);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(listener!=null){
                        int position = getAdapterPosition();
                        if(position!=RecyclerView.NO_POSITION){
                            listener.OnItemClick(position);
                        }
                    }
                }
            });
        }
    }
}
