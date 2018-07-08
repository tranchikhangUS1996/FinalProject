package com.example.lap60020_local.finalproject.Ui;

import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.lap60020_local.finalproject.ModelData.Entity.Account;
import com.example.lap60020_local.finalproject.MyApplication;
import com.example.lap60020_local.finalproject.R;
import com.example.lap60020_local.finalproject.ViewModel.AccountViewModel;

import butterknife.BindView;
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
    ImageView favorite;
    @Nullable
    @BindView(R.id.account_activity_rated)
    ImageView rated;
    @Nullable
    @BindView(R.id.account_activity_watchList)
    ImageView watchlist;
    @Nullable
    @BindView(R.id.account_activity_progressbar)
    ProgressBar progressBar;
    private CompositeDisposable disposable;

    private AccountViewModel accountViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_);
        accountViewModel = ((MyApplication) getApplication()).getAccountViewModel();
        disposable = new CompositeDisposable();
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

    private void setupAccount(Account account) {
        userName.setText(account.username);
        // set image nua
    }

    public void onLogoutClick(View v) {
        ((MyApplication) getApplication()).setSessionId(null);
        // thong bao logout
    }

    public void onWatchListClick(View v) {

    }

    public void onFavoriteClick(View v) {

    }

    public void onRatedClick(View v) {

    }
}
