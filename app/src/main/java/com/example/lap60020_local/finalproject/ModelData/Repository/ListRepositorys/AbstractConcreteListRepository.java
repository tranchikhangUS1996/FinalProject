package com.example.lap60020_local.finalproject.ModelData.Repository.ListRepositorys;

import android.util.Log;

import com.example.lap60020_local.finalproject.ModelData.Entity.Movie;
import com.example.lap60020_local.finalproject.ModelData.Entity.MovieResponse;
import com.example.lap60020_local.finalproject.ModelData.Params.Params;
import com.example.lap60020_local.finalproject.ModelData.retrofit.MovieAPI;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;

public abstract class AbstractConcreteListRepository implements IListRepository {

    private MovieAPI movieAPI;
    private final String apiKey;
    private int page = 1;
    private int Maxpage = 1;
    private List<Movie> mData;

    public AbstractConcreteListRepository(MovieAPI movieAPI, String apiKey) {
        this.movieAPI = movieAPI;
        this.apiKey = apiKey;
        mData = new ArrayList<>();
    }

    public abstract Observable<MovieResponse> init(Params params);

    @Override
    public Observable<List<Movie>> requetData(Params params) {
        // th1: co data
        if(mData.size() > 0) {
            return Observable.just(mData);
        }
        // th2: chua co data
        else {
            if(page <= Maxpage) {
                Log.d("Debug", "page " + mData.size() + " "+page);
                params.setPage(page);
                page++;
                return init(params).map(movieResponse -> {
                    Maxpage = movieResponse.getTotalPages();
                    mData =  movieResponse.getResults();
                    return mData;
                }).doOnError(error->{
                    page--;
                });
            }
            else {
                return Observable.empty();
            }
        }
    }

    @Override
    public Observable<List<Movie>> requestMoreData(Params params) {
        if(page <= Maxpage) {
            Log.d("Debug", "page " + mData.size() + " "+page);
            params.setPage(page);
            page++;
            return init(params).map(movieResponse -> {
                Maxpage = movieResponse.getTotalPages();
                List<Movie> movies = movieResponse.getResults();
                mData.addAll(movies);
                return mData;
            }).doOnError(error->{
                page--;
            });
        }
        else {
            return Observable.empty();
        }
    }
}
