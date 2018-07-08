package com.example.lap60020_local.finalproject.ModelData.Repository.ListRepositorys;

import com.example.lap60020_local.finalproject.ModelData.Entity.MovieResponse;
import com.example.lap60020_local.finalproject.ModelData.Params.Params;
import com.example.lap60020_local.finalproject.ModelData.Params.SimilarParams;
import com.example.lap60020_local.finalproject.ModelData.retrofit.MovieAPI;

import io.reactivex.Observable;

public class SimilarRepository extends AbstracChangedListRepository {

    private MovieAPI movieAPI;
    private String apiKey;

    public SimilarRepository(MovieAPI movieAPI, String apiKey) {
        super(movieAPI, apiKey);
        this.movieAPI = movieAPI;
        this.apiKey = apiKey;
    }

    @Override
    public Observable<MovieResponse> init(Params params) {
        SimilarParams similarParams = (SimilarParams) params;
        return movieAPI.provideSimilarMovies(similarParams.getId(),similarParams.getPage(),apiKey);
    }

    @Override
    public String getName() {
        return "Similar";
    }
}
