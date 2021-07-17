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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public String getUserId() {
        return id;

    }
}
