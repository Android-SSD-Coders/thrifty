package com.example.thrifty;

import androidx.appcompat.app.AppCompatActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.widget.TextView;

import com.example.thrifty.adapter.CartProductAdapter;
import com.example.thrifty.roomdatabase.Cart;

import java.util.List;

public class MyCartActivity extends AppCompatActivity {
    RecyclerView rv;
    CartProductAdapter cartProductAdapter;
    List<Cart>carts;
    TextView tvcount;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_cart);
        rv=(RecyclerView)findViewById(R.id.res);
        rv.setHasFixedSize(true);
        rv.setLayoutManager(new LinearLayoutManager(this));
        getCartData();
        tvcount=(TextView)findViewById(R.id.txtcartcount);
        int count=cartProductAdapter.getItemCount();


        updatacartcount();
        LocalBroadcastManager.getInstance(this).registerReceiver(mMasage,new IntentFilter("mymsg"));

    }

    private void updatacartcount() {
        int count=cartProductAdapter.getItemCount();

        if (count==0){
            tvcount.setText("Your Cart is Empty");
        }else {
            tvcount.setText(String.valueOf(count));
        }



    }

    private void getCartData() {
        carts=CartActivity.myDatabase.cartDao().getData();
        cartProductAdapter=new CartProductAdapter(carts,this);
        rv.setAdapter(cartProductAdapter);

    }

    @Override
    protected void onResume() {
        super.onResume();
        updatacartcount();
    }

    public BroadcastReceiver mMasage=new BroadcastReceiver(){

        @Override
        public void onReceive(Context context, Intent intent) {
            String cartcount=intent.getStringExtra("cartcount");
            if (carts.size()  ==0){
                tvcount.setText("Your Cart is Empty");
            }else {
                tvcount.setText(String.valueOf(carts.size()));
            }
        }
    };
}
