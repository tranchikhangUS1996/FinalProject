package com.example.lap60020_local.finalproject.ModelData.Repository.ListRepositorys;

import com.example.lap60020_local.finalproject.ModelData.Entity.Movie;
import com.example.lap60020_local.finalproject.ModelData.Params.Params;

import java.util.List;

import io.reactivex.Observable;

public class FavouriteRepository implements IListRepository {
    @Override
    public Observable<List<Movie>> requetData(Params params) {
        return null;
    }

    @Override
    public Observable<List<Movie>> requestMoreData(Params params) {
        return null;
    }

    @Override
    public String getName() {
        return "Favorite";
    }
}
