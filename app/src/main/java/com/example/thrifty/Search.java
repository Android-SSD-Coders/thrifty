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
import android.widget.ArrayAdapter;
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
import com.example.thrifty.adapters.SearchAdapter;

import java.util.ArrayList;
import java.util.List;

public class Search extends AppCompatActivity {

    SearchView searchView;
    ListView listView;
    ArrayList<String> list;
    ArrayAdapter<Product> adapter;

    ArrayList <Product> products = new ArrayList<>();
    RecyclerView categorizedRecView;
    SearchAdapter searchAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search2);
        configure();

        categorizedRecView = findViewById(R.id.categorizedRecView);
        categorizedRecView.setAdapter(searchAdapter);
        categorizedRecView.setLayoutManager(new LinearLayoutManager(getApplicationContext(),RecyclerView.VERTICAL,false));

        categorizedRecView.setAdapter(new SearchAdapter(products,this));
        Handler categorizedHandler = new Handler(Looper.myLooper(), new Handler.Callback() {
            @Override
            public boolean handleMessage(@NonNull Message msg) {
                categorizedRecView.getAdapter().notifyDataSetChanged();
                return false;
            }
        });


        searchView = findViewById(R.id.searchView2);
//        listView = findViewById(R.id.lv1);
        list = new ArrayList<>();
        list.add("ArmWear");
        list.add("Belts");
        list.add("Children Clothes");
        list.add("Dresses");
        list.add("Footwear");
        list.add("Jackets");
        list.add("Jeans");
        list.add("Knee clothing");
        list.add("Masks");
        list.add("Suits");


        Amplify.API.query(
                ModelQuery.list(Product.class),
                response -> {
                    for (Product product : response.getData()) {
                        products.add(product);
                    }
                }, error -> Log.e("MyAmplifyApp", "Query failure", error)
        );

//        adapter = new ArrayAdapter<Product>(this, android.R.layout.simple_list_item_1,products);
//        listView.setAdapter(adapter);


        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                if(products.contains(query)){
                    adapter.getFilter().filter(query);
                }else{
                    Toast.makeText(Search.this, "No Match found",Toast.LENGTH_LONG).show();
                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.getFilter().filter(newText);
                return false;
            }
        });

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