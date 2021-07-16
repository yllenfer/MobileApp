package com.example.mobileapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.WindowInsets;
import android.view.WindowInsetsController;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class LogIn extends AppCompatActivity {
    private EditText iemail;
    private EditText ipass;
    FirebaseAuth mAuth;
    DatabaseReference dataBase;

    @Override
    @SuppressWarnings("DEPRECATION")
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
        mAuth = FirebaseAuth.getInstance();
        dataBase = FirebaseDatabase.getInstance().getReference();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            final WindowInsetsController insetsController = getWindow().getInsetsController();
            if (insetsController != null) {
                insetsController.hide(WindowInsets.Type.statusBars());
            }
        } else {
            getWindow().setFlags(
                    WindowManager.LayoutParams.FLAG_FULLSCREEN,
                    WindowManager.LayoutParams.FLAG_FULLSCREEN
            );
        }
        iemail = findViewById(R.id.etUsername);
        ipass = findViewById(R.id.etPassword);
    }

    public void logIn() {
        String email = iemail.getText().toString();
        String pass = ipass.getText().toString();
            mAuth.signInWithEmailAndPassword(email, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        Toast.makeText(LogIn.this, "Welcome " + email, Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(LogIn.this, Product.class));
                        finish();
                    } else {
                        Toast.makeText(LogIn.this, "Incorrect password or email.", Toast.LENGTH_SHORT).show();
                    }
                };
            });
    }

    public void logIn(View view) {
        if(validate()) {
                logIn();
        }
    }

    public boolean validate() {
        String email = iemail.getText().toString();
        String pass = ipass.getText().toString();
        boolean response = true;
        if (email.isEmpty()) {
            iemail.setError("Required Field");
            iemail.requestFocus();
            response = false;
        }
        if (pass.isEmpty()) {
            ipass.setError("Required Field");
            ipass.requestFocus();
            response = false;
        } else {
            if (pass.length() < 6) {
                ipass.setError("Password must be 6 or more characters long.");
                ipass.requestFocus();
                response = false;
            }
        }
        return response;
    }

    public void goRegister(View view) {
        Intent intent  = new Intent(LogIn.this, Register.class);
        startActivity(intent);
        finish();
    }

    public void goProduct(View view) {
        Intent intent  = new Intent(LogIn.this, Profile.class);
        startActivity(intent);
        finish();
    }
}