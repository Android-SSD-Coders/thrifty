package com.example.thrifty;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.nex3z.notificationbadge.NotificationBadge;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ProductsActivity extends AppCompatActivity implements ProductListener, CartListener {
    ProductAdapter adapter;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.recycler_product)
    RecyclerView recycler_product;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products);

        init();
        loadProductFromFirebase();
    }

    private void loadProductFromFirebase() {
        List<ProductModel> productModels = new ArrayList<>();
        FirebaseDatabase.getInstance()
                .getReference("Drink")
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if (dataSnapshot.exists()) {
                            for (DataSnapshot productSnapshot : dataSnapshot.getChildren()) {
                                ProductModel productModel = productSnapshot.getValue(ProductModel.class);
                                productModel.setKey(productSnapshot.getKey());
                                productModels.add(productModel);
                            }
                            productListener.onProductLoadSuccess(productModels);
                        } else
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
        recycler_product.setAdapter(adapter);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
        recycler_product.setLayoutManager(gridLayoutManager);
        recycler_product.addItemDecoration(new SpaceItemDecoration());
    }


    @SuppressLint("NotifyDataSetChanged")
    @Override
    public void onProductLoadSuccess(List<ProductModel> productModelList) {
        ProductAdapter adapter = new ProductAdapter(this, productModelList);
        recycler_product.setAdapter(adapter);
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