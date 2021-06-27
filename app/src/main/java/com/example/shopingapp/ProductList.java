package com.example.shopingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.shopingapp.API_Repo.APIClient;
import com.example.shopingapp.Adapters.ProductListAdapter;
import com.example.shopingapp.Databases.ProductDataBase;
import com.example.shopingapp.Interfaces.ApiInterface;
import com.example.shopingapp.Interfaces.RecyclerClickFunctions;
import com.example.shopingapp.Models.BaseInfo;
import com.example.shopingapp.Models.LocalProducts;
import com.example.shopingapp.Models.Products;
import com.example.shopingapp.databinding.ActivityProductListBinding;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductList extends AppCompatActivity implements RecyclerClickFunctions {

    private ApiInterface apiInterface;
    private ActivityProductListBinding activityProductListBinding;
    private List<Products> data;
    private ProductListAdapter productListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityProductListBinding=ActivityProductListBinding.inflate(getLayoutInflater());
        setContentView(activityProductListBinding.getRoot());
        data=new ArrayList<>();
        setTitle("Product List");

        apiInterface= APIClient.getClient().create(ApiInterface.class);
        getdata();
        getAllCount();

    }




    public void getdata(){

        try {
            Call<BaseInfo> call=apiInterface.getDatas();
            call.enqueue(new Callback<BaseInfo>() {
                @Override
                public void onResponse(Call<BaseInfo> call, Response<BaseInfo> response) {
                    data=response.body().getProducts();
                    productListAdapter=new ProductListAdapter(ProductList.this,data,ProductList.this);
                    activityProductListBinding.list.setAdapter(productListAdapter);
                }

                @Override
                public void onFailure(Call<BaseInfo> call, Throwable t) {
                    Log.d("Test issue",t.getLocalizedMessage());
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onAddToCart(int position, int quantity) {

        if (quantity>0) {
            Products products=data.get(position);
            LocalProducts localProducts=new LocalProducts(products.getName(),products.getProduct_id(),products.getImage(),String.valueOf(quantity),products.getPrice());
            new InsertAsyncTask().execute(localProducts);
        }


    }

    class InsertAsyncTask extends AsyncTask<LocalProducts, Void, Void> {

        @Override
        protected Void doInBackground(LocalProducts... localProducts) {

            try {
                ProductDataBase.getInstance(getApplicationContext())
                        .dataBaseFunctionsDao()
                        .insertData(localProducts[0]);
            } catch (Exception e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            Toast.makeText(ProductList.this, "Added", Toast.LENGTH_SHORT).show();
            getAllCount();
        }
    }

    public void getAllCount() {

        try {
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    List<LocalProducts> data = ProductDataBase.getInstance(getApplicationContext())
                            .dataBaseFunctionsDao()
                            .showAll();

                    activityProductListBinding.button.setText("add to cart ("+data.size()+")");
                }
            });
            thread.start();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    protected void onResume() {
        super.onResume();
        getdata();
        getAllCount();

    }

    public void goToCart(View view) {
        Intent i1=new Intent(ProductList.this,CartScreen.class);
        startActivity(i1);
    }


}