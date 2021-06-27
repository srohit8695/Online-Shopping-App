package com.example.shopingapp.Databases;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.shopingapp.Interfaces.DataBaseFunctions;
import com.example.shopingapp.Models.LocalProducts;

@Database(entities = {LocalProducts.class}, version = 1)
public abstract class ProductDataBase extends RoomDatabase {

    private static volatile ProductDataBase INSTANCE;

    public abstract DataBaseFunctions dataBaseFunctionsDao();

    public static ProductDataBase getInstance(Context context){

        if(INSTANCE == null){
            synchronized (ProductDataBase.class){
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            ProductDataBase.class, "Products_Database.db")
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }

        return INSTANCE;
    }


}
