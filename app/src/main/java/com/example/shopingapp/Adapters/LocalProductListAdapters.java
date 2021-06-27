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
import com.example.shopingapp.Interfaces.RecyclerDatabaseFunction;
import com.example.shopingapp.Models.LocalProducts;
import com.example.shopingapp.databinding.LocalProductTempletBinding;
import com.example.shopingapp.databinding.ProductListTempletBinding;

import java.util.List;

public class LocalProductListAdapters extends RecyclerView.Adapter<LocalProductListAdapters.MyViewHolder> {
    private Context context;
    private List<LocalProducts> data;
    private RecyclerDatabaseFunction recyclerDatabaseFunction;

    public LocalProductListAdapters(Context context, List<LocalProducts> data,RecyclerDatabaseFunction recyclerDatabaseFunction) {
        this.context = context;
        this.data = data;
        this.recyclerDatabaseFunction=recyclerDatabaseFunction;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        LocalProductTempletBinding localProductTempletBinding;
        public MyViewHolder(LocalProductTempletBinding localProductTempletBinding) {
            super(localProductTempletBinding.getRoot());
            this.localProductTempletBinding=localProductTempletBinding;
        }

    }



    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater=LayoutInflater.from(parent.getContext());
        LocalProductTempletBinding localProductTempletBinding=LocalProductTempletBinding.inflate(layoutInflater,parent,false);
        return new MyViewHolder(localProductTempletBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        LocalProducts localProducts=data.get(position);
        holder.localProductTempletBinding.title.setText(localProducts.getName());
        holder.localProductTempletBinding.price.setText(localProducts.getPrice());
        holder.localProductTempletBinding.qty.setText(localProducts.getQuantity());
//        holder.localProductTempletBinding.total.setText("Rs : "+Integer.parseInt(localProducts.getPrice().trim())*Integer.parseInt(localProducts.getQuantity()));
        Glide.with(context)
                .load(localProducts.getImage())
                .into(holder.localProductTempletBinding.img);
        holder.localProductTempletBinding.remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recyclerDatabaseFunction.RemoveFromCart(localProducts.getRefid());
                data.remove(position);
                notifyItemRemoved(position);


            }
        });

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }


}
