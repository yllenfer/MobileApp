package com.example.mobileapp;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mobileapp.adapters.MessageListAdapter;
import com.example.mobileapp.adapters.NotificationAdapter;
import com.example.mobileapp.firestore.FirebaseClass;
import com.example.mobileapp.models.MessagesModel;
import com.example.mobileapp.models.NotificationModel;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;
import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class Chat extends AppCompatActivity {
    private RecyclerView recyclerView;
    private MessageListAdapter adapter;
    private LinearLayoutManager layoutManager;
    ArrayList<MessagesModel> list;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        Intent intent = getIntent();
        String datesent = intent.getStringExtra("dateSent");

        System.out.println(datesent);
        list = new ArrayList<>();

        FirebaseClass.getMessageData(this, datesent);

        buildRecycler();
        layoutManager.scrollToPosition(list.size() - 1);

        Button btnSendMessage = findViewById(R.id.button_gchat_send);
        EditText messageText = findViewById(R.id.edit_gchat_message);

        btnSendMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                User userfirebase = new User("firebase-user", FirebaseClass.getUserID());
                TextView timeText = findViewById(R.id.text_gchat_timestamp_other);
                TextView textMessage = findViewById(R.id.edit_gchat_message);

                FirebaseClass.setMessageData(new MessagesModel(textMessage.getText().toString(),
                        userfirebase,
                        60));

                clearData(list);
                recyclerView.scrollToPosition(list.size() - 1);

                messageText.setText("");

            }
        });

    }

    public void createList(String message, User user, Long date) {
        list.add(new MessagesModel(message, user, date));
        adapter.notifyDataSetChanged();

    }


    public void clearData(ArrayList<MessagesModel> messages) {
        messages.clear();
        adapter.notifyDataSetChanged();
    }


    public void buildRecycler() {
        recyclerView = findViewById(R.id.recycler_gchat);
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this);
        layoutManager.setStackFromEnd(true);
        adapter = new MessageListAdapter(list);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);


    }




}