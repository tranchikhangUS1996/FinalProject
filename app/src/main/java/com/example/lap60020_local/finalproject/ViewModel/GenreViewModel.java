package com.example.lap60020_local.finalproject.ViewModel;

import com.example.lap60020_local.finalproject.ModelData.Entity.Genre;
import com.example.lap60020_local.finalproject.ModelData.Repository.GenreRepository.IGenreRepository;

import java.util.List;

import io.reactivex.Observable;

public class GenreViewModel {

    private IGenreRepository genreRepository;

    public Observable<List<Genre>> getListGenre() {
        return genreRepository.getListGenre();
    }

}
