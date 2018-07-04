package com.example.lap60020_local.finalproject;

import android.app.Application;

import com.example.lap60020_local.finalproject.ViewModel.AccountMovieViewModel;

public class MyApplication extends Application {
    private String sessionId;
    private String toke;
    private AccountMovieViewModel WatchListViewModel;
    private AccountMovieViewModel RatedListViewModel;
    private AccountMovieViewModel FavoriteListViewModel;

    public AccountMovieViewModel getWatchListViewModel() {
        return WatchListViewModel;
    }

    public void setWatchListViewModel(AccountMovieViewModel watchListViewModel) {
        WatchListViewModel = watchListViewModel;
    }

    public AccountMovieViewModel getRatedListViewModel() {
        return RatedListViewModel;
    }

    public void setRatedListViewModel(AccountMovieViewModel ratedListViewModel) {
        RatedListViewModel = ratedListViewModel;
    }

    public AccountMovieViewModel getFavoriteListViewModel() {
        return FavoriteListViewModel;
    }

    public void setFavoriteListViewModel(AccountMovieViewModel favoriteListViewModel) {
        FavoriteListViewModel = favoriteListViewModel;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public String getToke() {
        return toke;
    }

    public void setToke(String toke) {
        this.toke = toke;
    }
}
