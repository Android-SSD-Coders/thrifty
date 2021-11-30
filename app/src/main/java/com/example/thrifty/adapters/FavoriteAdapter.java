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
import com.amplifyframework.datastore.generated.model.Favorite;
import com.example.thrifty.ProductView;
import com.example.thrifty.R;

import java.util.ArrayList;
import java.util.List;

public class FavoriteAdapter extends RecyclerView.Adapter<FavoriteAdapter
        .FavoriteHolder
> {
    List<Favorite> favoriteList
            = new ArrayList<>();

    public FavoriteAdapter(List<Favorite> favoriteList) {
        this.favoriteList= favoriteList;
    }


    @NonNull
    @Override
    public FavoriteAdapter
            .FavoriteHolder
 onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_layout, parent, false);
        return new FavoriteAdapter
                .FavoriteHolder
(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FavoriteAdapter
            .FavoriteHolder
 holder, @SuppressLint("RecyclerView") int position) {
        holder.favorite = favoriteList
                .get(position);
        TextView title = holder.itemView.findViewById(R.id.titlefrag1);
        TextView category = holder.itemView.findViewById(R.id.categoryfrag);
        TextView price = holder.itemView.findViewById(R.id.price);

        title.setText(holder.favorite.getTitleFav());
        category.setText(holder.favorite.getCategoryFav());
        price.setText(holder.favorite.getPriceFav());

        Button btn = holder.itemView.findViewById(R.id.button2);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(view.getContext());
                String email = sharedPreferences.getString("email", "email");

                Log.i("emaill", "Khair " + email);
                Toast.makeText(view.getContext(), "fav Button Clicked", Toast.LENGTH_LONG).show();
                String name = title.getText().toString();
                String cat = category.getText().toString();
                Favorite favorite = new Favorite.Builder()
                        .titleFav(name)
                        .imageFav("categoryFav")
                        .priceFav("15")
                        .sizeFav("15")
                        .categoryFav(cat)
                        .userId(email)
                        .build();
                Amplify.API.mutate(
                        ModelMutation.create(favorite),
                        response -> Log.i("MyAmplifyApp", "Added Todo with id: " + response.getData().getId()),
                        error -> Log.e("MyAmplifyApp", "Create failed", error)
                );
            }
        });



        holder.itemView.findViewById(R.id.card).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent goToDetails = new Intent(view.getContext(), Favorite.class);
                goToDetails.putExtra("Title", favoriteList
                        .get(position).getTitleFav());
                view.getContext().startActivity(goToDetails);
            }
        });
    }


    @Override
    public int getItemCount() {
        return favoriteList.size();
    }

    public static class FavoriteHolder
 extends RecyclerView.ViewHolder {
        public Favorite favorite;
        public View itemView;

        public FavoriteHolder
(@NonNull View itemView) {
            super(itemView);
            this.itemView = itemView;
        }
    }

}
