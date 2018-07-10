package com.example.lap60020_local.finalproject.Ui;

import android.app.Dialog;
import android.content.Context;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lap60020_local.finalproject.GlideApp;
import com.example.lap60020_local.finalproject.ModelData.Entity.FavouriteResponse;
import com.example.lap60020_local.finalproject.ModelData.Entity.Movie;
import com.example.lap60020_local.finalproject.ModelData.Entity.ObservableListData;
import com.example.lap60020_local.finalproject.ModelData.Entity.RateResponse;
import com.example.lap60020_local.finalproject.ModelData.Params.RecommendParams;
import com.example.lap60020_local.finalproject.ModelData.Params.SimilarParams;
import com.example.lap60020_local.finalproject.ModelData.Params.UserInteractParams;
import com.example.lap60020_local.finalproject.ModelData.retrofit.MyApiClient;
import com.example.lap60020_local.finalproject.MyApplication;
import com.example.lap60020_local.finalproject.R;
import com.example.lap60020_local.finalproject.Ui.Adapter.HorizontalListAdapter;
import com.example.lap60020_local.finalproject.ViewModel.AddToWatchListViewModel;
import com.example.lap60020_local.finalproject.ViewModel.DetailViewModel;
import com.example.lap60020_local.finalproject.ViewModel.MaskAsFavoriteViewModel;
import com.example.lap60020_local.finalproject.ViewModel.MoviesViewModel;
import com.example.lap60020_local.finalproject.ViewModel.RateViewModel;

import java.lang.ref.WeakReference;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Optional;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class DetailActivity extends AppCompatActivity {

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
    private RateViewModel rateViewModel;
    private AddToWatchListViewModel addToWatchListViewModel;
    private MaskAsFavoriteViewModel maskAsFavoriteViewModel;
    private Movie id;
    private CompositeDisposable disposable;
    private HorizontalListAdapter SimilarAdapter;
    private HorizontalListAdapter RecommenedAdapter;
    private Context context;
    private String sessionId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this,this);
        detailViewModel = ((MyApplication) getApplication()).getDetailViewModel();
        similarViewModel = ((MyApplication) getApplication()).getSimilarViewModel();
        recommendViewModel = ((MyApplication) getApplication()).getRecommendedViewModel();
        rateViewModel = ((MyApplication) getApplication()).getRateViewModel();
        SimilarAdapter = new HorizontalListAdapter(similarRecyclerView ,this);
        RecommenedAdapter = new HorizontalListAdapter(recommendRecyclerView, this);
        disposable = new CompositeDisposable();
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        id = DetailViewModel.getID();
        context = this;
        rateViewModel = ((MyApplication) getApplication()).getRateViewModel();
        addToWatchListViewModel = ((MyApplication) getApplication()).getAddToWatchListViewModel();
        maskAsFavoriteViewModel = ((MyApplication) getApplication()).getMaskAsFavoriteViewModel();
        sessionId = ((MyApplication) getApplication()).getSessionID();
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
        disposable.add(recommendViewModel.loadData(new RecommendParams(id.getId()))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new RecommendObserver()));

        SimilarProgressbar.setVisibility(View.VISIBLE);
        disposable.add(similarViewModel.loadData(new SimilarParams(id.getId()))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new SimilarObserver()));
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
            recommendViewModel.acceptLoad();
        }

        @Override
        public void onComplete() {
            RecomendProgressbar.setVisibility(View.INVISIBLE);
            recommendViewModel.acceptLoad();
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
            similarViewModel.acceptLoad();
        }

        @Override
        public void onComplete() {
            SimilarProgressbar.setVisibility(View.INVISIBLE);
            similarViewModel.acceptLoad();
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
//        Toast.makeText(this.context, "helloasdfasdf", Toast.LENGTH_SHORT).show();
        Dialog rankDialog = new Dialog(DetailActivity.this, R.style.FullHeightDialog);
        rankDialog.setContentView(R.layout.rank_dialog);
        rankDialog.setCancelable(true);
        RatingBar ratingBar = (RatingBar) rankDialog.findViewById(R.id.dialog_ratingbar);
        //set userRankedValue here
        ratingBar.setRating(1);

        TextView text = (TextView) rankDialog.findViewById(R.id.rank_dialog_text1);
        text.setText("Rate this movie");

        Button updateButton = (Button) rankDialog.findViewById(R.id.rank_dialog_button);
        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //update userRankedValue here
//                rateViewModel.rate(ratingBar.getRating());
                Toast.makeText(getBaseContext(), "Updated rate value", Toast.LENGTH_SHORT).show();
                rankDialog.dismiss();
            }
        });
        rankDialog.show();
    }

