package com.example.flixster;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder> {

    List<Movie> movies;
    Context context;
    OnClickListener ocl;

    public interface OnClickListener{
        public void OnClick(Movie movie);
    }

    public MovieAdapter(Context context, List<Movie> movies, OnClickListener ocl) {
        this.movies = movies;
        this.context = context;
        this.ocl = ocl;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View to_make = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_container, parent, false);
        return new ViewHolder(to_make);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(movies.get(position));
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        TextView title;
        TextView overview;
        ImageView poster;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
            overview = itemView.findViewById(R.id.overview);
            poster = itemView.findViewById(R.id.poster);
        }
        public void bind(final Movie movie){
            title.setText(movie.getTitle());

            title.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ocl.OnClick(movie);
                }
            });
            overview.setText(movie.getOverview());
            String img = movie.getPoster_path();
            if(context.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE)
                img = movie.getBackdrop_path();
            Glide.with(context).load(img).into(poster);
        }
    }
}
