package com.example.thrifty;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toolbar;

import com.google.android.material.bottomnavigation.BottomNavigationItemView;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Categories extends AppCompatActivity {

    Button btnWomen, btnMen, btnKids, btnWatches, btnBelts, btnAccessories;

    private Toolbar toolbar;
    private EditText searchBar;
    private ImageView btnSearch;
    private BottomNavigationView bottomNavigationView;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories);

        initViews();
        bottomNav();

        btnWomen = findViewById(R.id.btnWomen);
        btnMen = findViewById(R.id.btnMen);
        btnKids = findViewById(R.id.btnKids);
        btnWatches = findViewById(R.id.btnWatches);
        btnBelts = findViewById(R.id.btnBelts);
        btnAccessories = findViewById(R.id.btnAccessories);

        btnWomen.setOnClickListener(view ->{
            Intent intent = new Intent(this, WomenClothes.class);
            startActivity(intent);
        });
        btnMen.setOnClickListener(view ->{
            Intent intent = new Intent(this, MenClothes.class);
            startActivity(intent);
        });
        btnKids.setOnClickListener(view ->{
            Intent intent = new Intent(this, KidsClothes.class);
            startActivity(intent);
        });
        btnWatches.setOnClickListener(view ->{
            Intent intent = new Intent(this, Watches.class);
            startActivity(intent);
        });
        btnBelts.setOnClickListener(view ->{
            Intent intent = new Intent(this, Belts.class);
            startActivity(intent);
        });
        btnAccessories.setOnClickListener(view ->{
            Intent intent = new Intent(this, Accessories.class);
            startActivity(intent);
        });

    }
    public void bottomNav(){
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setSelectedItemId(R.id.search);
        BottomNavigationItemView homeNav = findViewById(R.id.homeNav);
        BottomNavigationItemView search = findViewById(R.id.search);
        BottomNavigationItemView profile = findViewById(R.id.profile);
//        BottomNavigationItemView cart = findViewById(R.id.cart);
//        BottomNavigationItemView wishlist = findViewById(R.id.wishlist);

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

    }

    private void initViews() {
        toolbar = findViewById(R.id.toolbar);
        searchBar = findViewById(R.id.searchBox);
        btnSearch = findViewById(R.id.btnSearch);
        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        recyclerView = findViewById(R.id.recViewSearchResults);
    }

}