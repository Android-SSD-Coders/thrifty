package com.example.thrifty.auth;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toolbar;

import com.amplifyframework.core.Amplify;
import com.example.thrifty.MainActivity;
import com.example.thrifty.R;

public class ConfirmationPage extends AppCompatActivity {

    public static final String TAG = "ConfirmationCode";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmation_page);

        EditText confirmationText= findViewById(R.id.editTextTextPassword3);
        EditText confirmEmail = findViewById(R.id.editTextTextPersonName7);
        Button btnConfirm = findViewById(R.id.button5);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_baseline_arrow_back_ios_new_24);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent   = new Intent(ConfirmationPage.this, Signup.class);
                startActivity(intent);
            }
        });
        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Amplify.Auth.confirmSignUp(
                        confirmEmail.getText().toString(),
                        confirmationText.getText().toString(),
                        result -> Log.i("AuthQuickstart", result.isSignUpComplete() ? "Confirm signUp succeeded" : "Confirm sign up not complete"),
                        error -> Log.e("AuthQuickstart", error.toString())
                );

                Intent intent = new Intent(ConfirmationPage.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}