package com.example.lap60020_local.finalproject.ViewModel;

import com.example.lap60020_local.finalproject.ModelData.Entity.Movie;
import com.example.lap60020_local.finalproject.ModelData.Params.Params;
import com.example.lap60020_local.finalproject.ModelData.Repository.ListRepositorys.IListRepository;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.subjects.BehaviorSubject;

public class ListViewModel extends MoviesViewModel {

    private final IListRepository listRepository;

    public ListViewModel(IListRepository listRepository) {
        this.listRepository = listRepository;
    }

    @Override
    public Observable<List<Movie>> initSubject(BehaviorSubject<Params> paramsBehaviorSubject) {
        return paramsBehaviorSubject.switchMap(params -> {
            if(params.getPage() == 0) {
                return listRepository.requetData(params);
            }
            else return listRepository.requestMoreData(params);
        });
    }
}
