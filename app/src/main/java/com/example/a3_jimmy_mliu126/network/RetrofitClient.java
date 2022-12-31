package com.example.a3_jimmy_mliu126.network;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

// manages API interface
// concrete interface implementation
// needs to be a Singleton
public class RetrofitClient {

    // instance of api
    private API api;

    // ctor
    private RetrofitClient() {
        // concrete instance of api interface
        Gson gson = new GsonBuilder().create();

        // instance of rfit
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(api.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        // create concrete instance
        this.api = retrofit.create(API.class);
    }

    public API getAPI() {
        return this.api;
    }

    // singleton
    private static RetrofitClient instance = null;
    public static synchronized RetrofitClient getInstance() {
        if (instance == null) {
            instance = new RetrofitClient();
        }
        return instance;
    }
}
