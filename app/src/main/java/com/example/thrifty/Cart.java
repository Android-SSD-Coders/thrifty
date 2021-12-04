package com.example.thrifty;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.amplifyframework.AmplifyException;
import com.amplifyframework.api.aws.AWSApiPlugin;
import com.amplifyframework.api.graphql.model.ModelQuery;
import com.amplifyframework.auth.cognito.AWSCognitoAuthPlugin;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.datastore.AWSDataStorePlugin;
import com.amplifyframework.datastore.generated.model.Favorite;
import com.amplifyframework.datastore.generated.model.Product;
import com.amplifyframework.storage.s3.AWSS3StoragePlugin;
import com.example.thrifty.adapters.CartAdapter;
import com.google.android.material.bottomnavigation.BottomNavigationItemView;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class Cart extends AppCompatActivity {
        private List<Favorite> favProduct = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

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


        RecyclerView recyclerView = findViewById(R.id.cartRecycler);
        CartAdapter cartAdapter = new CartAdapter(getApplicationContext());



        bottomNav();
        Button button1 = findViewById(R.id.button4);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    Intent intent = new Intent(Cart.this,Checkout.class);
                startActivity(intent);
            }
        });
        recyclerView.setAdapter(cartAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(), RecyclerView.VERTICAL,false));

        Handler handler = new Handler(Looper.myLooper(), new Handler.Callback() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public boolean handleMessage(@NonNull Message message) {
                recyclerView.getAdapter().notifyDataSetChanged();
                return false;
            }
        });


        TextView textView = findViewById(R.id.titlefrag1);
        TextView priceTxt = findViewById(R.id.price);
        TextView categoryTxt = findViewById(R.id.categoryfrag);
//        TextView descriptionTxt = findViewById(R.id.descriptionText);

        Intent intent = getIntent();
        String title = intent.getStringExtra("Title");
        Log.i("Jamal", "onCreate: Tilte  "+ title);
        String price = intent.getStringExtra("price");
        String category = intent.getStringExtra("category");
//        String description = intent.getStringExtra("description");





//        String url = intent.getExtras().getString("image");
//        ImageView image = findViewById(R.id.itemImage);
//        Log.i("imagview", url);
//        Picasso.get().load(url).into(image);

        Amplify.API.query(
                ModelQuery.list(Favorite.class),
                response->{
                    for (Favorite favorite:response.getData()){
                        favProduct.add(favorite);
                    }
                    handler.sendEmptyMessage(1);
                },
                error-> Log.e("MyAmplifyApp", "Query failure", error)
        );
        textView.setText(title);
        priceTxt.setText(price);
        categoryTxt.setText(category);
//        descriptionTxt.setText(description);


    }
    public void bottomNav(){
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setSelectedItemId(R.id.cart);
        BottomNavigationItemView homeNav = findViewById(R.id.homeNav);
        BottomNavigationItemView search = findViewById(R.id.search);
        BottomNavigationItemView cart = findViewById(R.id.cart);
        BottomNavigationItemView wishlist = findViewById(R.id.wishlist);
        BottomNavigationItemView profile = findViewById(R.id.profile);

        search.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), Categories.class);
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

        wishlist.setOnClickListener(view     -> {
            Intent intent = new Intent(getApplicationContext(), Favourate.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK| Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        });

        cart.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), Cart.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK| Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        });
    }
}