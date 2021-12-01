package com.example.thrifty.cartapi;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Myretrofit {

    private static final String BASE_URL="https://uniqueandrocode.000webhostapp.com/hiren/androidtutorial/mycart/";
    private static Myretrofit myClient;
    private Retrofit retrofit;

    private Myretrofit(){

        retrofit=new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
    }
    public static synchronized Myretrofit getInstance(){
        if (myClient==null){
            myClient=new Myretrofit();
        }
        return myClient;
    }
    public CartApi getMyApi(){
        return retrofit.create(CartApi.class);
    }

}