package com.example.thrifty.adapters;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;
import com.amplifyframework.datastore.generated.model.Product;
import java.util.List;
import com.example.thrifty.MainActivity;
import com.example.thrifty.ProductView;
import com.example.thrifty.R;
import com.example.thrifty.WomenClothes;
import com.squareup.picasso.Picasso;

public class WomenClothesAdapter extends RecyclerView.Adapter<WomenClothesAdapter.CategorizedProductHolder> {
    List<Product> products;

    public WomenClothesAdapter(List<Product> products, WomenClothes womenClothes){
        this.products = products;
    }
    @Override
    public CategorizedProductHolder onCreateViewHolder( ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_layout, parent , false);
        return  new WomenClothesAdapter.CategorizedProductHolder(view);
    }
    @Override
    public void onBindViewHolder(CategorizedProductHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.product = products.get(position);
        TextView title = holder.itemView.findViewById(R.id.titlefrag1);
        TextView category = holder.itemView.findViewById(R.id.categoryfrag);
        TextView price = holder.itemView.findViewById(R.id.price);
        ImageView image = holder.itemView.findViewById(R.id.idIVCourseImage);


        title.setText(holder.product.getTitle());
        category.setText(holder.product.getCategoryId());
        price.setText(holder.product.getPrice());
        Picasso.get().load(holder.product.getImage()).into(image);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent goToDetails = new Intent(view.getContext(), ProductView.class);
                goToDetails.putExtra("Title",products.get(position).getTitle());
                goToDetails.putExtra("category",products.get(position).getCategoryId());
                goToDetails.putExtra("price", products.get(position).getPrice());
                goToDetails.putExtra("image",products.get(position).getImage());


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

