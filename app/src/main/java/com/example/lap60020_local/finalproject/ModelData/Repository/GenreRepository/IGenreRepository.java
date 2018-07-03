package com.example.lap60020_local.finalproject.ModelData.Repository.GenreRepository;

import com.example.lap60020_local.finalproject.ModelData.Entity.Genre;

import java.util.List;

import io.reactivex.Observable;

public interface IGenreRepository {

    Observable<List<Genre>> getListGenre();
}
