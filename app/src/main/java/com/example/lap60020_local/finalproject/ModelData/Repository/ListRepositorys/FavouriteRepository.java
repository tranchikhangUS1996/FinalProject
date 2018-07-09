package com.example.lap60020_local.finalproject.ModelData.Repository.ListRepositorys;

import com.example.lap60020_local.finalproject.ModelData.Entity.Movie;
import com.example.lap60020_local.finalproject.ModelData.Entity.MovieResponse;
import com.example.lap60020_local.finalproject.ModelData.Params.Params;
import com.example.lap60020_local.finalproject.ModelData.Params.UserMovieParam;
import com.example.lap60020_local.finalproject.ModelData.retrofit.MovieAPI;

import java.util.List;

import io.reactivex.Observable;

public class FavouriteRepository extends AbstracChangedListRepository {

    MovieAPI movieAPI;
    String apiKey;

    public FavouriteRepository(MovieAPI movieAPI, String apiKey) {
        super(movieAPI,apiKey);
        this.movieAPI = movieAPI;
        this.apiKey = apiKey;
    }

    @Override
    public Observable<MovieResponse> init(Params params) {
        UserMovieParam userMovieParam = (UserMovieParam) params;
        return movieAPI.provideUserFavouriteMovies(apiKey, userMovieParam.getSessionID(), userMovieParam.getPage());
    }

    @Override
    public String getName() {
        return "Favorite";
    }
}
