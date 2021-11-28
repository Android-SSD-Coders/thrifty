package com.example.thrifty;

import java.util.List;

public interface CartListener {
    void onCartLoadSuccess(List<CartModel> cartModelList);
    void onCartLoadFailed(String message);
}
