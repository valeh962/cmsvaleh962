package com.example.valeh.coursemanagementsystem.Main.Fragment.PersonTypeList;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.valeh.coursemanagementsystem.Main.JsonWorks.MakeRequest.Roles;
import com.example.valeh.coursemanagementsystem.R;

import java.util.List;

public class Menu_lists_adapter extends RecyclerView.Adapter<Menu_lists_adapter.MainViewHolderr> {

    private List<Roles> mlists;
    public Menu_lists_adapter.OnItemClickListener mListener;
    int[] logos = {R.drawable.ic_004_professor,R.drawable.ic_003_student};


    public interface OnItemClickListener {
        void OnItemClick(int position);
    }

    public void setOnClickListener (OnItemClickListener listener){
        mListener = listener;
    }

    public static class MainViewHolderr extends RecyclerView.ViewHolder {

        public TextView tv1, tv2;
        public ImageView img;

        public MainViewHolderr(@NonNull View itemView, Menu_lists_adapter.OnItemClickListener listener) {
            super(itemView);

            tv1 = itemView.findViewById(R.id.textView3);
            tv2 = itemView.findViewById(R.id.textView18);
            img = itemView.findViewById(R.id.imageView5);

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

    public Menu_lists_adapter (List<Roles> lists){
        mlists = lists;
    }

    @NonNull
    @Override
    public Menu_lists_adapter.MainViewHolderr onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.mainmenu_list_tem,parent,false );
        Menu_lists_adapter.MainViewHolderr mvh = new Menu_lists_adapter.MainViewHolderr(v,mListener);

        return mvh;
    }

    @Override
    public void onBindViewHolder(@NonNull Menu_lists_adapter.MainViewHolderr holder, int position) {

        Roles roles = mlists.get(position);
        holder.tv1.setText(roles.getName());


        if(roles.getName().equals("Student")){
            holder.img.setImageResource(logos[1]);
        }
        if(roles.getName().equals("Teacher")){
            holder.img.setImageResource(logos[0]);
        }
        else
            holder.img.setImageResource(logos[1]);

    }

    @Override
    public int getItemCount() {
        return mlists.size();
    }
}
