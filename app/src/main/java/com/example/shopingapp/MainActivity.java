package com.example.shopingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.shopingapp.classes.Internet_status;
import com.example.shopingapp.databinding.ActivityProductListBinding;

public class MainActivity extends AppCompatActivity {

    private ImageView imageView;
    private Internet_status internetStatus;
    private TextView version;
    private ProgressBar progress;
    private ImageView refresh_icon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView = findViewById(R.id.imageView);
        progress=findViewById(R.id.progress);
        progress.setVisibility(View.VISIBLE);
        internetStatus=new Internet_status(this);
        version=findViewById(R.id.version);
        refresh_icon=findViewById(R.id.refresh_icon);

        operation();

        refresh_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progress.setVisibility(View.VISIBLE);
                refresh_icon.setVisibility(View.INVISIBLE);
                operation();
            }
        });
    }

    public void operation() {

        try {
            final Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    try{
                        if (internetStatus.internet_status()) {
                            Intent intent=new Intent(MainActivity.this,ProductList.class);
                            startActivity(intent);
                            finish();
                        } else {
                            Toast.makeText(MainActivity.this, "Check Internet Connectivity.", Toast.LENGTH_SHORT).show();
                            progress.setVisibility(View.INVISIBLE);
                            refresh_icon.setVisibility(View.VISIBLE);
                        }
                    }
                    catch (Exception e){
                        e.printStackTrace();
                    }
                }
            }, 2000);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }



}