package com.example.lap60020_local.finalproject.ModelData.Repository.ListRepositorys;

import com.example.lap60020_local.finalproject.ModelData.Entity.MovieResponse;
import com.example.lap60020_local.finalproject.ModelData.Params.PageParams;
import com.example.lap60020_local.finalproject.ModelData.Params.Params;
import com.example.lap60020_local.finalproject.ModelData.retrofit.MovieAPI;

import io.reactivex.Observable;

public class PopularRepository extends AbstractConcreteListRepository {

    private MovieAPI movieAPI;
    private String apiKey;

    public PopularRepository(MovieAPI movieAPI, String apiKey) {
        super(movieAPI, apiKey);
        this.movieAPI = movieAPI;
        this.apiKey = apiKey;
    }

    @Override
    public Observable<MovieResponse> init(Params params) {
        PageParams pageParams = (PageParams) params;
        return movieAPI.providePopularMovies(pageParams.getPage(), apiKey);
    }

    @Override
    public String getName() {
        return "Popular";
    }
}
