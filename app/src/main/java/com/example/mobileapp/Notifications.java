package com.example.mobileapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Notification;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.mobileapp.adapters.NotificationAdapter;
import com.example.mobileapp.models.NotificationModel;

import java.util.ArrayList;

public class Notifications extends AppCompatActivity {
    private RecyclerView recyclerView;
    private NotificationAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notifications);

        ArrayList<NotificationModel> list = new ArrayList<>();
        list.add(new NotificationModel("A title for now", "message from a friend"));
        list.add(new NotificationModel("Hello", "New offers for your intel i5"));

        View backButton = findViewById(R.id.back_button);

        recyclerView = findViewById(R.id.recyclerViewNotification);
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this);
        adapter = new NotificationAdapter(list);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        adapter.setOnItemClickListener(new NotificationAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Intent intent = new Intent(Notifications.this, Chat.class);
                startActivity(intent);
            }
        });

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(Notifications.this, Product.class);
//                startActivity(intent);
                finish();
            }
        });


    }



}