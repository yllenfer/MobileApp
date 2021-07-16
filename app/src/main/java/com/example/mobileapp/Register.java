package com.example.mobileapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class Register extends AppCompatActivity {
    private EditText iname;
    private EditText ilast;
    private EditText iemail;
    private EditText ipass;
    private EditText iconfirm;
    FirebaseAuth mAuth;
    DatabaseReference dataBase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        mAuth = FirebaseAuth.getInstance();
        dataBase = FirebaseDatabase.getInstance().getReference();
        iname = findViewById(R.id.etFirstname);
        ilast = findViewById(R.id.etLastname);
        iemail = findViewById(R.id.etEmail);
        ipass = findViewById(R.id.etPassword2);
        iconfirm = findViewById(R.id.etConfirmPassword);
    }

    public void registerUser() {
        String name = iname.getText().toString();
        String last = ilast.getText().toString();
        String email = iemail.getText().toString();
        String pass = ipass.getText().toString();
        mAuth.createUserWithEmailAndPassword(email, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Map<String, Object> map = new HashMap<>();
                    map.put("name", name);
                    map.put("last-name", last);
                    map.put("email", email);
                    map.put("password", pass);
                    map.put("image", "");
                    map.put("age", "ADD AGE");
                    map.put("phone_number", "ADD PHONE NUMBER");
                    String id = mAuth.getCurrentUser().getUid();

                    dataBase.child("Users").child(id).setValue(map).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task2) {
                            if (task2.isSuccessful()) {
                                startActivity(new Intent(Register.this, LogIn.class));
                                finish();
                            } else {
                                Toast.makeText(Register.this, "Unable to store data.", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });

                } else {
                    if (task.getException() instanceof FirebaseAuthUserCollisionException) {
                        Toast.makeText(Register.this, "User already exists.", Toast.LENGTH_SHORT).show();;
                    } else {
                        Toast.makeText(Register.this, "User cannot be registered.", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    public void register(View view) {
        if(validate()) {
            registerUser();
        }
    }

    public boolean validate() {
        boolean response = true;
        String name = iname.getText().toString();
        String last = ilast.getText().toString();
        String email = iemail.getText().toString();
        String pass = ipass.getText().toString();
        String confirm = iconfirm.getText().toString();
        if (name.isEmpty()) {
            iname.setError("Required Field");
            iname.requestFocus();
            response = false;
        }
        if (last.isEmpty()) {
            ilast.setError("Required Field");
            ilast.requestFocus();
            response = false;
        }
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
        if (confirm.isEmpty()) {
            iconfirm.setError("Required Field");
            iconfirm.requestFocus();
            response = false;
        }

        return response;
    }
    public void goLogIn(View view) {
        Intent intent = new Intent(Register.this, Product.class);
        startActivity(intent);
        finish();
    }
}