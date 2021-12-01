package com.example.thrifty.adapters;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.amplifyframework.api.graphql.model.ModelMutation;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.datastore.generated.model.Favourites;
import com.amplifyframework.datastore.generated.model.Product;

import java.util.ArrayList;
import java.util.List;

import com.amplifyframework.datastore.generated.model.User;
import com.example.thrifty.MainActivity;
import com.example.thrifty.ProductView;
import com.example.thrifty.R;


public class NewItemsAdapter extends RecyclerView.Adapter<NewItemsAdapter.NewItemsHolder> {
    List<Product> products = new ArrayList<>();
    List<Favourites> favourites;
    List<User> users;


    public NewItemsAdapter(List<Product> products) {
        this.products = products;
    }


    @NonNull
    @Override
    public NewItemsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_layout, parent, false);
        return new NewItemsAdapter.NewItemsHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NewItemsHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.product = products.get(position);
        TextView title = holder.itemView.findViewById(R.id.titlefrag1);
        TextView category = holder.itemView.findViewById(R.id.categoryfrag);
        TextView price = holder.itemView.findViewById(R.id.price);

        title.setText(holder.product.getTitle());
        category.setText(holder.product.getCategoryId());
        price.setText(holder.product.getPrice());

        holder.itemView.findViewById(R.id.card).setOnClickListener(new View.OnClickListener() {
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

    public static class NewItemsHolder extends RecyclerView.ViewHolder {
        public Product product;
        public View itemView;
        public User user;

        public NewItemsHolder(@NonNull View itemView) {
            super(itemView);
            this.itemView = itemView;

            SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(itemView.getContext());

            String email = sharedPreferences.getString("email", "Your email");

            Button btn = itemView.findViewById(R.id.button2);
            btn.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {
                    Toast.makeText(view.getContext(), "fav Button Clicked", Toast.LENGTH_LONG).show();

                    Favourites favourite = new Favourites.Builder()
                            .productId(product.getId())
                            .userId(email)
                            .build();

                    Amplify.API.mutate(
                            ModelMutation.create(favourite),
                            response -> Log.i("MyAmplifyApp", "Added Todo with id: " + response.getData().getId()),
                            error -> Log.e("MyAmplifyApp", "Create failed", error)

                    );
                }
            });

        }


    }
}

