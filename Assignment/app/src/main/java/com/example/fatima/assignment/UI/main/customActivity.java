package com.example.fatima.assignment.UI.main;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.fatima.assignment.R;
import com.squareup.picasso.Picasso;

public class customActivity extends AppCompatActivity {

    ImageView image;
    TextView title;
    TextView auth;
    TextView date;
    TextView description;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.custom_details);
        image=findViewById(R.id.image_details);
        title=findViewById(R.id.title);
        auth=findViewById(R.id.author);
        date=findViewById(R.id.date);
        description=findViewById(R.id.article);
//get the intent to get the extra string that contain the url
        Intent intent = getIntent();

        String TITLE= intent.getExtras().getString("title");
        String IMG= intent.getExtras().getString("urlImage");
        String AUTHOR = intent.getExtras().getString("auth");
        String DATE  = intent.getExtras().getString("date");
        String DESCRIPTION   = intent.getExtras().getString("description");

        Picasso.get().load(IMG).placeholder(R.drawable.ic_newsplaceholder).into(image);
        title.setText(TITLE);
        auth.setText(AUTHOR);
        date.setText(DATE);
        description.setText(DESCRIPTION);
    }
}