package com.example.mobileapp;


import android.os.Bundle;

import android.widget.TextClock;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class Profile extends AppCompatActivity {
    private FirebaseUser user;
    private DatabaseReference reference;
    private String userID;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        user = FirebaseAuth.getInstance().getCurrentUser();
        reference = FirebaseDatabase.getInstance().getReference().child("Users");
        userID = user.getUid();

        final TextView nameTV  = (TextView) findViewById(R.id.profile_name);
        final TextView emailTV  = (TextView) findViewById(R.id.profile_email);
        reference.child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                User userProfile = snapshot.getValue(User.class);
                if (userProfile != null) {
                    nameTV.setText(userProfile.name);
                    emailTV.setText(userProfile.email);
                } else {
                    Toast.makeText(Profile.this, "Empty", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {
                Toast.makeText(Profile.this, "Something wrong happened!", Toast.LENGTH_SHORT).show();
            }
        });
    }
    /*
    imgView .setVisibility(View.VISIBLE);
    imgView .setVisibility(View.INVISIBLE);
    imgView .setVisibility(View.GONE);

            Button btn = (Button) findViewById(R.id.push_button);
        btn.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                TextView tv = (TextView) findViewById(R.id.text_view);
                Button btn = (Button) findViewById(R.id.push_button);
                tv.setTextSize(TypedValue.COMPLEX_UNIT_SP,50);
                btn.setTextColor(Color.RED);
            }
        });
    */
}