package com.example.valeh.coursemanagementsystem.Main.Fragment.MainMenuLists.TeacherandStudents;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.valeh.coursemanagementsystem.Main.JsonWorks.Teachers.TeachersAllModel;
import com.example.valeh.coursemanagementsystem.R;

import java.util.ArrayList;


public class teacher_main_list_adapter extends RecyclerView.Adapter<teacher_main_list_adapter.MainViewHolder> {

    private ArrayList<TeachersAllModel> mlists;
    public OnItemClickListener mListener;

    public interface OnItemClickListener {
        void OnItemClick(int position);
    }

    public void setOnItemClickListener (OnItemClickListener listener){
        mListener = listener;
    }

    public static class MainViewHolder extends RecyclerView.ViewHolder{

        public TextView tv1,tv2,tv3;

        public MainViewHolder(@NonNull View itemView,OnItemClickListener listener) {
            super(itemView);

            tv1 = (TextView)itemView.findViewById(R.id.teacher_name);
            tv2 = (TextView)itemView.findViewById(R.id.teacher_subject);
         //   tv3 = (TextView)itemView.findViewById(R.id.teacher_money);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
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

    public teacher_main_list_adapter (ArrayList<TeachersAllModel> lists){
        mlists = lists;
    }

    @NonNull
    @Override
    public MainViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_teacherlist_layout1,parent,false );
        MainViewHolder mvh = new MainViewHolder(v,mListener);

        return mvh;

    }

    @Override
    public void onBindViewHolder(@NonNull MainViewHolder holder, int position) {

        TeachersAllModel currentitem = mlists.get(position);

        int subjectID=currentitem.getSubjectId();
        String subjectName;
        switch (subjectID){
            case 1:
                subjectName = "Java";
                break;
            case 2:
                subjectName = "C++";
                break;
            case 3:
                subjectName = "Android";
                break;
            case 4:
                subjectName = "IOS";
                break;
            case 5:
                subjectName = "C#";
                break;
                default:
                    subjectName = "Lesson not found!";
        }


        holder.tv1.setText(currentitem.getName()+" "+currentitem.getSurname());
        holder.tv2.setText(subjectName);
   //     holder.tv3.setText(currentitem.getPhone());

    }

    @Override
    public int getItemCount() {
        return mlists.size();
    }





}
