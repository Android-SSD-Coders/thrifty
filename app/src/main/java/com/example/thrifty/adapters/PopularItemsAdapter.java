package com.example.thrifty.adapters;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.amplifyframework.datastore.generated.model.Product;

import java.util.ArrayList;
import java.util.List;


public class PopularItemsAdapter extends RecyclerView.Adapter<PopularItemsAdapter.PopularItemViewHolder>{

    List<Product> popularItems = new ArrayList<>();

public PopularItemsAdapter(List<Product> popularItems){
    this.popularItems = popularItems;
}

    @NonNull
    @Override
    public PopularItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull PopularItemViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public static class PopularItemViewHolder extends RecyclerView.ViewHolder{
    public View itemView;
    public Product product;
    public PopularItemViewHolder(@NonNull View itemView) {
        super(itemView);
        this.itemView = itemView;
    }
}
}
