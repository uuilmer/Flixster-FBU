package com.example.flixster;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.codepath.asynchttpclient.AsyncHttpClient;
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler;
import com.example.flixster.databinding.ActivityInfoBinding;

import org.json.JSONException;
import org.json.JSONObject;

import jp.wasabeef.glide.transformations.RoundedCornersTransformation;
import okhttp3.Headers;

public class InfoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        ActivityInfoBinding binding = ActivityInfoBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        ImageView iv_backdrop = binding.infoBackdrop;

        Intent i = getIntent();
        String title = i.getStringExtra("title");
        String release_date = i.getStringExtra("release_date");
        float rating = i.getFloatExtra("rating", 0);
        String overview = i.getStringExtra("overview");
        String backdrop_path = i.getStringExtra("backdrop_path");

        final String key = i.getStringExtra("key");
        Glide.with(InfoActivity.this)
                .load(backdrop_path)
                .into(iv_backdrop);
        iv_backdrop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent j = new Intent(InfoActivity.this, MovieTrailerActivity.class);
                j.putExtra("key", key);
                startActivity(j);
            }
        });


        TextView tv_title = binding.infoTitle;
        TextView tv_release_date = binding.infoReleaseDate;
        TextView tv_overview = binding.infoOverview;
        RatingBar rb_rating = binding.ratingBar;

        tv_title.setText(title);
        tv_release_date.setText("Released " + release_date);
        rb_rating.setRating(rating);
        tv_overview.setText(overview);
    }
}