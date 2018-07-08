package com.example.lap60020_local.finalproject.Ui;

import android.app.SearchManager;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.example.lap60020_local.finalproject.ModelData.Entity.ObservableListData;
import com.example.lap60020_local.finalproject.ModelData.Params.SearchParams;
import com.example.lap60020_local.finalproject.MyApplication;
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

public class SearchActivity extends AppCompatActivity implements SearchView.OnQueryTextListener, LoadMoreNotifier {

    @Nullable
    @BindView(R.id.search_activity_toolbar)
    Toolbar toolbar;

    @Nullable
    @BindView(R.id.search_activity_search_bar)
    SearchView mSearch;

    @Nullable
    @BindView(R.id.search_activity_progressbar)
    ProgressBar progressBar;

    @Nullable
    @BindView(R.id.search_activity_recyclerview)
    RecyclerView recyclerView;

    MoviesViewModel moviesViewModel;
    VerticalListAdapter adapter;
    CompositeDisposable disposable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        ButterKnife.bind(this);
        EditText editText = mSearch.findViewById(android.support.v7.appcompat.R.id.search_src_text);
        editText.setTextColor(getResources().getColor(R.color.colorAccent));
        editText.setHintTextColor(getResources().getColor(R.color.colorAccent));
        disposable = new CompositeDisposable();
        moviesViewModel = ((MyApplication) getApplication()).getSearchViewModel();
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setDisplayHomeAsUpEnabled(true);
        SearchManager searchManager = (SearchManager) getSystemService(SEARCH_SERVICE);
        assert mSearch != null;
        assert searchManager != null;
        mSearch.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        mSearch.setOnQueryTextListener(this);
        adapter = new VerticalListAdapter(this,this, recyclerView);
    }

    @Override
    protected void onStart() {
        super.onStart();
        bind();
    }

    @Override
    protected void onStop() {
        super.onStop();
        unbind();
    }

    public void bind() {
        disposable.add(moviesViewModel.setDataStream()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new SearchObserver()));
        moviesViewModel.loadData(new SearchParams(mSearch.getQuery().toString()));
    }

    public void unbind() {
        disposable.clear();
    }

    @Override
    public void onScroll(int lastseen) {
        moviesViewModel.onScroll(lastseen);
    }

    @Override
    public void loadMore() {
        moviesViewModel.loadMoreData(new SearchParams(mSearch.getQuery().toString()));
    }

    public class SearchObserver extends DisposableObserver<ObservableListData> {

        @Override
        public void onNext(ObservableListData data) {
            adapter.receiveData(data.getData(), data.getSeePossition());
            progressBar.setVisibility(View.INVISIBLE);
        }

        @Override
        public void onError(Throwable e) {
            progressBar.setVisibility(View.INVISIBLE);
        }

        @Override
        public void onComplete() {
            progressBar.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        onScroll(0);
        moviesViewModel.loadData(new SearchParams(query));
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        moviesViewModel.onScroll(0);
        moviesViewModel.loadData(new SearchParams(newText));
        return false;
    }
}
