package com.example.lap60020_local.finalproject.ModelData.Repository.UserInteractRepository;

import com.example.lap60020_local.finalproject.ModelData.Entity.FavouriteResponse;
import com.example.lap60020_local.finalproject.ModelData.Entity.WatchlistBody;
import com.example.lap60020_local.finalproject.ModelData.Params.UserInteractParams;
import com.example.lap60020_local.finalproject.ModelData.retrofit.MovieAPI;

import io.reactivex.Observable;

public class AddToWatchListRepository {

    private MovieAPI movieAPI;
    private String apiKey;

    public AddToWatchListRepository(MovieAPI movieAPI, String apiKey) {
        this.movieAPI = movieAPI;
        this.apiKey = apiKey;
    }

    public Observable<FavouriteResponse> add(UserInteractParams params) {

        return movieAPI.addToWatchlist(apiKey, params.getSessionId(), new WatchlistBody(params.getId(),params.getValue()));
    }

}
