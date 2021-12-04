package com.example.thrifty.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.amplifyframework.api.graphql.model.ModelMutation;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.datastore.generated.model.Favorite;
import com.amplifyframework.datastore.generated.model.Product;

import java.util.ArrayList;
import java.util.List;

import com.example.thrifty.MainActivity;
import com.example.thrifty.ProductView;
import com.example.thrifty.R;
//import com.squareup.picasso.Picasso;


public class NewItemsAdapter extends RecyclerView.Adapter<NewItemsAdapter.NewItemsHolder> {
    List<Product> products = new ArrayList<>();

    public NewItemsAdapter(List<Product> products, MainActivity mainActivity){
      this.products = products;
    }


    @NonNull
    @Override
    public NewItemsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_layout, parent , false);
        return  new NewItemsAdapter.NewItemsHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NewItemsHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.product = products.get(position);
        TextView title = holder.itemView.findViewById(R.id.titlefrag1);
        TextView category = holder.itemView.findViewById(R.id.categoryfrag);
        TextView price = holder.itemView.findViewById(R.id.price);
        TextView des = holder.itemView.findViewById(R.id.des);
        ImageView image = holder.itemView.findViewById(R.id.idIVCourseImage);
                //        TextView email = holder.itemView.findViewById(R.id.editTextTextPersonName6);



        title.setText(holder.product.getTitle());
        category.setText(holder.product.getCategoryId());
        price.setText(holder.product.getPrice());
        des.setText(holder.product.getDescription());
//        Picasso.get().load(holder.product.getImage()).into(image);

        holder.itemView.findViewById(R.id.card).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent goToDetails = new Intent(view.getContext(), ProductView.class);
                goToDetails.putExtra("Title",products.get(position).getTitle());
                goToDetails.putExtra("category",products.get(position).getCategoryId());
                goToDetails.putExtra("price", products.get(position).getPrice());
                goToDetails.putExtra("image",products.get(position).getImage());
                goToDetails.putExtra("description",products.get(position).getDescription());
                view.getContext().startActivity(goToDetails);
            }
        });



//        holder.itemView.findViewById(R.id.fav).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent goTofav = new Intent(view.getContext(), ProductView.class);
//                goTofav.putExtra("Title",products.get(position).getTitle());
//                goTofav.putExtra("category",products.get(position).getCategoryId());
//                goTofav.putExtra("price", products.get(position).getPrice());
//                goTofav.putExtra("image",products.get(position).getImage());
//                goTofav.putExtra("description",products.get(position).getDescription());
//                view.getContext().startActivity(goTofav);
//            }
//        });
    }


    @Override
    public int getItemCount() {
        return products.size();
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