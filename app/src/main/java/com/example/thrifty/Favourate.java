package com.example.thrifty;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toolbar;

import com.amplifyframework.AmplifyException;
import com.amplifyframework.api.aws.AWSApiPlugin;
import com.amplifyframework.auth.cognito.AWSCognitoAuthPlugin;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.datastore.AWSDataStorePlugin;
import com.amplifyframework.storage.s3.AWSS3StoragePlugin;
import com.squareup.picasso.Picasso;

public class Favourate extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_view);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_baseline_arrow_back_ios_new_24);
        toolbar.setNavigationOnClickListener(v -> {
            Intent intent   = new Intent(Favourate.this, MainActivity.class);
            startActivity(intent);
        });

        try {
//            Amplify.addPlugin(new AWSApiPlugin());
            Amplify.addPlugin(new AWSS3StoragePlugin());
            Amplify.addPlugin(new AWSCognitoAuthPlugin());
            Amplify.addPlugin(new AWSDataStorePlugin());
            Amplify.addPlugin(new AWSApiPlugin());
            Amplify.configure(getApplicationContext());
            Log.i("MyAmplifyApp", "Initialized Amplify");
        } catch (AmplifyException error) {
            Log.e("MyAmplifyApp", "Could not initialize Amplify", error);
        }

        Intent intent = getIntent();
        String title = intent.getStringExtra("Title");
        String price = intent.getStringExtra("price");
        String category = intent.getStringExtra("category");
        String description = intent.getStringExtra("description");


        TextView textView = findViewById(R.id.txtTitle);
        TextView priceTxt = findViewById(R.id.txtPrice);
        TextView categoryTxt = findViewById(R.id.category);
        TextView descriptionTxt = findViewById(R.id.descriptionText);

        textView.setText(title);
        priceTxt.setText(price);
        categoryTxt.setText(category);
        descriptionTxt.setText(description);

//        String url = intent.getExtras().getString("image");
//        ImageView image = findViewById(R.id.itemImage);
//        Log.i("imagview", url);
//        Picasso.get().load(url).into(image);
    }
}