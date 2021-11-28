package com.example.thrifty.adapters;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.amplifyframework.datastore.generated.model.Product;

import java.util.ArrayList;
import java.util.List;

public class NewItemsAdapter extends RecyclerView.Adapter<NewItemsAdapter.NewItemsHolder> {
    List<Product> products = new ArrayList<>();

    public NewItemsAdapter(List<Product> products){
      this.products = products;
    }

    @NonNull
    @Override
    public NewItemsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull NewItemsHolder holder, int position) {

    }


    @Override
    public int getItemCount() {
        return 0;
    }

  public static class NewItemsHolder extends RecyclerView.ViewHolder{
        public  Product product;
        public View itemView;
        public NewItemsHolder(@NonNull View itemView) {
          super(itemView);
          this.itemView = itemView;
      }
  }
}

