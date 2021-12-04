package com.example.thrifty.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.amplifyframework.datastore.generated.model.Favorite;
import com.amplifyframework.datastore.generated.model.Product;
import com.example.thrifty.Cart;
import com.example.thrifty.ProductView;
import com.example.thrifty.R;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.NewItemsHolder> {

    List<Favorite> products = new ArrayList<>();
    Context context;

    public CartAdapter(Context context) {
        this.context = context;
    }

    public CartAdapter(List<Favorite> products, Cart cart) {
        this.products = products;
    }

    @NonNull
    @Override
    public NewItemsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_card, parent , false);
        return new NewItemsHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NewItemsHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.product = products.get(position);
        ImageView imageView = holder.itemView.findViewById(R.id.idIVCourseImage);
        TextView ProductName = holder.itemView.findViewById(R.id.titlefrag1);
        TextView ProductCat = holder.itemView.findViewById(R.id.categoryfrag);
        TextView ProductPrice = holder.itemView.findViewById(R.id.price);
        Button buttonView = holder.itemView.findViewById(R.id.button3);

        ImageView deleteImg = holder.itemView.findViewById(R.id.imageViw4);

        ProductName.setText(holder.product.getTitleFav());
        ProductCat.setText(holder.product.getCategoryFav());
        ProductPrice.setText(holder.product.getPriceFav());
        Picasso.get().load(holder.product.getImageFav() ).into(imageView);

        holder.itemView.findViewById(R.id.cardCart).setOnClickListener(view -> {
            Intent goToDetails = new Intent(view.getContext(), Cart.class);
            Intent name = goToDetails.putExtra("Title",products.get(position).getTitleFav());
//            goToDetails.putExtra("Title",products.get(position).getTitleFav());
            Log.i("Khair", "onClick: "+name);
            goToDetails.putExtra("category",products.get(position).getCategoryFav());
            goToDetails.putExtra("price", products.get(position).getPriceFav());
            goToDetails.putExtra("image",products.get(position).getImageFav());
//                goToDetails.putExtra("description",products.get(position).getDescription());
            view.getContext().startActivity(goToDetails);
        });


    }

    @Override
    public int getItemCount() {
        return 0;
    }


    public static class NewItemsHolder extends RecyclerView.ViewHolder{
        public  Favorite product;
        public View itemView;
        public NewItemsHolder(@NonNull View itemView) {
            super(itemView);
            this.itemView = itemView;
        }
    }
}
