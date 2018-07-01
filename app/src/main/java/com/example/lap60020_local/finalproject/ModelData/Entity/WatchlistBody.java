package com.example.lap60020_local.finalproject.ModelData.Entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class WatchlistBody {
    @SerializedName("media_type")
    @Expose
    private final String media_type = "movie";
    @SerializedName("media_id")
    @Expose
    private int media_id;
    @SerializedName("watchlist")
    @Expose
    private boolean watchlist;

    public WatchlistBody(int media_id, boolean watchlist) {
        this.media_id = media_id;
        this.watchlist = watchlist;
    }

    public String getMedia_type() {
        return media_type;
    }

    public int getMedia_id() {
        return media_id;
    }

    public void setMedia_id(int media_id) {
        this.media_id = media_id;
    }

    public boolean isWatchlist() {
        return watchlist;
    }

    public void setWatchlist(boolean watchlist) {
        this.watchlist = watchlist;
    }
}
