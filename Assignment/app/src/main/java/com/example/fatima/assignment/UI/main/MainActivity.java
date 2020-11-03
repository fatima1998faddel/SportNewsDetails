package com.example.fatima.assignment.UI.main;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fatima.assignment.R;
import com.example.fatima.assignment.pojo.Article;
import com.example.fatima.assignment.viewModels.TopHotlineViewModel;

import java.util.List;

public class MainActivity extends AppCompatActivity  {
    NewsRVAdapter adapter;
    TopHotlineViewModel topHotlineViewModel;
    private static final String TAG = "MainActivity";
    public static final String ARTICLE = "Article";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        checkConnection();

        topHotlineViewModel = ViewModelProviders.of(this).get(TopHotlineViewModel.class);
        topHotlineViewModel.getRootTop();

        RecyclerView recyclerView = findViewById(R.id.rv);
        adapter = new NewsRVAdapter();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        topHotlineViewModel.NewsMutableLiveData.observe(this, new Observer<List<Article>>() {
            @Override
            public void onChanged(List<Article> articles) {
                if (articles.size() > 0)
                    adapter.setMyList(articles);
                adapter.notifyDataSetChanged();
                Log.d(TAG, "onChanged: observer");
            }
        });

        //TODO 6- set the OnItemClickListener(the function on step 2) of the adapter
        adapter.setOnItemClickListener(new NewsRVAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Article article) {
                Intent intent = new Intent(MainActivity.this, customActivity.class);
                intent.putExtra("title", article.getTitle());
                intent.putExtra("urlImage", article.getUrlToImage());
                intent.putExtra("auth", article.getAuthor());
                intent.putExtra("date", article.getPublishedAt());
                intent.putExtra("description", article.getDescription());
                startActivity(intent);
            }
        });
    }

    public void checkConnection() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if (null != networkInfo) {
            if (networkInfo.getType() == ConnectivityManager.TYPE_WIFI) {
                Toast.makeText(this, "Wifi Network", Toast.LENGTH_SHORT).show();
            } else if (null != networkInfo) {
                if (networkInfo.getType() == ConnectivityManager.TYPE_MOBILE) ;
                Toast.makeText(this, "Mobile Network ", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "No Network", Toast.LENGTH_SHORT).show();
        }
    }
    
    }








