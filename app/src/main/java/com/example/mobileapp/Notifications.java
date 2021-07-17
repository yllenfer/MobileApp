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
import com.example.mobileapp.firestore.FirebaseClass;
import com.example.mobileapp.models.MessagesModel;
import com.example.mobileapp.models.NotificationModel;

import java.util.ArrayList;

public class Notifications extends AppCompatActivity implements NotificationAdapter.OnItemClickListener{
    private RecyclerView recyclerView;
    private NotificationAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    ArrayList<NotificationModel> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notifications);

        User user = new User();

        //getFromFirebase();

        list = new ArrayList<>();
        buildRecycler();
        FirebaseClass.getNotificationData(this);

        adapter.notifyDataSetChanged();

        View backButton = findViewById(R.id.back_button);

        adapter.setOnItemClickListener(new NotificationAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position, String date) {
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

    public void clearData(ArrayList<NotificationModel> notificationModels) {
        notificationModels.clear();
        adapter.notifyDataSetChanged();
    }

    public void createList(String date) {
        list.add(new NotificationModel("New Message", "message from an employee", date));
        adapter.notifyDataSetChanged();

    }

    public void buildRecycler() {
        recyclerView = findViewById(R.id.recyclerViewNotification);
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this);
        adapter = new NotificationAdapter(this, list, this::onItemClick);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

    }

    @Override
    public void onItemClick(int position, String date) {

//        clearData(list);
        Intent intent = new Intent(Notifications.this, Chat.class);
        System.out.println(position);
        intent.putExtra("dateSent", date);
        startActivity(intent);

    }
}