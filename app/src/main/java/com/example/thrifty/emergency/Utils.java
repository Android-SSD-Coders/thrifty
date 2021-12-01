package com.example.thrifty.emergency;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class Utils {

    private static int ID = 0;
    private static  final String ALL_ITEMS_KEY = "all_items";
    private static  final String DB_NAME = "fake_DB";
    private static Gson gson = new Gson();
    private static Type productListType = new TypeToken<ArrayList<Products>>(){}.getType();

    public static void initSharedPreferences(Context context){
        SharedPreferences sharedPreferences = context.getSharedPreferences(DB_NAME,context.MODE_PRIVATE);
        ArrayList<Products> products = gson.fromJson(sharedPreferences.getString(ALL_ITEMS_KEY,null),productListType);
        if (null == products){
            initItems(context);
        }
    }

    private static void initItems(Context context) {
        ArrayList<Products> allProducts = new ArrayList<>();
        Products shorts = new Products("Adidas Shorts",
                "20$",
                "This is good for football matches",
                "https://assets.adidas.com/images/h_840,f_auto,q_auto:sensitive,fl_lossy,c_fill,g_auto/6980a04f4ad24115bbbaa88d01220ab4_9366/3-Stripes_Shorts_Black_DH5798_21_model.jpg");

        allProducts.add(shorts);

        SharedPreferences sharedPreferences = context.getSharedPreferences(DB_NAME,context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(ALL_ITEMS_KEY, gson.toJson(allProducts));
        editor.commit();
    }

    public static ArrayList<Products> getAllProducts(Context context){
        SharedPreferences sharedPreferences = context.getSharedPreferences(DB_NAME,context.MODE_PRIVATE);
        ArrayList<Products> products = gson.fromJson(sharedPreferences.getString(ALL_ITEMS_KEY,null),productListType);
        return products;
    }

    public static int getID() {
        ID++;
        return ID;
    }
}
