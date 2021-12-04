package com.example.thrifty;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.amplifyframework.core.Amplify;
import com.google.android.material.bottomnavigation.BottomNavigationItemView;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.button.MaterialButton;
import com.squareup.picasso.Picasso;

public class Cart extends AppCompatActivity {
    LinearLayout inc;

    TextView t1, t2, t3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
counter();
        bottomNav();

        ImageView logout = findViewById(R.id.logout);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Amplify.Auth.signOut(
                        () -> Log.i("AuthQuickstart", "Signed out successfully"),
                        error -> Log.e("not complemte", error.toString())
                );
                Intent intent = new Intent(Cart.this, Signin.class);
                startActivity(intent);
            }
        });

        bottomNav();
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_baseline_arrow_back_ios_new_24);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Cart.this, MainActivity.class);
                startActivity(intent);
            }
        });


        Button button1 = findViewById(R.id.button4);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Cart.this, Checkout.class);
                startActivity(intent);
            }
        });




    }
        public void bottomNav(){
            BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);
            bottomNavigationView.setSelectedItemId(R.id.cart);
            BottomNavigationItemView homeNav = findViewById(R.id.homeNav);
            BottomNavigationItemView search = findViewById(R.id.search);
            BottomNavigationItemView cart = findViewById(R.id.cart);
            BottomNavigationItemView wishlist = findViewById(R.id.wishlist);
            BottomNavigationItemView profile = findViewById(R.id.profile);

            search.setOnClickListener(view -> {
                Intent intent = new Intent(getApplicationContext(), Categories.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK| Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            });

            homeNav.setOnClickListener(view -> {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK| Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            });

            profile.setOnClickListener(view -> {
                Intent intent = new Intent(getApplicationContext(), Profile.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK| Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            });

            wishlist.setOnClickListener(view -> {
                Intent intent = new Intent(getApplicationContext(), Favourate.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK| Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            });

            cart.setOnClickListener(view -> {
                Intent intent = new Intent(getApplicationContext(), Cart.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK| Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            });
        }


        public void counter(){
            inc = findViewById(R.id.inc);
            t1 = findViewById(R.id.t1);
            t2 = findViewById(R.id.t2);
            t3 = findViewById(R.id.t3);


            t1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    inc(false);
                }
            });

            t3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    inc(true);
                }
            });
        }

    private void inc(Boolean x){
        int y = Integer.parseInt(t2.getText().toString());
        if(x){
            y++;
            t2.setText(String.valueOf(y));
        }else {
            y--;
            if(y == 0){
                t2.setText("1");
            }else {
                t2.setText(String.valueOf(y));
            }
        }

        Toast.makeText(this, "number of product => "+t2.getText(), Toast.LENGTH_SHORT).show();
        }

    @Override
    protected void onResume() {
        super.onResume();
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(Cart.this);
        String name = sharedPreferences.getString("productname", "");
        TextView name2 = findViewById(R.id.productname);
        name2.setText(name);
        String price = sharedPreferences.getString("productprice", "");
        TextView price2 = findViewById(R.id.price);
        TextView price3 = findViewById(R.id.textView3);
        price2.setText(price);
        price3.setText(price);

        String category = sharedPreferences.getString("category", "");
        TextView category2 = findViewById(R.id.categoryfrag);
        category2.setText(category);

    }
}