package com.example.shopingapp.Interfaces;

import com.example.shopingapp.Models.BaseInfo;
import com.example.shopingapp.Models.Products;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface {
    @GET("/v2/5def7b172f000063008e0aa2")
    Call<BaseInfo> getDatas();
}
