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

    public int getTotalPrice(){return totalPrice;}

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getId() {
        return id;
    }

    public void setId(String ide) {
        this.id = id;
    }

    public Boolean getSelected() {
        return selected;
    }

    public void setSelected(Boolean selected) {
        this.selected = selected;
    }



    String product_name;
    Long price;
    String image;
    String id;
    Boolean cart;
    int totalPrice;
    public boolean selected;

    public CartModel(String product_name, Long price, String image, int totalPrice, String id, Boolean cart, Boolean selected) {
        this.product_name = product_name;
        this.price = price;
        this.image = image;
        this.totalPrice = totalPrice;
        this.cart = cart;
        this.id = id;
    }

    public CartModel() {

    }



}
