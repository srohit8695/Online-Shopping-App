package com.example.shopingapp.Models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "local_products")
public class LocalProducts {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "refid")
    private int refid;
    @ColumnInfo(name = "name")
    private String name;
    @ColumnInfo(name = "product_id")
    private String product_id;
    @ColumnInfo(name = "image")
    private String image;
    @ColumnInfo(name = "quantity")
    private String quantity;
    @ColumnInfo(name = "price")
    private String price;


    public LocalProducts(String name, String product_id, String image, String quantity, String price) {
        this.name = name;
        this.product_id = product_id;
        this.image = image;
        this.quantity = quantity;
        this.price = price;
    }

    public int getRefid() {
        return refid;
    }

    public void setRefid(int refid) {
        this.refid = refid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    @Override
    public String toString() {
        return "LocalProducts{" +
                "refid=" + refid +
                ", name='" + name + '\'' +
                ", product_id='" + product_id + '\'' +
                ", image='" + image + '\'' +
                ", quantity='" + quantity + '\'' +
                ", price='" + price + '\'' +
                '}';
    }
}
