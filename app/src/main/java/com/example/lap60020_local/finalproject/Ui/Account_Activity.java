package com.example.lap60020_local.finalproject.Ui;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.lap60020_local.finalproject.ModelData.Entity.Account;
import com.example.lap60020_local.finalproject.ModelData.Entity.MyCallback;
import com.example.lap60020_local.finalproject.ModelData.Params.UserMovieParam;
import com.example.lap60020_local.finalproject.MyApplication;
import com.example.lap60020_local.finalproject.R;
import com.example.lap60020_local.finalproject.ViewModel.AccountViewModel;
import com.example.lap60020_local.finalproject.ViewModel.FavoriteMovieViewModel;
import com.example.lap60020_local.finalproject.ViewModel.UserMovieViewModel;
import com.example.lap60020_local.finalproject.ViewModel.UserRatedMovieViewModel;
import com.example.lap60020_local.finalproject.ViewModel.watchListMovieViewModel;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Optional;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class Account_Activity extends AppCompatActivity {

    @Nullable
    @BindView(R.id.account_avatar_image)
    ImageView avatar;
    @Nullable
    @BindView(R.id.account_username)
    TextView userName;
    @Nullable
    @BindView(R.id.account_activity_logout)
    ImageView logout;
    @Nullable
    @BindView(R.id.account_activity_favorite)
    Button favorite;
    @Nullable
    @BindView(R.id.account_activity_rated)
    Button rated;
    @Nullable
    @BindView(R.id.account_activity_watchList)
    Button watchlist;
    @Nullable
    @BindView(R.id.account_activity_progressbar)
    ProgressBar progressBar;

    private CompositeDisposable disposable;

    private AccountViewModel accountViewModel;

    private UserMovieViewModel watchListViewModel;
    private UserMovieViewModel favoriteViewModel;
    private UserMovieViewModel ratedViewModel;
    private Context context;
    private String sessionId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_);
        ButterKnife.bind(this, this);
        accountViewModel = ((MyApplication) getApplication()).getAccountViewModel();
        watchListViewModel = new watchListMovieViewModel();
        favoriteViewModel = new FavoriteMovieViewModel();
        ratedViewModel = new UserRatedMovieViewModel();
        disposable = new CompositeDisposable();
        context = this;
        sessionId = ((MyApplication) getApplication()).getSessionID();
    }

    public void bind() {
        progressBar.setVisibility(View.VISIBLE);
        disposable.add(accountViewModel.getAccountDetail(((MyApplication) getApplication()).getSessionID())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new AccountObserver()));
    }

    public void unbind() {
        disposable.clear();
    }

    class AccountObserver extends DisposableObserver<Account> {

        @Override
        public void onNext(Account account) {
            setupAccount(account);
        }

        @Override
        public void onError(Throwable e) {
            progressBar.setVisibility(View.INVISIBLE);
            // thong bao loi mang hoac la chua login
        }

        @Override
        public void onComplete() {
            progressBar.setVisibility(View.INVISIBLE);
            // thong bao login thanh cong
        }
    }

    public class UserObserver extends DisposableObserver<MyCallback> {

        @Override
        public void onNext(MyCallback myCallback) {
            myCallback.doMyWork(context);
        }

        @Override
        public void onError(Throwable e) {

        }

        @Override
        public void onComplete() {

        }
    }

    private void setupAccount(Account account) {
        userName.setText(account.username);
        // set image nua
    }

    public void onLogoutClick(View v) {
        ((MyApplication) getApplication()).setSessionId(null);
        // thong bao logout
    }

    @Optional
    @OnClick(R.id.account_activity_watchList)
    public void onAccountWatchListClick(View v) {
        disposable.add(watchListViewModel.userClick(new UserMovieParam(sessionId))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new UserObserver()));
    }

    @Optional
    @OnClick(R.id.account_activity_favorite)
    public void onAccountFavoriteClick(View v) {
        disposable.add(favoriteViewModel.userClick(new UserMovieParam(sessionId))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new UserObserver()));
    }

    @Optional
    @OnClick(R.id.account_activity_rated)
    public void onAccountRatedClick(View v) {
        disposable.add(ratedViewModel.userClick(new UserMovieParam(sessionId))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new UserObserver()));
    }
}
