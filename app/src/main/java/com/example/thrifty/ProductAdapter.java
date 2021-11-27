package com.example.thrifty;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.amplifyframework.datastore.generated.model.Product;

import java.util.ArrayList;
import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder>  {
    List<Product> allProducts = new ArrayList<>();

    public ProductAdapter(List<Product> allProducts) {
        this.allProducts = allProducts;
    }

    public static class ProductViewHolder extends RecyclerView.ViewHolder{
        public Product product;
        View itemView;

        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);
            this.itemView = itemView;
        }
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.fragment_product,viewGroup,false);
        ProductViewHolder productViewHolder = new ProductViewHolder(view);
        return productViewHolder;    }

    @Override
    public void onBindViewHolder(@NonNull ProductAdapter.ProductViewHolder productViewHolder, @SuppressLint("RecyclerView") int position) {
        productViewHolder.product = allProducts.get(position);
        TextView productTitle = productViewHolder.itemView.findViewById(R.id.productTitleInFragment);
        TextView productCategory = productViewHolder.itemView.findViewById(R.id.productCategoryInFragment);

        productTitle.setText(productViewHolder.product.getTitle());
        productCategory.setText(productViewHolder.product.getCategory().toString());


        productViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), ProductDetails.class);
                intent.putExtra("title", allProducts.get(position).getTitle());
                intent.putExtra("category", allProducts.get(position).getCategory().toString());
                view.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return allProducts.size();
    }
}
