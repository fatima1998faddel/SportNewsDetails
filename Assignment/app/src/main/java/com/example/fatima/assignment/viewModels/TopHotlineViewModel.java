package com.example.fatima.assignment.viewModels;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.fatima.assignment.data.RetrofitClient;
import com.example.fatima.assignment.pojo.Article;
import com.example.fatima.assignment.pojo.Root;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TopHotlineViewModel extends ViewModel {
    public static final String TAG = "ArViewModel";


   public MutableLiveData<List<Article>> NewsMutableLiveData=new MutableLiveData<>();

    List<Article> list = new ArrayList<>();

    public void getRootTop(){
        RetrofitClient.getINSTANCE().getRootTop("sports","gb").enqueue(new Callback<Root>() {
            @Override
            public void onResponse(Call<Root> call, Response<Root> response) {

                Log.d(TAG,"onResponse ");
                List<Article> articles = response.body().articles;

                if(articles.size() > 0){
                    NewsMutableLiveData.setValue(articles);
                    list = articles;
                }

                Log.d(TAG,"Articles List Size "+articles.size());
            }

            @Override
            public void onFailure(Call<Root> call, Throwable t) {
                Log.d(TAG,t.getMessage());
;            }
        });
    }



}
