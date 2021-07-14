package com.example.mobileapp;

public class CartModel {

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public Boolean getCart() {
        return cart;
    }

    public void setCart(Boolean cart) {
        this.cart = cart;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    String product_name;
    Long price;
    String image;
    Boolean cart;


    public CartModel(String product_name, Long price, String image) {
        this.product_name = product_name;
        this.price = price;
        this.image = image;
        this.cart = cart;
    }

    public CartModel() {

    }


}
