 package com.example.thrifty.adapters;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.amplifyframework.datastore.generated.model.Product;

import java.util.ArrayList;
import java.util.List;

public class SuggestedItemsAdapter extends RecyclerView.Adapter<SuggestedItemsAdapter.SuggestedItemsViewHolder>{
    List<Product> suggestedProducts = new ArrayList<>();

    public SuggestedItemsAdapter(List<Product> suggestedProducts){
        this.suggestedProducts = suggestedProducts;
    }

    @NonNull
    @Override
    public SuggestedItemsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull SuggestedItemsViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public static class SuggestedItemsViewHolder extends RecyclerView.ViewHolder {

        public View itemView;
        public SuggestedItemsViewHolder(@NonNull View itemView) {
            super(itemView);
            this.itemView = itemView;
        }
    }
}
