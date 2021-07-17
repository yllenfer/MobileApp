package com.example.mobileapp.models;

import com.example.mobileapp.User;

public class MessagesModel {
    public String message;
    public User sender;
    public long createdAt;

    public MessagesModel() {

    }

    public MessagesModel(String message, User sender, long createdAt) {
        this.message = message;
        this.sender = sender;
        this.createdAt = createdAt;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public User getSender() {
        return sender;
    }

    public long getCreatedAt() {
        return createdAt;
    }

}

