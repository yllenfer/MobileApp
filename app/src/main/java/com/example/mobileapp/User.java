package com.example.mobileapp;

public class User {
    public String age, email, image, lastName, name, password, phone_number;

    public User() {

    }

    public User(String age, String email, String image, String lastName, String name, String password, String phone_number) {
        this.age = age;
        this.email = email;
        this.image = image;
        this.lastName = lastName;
        this.name = name;
        this.password = password;
        this.phone_number = phone_number;
    }
}
