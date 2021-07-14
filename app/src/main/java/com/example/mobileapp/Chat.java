package com.example.mobileapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mobileapp.adapters.MessageListAdapter;
import com.example.mobileapp.adapters.NotificationAdapter;
import com.example.mobileapp.firestore.FirebaseClass;
import com.example.mobileapp.models.MessagesModel;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class Chat extends AppCompatActivity {
    private RecyclerView recyclerView;
    private MessageListAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        User user1 = new User("jhonny", "54542");

        ArrayList<MessagesModel> list = new ArrayList<>();
        list.add(new MessagesModel("have you been to the party?", user1, 213));

        recyclerView = findViewById(R.id.recycler_gchat);
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this);
        adapter = new MessageListAdapter(list);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        Button btnSendMessage = findViewById(R.id.button_gchat_send);
        EditText messageText = findViewById(R.id.edit_gchat_message);


        btnSendMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                User userfirebase = new User("firebase-user", FirebaseClass.getUserID());
                list.add(new MessagesModel(messageText.getText().toString(), userfirebase , 21));
                recyclerView.setAdapter(adapter);
                messageText.setText("");

            }
        });

    }
}