package com.example.lap60020_local.finalproject.ViewModel;

import com.example.lap60020_local.finalproject.ModelData.Entity.Movie;
import com.example.lap60020_local.finalproject.ModelData.Entity.ObservableListData;
import com.example.lap60020_local.finalproject.ModelData.Params.Params;
import com.example.lap60020_local.finalproject.ModelData.Repository.ListRepositorys.IListRepository;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Predicate;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.BehaviorSubject;
import io.reactivex.subjects.PublishSubject;

public abstract class MoviesViewModel {
    private int seePossition;
    private PublishSubject<Params> behaviorSubject = PublishSubject.create();
     boolean loading = false;

    public Observable<ObservableListData> setDataStream() {

        return initSubject(behaviorSubject).map(data-> new ObservableListData(data, seePossition));
    }

    public abstract Observable<List<Movie>> initSubject(Observable<Params> paramsBehaviorSubject);

    public void onScroll(int lastseen) {
        this.seePossition = lastseen;
    }

    public void acceptLoad() {
        loading = false;
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
