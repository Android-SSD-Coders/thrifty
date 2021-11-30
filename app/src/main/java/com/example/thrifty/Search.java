package com.example.thrifty;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import com.amplifyframework.datastore.generated.model.Product;
import com.example.thrifty.adapters.AllProductsAdapter;
import com.example.thrifty.adapters.SearchAdapter;

import java.util.ArrayList;
import java.util.List;

public class Search extends AppCompatActivity {

    private final List<Product> categorizedProducts = new ArrayList<>();

    SearchView searchView;
    ListView listView;
    ArrayList<String> list;
    ArrayAdapter<String > adapter;

    List<String> products = new ArrayList<>();
    RecyclerView categorizedRecView;
    SearchAdapter searchAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search2);

        searchView = (SearchView) findViewById(R.id.searchView2);
        listView = (ListView) findViewById(R.id.lv1);

        //        categorizedRecView = findViewById(R.id.categorizedRecView);
//        categorizedRecView.setAdapter(allProductsAdapter);
//        categorizedRecView.setLayoutManager(new LinearLayoutManager(getApplicationContext(), RecyclerView.HORIZONTAL, false));
//        categorizedRecView.setAdapter(searchAdapter);


        list = new ArrayList<>();
        list.add("Armwear");
        list.add("Belts");
        list.add("Children Clothes");
        list.add("Dresses");
        list.add("Footwear");
        list.add("Jackets");
        list.add("Jeans");
        list.add("Knee clothing");
        list.add("Masks");
        list.add("Suits");

        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,list);
        listView.setAdapter(adapter);

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


        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                if(list.contains(query)){
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

}