package com.example.lap60020_local.finalproject.ModelData.Repository.ListRepositorys;

import com.example.lap60020_local.finalproject.ModelData.Entity.MovieResponse;
import com.example.lap60020_local.finalproject.ModelData.Params.Params;
import com.example.lap60020_local.finalproject.ModelData.Params.RecommendParams;
import com.example.lap60020_local.finalproject.ModelData.retrofit.MovieAPI;

import io.reactivex.Observable;

public class RecommendRepository extends AbstracChangedListRepository {

    private MovieAPI movieAPI;
    private String apiKey;

    public RecommendRepository(MovieAPI movieAPI, String apiKey) {
        super(movieAPI, apiKey);
        this.movieAPI = movieAPI;
        this.apiKey = apiKey;
    }

    @Override
    public Observable<MovieResponse> init(Params params) {
        RecommendParams recommendParams = (RecommendParams) params;
        return movieAPI.provideRecommendedMovies(recommendParams.getId(), recommendParams.getPage(),apiKey);
    }

    @Override
    public String getName() {
        return "Recommend";
    }
}
