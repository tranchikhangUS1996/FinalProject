package com.example.lap60020_local.finalproject.Ui;

import android.net.Uri;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.lap60020_local.finalproject.GlideApp;
import com.example.lap60020_local.finalproject.ModelData.Entity.Movie;
import com.example.lap60020_local.finalproject.ModelData.Entity.ObservableListData;
import com.example.lap60020_local.finalproject.ModelData.Params.RecommendParams;
import com.example.lap60020_local.finalproject.ModelData.Params.SimilarParams;
import com.example.lap60020_local.finalproject.ModelData.retrofit.MyApiClient;
import com.example.lap60020_local.finalproject.R;
import com.example.lap60020_local.finalproject.Ui.Adapter.HorizontalListAdapter;
import com.example.lap60020_local.finalproject.ViewModel.DetailViewModel;
import com.example.lap60020_local.finalproject.ViewModel.MoviesViewModel;

import butterknife.BindFont;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Optional;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class detailActivity extends AppCompatActivity {

    @BindView(R.id.detail_toolbar)
    @Nullable Toolbar toolbar;
    @BindView(R.id.detail_title)
    @Nullable TextView tittle;
    @BindView(R.id.detail_image)
    @Nullable ImageView image;
    @BindView(R.id.detail_rate_image)
    @Nullable ImageView rateImage;
    @BindView(R.id.detail_rate)
    @Nullable TextView rate;
    @BindView(R.id.detail_rate_count)
    @Nullable TextView rateCount;
    @BindView(R.id.detail_my_rate_image)
    @Nullable ImageView myRateImg;
    @BindView(R.id.detail_my_rate)
    @Nullable TextView myRate;
    @BindView(R.id.detail_watch_list)
    @Nullable ImageView watchList;
    @BindView(R.id.detail_my_favourite)
    @Nullable ImageView favorite;
    @BindView(R.id.detail_oveview)
    @Nullable TextView overView;
    @BindView(R.id.detail_similar_see_all)
    @Nullable TextView similarSeeAll;
    @BindView(R.id.detail_recylerview_similar)
    @Nullable RecyclerView similarRecyclerView;
    @BindView(R.id.detail_recommend_see_all)
    @Nullable TextView recommenedSeeAll;
    @BindView(R.id.detail_recylerview_recommend)
    @Nullable RecyclerView recommendRecyclerView;
    @BindView(R.id.detail_progressbar)
    @Nullable ProgressBar detail_progressbar;
    @BindView(R.id.detail_similar_progressbar)
    @Nullable ProgressBar SimilarProgressbar;
    @BindView(R.id.detail_recommended_progressbar)
    @Nullable ProgressBar RecomendProgressbar;

    private DetailViewModel detailViewModel;
    private MoviesViewModel similarViewModel;
    private MoviesViewModel recommendViewModel;
    private int id;
    private CompositeDisposable disposable;
    private HorizontalListAdapter SimilarAdapter;
    private HorizontalListAdapter RecommenedAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);
        disposable = new CompositeDisposable();
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        id = DetailViewModel.getID();
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
        detail_progressbar.setVisibility(View.VISIBLE);
        disposable.add(detailViewModel.setDataStream()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DetailObserver()));
        detailViewModel.bindData();

        RecomendProgressbar.setVisibility(View.VISIBLE);
        disposable.add(recommendViewModel.setDataStream()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new RecommendObserver()));
        recommendViewModel.loadData(new RecommendParams(id));

        SimilarProgressbar.setVisibility(View.VISIBLE);
        disposable.add(similarViewModel.setDataStream()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new SimilarObserver()));
        similarViewModel.loadData(new SimilarParams(id));
    }

    public void unbind() {
        disposable.clear();
    }

    class RecommendObserver extends DisposableObserver<ObservableListData> {

        @Override
        public void onNext(ObservableListData data) {
            RecommenedAdapter.receiveData(data.getData());
        }

        @Override
        public void onError(Throwable e) {
            RecomendProgressbar.setVisibility(View.INVISIBLE);
        }

        @Override
        public void onComplete() {
            RecomendProgressbar.setVisibility(View.INVISIBLE);
        }
    }

    class SimilarObserver extends DisposableObserver<ObservableListData> {

        @Override
        public void onNext(ObservableListData data) {
            SimilarAdapter.receiveData(data.getData());
        }

        @Override
        public void onError(Throwable e) {
            SimilarProgressbar.setVisibility(View.INVISIBLE);
        }

        @Override
        public void onComplete() {
            SimilarProgressbar.setVisibility(View.INVISIBLE);
        }
    }

    class DetailObserver extends DisposableObserver<Movie> {

        @Override
        public void onNext(Movie movie) {
            showMovie(movie);
        }

        @Override
        public void onError(Throwable e) {
            detail_progressbar.setVisibility(View.INVISIBLE);
        }

        @Override
        public void onComplete() {
            detail_progressbar.setVisibility(View.INVISIBLE);
        }
    }

    private void showMovie(Movie movie) {
        tittle.setText(movie.getTitle());
        String path = MyApiClient.IMAGE_PATH + movie.getPosterPath();
        GlideApp.with(this)
                .load(Uri.parse(path))
                .centerCrop()
                .placeholder(R.drawable.placeholder)
                .into(image);
        rate.setText(String.valueOf(movie.getVoteAverage()));
        rateCount.setText(String.valueOf(movie.getVoteCount()));
        overView.setText(movie.getOverview());
    }

    @Optional
    @OnClick({R.id.detail_rate, R.id.detail_rate_count, R.id.detail_rate_image})
    public void onRateClick(View v) {

    }

    @Optional
    @OnClick({R.id.detail_my_rate_image})
    public void onMyRateClick(View v) {

    }

    @Optional
    @OnClick(R.id.detail_watch_list)
    public void onWatchlistClick(View v) {

    }

    @Optional
    @OnClick(R.id.detail_my_favourite)
    public void onFavoriteClick(View v) {

    }

    @Optional
    @OnClick(R.id.detail_similar_see_all)
    public void onSimilarSeeAllClick(View v) {

    }

    @Optional
    @OnClick(R.id.detail_recommend_see_all)
    public void onRecommendedSeeAllClick(View v) {

    }

}
