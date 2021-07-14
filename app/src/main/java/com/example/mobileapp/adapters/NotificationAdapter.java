package com.example.mobileapp.adapters;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mobileapp.Chat;
import com.example.mobileapp.Checkout;
import com.example.mobileapp.Overview;
import com.example.mobileapp.R;
import com.example.mobileapp.models.NotificationModel;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class NotificationAdapter extends RecyclerView.Adapter<NotificationAdapter.ViewHolder> {
    private ArrayList<NotificationModel> list;
    private OnItemClickListener mlistener;

    public interface OnItemClickListener {
        void onItemClick(int position);
    }
    public void setOnItemClickListener(OnItemClickListener mlistener) {
        this.mlistener = mlistener;

    }


    public NotificationAdapter (ArrayList<NotificationModel> list) {
        this.list = list;
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
        NotificationModel notification = list.get(position);
        holder.text1.setText(notification.getTitle());
        holder.text2.setText(notification.getNotification_message());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView text1;
        public TextView text2;

        public ViewHolder(@NonNull @NotNull View itemView, OnItemClickListener mlistener) {
            super(itemView);
            text1 = itemView.findViewById(R.id.notification_title);
            text2 = itemView.findViewById(R.id.notification_description);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mlistener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            mlistener.onItemClick(position);
                        }
                    }

                }
            });

        }
    }
}
