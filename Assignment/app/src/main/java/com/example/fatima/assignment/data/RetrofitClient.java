package com.example.fatima.assignment.data;

import android.util.Log;

import com.example.fatima.assignment.pojo.Root;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    private static final String BASE_URL="https://newsapi.org/v2/";
    private EndPointsInterface arInterface;
    private static RetrofitClient INSTANCE;
    public static final String TAG = "Client";
    public RetrofitClient() {

        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        arInterface= retrofit.create(EndPointsInterface.class);
    }

    public static RetrofitClient getINSTANCE() {
        if(null == INSTANCE) {
            INSTANCE = new RetrofitClient();
        }
        return INSTANCE;
    }

    public Call<Root> getRootTop(String category,String country) {
        Log.d(TAG,"getRootTop()");
        return arInterface.rootTop("340abb56db1d4ffb95062186a3cdc552",category,country);
    }
}
