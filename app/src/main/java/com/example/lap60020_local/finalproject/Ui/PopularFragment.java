package com.example.lap60020_local.finalproject.Ui;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.lap60020_local.finalproject.ModelData.Entity.ObservableListData;
import com.example.lap60020_local.finalproject.ModelData.Params.PageParams;
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
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_popular, container, false);
        ButterKnife.bind(this, v);
        moviesViewModel = ((MyApplication) getActivity().getApplication()).getPopularViewModel();
        adater = new VerticalListAdapter(getContext(), this, recyclerView);
        return v;
    }

    @Override
    public void onStart() {
        super.onStart();
        bind();
    }

    @Override
    public void onStop() {
        super.onStop();
        unbind();
    }

    public void bind() {
        disposable.add(moviesViewModel.setDataStream()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new PopularObserver()));
        moviesViewModel.loadData(new PageParams());
    }

    public void unbind() {
        disposable.clear();
    }

    public void loadData() {
        assert progressBar != null;
        progressBar.setVisibility(View.VISIBLE);
        moviesViewModel.loadData(new PageParams());
    }

    @Override
    public void onScroll(int lastseen) {
        moviesViewModel.onScroll(lastseen);
    }

    @Override
    public void loadMore() {
        moviesViewModel.loadMoreData(new PageParams());
    }

    public class PopularObserver extends DisposableObserver<ObservableListData>{

        @Override
        public void onNext(ObservableListData data) {
            adater.receiveData(data.getData(), data.getSeePossition());
            moviesViewModel.acceptLoad();
            progressBar.setVisibility(View.INVISIBLE);
        }

        @Override
        public void onError(Throwable e) {
            assert progressBar != null;
            progressBar.setVisibility(View.INVISIBLE);
            moviesViewModel.acceptLoad();
            Toast.makeText(getContext(),e.getMessage(),Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onComplete() {
            assert progressBar != null;
            progressBar.setVisibility(View.INVISIBLE);
            moviesViewModel.acceptLoad();
        }
    }
}
