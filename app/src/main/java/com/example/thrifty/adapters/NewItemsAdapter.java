package com.example.thrifty.adapters;

import android.annotation.SuppressLint;
import android.content.Intent;
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
import com.amplifyframework.datastore.generated.model.Product;

import java.util.ArrayList;
import java.util.List;

import com.example.thrifty.MainActivity;
import com.example.thrifty.R;




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

        title.setText(holder.product.getTitle());
        category.setText(holder.product.getCategoryId());

        Button favButtons = holder.itemView.findViewById(R.id.favButton);
        favButtons.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView name = holder.itemView.findViewById(R.id.titlefrag1);
                TextView category = holder.itemView.findViewById(R.id.categoryfrag);
//                TextView email = findViewById(R.id.editTextTextPersonName6);

                String nameFav = name.getText().toString();
                String categoryFav = category.getText().toString();
//                String emailFav = email.getText().toString();

                Favorite favorite = new Favorite.Builder()
                        .titleFav(nameFav)
                        .imageFav("categoryFav")
                        .priceFav("15")
                        .sizeFav("15")
                        .categoryFav(categoryFav)
                        .userId("emailFav")
                        .build();
                Log.i("Favorie","kkkkkkkkkkkkkkkkkkk"+favorite);

                Amplify.API.mutate(
                        ModelMutation.create(favorite),
                        response -> Log.i("MyAmplifyApp", "Added Todo with id: " + response.getData().getId()),
                        error -> Log.e("MyAmplifyApp", "Create failed", error)
                );

                Toast.makeText(view.getContext(), "add to fav", Toast.LENGTH_SHORT).show();
            }
        });

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent goToDetails = new Intent(view.getContext(), MainActivity.class);
                goToDetails.putExtra("Title",products.get(position).getTitle());
                goToDetails.putExtra("category",products.get(position).getCategoryId());

                view.getContext().startActivity(goToDetails);
            }
        });
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

