package com.example.mobileapp;

public class ProductModel {

    String product_name;
    Long price;
    String description;
    Long quantity;
    String image;
    String productId;

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public ProductModel(String product_name, Long price, String description, Long quantity, String image, String productId) {
        this.product_name = product_name;
        this.price = price;
        this.description = description;
        this.quantity = quantity;
        this.image = image;
        this.productId = productId;
    }

    public ProductModel(String name, Long price, String description, Long quantity, String image) {
        this.product_name = name;
        this.price = price;
        this.description = description;
        this.quantity = quantity;
        this.image = image;

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


}
