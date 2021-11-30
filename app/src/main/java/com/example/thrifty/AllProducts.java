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
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import com.amplifyframework.AmplifyException;
import com.amplifyframework.analytics.pinpoint.AWSPinpointAnalyticsPlugin;
import com.amplifyframework.api.aws.AWSApiPlugin;
import com.amplifyframework.api.graphql.model.ModelQuery;
import com.amplifyframework.auth.cognito.AWSCognitoAuthPlugin;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.datastore.AWSDataStorePlugin;
import com.amplifyframework.datastore.generated.model.Product;
import com.amplifyframework.storage.s3.AWSS3StoragePlugin;
import com.example.thrifty.adapters.AllProductsAdapter;
import com.example.thrifty.adapters.MenClothesAdapter;

import java.util.ArrayList;
import java.util.List;

public class AllProducts extends AppCompatActivity {

    private final List<Product> categorizedProducts = new ArrayList<>();
    List<String> products = new ArrayList<>();
    RecyclerView categorizedRecView;
    AllProductsAdapter allProductsAdapter;
    SearchView searchView;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_products);
        configure();

        listView = (ListView) findViewById(R.id.lv1);

//        categorizedRecView = findViewById(R.id.categorizedRecView);
//        categorizedRecView.setAdapter(allProductsAdapter);
//        categorizedRecView.setLayoutManager(new LinearLayoutManager(getApplicationContext(), RecyclerView.HORIZONTAL, false));

        listView.setAdapter((ListAdapter) allProductsAdapter);


//        categorizedRecView.setAdapter(new AllProductsAdapter(categorizedProducts, this));
//        categorizedRecView.setAdapter(new AllProductsAdapter(categorizedProducts, this));
//        Handler categorizedHandler = new Handler(Looper.myLooper(), new Handler.Callback() {
//            @Override
//            public boolean handleMessage(@NonNull Message msg) {
//                listView.getAdapter();
//                return false;
//            }
//        });

//        Amplify.API.query(
//                ModelQuery.list(Product.class),
//                response -> {
//                    for (Product product : response.getData()) {
//                        categorizedProducts.add(product);
//                        products.add(product.toString());
//                    }
//                    categorizedHandler.sendEmptyMessage(1);
//                }, error -> Log.e("MyAmplifyApp", "Query failure", error)
//        );

//        searchView = (SearchView) findViewById(R.id.searchView);
//
//        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
//            @Override
//            public boolean onQueryTextSubmit(String query) {
//
//                if(list.contains(query)){
//                    adapter.getFilter().filter(query);
//                }else{
//                    Toast.makeText(SearchActivity.this, "No Match found",Toast.LENGTH_LONG).show();
//                }
//                return false;
//            }
//
//            @Override
//            public boolean onQueryTextChange(String newText) {
//                return false;
//            }
    }
    public void configure() {
        try {
            Amplify.addPlugin(new AWSPinpointAnalyticsPlugin(getApplication()));
            Amplify.addPlugin(new AWSS3StoragePlugin());
            Amplify.addPlugin(new AWSCognitoAuthPlugin());
            Amplify.addPlugin(new AWSApiPlugin());
            Amplify.addPlugin(new AWSDataStorePlugin());
            Amplify.configure(getApplicationContext());
            Log.i("MyAmplifyApp", "Initialized Amplify");
        } catch (AmplifyException error) {
            Log.e("MyAmplifyApp", "Could not initialize Amplify", error);
        }
    }
}