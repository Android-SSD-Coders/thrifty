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
import com.example.thrifty.adapters.AccessoriesAdapter;

import java.util.ArrayList;
import java.util.List;

public class Accessories extends AppCompatActivity {

    private List<Product> categorizedProducts = new ArrayList<>();
    RecyclerView categorizedRecView;
    AccessoriesAdapter accessoriesAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accessories);


        categorizedRecView = findViewById(R.id.categorizedRecView);
        categorizedRecView.setAdapter(accessoriesAdapter);
        categorizedRecView.setLayoutManager(new LinearLayoutManager(getApplicationContext(),RecyclerView.HORIZONTAL,false));

        categorizedRecView.setAdapter(new AccessoriesAdapter(categorizedProducts,this));
        Handler categorizedHandler = new Handler(Looper.myLooper(), new Handler.Callback() {
            @Override
            public boolean handleMessage(@NonNull Message msg) {
                categorizedRecView.getAdapter().notifyDataSetChanged();
                return false;
            }
        });
        Amplify.API.query(
                ModelQuery.list(Product.class,Product.CATEGORY_ID.eq("accessories")),
                response -> {
                    for (Product product : response.getData()) {
                        categorizedProducts.add(product);
                    }
                    categorizedHandler.sendEmptyMessage(1);
                }, error -> Log.e("MyAmplifyApp", "Query failure", error)
        );

    }

}