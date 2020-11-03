package com.example.fatima.assignment.data;

import com.example.fatima.assignment.pojo.Root;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface EndPointsInterface {
    @GET("top-headlines")
    Call<Root> rootTop(@Query("apiKey") String API_Key,@Query("category")String category, @Query("country")String country);
}
