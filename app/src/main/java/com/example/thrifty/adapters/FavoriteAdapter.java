package com.example.thrifty.adapters;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.amplifyframework.datastore.generated.model.Favorite;
import com.example.thrifty.MainActivity;
import com.example.thrifty.R;

import java.util.ArrayList;
import java.util.List;


public class FavoriteAdapter extends RecyclerView.Adapter<FavoriteAdapter.NewItemsHolder> {
    List<Favorite> favoriteList = new ArrayList<>();

    public FavoriteAdapter(List<Favorite> favoriteList, MainActivity mainActivity){
        this.favoriteList = favoriteList;
    }


    @NonNull
    @Override
    public NewItemsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_layout, parent , false);
        return  new FavoriteAdapter.NewItemsHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NewItemsHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.Favorite = favoriteList.get(position);
        TextView title = holder.itemView.findViewById(R.id.titlefrag1);
        TextView category = holder.itemView.findViewById(R.id.categoryfrag);

        title.setText(holder.Favorite.getTitleFav());
        category.setText(holder.Favorite.getCategoryFav());


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent goToDetails = new Intent(view.getContext(), MainActivity.class);
                goToDetails.putExtra("Title",favoriteList.get(position).getTitleFav());
                goToDetails.putExtra("category",favoriteList.get(position).getCategoryFav());

                view.getContext().startActivity(goToDetails);
            }
        });
    }


    @Override
    public int getItemCount() {
        return favoriteList.size();
    }

    public static class NewItemsHolder extends RecyclerView.ViewHolder{
        public  Favorite Favorite;
        public View itemView;
        public NewItemsHolder(@NonNull View itemView) {
            super(itemView);
            this.itemView = itemView;
        }
    }
}
