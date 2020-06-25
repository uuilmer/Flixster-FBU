package com.example.flixster;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Movie{
    String poster_path;
    String backdrop_path;
    String title;
    String overview;
    public Movie(JSONObject obj) throws JSONException {
        poster_path = obj.getString("poster_path");
        title = obj.getString("title");
        overview = obj.getString("overview");
        backdrop_path = obj.getString("backdrop_path");
    }
    public static List<Movie> fromJsonArray(JSONArray arr){
        List<Movie> movies = new ArrayList<>();
        for(int i = 0; i < arr.length(); i++){
            try {
                movies.add(new Movie(arr.getJSONObject(i)));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return movies;
    }

    public String getBackdrop_path(){
        return String.format("https://image.tmdb.org/t/p/w342/%s", backdrop_path);
    }

    public String getPoster_path() {
        return String.format("https://image.tmdb.org/t/p/w342/%s", poster_path);
    }

    public String getTitle() {
        return title;
    }

    public String getOverview() {
        return overview;
    }
}
