package com.example.thrifty;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Database;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.amplifyframework.datastore.generated.model.Product;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.nex3z.notificationbadge.NotificationBadge;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ProductsActivity extends AppCompatActivity implements ProductListener, CartListener {
    @BindView((R.id.recycler_product))
    RecyclerView recyclerView;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.productLayout)
    RelativeLayout productLayout;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.badge)
    NotificationBadge badge;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.btnCart)
    FrameLayout btnCart;

    ProductListener productListener;
    CartListener cartListener;

    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products);

//        listView = findViewById(R.id.listView);

        init();



        DatabaseReference database = FirebaseDatabase.getInstance("https://thriftycart-default-rtdb.europe-west1.firebasedatabase.app").getReference().child("Drink");
        List<ProductModel> productModels = new ArrayList<>();
        Log.i("Reference","Reference===>"+database);
        database.addListenerForSingleValueEvent(new ValueEventListener() {

//        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Log.i("Zeft","zafateeeeeeeeeeet"+recyclerView);

                if (dataSnapshot.exists()) {
                    for (DataSnapshot productSnapshot:dataSnapshot.getChildren()) {
                        ProductModel productModel = productSnapshot.getValue(ProductModel.class);
                        Log.i("Heba","product one to add 5555555"+productModel);
                        productModel.setKey(productSnapshot.getKey());
                        Log.i("Jamal","product one to add 5555555"+productModel);
                        productModels.add(productModel);
                        Log.i("Khair","product one to add 9999999999"+productModel);
                    }
                    productListener.onProductLoadSuccess(productModels);
                } else
                Log.i("Nawal","product one to add 9999999999"+productModels);
                productListener.onProductLoadFailed("can't find products");
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                productListener.onProductLoadFailed(databaseError.getMessage());
            }
        });

    }

    private void init() {
        ButterKnife.bind(this);
        productListener = this;
        cartListener = this;
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.addItemDecoration(new SpaceItemDecoration());
    }


    @SuppressLint("NotifyDataSetChanged")
    @Override
    public void onProductLoadSuccess(List<ProductModel> productModels) {
        ProductAdapter adapter = new ProductAdapter(this, productModels);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onProductLoadFailed(String message) {
        Snackbar.make(productLayout,message, Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void onCartLoadSuccess(List<CartModel> cartModelList) {

    }

    @Override
    public void onCartLoadFailed(String message) {

    }
}