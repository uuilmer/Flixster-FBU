package com.example.flixster;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.RatingBar;
import android.widget.TextView;

public class InfoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        Intent i = getIntent();
        String title = i.getStringExtra("title");
        String release_date = i.getStringExtra("release_date");
        float rating = i.getFloatExtra("rating", 0);
        String overview = i.getStringExtra("overview");

        TextView tv_title = findViewById(R.id.info_title);
        TextView tv_release_date = findViewById(R.id.info_release_date);
        TextView tv_overview = findViewById(R.id.info_overview);
        RatingBar rb_rating = findViewById(R.id.ratingBar);

        tv_title.setText(title);
        tv_release_date.setText("Released " + release_date);
        rb_rating.setRating(rating);
        tv_overview.setText(overview);
    }
}