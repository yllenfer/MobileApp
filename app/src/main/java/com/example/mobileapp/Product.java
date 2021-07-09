package com.example.mobileapp;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class Product extends RecyclerView.Adapter <Product.ViewHolder> {

    public Product(Context context, List<Member> memberList) {
        this.context = context;
        this.memberList = memberList;
    }

    Context context;
    List<Member> memberList;


    @NotNull
    @Override
    public Product.ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.image, parent, false));
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position){
        Glide.with(context).load(memberList.get(position).getImage()).into(holder.image);
        holder.name.setText(memberList.get(position).getProduct_name());
        holder.description.setText(memberList.get(position).getDescription());
        holder.price.setText(memberList.get(position).getPrice());
        holder.quantity.setText(memberList.get(position).getQuantity());



    }

    @Override
    public int getItemCount() {
        return 0;
    }


    public class ViewHolder extends  RecyclerView.ViewHolder {
         ImageView image;
         TextView price;
         TextView description;
         TextView name;
         TextView quantity;



        public ViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);

            image =itemView.findViewById(R.id.roundedImage);
            price = itemView.findViewById(R.id.price);
            price = itemView.findViewById(R.id.p_description);
            price = itemView.findViewById(R.id.title);
            price = itemView.findViewById(R.id.quantity);




        }
    }


}



