package com.example.thrifty;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.amplifyframework.core.Amplify;
import com.google.android.material.bottomnavigation.BottomNavigationItemView;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Profile extends AppCompatActivity {
    private static final String TAG = "SearchActivity";
    private Toolbar toolbar;
    private EditText searchBar;
    private ImageView btnSearch, imageView, logout;
    private BottomNavigationView bottomNavigationView;
    private RecyclerView recyclerView;
    private Button addProduct, adminTrack, userTrack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        initViews();
        bottomNav();

        toolbar = findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_baseline_arrow_back_ios_new_24);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent   = new Intent(Profile.this, MainActivity.class);
                startActivity(intent);
            }
        });
        imageView = findViewById(R.id.fav);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Profile.this,MainActivity.class);
                startActivity(intent);
            }
        });
        logout = findViewById(R.id.logout);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Amplify.Auth.signOut(
                        () -> Log.i("AuthQuickstart", "Signed out successfully"),
                        error -> Log.e("not complemte", error.toString())
                );
                Intent intent = new Intent(Profile.this, Signin.class);
                startActivity(intent);
            }
        });
        addProduct = findViewById(R.id.admin);
        addProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Profile.this,Admin.class);
                startActivity(intent);
            }
        });
        userTrack = findViewById(R.id.userTrack);
        userTrack.setOnClickListener(view ->{
            Intent intent = new Intent(this,TrackActivity.class);
        });
        adminTrack = findViewById(R.id.adminTrack);
        userTrack.setOnClickListener(view ->{
            Intent intent = new Intent(this,OrderTracking.class);
        });
    }
    @Override
    protected void onResume() {
        super.onResume();
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(Profile.this);
        String email1 = sharedPreferences.getString("email", "Your email");
        TextView email = findViewById(R.id.email);
        email.setText(email1);

        String name = sharedPreferences.getString("name", "Your email");
        TextView name2 = findViewById(R.id.username);
        name2.setText(name);

        String phone = sharedPreferences.getString("phone", "Your phone number");
        TextView phone1 = findViewById(R.id.phone);
        phone1.setText(phone);

        Button stopButton = (Button) findViewById(R.id.admin);
        stopButton.setVisibility(View.GONE);
        adminTrack.setVisibility(View.GONE);
        if (email1.equals("ssdcoders@gmail.com")){
            stopButton.setVisibility(View.VISIBLE);
            adminTrack.setVisibility(View.VISIBLE);
            userTrack.setVisibility(View.GONE);
        }
    }

    public void bottomNav(){
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setSelectedItemId(R.id.profile);
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

        cart.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), Cart.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK| Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        });

        wishlist.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), Favourate.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK| Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        });
    }
    private void initViews() {
        toolbar = findViewById(R.id.toolbar);
        searchBar = findViewById(R.id.searchBox);
        btnSearch = findViewById(R.id.btnSearch);
        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        recyclerView = findViewById(R.id.recViewSearchResults);
    }
}