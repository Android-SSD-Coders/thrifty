package com.example.thrifty;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class Categories extends AppCompatActivity {

    Button btnWomen, btnMen, btnKids, btnWatches, btnBelts, btnAccessories;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories);

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
}