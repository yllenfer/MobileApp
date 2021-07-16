package com.example.mobileapp;

import com.example.mobileapp.firestore.FirebaseClass;

public class User {
    public String id;
    public String email, lastName, name, password;

    public User() {
    }

    public User(String name, String id) {
        this.name = name;
        this.id = id;

    }

    public User(String email, String lastName, String name, String password) {
        this.email = email;
        this.lastName = lastName;
        this.name = name;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public String getUserId() {
        return id;

    }
}
