package com.example.lap60020_local.finalproject.ModelData.Repository.ListRepositorys;

import com.example.lap60020_local.finalproject.ModelData.Entity.MovieResponse;
import com.example.lap60020_local.finalproject.ModelData.Params.Params;
import com.example.lap60020_local.finalproject.ModelData.Params.SearchParams;
import com.example.lap60020_local.finalproject.ModelData.retrofit.MovieAPI;

import io.reactivex.Observable;

public class SearchRepository extends AbstracChangedListRepository {

    private MovieAPI movieAPI;
    private String apiKey;

    public SearchRepository(MovieAPI movieAPI, String apiKey) {
        super(movieAPI, apiKey);
        this.movieAPI = movieAPI;
        this.apiKey = apiKey;
    }

    @Override
    public Observable<MovieResponse> init(Params params) {
        SearchParams searchParams = (SearchParams) params;
        return movieAPI.getSearchMovies(searchParams.getText(), searchParams.getPage(),apiKey);
    }

    @Override
    public String getName() {
        return "Search";
    }
}
