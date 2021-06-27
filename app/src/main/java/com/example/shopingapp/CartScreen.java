package com.example.shopingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.shopingapp.Adapters.LocalProductListAdapters;
import com.example.shopingapp.Databases.ProductDataBase;
import com.example.shopingapp.Interfaces.RecyclerDatabaseFunction;
import com.example.shopingapp.Models.LocalProducts;
import com.example.shopingapp.databinding.ActivityCartScreenBinding;

import java.util.List;

public class CartScreen extends AppCompatActivity implements RecyclerDatabaseFunction {

    private ActivityCartScreenBinding activityCartScreenBinding;
    private List<LocalProducts> data;
    private LocalProductListAdapters localProductListAdapters;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityCartScreenBinding=ActivityCartScreenBinding.inflate(getLayoutInflater());
        setContentView(activityCartScreenBinding.getRoot());
        setTitle("Cart Screen");

        new ShowData().execute();
        getCount();
    }



    public void paymentMethod(View view) {
        Toast.makeText(this, "Select Payment Method", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void RemoveFromCart(int refId) {
        new RemoveData().execute(refId);
    }


    class ShowData extends AsyncTask<Void, Void, List<LocalProducts>> {

        @Override
        protected List<LocalProducts> doInBackground(Void... voids) {

            data = ProductDataBase.getInstance(getApplicationContext())
                    .dataBaseFunctionsDao()
                    .showAll();

            return data;
        }

        @Override
        protected void onPostExecute(List<LocalProducts> localProducts) {
            super.onPostExecute(localProducts);

            try {
                if (localProducts.size()>0) {
                    localProductListAdapters=new LocalProductListAdapters(CartScreen.this,localProducts,CartScreen.this);
                    activityCartScreenBinding.list.setAdapter(localProductListAdapters);
                } else {
                    Toast.makeText(CartScreen.this, "Cart is empty", Toast.LENGTH_SHORT).show();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

    class RemoveData extends AsyncTask<Integer, Void, Void> {

        @Override
        protected Void doInBackground(Integer... integers) {

             ProductDataBase.getInstance(getApplicationContext())
                    .dataBaseFunctionsDao()
                    .deleteById(integers[0]);

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            getCount();
            Toast.makeText(CartScreen.this, "Removed", Toast.LENGTH_SHORT).show();
        }
    }



    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();// so coming back to this activity oncreate will execute or make call in onresume
    }

    public void getCount() {

        try {
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    List<LocalProducts> data = ProductDataBase.getInstance(getApplicationContext())
                            .dataBaseFunctionsDao()
                            .showAll();

                    activityCartScreenBinding.buy.setText("Buy ("+data.size()+")");
                }
            });
            thread.start();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


}