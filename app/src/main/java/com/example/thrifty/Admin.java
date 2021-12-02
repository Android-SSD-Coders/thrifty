package com.example.thrifty;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toolbar;

import com.amplifyframework.AmplifyException;
import com.amplifyframework.api.aws.AWSApiPlugin;
import com.amplifyframework.api.graphql.model.ModelMutation;
import com.amplifyframework.auth.cognito.AWSCognitoAuthPlugin;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.datastore.AWSDataStorePlugin;
import com.amplifyframework.datastore.generated.model.Product;
import com.amplifyframework.storage.s3.AWSS3StoragePlugin;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Admin extends AppCompatActivity {
    String imageName = "";
    public Uri uri;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_baseline_arrow_back_ios_new_24);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent   = new Intent(Admin.this, Profile.class);
                startActivity(intent);
            }
        });
        try {
            Amplify.addPlugin(new AWSS3StoragePlugin());
            Amplify.addPlugin(new AWSCognitoAuthPlugin());
            Amplify.addPlugin(new AWSDataStorePlugin());
            Amplify.addPlugin(new AWSApiPlugin());
            Amplify.configure(getApplicationContext());
            Log.i("MyAmplifyApp", "Initialized Amplify");
        } catch (AmplifyException error) {
            Log.e("MyAmplifyApp", "Could not initialize Amplify", error);
        }


        Button uploadImg = findViewById(R.id.upload);
        uploadImg.setOnClickListener(view -> {
            fileChoose();
            uploadInputStream();

        });


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
            SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
            String imageURl = sharedPreferences.getString("FileUrlForReal", "no files");
            Intent intent = new Intent(Admin.this,MainActivity.class);
            startActivity(intent);



            Product product = new Product.Builder()
                    .title(setname)
                    .description(setdescription)
                    .price(setprice)
                    .size(setsize)
                    .color(setcolor)
                    .image(imageURl)
                    .categoryId(setcategory)
                    .build();

            Amplify.API.mutate(
                    ModelMutation.create(product),
                    response -> Log.i("MyAmplifyApp", "Added Todo with id: " + response.getData().getId()),
                    error -> Log.e("MyAmplifyApp", "Create failed", error)
            );
        });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        @SuppressLint("SimpleDateFormat") SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");
        String fileName = sdf.format(new Date());
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        File uploadFile = new File(getApplicationContext().getFilesDir(), fileName);
        try {
            InputStream exampleInputStream = getContentResolver().openInputStream(data.getData());
            OutputStream outputStream = new FileOutputStream(uploadFile);
            imageName = data.getData().toString();
            byte[] buff = new byte[1024];
            int length;
            while ((length = exampleInputStream.read(buff)) > 0) {
                outputStream.write(buff, 0, length);
            }
            exampleInputStream.close();
            outputStream.close();
            Amplify.Storage.uploadFile(
                    fileName + ".jpg",
                    uploadFile,
                    result -> {
                        Log.i("MyAmplifyAppUpload", "Successfully uploaded: " + result.getKey());
                        Amplify.Storage.getUrl(result.getKey(), urlResult -> {
                            sharedPreferences.edit().putString("FileUrlForReal", urlResult.getUrl().toString()).apply();
                        }, urlError -> {
                            Log.e("TAG", "onActivityResult: Error please dont be mad");
                        });
                    },
                    storageFailure -> Log.e("MyAmplifyApp", "Upload failed", storageFailure)
            );
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void uploadInputStream() {
        if (uri != null) {
            try {
                InputStream exampleInputStream = getContentResolver().openInputStream(uri);

                Amplify.Storage.uploadInputStream(
                        imageName,
                        exampleInputStream,
                        result -> Log.i("MyAmplifyApp", "Successfully uploaded: " + result.getKey()),
                        storageFailure -> Log.e("MyAmplifyApp", "Upload failed", storageFailure)
                );
            } catch (FileNotFoundException error) {
                Log.e("MyAmplifyApp", "Could not find file to open for input stream.", error);
            }
        }
    }


    public void fileChoose() {
        Intent fileChoose = new Intent(Intent.ACTION_GET_CONTENT);
        fileChoose.setType("*/*");
        fileChoose = Intent.createChooser(fileChoose, "choose file");
        startActivityForResult(fileChoose, 1234);
    }

}