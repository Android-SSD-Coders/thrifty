package com.example.thrifty.adapters;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.amplifyframework.datastore.generated.model.Product;
import com.example.thrifty.Accessories;
import com.example.thrifty.MainActivity;
import com.example.thrifty.ProductView;
import com.example.thrifty.R;

import java.util.List;

public class AccessoriesAdapter extends RecyclerView.Adapter<AccessoriesAdapter.CategorizedProductHolder> {
    List<Product> products;

    public AccessoriesAdapter(List<Product> products, Accessories accessories){
        this.products = products;
    }
    @Override
    public CategorizedProductHolder onCreateViewHolder( ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_layout, parent , false);
        return  new AccessoriesAdapter.CategorizedProductHolder(view);
    }
    @Override
    public void onBindViewHolder(CategorizedProductHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.product = products.get(position);
        TextView title = holder.itemView.findViewById(R.id.titlefrag1);
        TextView category = holder.itemView.findViewById(R.id.categoryfrag);
        TextView price = holder.itemView.findViewById(R.id.price);

        title.setText(holder.product.getTitle());
        category.setText(holder.product.getCategoryId());
        price.setText(holder.product.getPrice());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent goToDetails = new Intent(view.getContext(), ProductView.class);
                goToDetails.putExtra("Title",products.get(position).getTitle());
                goToDetails.putExtra("category",products.get(position).getCategoryId());
                goToDetails.putExtra("price", products.get(position).getPrice());

                view.getContext().startActivity(goToDetails);
            }
        });
    }
    @Override
    public int getItemCount() {
        return products.size();
    }

    public static class CategorizedProductHolder extends RecyclerView.ViewHolder{
        public  Product product;
        public View itemView;
        public CategorizedProductHolder(View itemView) {
            super(itemView);
            this.itemView = itemView;
        }
    }
}

