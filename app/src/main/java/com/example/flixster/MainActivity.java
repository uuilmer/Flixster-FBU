package com.example.flixster;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.codepath.asynchttpclient.AsyncHttpClient;
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Headers;


public class MainActivity extends AppCompatActivity {

    public static final String NOW_PLAYING_URL = "https://api.themoviedb.org/3/movie/now_playing?api_key=a07e22bc18f5cb106bfe4cc1f83ad8ed";
    List<Movie> movies;
    RecyclerView rvMovies;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rvMovies = findViewById(R.id.main_rv);
        movies = new ArrayList<>();
        final MovieAdapter mvAdapter = new MovieAdapter(getApplicationContext(), movies);
        rvMovies.setAdapter(mvAdapter);
        rvMovies.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        AsyncHttpClient client = new AsyncHttpClient();
        client.get(NOW_PLAYING_URL, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Headers headers, JSON json) {
                JSONObject out = json.jsonObject;
                try {
                    JSONArray results = out.getJSONArray("results");
                    System.out.println(results.toString());
                    movies.addAll(Movie.fromJsonArray(results));
                    mvAdapter.notifyDataSetChanged();

                } catch (JSONException e) {
                    e.printStackTrace();
                    System.out.println("Error retrieving results from Movie db API");
                }

            }

            @Override
            public void onFailure(int statusCode, Headers headers, String response, Throwable throwable) {
                System.out.println("bad");
            }
        });
    }
}