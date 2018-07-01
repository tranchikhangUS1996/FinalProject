package com.example.lap60020_local.finalproject.Ui;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.lap60020_local.finalproject.ModelData.Entity.Movie;
import com.example.lap60020_local.finalproject.ModelData.Entity.ObservableListData;
import com.example.lap60020_local.finalproject.ModelData.Params.PageParams;
import com.example.lap60020_local.finalproject.R;
import com.example.lap60020_local.finalproject.Ui.Adapter.LoadMoreNotifier;
import com.example.lap60020_local.finalproject.Ui.Adapter.VerticalListAdapter;
import com.example.lap60020_local.finalproject.ViewModel.MoviesViewModel;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;


/**
 * A simple {@link Fragment} subclass.
 */
public class PopularFragment extends Fragment implements LoadMoreNotifier {

    @Nullable
    @BindView(R.id.popular_fragment_progressbar)
    ProgressBar progressBar;
    @Nullable
    @BindView(R.id.popular_fragment_recyclerview)
    RecyclerView recyclerView;
    VerticalListAdapter adater;
    CompositeDisposable disposable;
    MoviesViewModel moviesViewModel;

    public PopularFragment() {
        disposable = new CompositeDisposable();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_popular, container, false);
        ButterKnife.bind(this, v);
        return v;
    }

    @Override
    public void loadMore(int lastSeen) {
        moviesViewModel.getMoredata(new PageParams(), lastSeen);
    }

    public void bind() {
        progressBar.setVisibility(View.VISIBLE);
        disposable.add(moviesViewModel.getData(new PageParams())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new PopularObserver()));

        disposable.add(moviesViewModel.setMoredataStream()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(data->{
                    adater.receiveData(data.getData(),data.getSeePossition());
                }));
    }

    public void unbind() {
        disposable.clear();
    }

    public class PopularObserver extends DisposableObserver<ObservableListData>{

        @Override
        public void onNext(ObservableListData data) {
            adater.receiveData(data.getData(), data.getSeePossition());
        }

        @Override
        public void onError(Throwable e) {
            progressBar.setVisibility(View.INVISIBLE);
            Toast.makeText(getContext(),e.getMessage(),Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onComplete() {
            progressBar.setVisibility(View.INVISIBLE);
        }
    }
}
