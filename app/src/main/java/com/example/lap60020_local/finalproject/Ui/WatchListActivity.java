package com.example.lap60020_local.finalproject.Ui;

import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import com.example.lap60020_local.finalproject.ModelData.Entity.ObservableListData;
import com.example.lap60020_local.finalproject.ModelData.Params.Params;
import com.example.lap60020_local.finalproject.R;
import com.example.lap60020_local.finalproject.Ui.Adapter.LoadMoreNotifier;
import com.example.lap60020_local.finalproject.Ui.Adapter.VerticalListAdapter;
import com.example.lap60020_local.finalproject.ViewModel.MoviesViewModel;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class WatchListActivity extends AppCompatActivity implements LoadMoreNotifier {

    @Nullable
    @BindView(R.id.watchlist_activity_recyclerview)
    RecyclerView recyclerView;
    @Nullable
    @BindView(R.id.watchlist_activity_progressbar)
    ProgressBar progressBar;
    @Nullable
    @BindView(R.id.watchlist_activity_toolbar)
    Toolbar toolbar;
    private VerticalListAdapter adapter;
    private MoviesViewModel moviesViewModel;
    private CompositeDisposable disposable;
    private Params params;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_watch_list);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        adapter = new VerticalListAdapter(this, this, recyclerView);
        moviesViewModel = (MoviesViewModel) getIntent().getExtras().get("VIEWMODEL");
        params = (Params) getIntent().getExtras().get("PARAMS");
        disposable = new CompositeDisposable();
    }

    @Override
    public void onScroll(int lastseen) {
        moviesViewModel.onScroll(lastseen);
    }

    @Override
    public void loadMore() {
        moviesViewModel.loadMoreData(params);
    }

    public void bind() {
        disposable.add(moviesViewModel.setDataStream()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new WatchlistObserver()));
        moviesViewModel.loadData(params);
    }

    public void unbind() {
        disposable.clear();
    }

    public class WatchlistObserver extends DisposableObserver<ObservableListData> {

        @Override
        public void onNext(ObservableListData data) {
            adapter.receiveData(data.getData(), data.getSeePossition());
        }

        @Override
        public void onError(Throwable e) {
            progressBar.setVisibility(View.INVISIBLE);
            Log.e("WatchListObserver", e.getMessage());
        }

        @Override
        public void onComplete() {
            progressBar.setVisibility(View.INVISIBLE);
        }
    }
}
