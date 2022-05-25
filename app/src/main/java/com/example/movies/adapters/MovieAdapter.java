package com.example.movies.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.movies.R;
import com.example.movies.data.MovieData;

import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder> {

    private List<MovieData> movies;

    public MovieAdapter(List<MovieData> movies) {
        this.movies = movies;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View item = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_adapter, parent, false);
        return new ViewHolder(item);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        MovieData movie = movies.get(position);
        Context context = holder.itemView.getContext();

        // Glide para recuperar imagens
        Glide.with(context).load(movie.getPoster_url()).into(holder.poster_url);
        holder.genres.setText(movie.getGenres());
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    public class ViewHolder extends  RecyclerView.ViewHolder{

        ImageView poster_url;
        TextView genres;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            poster_url = itemView.findViewById(R.id.imageMovie);
            genres = itemView.findViewById(R.id.txtGenre);
        }
    }
}
