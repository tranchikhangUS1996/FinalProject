package com.example.lap60020_local.finalproject.ViewModel;

import android.util.Log;

import com.example.lap60020_local.finalproject.ModelData.Entity.Movie;
import com.example.lap60020_local.finalproject.ModelData.Params.Params;
import com.example.lap60020_local.finalproject.ModelData.Params.SearchParams;
import com.example.lap60020_local.finalproject.ModelData.Repository.ListRepositorys.IListRepository;

import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.BehaviorSubject;
import io.reactivex.subjects.PublishSubject;

public class SearchViewModel extends MoviesViewModel {

    IListRepository repository;

    public SearchViewModel(IListRepository repository) {
        this.repository = repository;
    }

    @Override
    public Observable<List<Movie>> initSubject(Observable<Params> paramsBehaviorSubject) {

        return paramsBehaviorSubject.debounce(300, TimeUnit.MILLISECONDS)
                .filter(params -> {
                    SearchParams searchParams = (SearchParams) params;
                    return !searchParams.getText().isEmpty();
                }).concatMap(params -> {
                    SearchParams searchParams = (SearchParams) params;
                    Log.d("Debug", "swithmap " + searchParams.getText());
                    if (params.getType() == 0) {
                        return repository.requetData(params);
                    } else {
                        return repository.requestMoreData(params);
                    }
                });
    }

}
