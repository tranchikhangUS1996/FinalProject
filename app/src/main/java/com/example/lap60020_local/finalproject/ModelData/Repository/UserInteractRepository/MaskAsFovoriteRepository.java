package com.example.lap60020_local.finalproject.ModelData.Repository.UserInteractRepository;

import com.example.lap60020_local.finalproject.ModelData.Entity.AddToWatchListResponse;
import com.example.lap60020_local.finalproject.ModelData.Entity.FavouriteBody;
import com.example.lap60020_local.finalproject.ModelData.Entity.FavouriteResponse;
import com.example.lap60020_local.finalproject.ModelData.Params.UserInteractParams;
import com.example.lap60020_local.finalproject.ModelData.retrofit.MovieAPI;

import io.reactivex.Observable;

public class MaskAsFovoriteRepository {

    private MovieAPI movieAPI;
    private String apiKey;

    public MaskAsFovoriteRepository(MovieAPI movieAPI, String apiKey) {
        this.movieAPI = movieAPI;
        this.apiKey = apiKey;
    }

    public Observable<FavouriteResponse> mask(UserInteractParams userInteractParams) {
        return movieAPI.maskAsFavourite(apiKey, userInteractParams.getSessionId(), new FavouriteBody(userInteractParams.getId(),userInteractParams.getValue()));
    }
}
