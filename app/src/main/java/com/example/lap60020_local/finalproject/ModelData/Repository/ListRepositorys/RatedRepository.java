package com.example.lap60020_local.finalproject.ModelData.Repository.ListRepositorys;

import com.example.lap60020_local.finalproject.ModelData.Entity.Movie;
import com.example.lap60020_local.finalproject.ModelData.Entity.MovieResponse;
import com.example.lap60020_local.finalproject.ModelData.Params.Params;
import com.example.lap60020_local.finalproject.ModelData.Params.UserMovieParam;
import com.example.lap60020_local.finalproject.ModelData.Params.UserRatingParams;
import com.example.lap60020_local.finalproject.ModelData.retrofit.MovieAPI;

import java.util.List;

import io.reactivex.Observable;

public class RatedRepository extends AbstracChangedListRepository {

    private MovieAPI movieAPI;
    private String apiKey;

    public RatedRepository(MovieAPI movieAPI, String apiKey) {
        super(movieAPI,apiKey);
        this.movieAPI = movieAPI;
        this.apiKey = apiKey;
    }

    @Override
    public Observable<MovieResponse> init(Params params) {
        UserMovieParam userMovieParam = (UserMovieParam) params;
        return movieAPI.provideUserRatedMovies(userMovieParam.getPage(),userMovieParam.getSessionID(),apiKey);
    }

    @Override
    public String getName() {
        return "Rated";
    }
}
