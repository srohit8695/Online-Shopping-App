package com.example.shopingapp.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.shopingapp.Interfaces.RecyclerClickFunctions;
import com.example.shopingapp.Models.Products;
import com.example.shopingapp.databinding.ProductListTempletBinding;

import java.util.List;

public class ProductListAdapter extends RecyclerView.Adapter<ProductListAdapter.MyViewHolder> {

    private Context context;
    private List<Products> info;
    private RecyclerClickFunctions recyclerClickFunctions;


    public ProductListAdapter(Context context, List<Products> info, RecyclerClickFunctions recyclerClickFunctions) {
        this.context = context;
        this.info = info;
        this.recyclerClickFunctions = recyclerClickFunctions;
    }



    public class MyViewHolder extends RecyclerView.ViewHolder{

        ProductListTempletBinding productListTempletBinding;
        public MyViewHolder(ProductListTempletBinding productListTempletBinding) {
            super(productListTempletBinding.getRoot());
            this.productListTempletBinding=productListTempletBinding;
        }

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater=LayoutInflater.from(parent.getContext());
        ProductListTempletBinding productListTempletBinding=ProductListTempletBinding.inflate(layoutInflater,parent,false);
        return new MyViewHolder(productListTempletBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Products products=info.get(position);
        holder.productListTempletBinding.title.setText(products.getName());
        holder.productListTempletBinding.price.setText(products.getPrice());
        final int[] qty = {0};
        holder.productListTempletBinding.add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                qty[0]++;
                holder.productListTempletBinding.qty.setText(String.valueOf(qty[0]));
            }
        });
        holder.productListTempletBinding.remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(qty[0] >0){
                    qty[0]--;
                    holder.productListTempletBinding.qty.setText(String.valueOf(qty[0]));
                }
            }
        });
        holder.productListTempletBinding.addCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Integer.parseInt(holder.productListTempletBinding.qty.getText().toString().trim())>0) {
                    recyclerClickFunctions.onAddToCart(position, qty[0]);
                } else {
                    Toast.makeText(context, "Add Quantity", Toast.LENGTH_SHORT).show();
                }
            }
        });
        Glide.with(context)
                .load(products.getImage())
                .into(holder.productListTempletBinding.img);


    }

    @Override
    public int getItemCount() {
        return info.size();
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }



}
