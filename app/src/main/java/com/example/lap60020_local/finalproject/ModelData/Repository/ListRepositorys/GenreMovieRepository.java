package com.example.lap60020_local.finalproject.ModelData.Repository.ListRepositorys;

import com.example.lap60020_local.finalproject.ModelData.Entity.MovieResponse;
import com.example.lap60020_local.finalproject.ModelData.Params.GenreMovieParams;
import com.example.lap60020_local.finalproject.ModelData.Params.Params;
import com.example.lap60020_local.finalproject.ModelData.retrofit.MovieAPI;

import io.reactivex.Observable;

public class GenreMovieRepository extends AbstracChangedListRepository {

    private MovieAPI movieAPI;
    private String apiKey;

    public GenreMovieRepository(MovieAPI movieAPI, String apiKey) {
        super(movieAPI, apiKey);
        this.movieAPI = movieAPI;
        this.apiKey = apiKey;
    }

    @Override
    public Observable<MovieResponse> init(Params params) {
        GenreMovieParams genreMovieParams = (GenreMovieParams) params;
        return movieAPI.provideMovieByGenre(genreMovieParams.getPage(), genreMovieParams.getId(),apiKey);
    }

    @Override
    public String getName() {
        return "Genre";
    }
}
