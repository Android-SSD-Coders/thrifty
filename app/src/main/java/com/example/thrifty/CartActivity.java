package com.example.thrifty;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.thrifty.adapter.MyProductAdapter;
import com.example.thrifty.cartapi.Myretrofit;
import com.example.thrifty.model.MyProductData;
import com.example.thrifty.roomdatabase.MyDatabase;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CartActivity extends AppCompatActivity {

    RecyclerView rv;
    ImageView cartbtn;
    TextView cartcount;
    MyProductAdapter myProductAdapter;
    public static MyDatabase myDatabase;
    List<MyProductData>myProductData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cartactivity_main);
        rv=(RecyclerView)findViewById(R.id.res);
        rv.setHasFixedSize(true);
        rv.setLayoutManager(new GridLayoutManager(this,2));
        cartbtn=(ImageView)findViewById(R.id.cart_btn);
        cartcount=(TextView)findViewById(R.id.cartcount);

        //updatacartcount();
        cartbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CartActivity.this, MyCartActivity.class));
            }
        });

        myDatabase= Room.databaseBuilder(getApplicationContext(),MyDatabase.class,"My_Cart").allowMainThreadQueries().build();

        getdata();


    }

    private void updatacartcount() {
        if (cartcount==null)return;
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (myDatabase.cartDao().countCart()==0)
                    cartcount.setVisibility(View.INVISIBLE);
                else {
                    cartcount.setVisibility(View.VISIBLE);
                    cartcount.setText(String.valueOf(myDatabase.cartDao().countCart()));
                }
            }
        });

    }

    private void getdata() {
        Call<List<MyProductData>>call= Myretrofit.getInstance().getMyApi().getProductData();
        call.enqueue(new Callback<List<MyProductData>>() {
            @Override
            public void onResponse(Call<List<MyProductData>> call, Response<List<MyProductData>> response) {
                myProductData=response.body();
                myProductAdapter=new MyProductAdapter(myProductData,CartActivity.this);
                rv.setAdapter(myProductAdapter);
            }

            @Override
            public void onFailure(Call<List<MyProductData>> call, Throwable t) {

            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        updatacartcount();
    }
}