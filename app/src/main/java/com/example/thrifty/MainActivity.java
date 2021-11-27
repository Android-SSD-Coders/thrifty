package com.example.thrifty;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;



import android.util.Log;
import android.widget.EditText;

import com.amplifyframework.AmplifyException;
import com.amplifyframework.analytics.pinpoint.AWSPinpointAnalyticsPlugin;
import com.amplifyframework.auth.cognito.AWSCognitoAuthPlugin;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.datastore.AWSDataStorePlugin;
import com.amplifyframework.storage.s3.AWSS3StoragePlugin;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {
            Amplify.addPlugin(new AWSPinpointAnalyticsPlugin(getApplication()));
            Amplify.addPlugin(new AWSS3StoragePlugin());
            Amplify.addPlugin(new AWSCognitoAuthPlugin());
            Amplify.addPlugin(new AWSDataStorePlugin());
            Amplify.configure(getApplicationContext());
            Log.i("MyAmplifyApp", "Initialized Amplify");
        } catch (AmplifyException error) {
            Log.e("MyAmplifyApp", "Could not initialize Amplify", error);
        }
findViewById(R.id.button3).setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        Intent intent = new Intent(MainActivity.this, Signup.class);
        startActivity(intent);
    }
});

//        playButton = (Button) findViewById(R.id.close);
//        playButton.setVisibility(View.VISIBLE);
//        playButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                //when play is clicked show stop button and hide play button
//                playButton.setVisibility(View.GONE);
//                stopButton.setVisibility(View.VISIBLE);
//            }
//        });




    }
}