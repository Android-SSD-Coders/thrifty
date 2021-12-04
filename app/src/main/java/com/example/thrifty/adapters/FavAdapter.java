//package com.example.thrifty.adapters;
//
//import android.annotation.SuppressLint;
//import android.content.Intent;
//import android.content.SharedPreferences;
//import android.preference.PreferenceManager;
//import android.util.Log;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.Button;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import androidx.annotation.NonNull;
//import androidx.recyclerview.widget.RecyclerView;
//
//import com.amplifyframework.api.graphql.model.ModelMutation;
//import com.amplifyframework.core.Amplify;
//import com.amplifyframework.datastore.generated.model.Favourites;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import com.amplifyframework.datastore.generated.model.Product;
//import com.example.thrifty.ProductView;
//import com.example.thrifty.R;
//
//
//public class FavAdapter extends RecyclerView.Adapter<FavAdapter.FavHolder> {
//    List<Product> favouritesList = new ArrayList<>();
//    List<Product> productList = new ArrayList<>();
//
//    public FavAdapter(List<Product> favouritesList) {
//        this.favouritesList = favouritesList;
//    }
//
//
//    @NonNull
//    @Override
//    public FavHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_layout, parent, false);
//        return new FavAdapter.FavHolder(view);
//    }
//    Button btn = itemView.findViewById(R.id.fav);
//    @Override
//    public void onBindViewHolder(@NonNull FavHolder holder, @SuppressLint("RecyclerView") int position) {
//
//        btn.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View view) {
//                holder.product = favouritesList.get(position);
//                TextView title = holder.itemView.findViewById(R.id.titlefrag1);
//                TextView category = holder.itemView.findViewById(R.id.categoryfrag);
//
//                title.setText(holder.favourite.getTitle());
//                category.setText(holder.favourite.getCategoryId());
//            }
//        });
//
//
//    }
//
//
//    @Override
//    public int getItemCount() {
//        return favouritesList.size();
//    }
//
//
//    public static class FavHolder extends RecyclerView.ViewHolder {
//        public Product favourite;
//        public View itemView;
//        public Product product;
//
//        public FavHolder(@NonNull View itemView) {
//            super(itemView);
//            this.itemView = itemView;
//
//            SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(itemView.getContext());
//
//            String email = sharedPreferences.getString("email", "Your email");
//
//
//
//        }
//    }
//}