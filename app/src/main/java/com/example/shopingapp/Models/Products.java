package com.example.shopingapp.Models;

import androidx.room.Entity;

import com.google.gson.annotations.SerializedName;


public class Products {
    private String name;
    private String id;
    private String product_id;
    private String image;
    private String quantity;
    private String price;
    private String special;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProduct_id() {
        return product_id;
    }

    public void setProduct_id(String product_id) {
        this.product_id = product_id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getSpecial() {
        return special;
    }

    public void setSpecial(String special) {
        this.special = special;
    }

    @Override
    public String toString() {
        return "Products{" +
                "name='" + name + '\'' +
                ", id='" + id + '\'' +
                ", product_id='" + product_id + '\'' +
                ", image='" + image + '\'' +
                ", quantity='" + quantity + '\'' +
                ", price='" + price + '\'' +
                ", special='" + special + '\'' +
                '}';
    }
}
