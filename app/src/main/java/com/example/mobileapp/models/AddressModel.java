package com.example.mobileapp.models;

public class AddressModel {
    private String user_id;

    private String address;
    private String state;
    private String postal_code;


    public AddressModel(String user_id, String address, String state, String postal_code) {
        this.user_id = user_id;
        this.address = address;
        this.state = state;
        this.postal_code = postal_code;
    }


    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getPostal_code() {
        return postal_code;
    }

    public void setPostal_code(String postal_code) {
        this.postal_code = postal_code;
    }
}
