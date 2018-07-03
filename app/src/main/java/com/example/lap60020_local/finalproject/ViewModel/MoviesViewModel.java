package com.example.lap60020_local.finalproject.ViewModel;

import com.example.lap60020_local.finalproject.ModelData.Entity.Movie;
import com.example.lap60020_local.finalproject.ModelData.Entity.ObservableListData;
import com.example.lap60020_local.finalproject.ModelData.Params.Params;
import com.example.lap60020_local.finalproject.ModelData.Repository.ListRepositorys.IListRepository;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.subjects.BehaviorSubject;

public abstract class MoviesViewModel {
    private int seePossition;
    private BehaviorSubject<Params> behaviorSubject = BehaviorSubject.create();

    public Observable<ObservableListData> setDataStream() {
        return initSubject(behaviorSubject).map(data-> new ObservableListData(data, seePossition));
    }

    public abstract Observable<List<Movie>> initSubject(BehaviorSubject<Params> paramsBehaviorSubject);

    public void onScroll(int lastseen) {
        this.seePossition = lastseen;
    }

    public void loadMoreData(Params params) {
        params.setType(1);
        behaviorSubject.onNext(params);
    }

    public void loadData(Params params) {
        params.setType(0);
        behaviorSubject.onNext(params);
    }

}
