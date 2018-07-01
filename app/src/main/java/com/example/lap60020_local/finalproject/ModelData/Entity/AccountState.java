package com.example.lap60020_local.finalproject.ModelData.Entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AccountState {
    @SerializedName("id")
    @Expose
    public Integer id;
    @SerializedName("favorite")
    @Expose
    public Boolean favorite;
    @SerializedName("rated")
    @Expose
    public Rated rated;
    @SerializedName("watchlist")
    @Expose
    public Boolean watchlist;

    public AccountState(Integer id, Boolean favorite, Rated rated, Boolean watchlist) {
        this.id = id;
        this.favorite = favorite;
        this.rated = rated;
        this.watchlist = watchlist;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Boolean getFavorite() {
        return favorite;
    }

    public void setFavorite(Boolean favorite) {
        this.favorite = favorite;
    }

    public Rated getRated() {
        return rated;
    }

    public void setRated(Rated rated) {
        this.rated = rated;
    }

    public Boolean getWatchlist() {
        return watchlist;
    }

    public void setWatchlist(Boolean watchlist) {
        this.watchlist = watchlist;
    }
}
