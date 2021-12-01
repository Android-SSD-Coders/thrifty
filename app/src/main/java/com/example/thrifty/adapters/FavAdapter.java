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

import java.util.ArrayList;
import java.util.List;

import com.amplifyframework.datastore.generated.model.Product;
import com.example.thrifty.ProductView;
import com.example.thrifty.R;


public class FavAdapter extends RecyclerView.Adapter<FavAdapter.FavHolder> {
    List<Favourites> favouritesList = new ArrayList<>();
    List<Favourites> productList = new ArrayList<>();

    public FavAdapter(List<Favourites> favouritesList) {
        this.favouritesList = favouritesList;
    }


    @NonNull
    @Override
    public FavHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_layout, parent, false);
        return new FavAdapter.FavHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FavHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.favourite = favouritesList.get(position);

        TextView title = holder.itemView.findViewById(R.id.titlefrag1);
        TextView category = holder.itemView.findViewById(R.id.categoryfrag);
//        TextView price = holder.itemView.findViewById(R.id.price);

        title.setText(holder.favourite.getProductId());
        category.setText(holder.favourite.getUserId());
//        price.setText(holder.product.getPrice());
    }


    @Override
    public int getItemCount() {
        return favouritesList.size();
    }


    public static class FavHolder extends RecyclerView.ViewHolder {
        public Favourites favourite;
        public View itemView;
        public Product product;

        public FavHolder(@NonNull View itemView) {
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


