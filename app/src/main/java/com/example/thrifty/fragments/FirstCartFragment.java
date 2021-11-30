package com.example.thrifty.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.amplifyframework.datastore.generated.model.Product;
import com.example.thrifty.CartActivity;
import com.example.thrifty.R;
import com.example.thrifty.adapters.CartAdapter;

public class FirstCartFragment extends Fragment implements CartAdapter.DeleteItem {

    private RecyclerView recyclerView;
    private TextView  txtTotalPrice, txtNoItems;
    private Button nextButton;
    private RelativeLayout relativeLayout;

    private CartAdapter cartAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view  = inflater.inflate(R.layout.first_cart_fragment, container, false);
        initViews(view);
        cartAdapter = new CartAdapter(this, getActivity());
        recyclerView.setAdapter(cartAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        return view;
    }

    private void initViews(View view){
        recyclerView = view.findViewById(R.id.recyclerView);
        txtTotalPrice = view.findViewById(R.id.txtTotalPrice);
        txtNoItems = view.findViewById(R.id.txtNoItems);
        nextButton = view.findViewById(R.id.nextButton);
        relativeLayout = view.findViewById(R.id.itemsRelLayout);
    }

    @Override
    public void onDeleteItem(Product product) {

    }
}
