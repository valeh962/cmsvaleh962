package com.example.valeh.coursemanagementsystem.Main.Fragment.Members;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.valeh.coursemanagementsystem.Main.JsonWorks.Members.Member_Students_data;
import com.example.valeh.coursemanagementsystem.Main.JsonWorks.Members.Member_Teachers_data;
import com.example.valeh.coursemanagementsystem.R;

import java.util.ArrayList;

public class Members_adapter_tcr extends RecyclerView.Adapter<Members_adapter_tcr.MainViewHolder>{

    public ArrayList<Member_Teachers_data> mlist;
    public OnItemClickListener mlistener;

    public interface OnItemClickListener {
        void OnItemClick(int position);
    }

    public void setOnItemClickListener(Members_adapter_tcr.OnItemClickListener onItemClickListener) {
        mlistener = onItemClickListener;
    }

    public Members_adapter_tcr(ArrayList<Member_Teachers_data> lists){
        mlist = lists;
    }

    @NonNull
    @Override
    public MainViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.groupdetails_custom, viewGroup, false);
        Members_adapter_tcr.MainViewHolder mvh = new Members_adapter_tcr.MainViewHolder(v, mlistener);
        return mvh;
    }

    @Override
    public void onBindViewHolder(@NonNull MainViewHolder mainViewHolder, int i) {

        Member_Teachers_data data = mlist.get(i);
        mainViewHolder.tv1.setText(data.getName()+" "+data.getSurname());

    }

    @Override
    public int getItemCount() {
        return mlist.size();
    }

    public class MainViewHolder extends RecyclerView.ViewHolder {

        TextView tv1;

        public MainViewHolder(@NonNull View itemView, OnItemClickListener mlistener) {
            super(itemView);

            tv1 = itemView.findViewById(R.id.textView42);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(mlistener!=null){
                        int position = getAdapterPosition();
                        if(position!=RecyclerView.NO_POSITION){
                            mlistener.OnItemClick(position);
                        }}
                }
            });
        }
    }
}
