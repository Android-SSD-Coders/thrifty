package com.example.thrifty;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.preference.PreferenceManager;
import android.view.View;
import android.util.Log;
import android.widget.Toolbar;

import com.amplifyframework.AmplifyException;
import com.amplifyframework.api.aws.AWSApiPlugin;
import com.amplifyframework.api.graphql.model.ModelQuery;
import com.amplifyframework.auth.cognito.AWSCognitoAuthPlugin;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.datastore.AWSDataStorePlugin;
import com.amplifyframework.datastore.generated.model.Favourites;
import com.amplifyframework.datastore.generated.model.Product;
import com.amplifyframework.storage.s3.AWSS3StoragePlugin;
import com.example.thrifty.adapters.FavAdapter;
import com.example.thrifty.fragments.MainMenuFragment;
import com.google.android.material.bottomnavigation.BottomNavigationItemView;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class FavouriteActivity extends AppCompatActivity  {

    private List<Favourites> favProduct = new ArrayList<>();
    private List<Product> myProduct= new ArrayList<>();

    RecyclerView favRecView;
    FavAdapter favAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MainMenuFragment mainMenuFragment = new MainMenuFragment();
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        toolbar.setNavigationIcon(R.drawable.ic_baseline_arrow_back_ios_new_24);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });

        try {
            Amplify.addPlugin(new AWSS3StoragePlugin());
            Amplify.addPlugin(new AWSCognitoAuthPlugin());
            Amplify.addPlugin(new AWSDataStorePlugin());
            Amplify.addPlugin(new AWSApiPlugin());
            Amplify.configure(getApplicationContext());
            Log.i("MyAmplifyApp", "Initialized Amplify");
        } catch (AmplifyException error) {
            Log.e("MyAmplifyApp", "Could not initialize Amplify", error);
        }

        bottomNav();
        initRecyclerViews();
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
            Intent intent = new Intent(getApplicationContext(), FavouriteActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK| Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        });
    }

    private void initRecyclerViews() {
        favRecView = findViewById(R.id.FavRecView);
        favRecView.setAdapter(favAdapter);
        favRecView.setLayoutManager(new LinearLayoutManager(getApplicationContext(), RecyclerView.HORIZONTAL, false));

        favRecView.setAdapter(new FavAdapter(favProduct));
        Handler handler = new Handler(Looper.myLooper(), new Handler.Callback() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public boolean handleMessage(@NonNull Message message) {
                Objects.requireNonNull(favRecView.getAdapter()).notifyDataSetChanged();
                return false;
            }
        });

        Amplify.API.query(
                ModelQuery.list(Product.class),
                response -> {
                    for (Product todo : response.getData()) {
                        myProduct.add(todo);
                    }
                    handler.sendEmptyMessage(1);
                }, error -> Log.e("MyAmplifyApp", "Query failure", error)
        );
    }
}
