package com.example.thrifty;

import java.util.List;

public interface ProductListener {
    void onProductLoadSuccess(List<ProductModel> productModelList);
    void onProductLoadFailed(String message);
}
