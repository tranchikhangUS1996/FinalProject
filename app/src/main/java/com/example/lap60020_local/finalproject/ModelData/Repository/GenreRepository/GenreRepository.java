package com.example.lap60020_local.finalproject.ModelData.Repository.GenreRepository;

import com.example.lap60020_local.finalproject.ModelData.Entity.Genre;
import com.example.lap60020_local.finalproject.ModelData.Entity.GenreResponse;
import com.example.lap60020_local.finalproject.ModelData.retrofit.MovieAPI;

import java.util.List;

import io.reactivex.Observable;

public class GenreRepository implements IGenreRepository {

    private MovieAPI movieAPI;
    private final String apiKey;

    public GenreRepository(MovieAPI movieAPI, String apiKey) {
        this.movieAPI = movieAPI;
        this.apiKey = apiKey;
    }

    @Override
    public Observable<List<Genre>> getListGenre() {
        return movieAPI.provideGenres(apiKey).map(genreResponse -> {
            List<Genre> list = genreResponse.getGenres();
            return list;
        });
    }
}
