package com.example.mobileapp;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.NotNull;

public class ViewHolder extends RecyclerView.ViewHolder{

    View view;




    public ViewHolder(@NonNull @NotNull View itemView) {
        super(itemView);

        view  = itemView;
    }

    public void setDetails(Context context, String title, String description, String image){

        TextView mTitleTv =  view.findViewById(R.id.rTitleView);
        TextView mDescription = view.findViewById(R.id.rDescriptionView);
        ImageView mImagetv = view.findViewById(R.id.rImageView);

        mTitleTv.setText(title);
        mDescription.setText(description);
        Picasso.get().load(image).into(mImagetv);



    }
}
