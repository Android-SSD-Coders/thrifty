package com.example.thrifty;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.amazonaws.mobileconnectors.pinpoint.PinpointManager;
import com.amplifyframework.api.graphql.model.ModelMutation;
import com.amplifyframework.api.graphql.model.ModelQuery;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.datastore.generated.model.Favorite;
import com.amplifyframework.datastore.generated.model.Product;
import com.example.thrifty.adapters.FavoriteAdapter;
import com.example.thrifty.adapters.NewItemsAdapter;
import com.google.android.material.bottomnavigation.BottomNavigationItemView;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class FavActivity extends AppCompatActivity {

    private List<Favorite> favoriteList;


    FavoriteAdapter favoriteAdapter;
    public static PinpointManager getPinpointManager(Context applicationContext) {
        return null;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite);

        bottomNav();
        initRecyclerViews();

        List<Favorite> favoriteList = new ArrayList<>();

        TextView title = findViewById(R.id.titlefrag1);
        TextView category = findViewById(R.id.categoryfrag);
        TextView price = findViewById(R.id.price);

//        Button btn = findViewById(R.id.button2);
//        btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(view.getContext());
//                String email = sharedPreferences.getString("email","email");
//
//                Toast.makeText(view.getContext(), "fav Button Clicked", Toast.LENGTH_LONG).show();
//                String name = title.getText().toString();
//                String cat = category.getText().toString();
//
//                Favorite favorite = new Favorite.Builder()
//                        .titleFav(name)
//                        .imageFav("categoryFav")
//                        .priceFav("15")
//                        .sizeFav("15")
//                        .categoryFav(cat)
//                        .userId(email)
//                        .build();
//                Amplify.API.mutate(
//                        ModelMutation.create(favorite),
//                        response -> Log.i("MyAmplifyApp", "Added Todo with id: " + response.getData().getId()),
//                        error -> Log.e("MyAmplifyApp", "Create failed", error)
//
//                );
//            }
//        });

    }

    public void bottomNav(){
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setSelectedItemId(R.id.homeNav);
        BottomNavigationItemView homeNav = findViewById(R.id.homeNav);
        BottomNavigationItemView search = findViewById(R.id.search);
//        BottomNavigationItemView cart = findViewById(R.id.cart);
        BottomNavigationItemView wishlist = findViewById(R.id.wishlist);
        BottomNavigationItemView profile = findViewById(R.id.profile);

        search.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), SearchActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK| Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        });

        homeNav.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK| Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        });

        profile.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), Profile.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK| Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        });

        wishlist.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), FavoriteActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK| Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        });
    }


    private void initRecyclerViews(){
        RecyclerView favoriteView = findViewById(R.id.favoriteRecycle);
        favoriteView.setLayoutManager(new LinearLayoutManager(this));


        favoriteView.setAdapter(new FavoriteAdapter(favoriteList));
        Handler handler = new Handler(Looper.myLooper(), message -> {
            Objects.requireNonNull(favoriteView.getAdapter()).notifyDataSetChanged();
            return false;
        });


        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        String email = sharedPreferences.getString("email","email");


        Amplify.API.query(
                ModelQuery.list(Favorite.class),
                response -> {
                    for (Favorite todo : response.getData()) {
                        if(todo.getUserId() == email) {
                            favoriteList.add(todo);
                            Log.i("Ya rab", "ya rab "+favoriteList);
                        }
                    }
                    handler.sendEmptyMessage(1);
                }, error -> Log.e("MyAmplifyApp", "Query failure", error)
        );
    }
}