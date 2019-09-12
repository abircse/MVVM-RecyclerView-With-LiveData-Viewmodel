package com.example.mvvmrecyclerview.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mvvmrecyclerview.R;
import com.example.mvvmrecyclerview.model.User;
import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.Holder>{


    private List<User> users;

    public UserAdapter(List<User> users) {
        this.users = users;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_layout,parent,false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, final int position) {


        holder.name.setText(users.get(position).getName());
        holder.designation.setText(users.get(position).getDesignation());

    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    class Holder extends RecyclerView.ViewHolder{

        TextView name;
        TextView designation;


        public Holder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.textView1);
            designation = itemView.findViewById(R.id.textView2);

        }
    }

}
