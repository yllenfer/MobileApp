package com.example.mobileapp;

public class Member {

    String product_name, price, description, quantity;

    public Member(String name, String price, String description, String quantity) {
        this.product_name = name;
        this.price = price;
        this.description = description;
        this.quantity = quantity;
    }

    public Member() {


    }



    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }




    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }



    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }


}
