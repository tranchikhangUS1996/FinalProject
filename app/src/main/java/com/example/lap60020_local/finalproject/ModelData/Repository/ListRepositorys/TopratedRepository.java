package com.example.lap60020_local.finalproject.ModelData.Repository.ListRepositorys;

import com.example.lap60020_local.finalproject.ModelData.Entity.Movie;
import com.example.lap60020_local.finalproject.ModelData.Entity.MovieResponse;
import com.example.lap60020_local.finalproject.ModelData.Params.PageParams;
import com.example.lap60020_local.finalproject.ModelData.Params.Params;
import com.example.lap60020_local.finalproject.ModelData.retrofit.MovieAPI;

import java.util.List;

import io.reactivex.Observable;

public class TopratedRepository extends AbstractConcreteListRepository {

    private MovieAPI movieAPI;
    private String apiKey;

    public TopratedRepository(MovieAPI movieAPI, String apiKey) {
        super(movieAPI,apiKey);
        this.movieAPI = movieAPI;
        this.apiKey = apiKey;
    }

    @Override
    public Observable<MovieResponse> init(Params params) {
        PageParams pageParams = (PageParams) params;
        return movieAPI.provideTopratedMovies(pageParams.getPage(), apiKey);
    }

    @Override
    public String getName() {
        return "Toprated";
    }
}
