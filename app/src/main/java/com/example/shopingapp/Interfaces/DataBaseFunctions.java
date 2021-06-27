package com.example.shopingapp.Interfaces;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.shopingapp.Models.LocalProducts;

import java.util.List;

@Dao
public interface DataBaseFunctions {

    @Insert
    void insertData(LocalProducts localProducts);

    @Query("DELETE FROM local_products WHERE refId = :id")
    void deleteById(int id);

    @Query("SELECT * FROM local_products")
    List<LocalProducts> showAll();

}
