package com.example.lap60020_local.finalproject.ModelData.Repository.UserInteractRepository;

import com.example.lap60020_local.finalproject.ModelData.Entity.RateResponse;
import com.example.lap60020_local.finalproject.ModelData.Entity.Rated;
import com.example.lap60020_local.finalproject.ModelData.Params.RatedParams;
import com.example.lap60020_local.finalproject.ModelData.Params.UserRatingParams;
import com.example.lap60020_local.finalproject.ModelData.retrofit.MovieAPI;

import io.reactivex.Observable;

public class RateRepository {

    MovieAPI movieAPI;
    String apiKey;

    public RateRepository(MovieAPI movieAPI, String apiKey) {
        this.movieAPI = movieAPI;
        this.apiKey = apiKey;
    }

    public Observable<RateResponse> rate(UserRatingParams params) {
        return movieAPI.rateMovie(params.getId(),apiKey, new Rated(params.getRate()));
    }

}
