package com.example.lap60020_local.finalproject.ViewModel;

import com.example.lap60020_local.finalproject.ModelData.Entity.Movie;
import com.example.lap60020_local.finalproject.ModelData.Repository.DetailRepository.IDetailRepository;

import io.reactivex.Observable;
import io.reactivex.subjects.BehaviorSubject;

public class DetailViewModel {

    private static Movie ID;
    private IDetailRepository repository;
    private BehaviorSubject<Integer> subject = BehaviorSubject.create();

    public DetailViewModel(IDetailRepository repository) {
        this.repository = repository;
    }

    public static void setId(Movie id){
         ID = id;
    }

    public static Movie getID() {
        return ID;
    }

    public Observable<Movie> setDataStream() {
        return repository.getData(ID.getId());
    }

    public void bindData(){
        subject.onNext(ID.getId());
    }
}
