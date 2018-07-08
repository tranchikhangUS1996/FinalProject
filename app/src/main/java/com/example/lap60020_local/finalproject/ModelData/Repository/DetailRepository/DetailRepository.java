package com.example.lap60020_local.finalproject.ModelData.Repository.DetailRepository;

import com.example.lap60020_local.finalproject.ModelData.Entity.Movie;
import com.example.lap60020_local.finalproject.ModelData.retrofit.MovieAPI;

import io.reactivex.Observable;

public class DetailRepository implements  IDetailRepository{

    private MovieAPI movieAPI;
    private final String apiKey;

    public DetailRepository(MovieAPI movieAPI, String apiKey) {
        this.movieAPI = movieAPI;
        this.apiKey = apiKey;
    }

    @Override
    public Observable<Movie> getData(int id) {
        return movieAPI.getMovieDetails(id,apiKey);
    }
}
