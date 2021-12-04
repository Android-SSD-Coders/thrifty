package com.example.thrifty;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;

import com.amplifyframework.api.graphql.model.ModelQuery;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.datastore.generated.model.Product;
import com.example.thrifty.adapters.WomenClothesAdapter;

import java.util.ArrayList;
import java.util.List;

public class WomenClothes extends AppCompatActivity {

    private List<Product> categorizedProducts = new ArrayList<>();
    RecyclerView categorizedRecView;
    WomenClothesAdapter womenClothesAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_women_clothes);

        categorizedRecView = findViewById(R.id.categorizedRecView);
        categorizedRecView.setAdapter(womenClothesAdapter);
        categorizedRecView.setLayoutManager(new LinearLayoutManager(getApplicationContext(),RecyclerView.HORIZONTAL,false));

        categorizedRecView.setAdapter(new WomenClothesAdapter(categorizedProducts, WomenClothes.this));
        Handler categorizedHandler = new Handler(Looper.myLooper(), new Handler.Callback() {
            @Override
            public boolean handleMessage(@NonNull Message msg) {
                categorizedRecView.getAdapter().notifyDataSetChanged();
                return false;
            }
        });

        Amplify.API.query(
                ModelQuery.list(Product.class,Product.CATEGORY_ID.eq("women_clothes")),
                response -> {
                    for (Product product : response.getData()) {
                        categorizedProducts.add(product);
                    }
                    categorizedHandler.sendEmptyMessage(1);
                }, error -> Log.e("MyAmplifyApp", "Query failure", error)
        );

    }
}