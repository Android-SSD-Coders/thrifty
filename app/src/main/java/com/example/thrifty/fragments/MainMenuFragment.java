package com.example.thrifty.fragments;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.thrifty.MainActivity;
import com.example.thrifty.R;
import com.example.thrifty.SearchActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;


public class MainMenuFragment extends Fragment {
 BottomNavigationView bottomNavigationView;
    public MainMenuFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main_menu, container, false);
        // Inflate the layout for this fragment
        initView(view);
        bottomNav();
        return view;

    }
    private void initView(View view){

        bottomNavigationView = view.findViewById(R.id.bottomNavigationView);
    }
    @SuppressLint("NonConstantResourceId")
    public void bottomNav(){
        bottomNavigationView.setSelectedItemId(R.id.search);
        bottomNavigationView.setOnItemSelectedListener(item -> {
            switch (item.getItemId()){
                case R.id.homeNav:
                    Toast.makeText(getActivity(),"Home Clicked", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(getContext(), MainActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK| Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                    break;

                case R.id.search:
                    Toast.makeText(getContext(),"Search Clicked", Toast.LENGTH_LONG).show();
                    Intent intent1 = new Intent(getContext(), SearchActivity.class);
                    intent1.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK| Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent1);
                    break;

                case R.id.cart:
                    Toast.makeText(getContext(),"Cart Clicked", Toast.LENGTH_LONG).show();
//                    Intent intent2 = new Intent(getContext(), Cart.class);
//                    intent2.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK| Intent.FLAG_ACTIVITY_NEW_TASK);
//                    startActivity(intent2);
                    break;
                case R.id.wishlist:
                    Toast.makeText(getContext(),"Wishlist Clicked", Toast.LENGTH_LONG).show();
//                    Intent intent3 = new Intent(getContext(), Wishlist.class);
//                    intent3.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK| Intent.FLAG_ACTIVITY_NEW_TASK);
//                    startActivity(intent3);
                    break;

                case R.id.profile:
                    Toast.makeText(getContext(),"Profile Clicked", Toast.LENGTH_LONG).show();
//                    Intent intent4 = new Intent(getContext(), Profile.class);
//                    intent4.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK| Intent.FLAG_ACTIVITY_NEW_TASK);
//                    startActivity(intent4);
                    break;
                default:
                    Toast.makeText(getContext(),"None", Toast.LENGTH_LONG).show();
                    break;
            }
            return false;
        });
//        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
//            switch (item.getItemId()){
//                case R.id.homeNav:
//                    Toast.makeText(getContext(),"Home Clicked", Toast.LENGTH_LONG).show();
//                    break;
//
//                case R.id.search:
//                    Toast.makeText(getContext(),"Search Clicked", Toast.LENGTH_LONG).show();
//                    break;
//
//                case R.id.cart:
//                    Toast.makeText(getContext(),"Cart Clicked", Toast.LENGTH_LONG).show();
//                    break;
//                case R.id.wishlist:
//                    Toast.makeText(getContext(),"Wishlist Clicked", Toast.LENGTH_LONG).show();
//                    break;
//
//                case R.id.profile:
//                    Toast.makeText(getContext(),"Profile Clicked", Toast.LENGTH_LONG).show();
//                    break;
//                default:
//                    Toast.makeText(getContext(),"None", Toast.LENGTH_LONG).show();
//                    break;
//            }
//
//            return false;
//        });
    }
}