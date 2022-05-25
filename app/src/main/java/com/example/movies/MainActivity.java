package com.example.movies;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.movies.adapters.MovieAdapter;
import com.example.movies.data.MovieData;
import com.example.movies.data.MovieInterface;
import com.example.movies.databinding.ActivityMainBinding;
import com.google.android.material.snackbar.Snackbar;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private MovieInterface movieInterface;
    private RecyclerView.Adapter movieAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        initRetrofit();
        setupMovieList();
    }

    private void initRetrofit() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://desafio-mobile.nyc3.digitaloceanspaces.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        movieInterface = retrofit.create(MovieInterface.class);
    }

    private void setupMovieList() {
        binding.rvMovie.setLayoutManager(new LinearLayoutManager(this));

        // retornando os filmes da API
        movieInterface.getData().enqueue(new Callback<List<MovieData>>() {
            @Override
            public void onResponse(Call<List<MovieData>> call, Response<List<MovieData>> response) {
                if(response.isSuccessful()){
                    List<MovieData> movies = response.body();
                    Log.i("Sucesso", "Itens: " + movies.size());
                } else {
                    messageError();
                }
            }

            @Override
            public void onFailure(Call<List<MovieData>> call, Throwable t) {
                messageError();
            }
        });
    }

    private void messageError() {
        Snackbar.make(binding.getRoot(), "Erro de conexão!", Snackbar.LENGTH_LONG).show();
    }
}