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
    private RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        ArrayList<MessagesModel> itemsList = createList();
        buildRecycler(itemsList);

        Button btnSendMessage = findViewById(R.id.button_gchat_send);
        EditText messageText = findViewById(R.id.edit_gchat_message);


        recyclerView.scrollToPosition(itemsList.size() - 1);

        btnSendMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                User userfirebase = new User("firebase-user", FirebaseClass.getUserID());
                TextView timeText = findViewById(R.id.text_gchat_timestamp_other);
                TextView textMessage = findViewById(R.id.edit_gchat_message);

                FirebaseClass.setMessageData(new MessagesModel(textMessage.getText().toString(),
                        userfirebase,
                        21));

                clearData(itemsList);

                messageText.setText("");

            }
        });

    }


    public void clearData(ArrayList<MessagesModel> messages) {
        messages.clear();
        adapter.notifyDataSetChanged();
    }

    public ArrayList<MessagesModel> createList() {
        ArrayList<MessagesModel> itemsList = new ArrayList<>();
        DatabaseReference db = FirebaseDatabase.getInstance().getReference("messages");
        db.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                for (DataSnapshot date : snapshot.getChildren()) {
                    for (DataSnapshot randomNumber : date.getChildren()) {
                        String message = randomNumber.child("messageData").child("message").getValue().toString();
                        String createdAt = randomNumber.child("messageData").child("createdAt").getValue().toString();
                        User userFireBase = randomNumber.child("messageData").child("sender").getValue(User.class);

                        //listMessages.add(new MessagesModel(message, userFireBase, Long.parseLong(createdAt)));
                        itemsList.add(new MessagesModel(message, userFireBase, Long.parseLong(createdAt)));

                    }
                }

            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {

            }
        });

        return itemsList;

    }

    public void buildRecycler(ArrayList MessagesModel) {
        recyclerView = findViewById(R.id.recycler_gchat);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        adapter = new MessageListAdapter(MessagesModel);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.getRecycledViewPool().clear();
        recyclerView.setAdapter(adapter);

        adapter.notifyDataSetChanged();

    }


}