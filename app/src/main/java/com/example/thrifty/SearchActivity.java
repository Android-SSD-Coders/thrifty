package com.example.thrifty;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class SearchActivity extends AppCompatActivity {
    private static final String TAG = "SearchActivity";
    private Toolbar toolbar;
    private EditText searchBar;
    private ImageView btnSearch;
    private BottomNavigationView bottomNavigationView;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        initViews();
        bottomNav();
    }

    private void bottomNav() {
        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.homeNav:
                        Intent intent = new Intent(SearchActivity.this, MainActivity.class);
                        startActivity(intent);
                        break;

                    case R.id.search:

                        break;

                    case R.id.cart:

                        break;

                    default:
                        break;
                }
                return false;
            }
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