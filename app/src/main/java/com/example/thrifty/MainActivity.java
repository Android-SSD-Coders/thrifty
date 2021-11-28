package com.example.thrifty;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import android.view.MenuItem;
import android.view.View;


import android.util.Log;
import android.widget.Toast;
import android.widget.Toolbar;

import com.amplifyframework.AmplifyException;
import com.amplifyframework.analytics.pinpoint.AWSPinpointAnalyticsPlugin;
import com.amplifyframework.auth.cognito.AWSCognitoAuthPlugin;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.datastore.AWSDataStorePlugin;
import com.amplifyframework.storage.s3.AWSS3StoragePlugin;
import com.example.thrifty.adapters.NewItemsAdapter;
import com.example.thrifty.adapters.PopularItemsAdapter;
import com.example.thrifty.adapters.SuggestedItemsAdapter;
import com.example.thrifty.fragments.MainMenuFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity extends AppCompatActivity  {
//    implements BottomNavigationView.OnNavigationItemSelectedListener

     RecyclerView newItemRecView, suggestedRecView, popularRecView;
     NewItemsAdapter newItemsAdapter;
     SuggestedItemsAdapter suggestedItemsAdapter;
     PopularItemsAdapter popularItemsAdapter;
     BottomNavigationView bottomNavigationView;

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
            Amplify.addPlugin(new AWSPinpointAnalyticsPlugin(getApplication()));
            Amplify.addPlugin(new AWSS3StoragePlugin());
            Amplify.addPlugin(new AWSCognitoAuthPlugin());
            Amplify.addPlugin(new AWSDataStorePlugin());
            Amplify.configure(getApplicationContext());
            Log.i("MyAmplifyApp", "Initialized Amplify");
        } catch (AmplifyException error) {
            Log.e("MyAmplifyApp", "Could not initialize Amplify", error);
        }
//TODO This shit crashes the APP We Need To Fix It
//        mainMenuFragment.bottomNav();
// bottomNav();
        initRecyclerViews();


//findViewById(R.id.button3).setOnClickListener(new View.OnClickListener() {
//    @Override
//    public void onClick(View view) {
//        Intent intent = new Intent(MainActivity.this, Signup.class);
//        startActivity(intent);
//    }
//});

    }

private void initRecyclerViews(){
    newItemRecView = findViewById(R.id.newItemsRecView);
    suggestedRecView = findViewById(R.id.suggestedRecView);
    popularRecView = findViewById(R.id.popularRecView);

    newItemRecView.setAdapter(newItemsAdapter);
    newItemRecView.setLayoutManager(new LinearLayoutManager(getApplicationContext(),RecyclerView.HORIZONTAL,false));

    suggestedRecView.setAdapter(suggestedItemsAdapter);
    suggestedRecView.setLayoutManager(new LinearLayoutManager(getApplicationContext(), RecyclerView.HORIZONTAL,false));

    popularRecView.setAdapter(popularItemsAdapter);
    popularRecView.setLayoutManager(new LinearLayoutManager(getApplicationContext(),RecyclerView.HORIZONTAL,false));
}

@SuppressLint("NonConstantResourceId")
private void bottomNav(){
    bottomNavigationView.setSelectedItemId(R.id.search);
    bottomNavigationView.setOnItemSelectedListener(item -> {
        switch (item.getItemId()){
            case R.id.homeNav:
//                Toast.makeText(getApplicationContext(),"Home Clicked", Toast.LENGTH_LONG).show();
//                Intent intent = new Intent(getContext(), MainActivity.class);
//                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK| Intent.FLAG_ACTIVITY_NEW_TASK);
//                startActivity(intent);
                break;

            case R.id.search:
                Toast.makeText(getApplicationContext(),"Search Clicked", Toast.LENGTH_LONG).show();
                Intent intent1 = new Intent(MainActivity.this, SearchActivity.class);
                intent1.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK| Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent1);
                break;

            case R.id.cart:
                Toast.makeText(getApplicationContext(),"Cart Clicked", Toast.LENGTH_LONG).show();
//                    Intent intent2 = new Intent(getContext(), Cart.class);
//                    intent2.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK| Intent.FLAG_ACTIVITY_NEW_TASK);
//                    startActivity(intent2);
                break;
            case R.id.wishlist:
                Toast.makeText(getApplicationContext(),"Wishlist Clicked", Toast.LENGTH_LONG).show();
//                    Intent intent3 = new Intent(MainActivity.this, Wishlist.class);
//                    intent3.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK| Intent.FLAG_ACTIVITY_NEW_TASK);
//                    startActivity(intent3);
                break;

            case R.id.profile:
                Toast.makeText(getApplicationContext(),"Profile Clicked", Toast.LENGTH_LONG).show();
//                    Intent intent4 = new Intent(MainActivity.this, Profile.class);
//                    intent4.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK| Intent.FLAG_ACTIVITY_NEW_TASK);
//                    startActivity(intent4);
                break;
            default:
                Toast.makeText(getApplicationContext(),"None", Toast.LENGTH_LONG).show();
                break;
        }
        return false;
    });
}
}