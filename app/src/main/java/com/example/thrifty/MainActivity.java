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
import android.widget.Button;

import android.widget.Toolbar;

import com.amplifyframework.AmplifyException;
import com.amplifyframework.api.aws.AWSApiPlugin;
import com.amplifyframework.api.graphql.model.ModelQuery;
import com.amplifyframework.auth.cognito.AWSCognitoAuthPlugin;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.datastore.AWSDataStorePlugin;
import com.amplifyframework.datastore.generated.model.Product;
import com.amplifyframework.storage.s3.AWSS3StoragePlugin;
import com.example.thrifty.adapters.NewItemsAdapter;
import com.example.thrifty.adapters.PopularItemsAdapter;
import com.example.thrifty.adapters.SuggestedItemsAdapter;
import com.example.thrifty.fragments.MainMenuFragment;
import com.google.android.material.bottomnavigation.BottomNavigationItemView;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity  {

    private List<Product> NewProduct = new ArrayList<>();
    private List<Product> PopularProduct = new ArrayList<>();
    private List<Product> SuggestProduct = new ArrayList<>();


    RecyclerView newItemRecView, suggestedRecView, popularRecView;
    NewItemsAdapter newItemsAdapter;
    SuggestedItemsAdapter suggestedItemsAdapter;
    PopularItemsAdapter popularItemsAdapter;


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
//            Amplify.addPlugin(new AWSPinpointAnalyticsPlugin(getApplication()));
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



        findViewById(R.id.admin).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Admin.class);
                startActivity(intent);
            }
        });
        findViewById(R.id.map).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Tracking.class);
                startActivity(intent);
            }
        });

    }

    public void bottomNav(){
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setSelectedItemId(R.id.homeNav);
        BottomNavigationItemView homeNav = findViewById(R.id.homeNav);
        BottomNavigationItemView search = findViewById(R.id.search);
//        BottomNavigationItemView cart = findViewById(R.id.cart);
//        BottomNavigationItemView wishlist = findViewById(R.id.wishlist);
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
    }

    private void initRecyclerViews(){

        newItemRecView = findViewById(R.id.newItemsRecView);
        suggestedRecView = findViewById(R.id.suggestedRecView);
        popularRecView = findViewById(R.id.popularRecView);

//        newItemRecView.setAdapter(newItemsAdapter);
        newItemRecView.setLayoutManager(new LinearLayoutManager(getApplicationContext(),RecyclerView.HORIZONTAL,false));

        suggestedRecView.setAdapter(suggestedItemsAdapter);
        suggestedRecView.setLayoutManager(new LinearLayoutManager(getApplicationContext(), RecyclerView.HORIZONTAL,false));

        popularRecView.setAdapter(popularItemsAdapter);
        popularRecView.setLayoutManager(new LinearLayoutManager(getApplicationContext(),RecyclerView.HORIZONTAL,false));

        newItemRecView.setAdapter(new NewItemsAdapter(NewProduct , MainActivity.this));
        Handler handler = new Handler(Looper.myLooper(), new Handler.Callback() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public boolean handleMessage(@NonNull Message message) {
                newItemRecView.getAdapter().notifyDataSetChanged();
                return false;
            }
        });


        popularRecView.setAdapter(new NewItemsAdapter(PopularProduct , MainActivity.this));
        Handler popularhandler = new Handler(Looper.myLooper(), new Handler.Callback() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public boolean handleMessage(@NonNull Message message) {
                popularRecView.getAdapter().notifyDataSetChanged();
                return false;
            }
        });

        suggestedRecView.setAdapter(new NewItemsAdapter(SuggestProduct , MainActivity.this));
        Handler suggesthandler = new Handler(Looper.myLooper(), new Handler.Callback() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public boolean handleMessage(@NonNull Message message) {
                suggestedRecView.getAdapter().notifyDataSetChanged();
                return false;
            }
        });

        Amplify.API.query(
                ModelQuery.list(Product.class),
                response -> {
                    for (Product todo : response.getData()) {
                        NewProduct.add(todo);
                    }
                    handler.sendEmptyMessage(1);
                }, error -> Log.e("MyAmplifyApp", "Query failure", error)
        );
//
//
//
        Amplify.API.query(
                ModelQuery.list(Product.class),
                response -> {
                    for (Product todo : response.getData()) {
                        PopularProduct.add(todo);
                    }
                    popularhandler.sendEmptyMessage(1);
                }, error -> Log.e("MyAmplifyApp", "Query failure", error)
        );


        Amplify.API.query(
                ModelQuery.list(Product.class),
                response -> {
                    for (Product todo : response.getData()) {
                        SuggestProduct.add(todo);
                    }
                    suggesthandler.sendEmptyMessage(1);
                }, error -> Log.e("MyAmplifyApp", "Query failure", error)
        );
    }


    @Override
    protected void onResume() {
        super.onResume();
        Button stopButton = (Button) findViewById(R.id.admin);
        stopButton.setVisibility(View.GONE);
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(MainActivity.this);
        String email1 = sharedPreferences.getString("email", "Your email");
        if (email1.equals("jamalwari2@gmail.com")){
            stopButton.setVisibility(View.VISIBLE);
        }
    }

}
