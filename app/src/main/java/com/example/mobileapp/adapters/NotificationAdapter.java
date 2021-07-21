package com.example.mobileapp.adapters;

import android.app.Activity;
import android.app.Notification;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mobileapp.Chat;
import com.example.mobileapp.Checkout;
import com.example.mobileapp.Notifications;
import com.example.mobileapp.Overview;
import com.example.mobileapp.R;
import com.example.mobileapp.models.NotificationModel;

import org.jetbrains.annotations.NotNull;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class NotificationAdapter extends RecyclerView.Adapter<NotificationAdapter.ViewHolder> {
    private ArrayList<NotificationModel> list;
    private OnItemClickListener mlistener;
    public NotificationModel notification;
    public Activity activity;
    private OnItemClickListener OnItemClickListener;


    public interface OnItemClickListener {
        void onItemClick(int position, String date);
    }

    public void setOnItemClickListener(OnItemClickListener mlistener) {
        this.mlistener = mlistener;

    }

    public NotificationAdapter(Activity activity, ArrayList<NotificationModel> list, OnItemClickListener OnItemClickListener) {
        this.activity = activity;
        this.list = list;
        this.OnItemClickListener = OnItemClickListener;
    }

    @NonNull
    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.notification_card, parent, false);
        return new ViewHolder(v, mlistener);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull ViewHolder holder, int position) {

        notification = list.get(position);
        holder.text1.setText(notification.getTitle());
        holder.text2.setText(notification.getNotification_message());
        String date = notification.getTime;


        holder.date.setText(date);

        String daten = notification.getGetTime();

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mlistener != null) {
                    if (position != RecyclerView.NO_POSITION) {
                        OnItemClickListener.onItemClick(position, daten);

                    }
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();

    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView text1;
        public TextView text2;
        public TextView date;

        public ViewHolder(@NonNull @NotNull View itemView, OnItemClickListener mlistener) {
            super(itemView);

            text1 = itemView.findViewById(R.id.notification_title);
            text2 = itemView.findViewById(R.id.notification_description);
            date = itemView.findViewById(R.id.notification_date);

//            itemView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    if (mlistener != null) {
//                        int position = getAdapterPosition();
//                        if (position != RecyclerView.NO_POSITION) {
//                            mlistener.onItemClick(position, notification.getTime);
//
//                        }
//                    }
//                }
//            });


        }

    }

}
