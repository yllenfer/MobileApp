package com.example.mobileapp.models;

public class NotificationModel {
    public String title;
    public String notification_message;

    public NotificationModel(String title, String notification_message) {
        this.title = title;
        this.notification_message = notification_message;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getNotification_message() {
        return notification_message;
    }

    public void setNotification_message(String notification_message) {
        this.notification_message = notification_message;
    }
}
