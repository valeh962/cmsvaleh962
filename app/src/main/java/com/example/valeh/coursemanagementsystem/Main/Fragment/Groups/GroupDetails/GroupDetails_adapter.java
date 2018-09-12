package com.example.valeh.coursemanagementsystem.Main.Fragment.Groups.GroupDetails;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.media.Image;
import android.net.Uri;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.valeh.coursemanagementsystem.Main.DI.MyApp_classes.MyApp;
import com.example.valeh.coursemanagementsystem.Main.DI.SharedManagement;
import com.example.valeh.coursemanagementsystem.Main.Fragment.Groups.MyGroups_adapter;
import com.example.valeh.coursemanagementsystem.Main.JsonWorks.Groups.GroupsMainDatum;
import com.example.valeh.coursemanagementsystem.Main.JsonWorks.Groups.StudentList;
import com.example.valeh.coursemanagementsystem.R;

import java.util.ArrayList;

import javax.inject.Inject;

public class GroupDetails_adapter extends RecyclerView.Adapter<GroupDetails_adapter.MainViewHolder> {

    private ArrayList<StudentList> mlist;
    public GroupDetails_adapter.OnItemClickListener mlistener;
    int groupId;

    @Inject
    SharedManagement sharedManagement;

    @NonNull
    @Override
    public GroupDetails_adapter.MainViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.groupdetails_custom, viewGroup, false);
        GroupDetails_adapter.MainViewHolder mvh = new GroupDetails_adapter.MainViewHolder(v, mlistener);
        return mvh;
    }

    @Override
    public void onBindViewHolder(@NonNull GroupDetails_adapter.MainViewHolder mainViewHolder, int i) {

        MyApp.app().basicComponent().GroupDetails_adapter_inject(this);

        groupId = sharedManagement.getIntSaved("groupid");
        StudentList groupsMainDatum = mlist.get(i);
        //    ArrayList<StudentList> studentLists = groupsMainDatum.getStudentList();
        String stdName = groupsMainDatum.getName();
        String stdName2 = groupsMainDatum.getSurname();
        String phone = groupsMainDatum.getPhone();

//        if(groupsMainDatum.getId() == groupId)
        mainViewHolder.tv1.setText("" + stdName + " " + stdName2);
        //      else mainViewHolder.relativeLayout.setVisibility(View.GONE);


    }

    @Override
    public int getItemCount() {
        return mlist.size();
    }

    public interface OnItemClickListener {
        void OnItemClick(int position);
    }

    public void setOnItemClickListener(GroupDetails_adapter.OnItemClickListener onItemClickListener) {
        mlistener = onItemClickListener;
    }

    public GroupDetails_adapter(ArrayList<StudentList> lists) {
        mlist = lists;
    }

    public class MainViewHolder extends RecyclerView.ViewHolder {

        public TextView tv1;
        public RelativeLayout relativeLayout;


        public MainViewHolder(@NonNull View itemView, GroupDetails_adapter.OnItemClickListener onItemClickListener) {
            super(itemView);

            tv1 = itemView.findViewById(R.id.textView42);
            relativeLayout = itemView.findViewById(R.id.grp_det);




            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(onItemClickListener!=null){
                        int position = getAdapterPosition();
                        if(position!=RecyclerView.NO_POSITION){
                            onItemClickListener.OnItemClick(position);
                        }}
                }
            });

        }
    }
}
