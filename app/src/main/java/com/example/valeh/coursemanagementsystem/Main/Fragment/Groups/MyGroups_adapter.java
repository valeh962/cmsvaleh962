package com.example.valeh.coursemanagementsystem.Main.Fragment.Groups;


import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.valeh.coursemanagementsystem.Main.JsonWorks.Groups.GroupsMainDatum;
import com.example.valeh.coursemanagementsystem.R;

import java.util.ArrayList;

public class MyGroups_adapter extends RecyclerView.Adapter<MyGroups_adapter.MainViewHolder>{

    private ArrayList<GroupsMainDatum> mlist;
    public OnItemClickListener mlistener;

    @NonNull
    @Override
    public MainViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.groups_cardview,viewGroup,false );
        MyGroups_adapter.MainViewHolder mvh = new MyGroups_adapter.MainViewHolder(v,mlistener);
        return mvh;
    }

    @Override
    public void onBindViewHolder(@NonNull MainViewHolder mainViewHolder, int i) {

        GroupsMainDatum groupsMainDatum = mlist.get(i);
        int gruoupId = groupsMainDatum.getId();
        String subject = groupsMainDatum.getSubject().getName();
        String teacher = groupsMainDatum.getTeacher().getName();

        mainViewHolder.tv1.setText("Group id: "+String.valueOf(gruoupId));
        mainViewHolder.tv2.setText("Subject: "+subject);
        mainViewHolder.tv3.setText("Teacher: "+teacher);

    }

    @Override
    public int getItemCount() {
        return mlist.size();
    }

    public interface OnItemClickListener {
        void OnItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener){
        mlistener = onItemClickListener;
    }

    public MyGroups_adapter (ArrayList<GroupsMainDatum> lists){
        mlist = lists;
    }

    public class MainViewHolder extends RecyclerView.ViewHolder {

        public TextView tv1,tv2,tv3;

        public MainViewHolder(@NonNull View itemView,OnItemClickListener onItemClickListener) {
            super(itemView);

            tv1 = itemView.findViewById(R.id.textView17);
            tv2 = itemView.findViewById(R.id.textView28);
            tv3 = itemView.findViewById(R.id.textView30);

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
