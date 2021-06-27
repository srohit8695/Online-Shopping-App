package com.example.shopingapp.Models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class BaseInfo {
    @SerializedName("products")
    private List<Products> products;

    public BaseInfo(List<Products> products) {
        this.products = products;
    }

    public List<Products> getProducts() {
        return products;
    }

    public void setProducts(List<Products> products) {
        this.products = products;
    }

    @Override
    public String toString() {
        return "BaseInfo{" +
                "products=" + products +
                '}';
    }
}
