package com.example.lap60020_local.finalproject.ViewModel;

import android.util.Log;

import com.example.lap60020_local.finalproject.ModelData.Entity.Movie;
import com.example.lap60020_local.finalproject.ModelData.Params.Params;
import com.example.lap60020_local.finalproject.ModelData.Repository.ListRepositorys.IListRepository;

import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.BehaviorSubject;
import io.reactivex.subjects.PublishSubject;

public class ListViewModel extends MoviesViewModel {

    private final IListRepository listRepository;

    public ListViewModel(IListRepository listRepository) {
        this.listRepository = listRepository;
    }

    @Override
    public Observable<List<Movie>> initSubject(Observable<Params> observable) {
        return observable
                .filter(params -> !loading).concatMap(params -> {
                    Log.d("Debug", Thread.currentThread().getName() + " concatmap");
                    loading = true;
                    if (params.getType() == 0) {
                        return listRepository.requetData(params);
                    } else return listRepository.requestMoreData(params);
                });
    }
}
