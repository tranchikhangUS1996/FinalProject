package com.example.lap60020_local.finalproject.ViewModel;

import com.example.lap60020_local.finalproject.ModelData.Entity.Movie;
import com.example.lap60020_local.finalproject.ModelData.Params.Params;
import com.example.lap60020_local.finalproject.ModelData.Repository.ListRepositorys.IListRepository;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.subjects.BehaviorSubject;

public class AccountMovieViewModel extends MoviesViewModel {

    private IListRepository repository;

    public AccountMovieViewModel(IListRepository repository) {
        this.repository = repository;
    }

    @Override
    public Observable<List<Movie>> initSubject(BehaviorSubject<Params> paramsBehaviorSubject) {
        return paramsBehaviorSubject.concatMap(params -> {
            if(params.getType() == 0) {
                return repository.requetData(params);
            }
            else return repository.requestMoreData(params);
        });
    }
}