//    @Optional
//    @OnClick(R.id.detail_watch_list)
    public void onDetailWatchlistClick(View v) {
        UserInteractParams userInteractParams = new UserInteractParams(!id.isWatchlist(),sessionId, id.getId());
        disposable.add(addToWatchListViewModel.add(userInteractParams)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new WatchListConsumer(new WeakReference<>(v))));
    }

    public class WatchListConsumer extends DisposableObserver<FavouriteResponse> {

        WeakReference<View> weakReference;

        public WatchListConsumer(WeakReference<View> weakReference) {
            this.weakReference = weakReference;
        }

        @Override
        public void onNext(FavouriteResponse favouriteResponse)  {
            if(weakReference != null && weakReference.get() != null) {
                ImageView imageView = (ImageView) weakReference.get();
                if(favouriteResponse.statusCode == 12) {
                    if(id.isWatchlist()) {
                        imageView.setImageResource(R.drawable.addwatchlist);
                    } else {
                        imageView.setImageResource(R.drawable.addedwatchlist);
                    }
                }
            }
        }

        @Override
        public void onError(Throwable e) {
            Toast.makeText(context, e.getMessage().toString(), Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onComplete() {

        }
    }

    public class FavouriteConsumer extends DisposableObserver<FavouriteResponse> {

        WeakReference<View> weakReference;

        public FavouriteConsumer(WeakReference<View> weakReference) {
            this.weakReference = weakReference;
        }

        @Override
        public void onNext(FavouriteResponse favouriteResponse) {
            if(weakReference != null && weakReference.get() != null) {
                ImageView imageView = (ImageView) weakReference.get();
                if(favouriteResponse.statusCode == 12) {
                    if(id.isFavorite()) {
                        imageView.setImageResource(R.drawable.favorite);
                    } else {
                        imageView.setImageResource(R.drawable.favourited);
                    }
                }
            }
        }

        @Override
        public void onError(Throwable e) {
            Toast.makeText(context, e.getMessage().toString(),Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onComplete() {

        }
    }

    public class RatingConsumer extends DisposableObserver<FavouriteResponse> {

        WeakReference<View> weakReference;

        public RatingConsumer(WeakReference<View> weakReference) {
            this.weakReference = weakReference;
        }

        @Override
        public void onNext(FavouriteResponse favouriteResponse) {
            if(weakReference != null && weakReference.get() != null) {
                ImageView imageView = (ImageView) weakReference.get();
                if(favouriteResponse.statusCode == 1) {
                    if(id.isRated()) {
                        imageView.setImageResource(R.drawable.you_unrate);
                    } else {
                        imageView.setImageResource(R.drawable.my_rated);
                    }
                }
            }
        }

        @Override
        public void onError(Throwable e) {
            Toast.makeText(context, e.getMessage().toString(), Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onComplete() {

        }
    }

    @Optional
    @OnClick(R.id.detail_my_favourite)
    public void onFavoriteClick(View v) {
        UserInteractParams userInteractParams = new UserInteractParams(!id.isFavorite(), sessionId, id.getId());
        disposable.add(maskAsFavoriteViewModel.mask(userInteractParams).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new FavouriteConsumer(new WeakReference<>(v))));
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
