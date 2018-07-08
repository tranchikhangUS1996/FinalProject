package com.example.lap60020_local.finalproject.ViewModel;

import com.example.lap60020_local.finalproject.ModelData.Entity.Movie;
import com.example.lap60020_local.finalproject.ModelData.Entity.ObservableListData;
import com.example.lap60020_local.finalproject.ModelData.Params.Params;
import java.util.List;

import io.reactivex.Observable;


public abstract class MoviesViewModel {
    private int seePossition;
     boolean loading = false;

    public abstract Observable<List<Movie>> initSubject(Observable<Params> observable);

    public void onScroll(int lastseen) {
        this.seePossition = lastseen;
    }

    public void acceptLoad() {
        loading = false;
    }

    public Observable<ObservableListData> loadMoreData(Params params) {
        params.setType(1);
        return initSubject(Observable.just(params)).map(list-> new ObservableListData(list, seePossition));
    }

    public Observable<ObservableListData> loadData(Params params) {
        params.setType(0);
        return initSubject(Observable.just(params)).map(list-> new ObservableListData(list, seePossition));
    }

}
