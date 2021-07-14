package com.example.mobileapp.adapters;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mobileapp.R;

import com.example.mobileapp.firestore.FirebaseClass;
import com.example.mobileapp.models.MessagesModel;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;


public class MessageListAdapter extends RecyclerView.Adapter {
    private static final int VIEW_TYPE_MESSAGE_SENT = 1;
    private static final int VIEW_TYPE_MESSAGE_RECEIVED = 2;

    private ArrayList<MessagesModel> list;

    public MessageListAdapter(ArrayList<MessagesModel> list) {
        this.list = list;
    }


    private static class ReceivedMessageHolder extends RecyclerView.ViewHolder {
        TextView timeText, TextMessage, userName;

        ReceivedMessageHolder(View itemView) {
            super(itemView);
            timeText = itemView.findViewById(R.id.text_gchat_timestamp_other);
            TextMessage = itemView.findViewById(R.id.text_gchat_message_other);
            userName = itemView.findViewById(R.id.text_gchat_user_other);

        }

        void bind(MessagesModel message) {
            userName.setText(message.getSender().getName());
            TextMessage.setText(message.getMessage());

        }
    }

    private static class SentMessageHolder extends RecyclerView.ViewHolder {
        TextView timeText, TextMessage, userName;

        SentMessageHolder(View itemView) {
            super(itemView);
            timeText = itemView.findViewById(R.id.text_gchat_timestamp_other);
            TextMessage = itemView.findViewById(R.id.text_gchat_message_me);
        }

        void bind(MessagesModel message) {
            TextMessage.setText(message.getMessage());
        }
    }

    @NotNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NotNull ViewGroup parent, int viewType) {
        View view;

        if (viewType == VIEW_TYPE_MESSAGE_SENT) {
            view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.message_me, parent, false);
            return new SentMessageHolder(view);
        } else if (viewType == VIEW_TYPE_MESSAGE_RECEIVED) {
            view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.message_user, parent, false);
            return new ReceivedMessageHolder(view);
        }
        return null;
    }


    @Override
    public int getItemViewType(int position) {
        MessagesModel message = list.get(position);

        if (message.getSender().getUserId().equals(FirebaseClass.getUserID())) {
            // If the current user is the sender of the message
            return VIEW_TYPE_MESSAGE_SENT;
        } else {
            // If some other user sent the message
            return VIEW_TYPE_MESSAGE_RECEIVED;
        }
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull RecyclerView.ViewHolder holder, int position) {

        MessagesModel message = list.get(position);

        switch (holder.getItemViewType()) {
            case VIEW_TYPE_MESSAGE_SENT:
                ((SentMessageHolder) holder).bind(message);
                break;
            case VIEW_TYPE_MESSAGE_RECEIVED:
                ((ReceivedMessageHolder) holder).bind(message);
        }

    }

    @Override
    public int getItemCount() {
        return list.size();
    }



}

