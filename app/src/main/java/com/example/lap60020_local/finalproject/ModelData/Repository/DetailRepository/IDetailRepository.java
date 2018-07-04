package com.example.lap60020_local.finalproject.ModelData.Repository.DetailRepository;

import com.example.lap60020_local.finalproject.ModelData.Entity.Movie;

import io.reactivex.Observable;

public interface IDetailRepository {
    Observable<Movie> getData(int id);
}
