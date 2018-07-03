package com.example.lap60020_local.finalproject.ViewModel;

import com.example.lap60020_local.finalproject.ModelData.Entity.Movie;
import com.example.lap60020_local.finalproject.ModelData.Params.Params;
import com.example.lap60020_local.finalproject.ModelData.Params.SearchParams;
import com.example.lap60020_local.finalproject.ModelData.Repository.ListRepositorys.IListRepository;

import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.subjects.BehaviorSubject;

public class SearchViewModel extends MoviesViewModel {

    IListRepository repository;

    public SearchViewModel(IListRepository repository) {
        this.repository = repository;
    }

    @Override
    public Observable<List<Movie>> initSubject(BehaviorSubject<Params> paramsBehaviorSubject) {
        return paramsBehaviorSubject.debounce(500, TimeUnit.MILLISECONDS)
                .filter(params -> {
            SearchParams searchParams = (SearchParams) params;
            if(searchParams.getText().isEmpty()) return false;
            return true;
        }).distinctUntilChanged().switchMap(params -> {
                if (params.getType()==0) {
                    return repository.requetData(params);
                }
                else {
                    return repository.requestMoreData(params);
                }
        });
    }

}
