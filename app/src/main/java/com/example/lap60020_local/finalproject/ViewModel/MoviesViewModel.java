package com.example.lap60020_local.finalproject.ViewModel;

import com.example.lap60020_local.finalproject.ModelData.Entity.ObservableListData;
import com.example.lap60020_local.finalproject.ModelData.Params.PageParams;
import com.example.lap60020_local.finalproject.ModelData.Params.Params;
import com.example.lap60020_local.finalproject.ModelData.Repository.ListRepositorys.IListRepository;

import io.reactivex.Observable;
import io.reactivex.subjects.BehaviorSubject;

public class MoviesViewModel {
    private IListRepository listRepository;
    private int seePossition;
    private int page = 1;
    private BehaviorSubject<Params> behaviorSubject = BehaviorSubject.create();

    public MoviesViewModel(IListRepository listRepository) {
        this.listRepository = listRepository;
    }

    public void setSeePossition(int possition) {
        this.seePossition = possition;
    }

    public Observable<ObservableListData> getData(Params params) {
        params.setPage(page);
        return listRepository.getData(params).doOnComplete(()->{
            page++;
        }).map(data->{
            return new ObservableListData(data, seePossition);
        });

    }

    public Observable<ObservableListData> setMoredataStream() {
        return behaviorSubject.concatMap(params -> {
            return listRepository.getData(params);
        }).map(data->{
           return new ObservableListData(data, seePossition);
        }).doOnComplete(()->{
            page++;
        });
    }

    public void getMoredata(Params params, int seePossition) {
        this.seePossition = seePossition;
        params.setPage(page);
        behaviorSubject.onNext(params);
    }
}
