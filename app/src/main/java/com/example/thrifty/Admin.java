package com.example.thrifty;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toolbar;

import com.amplifyframework.AmplifyException;
//import com.amplifyframework.api.graphql.model.ModelMutation;
import com.amplifyframework.api.aws.AWSApiPlugin;
import com.amplifyframework.api.graphql.model.ModelMutation;
import com.amplifyframework.auth.cognito.AWSCognitoAuthPlugin;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.datastore.AWSDataStorePlugin;
import com.amplifyframework.datastore.generated.model.Category;
import com.amplifyframework.datastore.generated.model.Product;
import com.amplifyframework.storage.s3.AWSS3StoragePlugin;

public class Admin extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_baseline_arrow_back_ios_new_24);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent   = new Intent(Admin.this, MainActivity.class);
                startActivity(intent);
            }
        });

        try {
//            Amplify.addPlugin(new AWSPinpointAnalyticsPlugin(getApplication()));
            Amplify.addPlugin(new AWSS3StoragePlugin());
            Amplify.addPlugin(new AWSCognitoAuthPlugin());
            Amplify.addPlugin(new AWSDataStorePlugin());
            Amplify.addPlugin(new AWSApiPlugin());
            Amplify.configure(getApplicationContext());
            Log.i("MyAmplifyApp", "Initialized Amplify");
        } catch (AmplifyException error) {
            Log.e("MyAmplifyApp", "Could not initialize Amplify", error);
        }

        Spinner spinnerLanguages=findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter=ArrayAdapter.createFromResource(this, R.array.shop, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        spinnerLanguages.setAdapter(adapter);

        Spinner spinnersize=findViewById(R.id.sizespinner);
        ArrayAdapter<CharSequence> adapter1=ArrayAdapter.createFromResource(this, R.array.size, android.R.layout.simple_spinner_item);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_item);
        spinnersize.setAdapter(adapter1);

        Button add = findViewById(R.id.addproduct);
        add.setOnClickListener(view -> {
            EditText name = findViewById(R.id.nametext);
            EditText description = findViewById(R.id.descriptiontext);
            EditText price = findViewById(R.id.pricetext);
            Spinner category = findViewById(R.id.spinner);
            Spinner size = findViewById(R.id.sizespinner);
            EditText color = findViewById(R.id.colortext);

            String setname = name.getText().toString();
            String setdescription = description.getText().toString();
            String setprice = price.getText().toString();
            String setcategory = category.getSelectedItem().toString();
            String setsize = size.getSelectedItem().toString();
            String setcolor = color.getText().toString();
            Log.i("category", setcategory );

            Intent intent = new Intent(Admin.this,MainActivity.class);
            startActivity(intent);

            Product product = new Product.Builder()
                    .title(setname)
                    .description(setdescription)
                    .price(setprice)
                    .size(setsize)
                    .color(setcolor)
                    .image("")
                    .categoryId(setcategory)
                    .build();

            Amplify.API.mutate(
                    ModelMutation.create(product),
                    response -> Log.i("MyAmplifyApp", "Added Todo with id: " + response.getData().getId()),
                    error -> Log.e("MyAmplifyApp", "Create failed", error)
            );
        });
    }
}