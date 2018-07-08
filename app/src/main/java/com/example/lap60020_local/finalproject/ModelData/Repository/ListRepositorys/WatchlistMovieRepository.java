package com.example.lap60020_local.finalproject.ModelData.Repository.ListRepositorys;

import com.example.lap60020_local.finalproject.ModelData.Entity.Movie;
import com.example.lap60020_local.finalproject.ModelData.Entity.MovieResponse;
import com.example.lap60020_local.finalproject.ModelData.Params.Params;
import com.example.lap60020_local.finalproject.ModelData.retrofit.MovieAPI;

import java.util.List;

import io.reactivex.Observable;

public class WatchlistMovieRepository extends AbstractConcreteListRepository {

    public WatchlistMovieRepository(MovieAPI movieAPI, String apiKey) {
        super(movieAPI, apiKey);
    }

    @Override
    public Observable<MovieResponse> init(Params params) {
        return null;
    }

    @Override
    public String getName() {
        return "WatchList";
    }
}
