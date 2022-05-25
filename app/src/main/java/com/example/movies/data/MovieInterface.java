package com.example.movies.data;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface MovieInterface {

    @GET("movies")
    Call<List<MovieData>> getData();
}
