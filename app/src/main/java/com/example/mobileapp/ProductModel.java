package com.example.mobileapp;

public class ProductModel {

    String product_name;
    Long price;
    String description;
    Long quantity;
    String image;
    String productId;
    Boolean cart;
    String id;





    public ProductModel(String product_name, Long price, String description, Long quantity, String image, Boolean cart, String productId, String id) {
        this.product_name = product_name;
        this.price = price;
        this.description = description;
        this.quantity = quantity;
        this.image = image;
        this.productId = productId;
        this.cart = cart;
        this.id = id;
    }

    public ProductModel(String name, Long price, String description, Long quantity, String image, Boolean cart) {
        this.product_name = name;
        this.price = price;
        this.description = description;
        this.quantity = quantity;
        this.image = image;
        this.cart = cart;

    }

    public ProductModel() {


    }



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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }



    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }


    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Boolean getCart() {
        return cart;
    }

    public void setCart(Boolean cart) {
        this.cart = cart;
    }


}
