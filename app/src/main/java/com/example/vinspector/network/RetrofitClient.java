package com.example.vinspector.network;

import com.example.vinspector.constants.Constants;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

    private static VinApi vinApi;

    public static VinApi getVinApi(){
        if(vinApi == null){
            createVinApi();
        }
        return  vinApi;
    }

    private static void createVinApi(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        vinApi = retrofit.create(VinApi.class);
    }
}
