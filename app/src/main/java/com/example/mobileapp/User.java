package com.example.mobileapp;

public class User {
    public String email, lastName, name, password;

    public User() {

    }

    public User(String email, String lastName, String name, String password) {
        this.email = email;
        this.lastName = lastName;
        this.name = name;
        this.password = password;
    }
}
